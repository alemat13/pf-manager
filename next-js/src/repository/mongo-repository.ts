import { MongoClient, Collection, ObjectId, Filter, OptionalUnlessRequiredId } from 'mongodb';

export default abstract class MongoRepository<T_DTO extends {}, T extends {}> {
    private client: MongoClient;
    private collection: Collection<T_DTO>;

    constructor(collectionName: string) {
        const {
            DB_URL : url,
            DB_USER : user,
            DB_PASSWORD : password,
            DB_NAME : database
        } = process.env;
        this.client = new MongoClient(`mongodb+srv://${user}:${password}@${url}/${database}?retryWrites=true&w=majority`);
        this.collection = this.client.db(database).collection<T_DTO>(collectionName);
    }

    async connect(): Promise<void> {
        await this.client.connect();
    }

    async disconnect(): Promise<void> {
        await this.client.close();
    }

    async create(item: T): Promise<string> {
        const result = await this.collection.insertOne(this.getDTO(item) as OptionalUnlessRequiredId<T_DTO>);
        return result.insertedId.toString();
    }

    async findById(id: string): Promise<T_DTO | null> {
        const result = await this.collection.findOne({ _id: new ObjectId(id) } as Filter<T_DTO>);
        return result as T_DTO | null;
    }

    async findByIds(id: string): Promise<T_DTO[] | null> {
        const result = await this.collection.find({ _id: { $in: new ObjectId(id)} } as Filter<T_DTO>).toArray();
        return result as T_DTO[] | null;
    }

    async findAll(): Promise<T_DTO[]> {
        const cursor = this.collection.find();
        const result = await cursor.toArray();
        return result as T_DTO[];
    }

    async update(id: string, item: T_DTO): Promise<boolean> {
        const result = await this.collection.replaceOne(this.getIdFilter(id), item);
        return result.modifiedCount > 0;
    }

    async delete(id: string): Promise<boolean> {
        const result = await this.collection.deleteOne(this.getIdFilter(id));
        return result.deletedCount > 0;
    }

    async drop(): Promise<boolean> {
        return this.collection.drop();
    }

    private getIdFilter(id: string) {
        return {_id: new ObjectId(id)} as Filter<T_DTO>;
    }

    abstract getDTO(item: T): T_DTO;
    abstract getFromDTO(dto: T_DTO): T;
}
