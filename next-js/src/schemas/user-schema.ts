import mongoose, { Document, Schema } from "mongoose";

const userSchema = new Schema({
    userName: { type: String, required: true },
    emailAddress: { type: String, required: true}
})

export const UserModel = mongoose.model('User', userSchema);
