import mongoose, { Schema } from "mongoose";

const transactionSchema = new Schema({
    amount: { type: Number, required: true },
    transactionDate: { type: Date, required: true },
    title: { type: String, required: true },
    account: { type: Schema.Types.ObjectId, ref: 'Account' },
    description: { type: String },
    parents: [{ type: Schema.Types.ObjectId, ref: 'Transaction' }],
    children: [{ type: Schema.Types.ObjectId, ref: 'Transaction' }],
});

export const TransactionModel = mongoose.model('Transaction', transactionSchema);