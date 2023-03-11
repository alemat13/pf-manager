import { PartialObject } from "./partial-object";
import User from "./user";

export default class Account {
    _id?: string;
    title: string;
    bankName: string;
    owners: PartialObject<User>[] = [];

    constructor(
        title: string,
        bankName: string,
        id?: string
    ) {
        this._id = id;
        this.title = title;
        this.bankName = bankName;
    }
}