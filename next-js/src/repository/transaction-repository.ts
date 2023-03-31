import User from "@/models/user";
import MongoRepository from "./mongo-repository";
import Transaction from "@/models/transaction";
import { ObjectId } from "mongodb";
import Account from "@/models/account";

type TransactionDto = {
    children_ids: ObjectId[];
    parents_ids: ObjectId[];
    _id?: ObjectId;
    title: string;
    description?: string;
    amount: number;
    transactionDate: Date;
    account_id?: ObjectId;
}

export default class TransactionRepository extends MongoRepository<TransactionDto, Transaction> {
    constructor() {
        super('transactions');
    }

    getDTO(transaction: Transaction): TransactionDto {
        return {
            ...transaction,
            _id: undefined,
            children_ids: transaction.children ? transaction.children.map(t => new ObjectId(t._id)) : [],
            parents_ids: transaction.parents ? transaction.parents.map(t => new ObjectId(t._id)) : [],
            account_id: transaction.account ? new ObjectId(transaction.account._id) : undefined
        }
    }
    getFromDTO(dto: TransactionDto): Transaction {
        return {
            ...dto,
            children: [],
            parents: [],
            _id: dto._id?.toString()
        }
    }
}