import mongoose, { Schema } from "mongoose";
import MongooseRepository from "./mongo-repository";
import Account from "@/models/account";

const accountSchema = new Schema({
    title: { type: String, required: true },
    bankName: { type: String, required: true },
    owners: [{ type: Schema.Types.ObjectId, ref: 'User' }],
});

export default class AccountRepository extends MongooseRepository<Account> {
    constructor() {
        super(accountSchema, 'Accounts');
    }
}