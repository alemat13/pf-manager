import Account from "./account";

export default class Transaction {
  _id?: string;
  title: string;
  description?: string;
  amount: number;
  transactionDate: Date;
  parent?: Transaction;
  children: Transaction[] = [];
  account?: Account;

  constructor(
    title: string,
    amount: number,
    transactionDate: Date,
    id?: string,
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
    return this.children.length == 0;
  }
}
