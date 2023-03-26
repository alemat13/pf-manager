import Transaction from "@/models/transaction";
import { ObjectId } from "mongodb";
import DUMMY_ACCOUNTS from "./dummy-accounts";

const [bousorama, hsbc] = DUMMY_ACCOUNTS;

const DUMMY_TRANSACTIONS: Transaction[] = [
  new Transaction(
    "Carrefour Market",
    35.95,
    new Date(),
    new ObjectId(),
    "Carrefour Market 07/03/2023",
    hsbc
  ),
  new Transaction(
    "Navigo",
    80.95,
    new Date(),
    new ObjectId(),
    "Abonnement Navigo",
    bousorama
  ),
  new Transaction(
    "SFR",
    19.95,
    new Date(),
    new ObjectId(),
    "Abonnement SFR Fibre",
    hsbc
  ),
];

export default DUMMY_TRANSACTIONS;