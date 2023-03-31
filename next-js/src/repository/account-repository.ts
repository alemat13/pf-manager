import Account from "@/models/account";
import { ObjectId } from "mongodb";
import MongoRepository from "./mongo-repository";

type AccountDTO = {
    _id?: ObjectId;
    title: string;
    bankName: string;
    owners_ids: ObjectId[];
}

export class AccountRepository extends MongoRepository<AccountDTO, Account> {
    constructor() {
        super('accounts');
    }
    getDTO(account: Account): AccountDTO {
        return {
            ...account,
            _id: new ObjectId(account._id),
            owners_ids: account.owners ? account.owners.map(o => new ObjectId(o._id)) : []
        };
    }
    getFromDTO(dto: AccountDTO): Account {
        return {
            ...dto,
            _id: dto._id?.toString(),
            owners: []
        }
    }
}