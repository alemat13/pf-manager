import Transaction from "@/models/transaction";
import DUMMY_ACCOUNTS from "./dummy-accounts";

const [bousorama, hsbc] = DUMMY_ACCOUNTS;

const DUMMY_TRANSACTIONS: Transaction[] = [
  new Transaction(
    "Carrefour Market",
    35.95,
    new Date(),
    "1",
    "Carrefour Market 07/03/2023",
    hsbc
  ),
  new Transaction(
    "Navigo",
    80.95,
    new Date(),
    "2",
    "Abonnement Navigo",
    bousorama
  ),
  new Transaction(
    "SFR",
    19.95,
    new Date(),
    "3",
    "Abonnement SFR Fibre",
    hsbc
  ),
];

export default DUMMY_TRANSACTIONS;