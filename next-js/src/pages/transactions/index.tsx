import AccountItem from "@/components/Accounts/AccountItem";
import TransactionItem from "@/components/Transactions/TransactionItem";
import { DUMMY_ACCOUNTS } from "@/dummies/dummy-accounts";
import { DUMMY_TRANSACTIONS } from "@/dummies/dummy-transactions";

const TransactionsPage = () => {
    const transactions = DUMMY_TRANSACTIONS;
    return <ul>
        {transactions.map(t => <TransactionItem transaction={t} key={t.id} />)}
    </ul>;
}

export default TransactionsPage;
