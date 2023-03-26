import { ObjectId } from "mongodb";
import Account from "./account";

export default class Transaction {
  _id?: ObjectId;
  title: string;
  description?: string;
  amount: number;
  transactionDate: Date;
  parents_id: ObjectId[] = [];
  children_id: Transaction[] = [];
  account?: Account;

  constructor(
    title: string,
    amount: number,
    transactionDate: Date,
    id?: ObjectId,
    description?: string,
    account?: Account
  ) {
    this.title = title;
    this.amount = amount;
    this.transactionDate = transactionDate;
    this._id = id;
    this.description = description;
  }

  isActive() {
    return this.children_id.length == 0;
  }
}
