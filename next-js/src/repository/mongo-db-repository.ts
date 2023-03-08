import { MongoClient, Collection, ObjectId, Filter } from 'mongodb';

abstract class MongoRepository<T> {
    private client: MongoClient;
    private collection: Collection<T>;

    constructor(uri: string, dbName: string, collectionName: string) {
        this.client = new MongoClient(uri, { useUnifiedTopology: true });
        this.collection = this.client.db(dbName).collection(collectionName);
    }

    async connect(): Promise<void> {
        await this.client.connect();
    }

    async disconnect(): Promise<void> {
        await this.client.close();
    }

    async create(item: T): Promise<T> {
        const result = await this.collection.insertOne(item);
        return result.ops[0];
    }

    async findById(id: string): Promise<T | null> {
        const result = await this.collection.findOne({ _id: id });
        return result as T | null;
    }

    async findAll(): Promise<T[]> {
        const cursor = await this.collection.find();
        const result = await cursor.toArray();
        return result as T[];
    }

    async update(id: string, item: T): Promise<boolean> {
        const result = await this.collection.replaceOne({ _id: new ObjectId(id) }, item);
        return result.modifiedCount > 0;
    }

    async delete(id: string): Promise<boolean> {
        const result = await this.collection.deleteOne({ _id: id });
        return result.deletedCount > 0;
    }
}
