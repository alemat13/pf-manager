import Account from "./account";

export default interface Transaction {
  _id?: string;
  title: string;
  description?: string;
  amount: number;
  transactionDate: Date;
  parents?: Transaction[];
  children?: Transaction[];
  account?: Account;
}
