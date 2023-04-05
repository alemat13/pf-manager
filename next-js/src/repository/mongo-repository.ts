import mongoose, { Model, Schema } from "mongoose"

export default class MongooseRepository<T> {
    ObjectModel: Model<T>;
    constructor(objectSchema: Schema<T>, modelName: string) {
        this.ObjectModel = mongoose.model<T>(modelName, objectSchema);
        const {
            DB_URL: url,
            DB_USER: user,
            DB_PASSWORD: password,
            DB_NAME: database
        } = process.env;
        const connect = async () => {
            await mongoose.connect(`mongodb+srv://${user}:${password}@${url}/${database}?retryWrites=true&w=majority`);
        }
        connect().catch(err => console.log(err));
    }
    save(item: T): Promise<T> {
        const itemModel = new this.ObjectModel(item);
        return itemModel.save();
    }
    async find(): Promise<T[]> {
        const items = await this.ObjectModel.find();
        return items;
    }
    drop(): Promise<boolean> {
        return this.ObjectModel.collection.drop();
    }
}