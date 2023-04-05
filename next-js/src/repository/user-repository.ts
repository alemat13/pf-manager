import User from "@/models/user";
import { Schema } from "mongoose"
import MongooseRepository from "./mongo-repository";

const userSchema = new Schema<User>({
    userName: { type: String, required: true },
    emailAddress: { type: String, required: true}
})

export default class UserRepository extends MongooseRepository<User> {
    constructor() {
        super(userSchema, 'Users');
    }
}