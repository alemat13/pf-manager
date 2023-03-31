import mongoose, { Schema } from "mongoose";

const accountSchema = new Schema({
    title: { type: String, required: true },
    bankName: { type: String, required: true },
    owners: [{ type: Schema.Types.ObjectId, ref: 'User' }],
});

export const AccountModel = mongoose.model('Account', accountSchema);
