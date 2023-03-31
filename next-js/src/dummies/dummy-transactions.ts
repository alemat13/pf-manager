import Transaction from "@/models/transaction";
import { ObjectId } from "mongodb";
import DUMMY_ACCOUNTS from "./dummy-accounts";

const [bousorama, hsbc] = DUMMY_ACCOUNTS;

const DUMMY_TRANSACTIONS: Transaction[] = [
  {
    title: "Carrefour Market",
    amount: 35.95,
    transactionDate: new Date(),
    description: "Carrefour Market 07/03/2023",
    account: hsbc
  },
  {
    title: "Navigo",
    amount: 80.95,
    transactionDate: new Date(),
    description: "Abonnement Navigo",
    account: bousorama
  },
  {
    title: "SFR",
    amount: 19.95,
    transactionDate: new Date(),
    description: "Abonnement SFR Fibre",
    account: hsbc
  },
];

export default DUMMY_TRANSACTIONS;