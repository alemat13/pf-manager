import TransactionsList from "@/components/Transactions/TransactionsList";
import DUMMY_TRANSACTIONS from "@/dummies/dummy-transactions";

const TransactionsPage = () => {
    return <TransactionsList items={DUMMY_TRANSACTIONS}/>
}

export default TransactionsPage;
