import { PartialObject, NotYetCreated } from '@/models/partial-object';
import { MongoClient, Collection, ObjectId, Filter, OptionalUnlessRequiredId } from 'mongodb';

export default class MongoRepository<T extends {}> {
    private client: MongoClient;
    private collection: Collection<T>;

    constructor(collectionName: string) {
        const {
            DB_URL : url,
            DB_USER : user,
            DB_PASSWORD : password,
            DB_NAME : database
        } = process.env;
        this.client = new MongoClient(`mongodb+srv://${user}:${password}@${url}/${database}?retryWrites=true&w=majority`);
        this.collection = this.client.db(database).collection<T>(collectionName);
    }

    async connect(): Promise<void> {
        await this.client.connect();
    }

    async disconnect(): Promise<void> {
        await this.client.close();
    }

    async create(item: NotYetCreated<T>): Promise<T> {
        const result = await this.collection.insertOne(item as OptionalUnlessRequiredId<T>);
        const returnedObj: T = { _id: result.insertedId.toString(), ...item } as unknown as T;
        return returnedObj;
    }

    async findById(id: string): Promise<T | null> {
        const result = await this.collection.findOne({ _id: new ObjectId(id) } as Filter<T>);
        return result as T | null;
    }

    async findByPartial(item: PartialObject<T>): Promise<T | null> {
        return this.findById(item._id);
    }

    async findByIds(id: string): Promise<T[] | null> {
        const result = await this.collection.find({ _id: { $in: new ObjectId(id)} } as Filter<T>).toArray();
        return result as T[] | null;
    }

    async findAll(): Promise<T[]> {
        const cursor = this.collection.find();
        const result = await cursor.toArray();
        return result as T[];
    }

    async update(id: string, item: T): Promise<boolean> {
        const result = await this.collection.replaceOne(this.getIdFilter(id), item);
        return result.modifiedCount > 0;
    }

    async delete(id: string): Promise<boolean> {
        const result = await this.collection.deleteOne(this.getIdFilter(id));
        return result.deletedCount > 0;
    }

    private getIdFilter(id: string) {
        return {_id: new ObjectId(id)} as Filter<T>;
    }
}
