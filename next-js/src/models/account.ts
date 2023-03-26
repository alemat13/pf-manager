import { ObjectId } from "mongodb";
import User from "./user";

export default class Account {
    _id?: ObjectId;
    title: string;
    bankName: string;
    owners_id: ObjectId[] =  [];

    constructor(
        title: string,
        bankName: string,
        id?: ObjectId,
        owners_id?: ObjectId[]
    ) {
        this._id = id;
        this.title = title;
        this.bankName = bankName;
        if(owners_id) {
            this.owners_id = owners_id;
        }
    }
}