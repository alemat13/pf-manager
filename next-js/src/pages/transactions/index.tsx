import AccountComponent from "@/components/Accounts/Account";
import TransactionComponent from "@/components/Transactions/Transaction";
import { DUMMY_ACCOUNTS } from "@/dummies/dummy-accounts";
import { DUMMY_TRANSACTIONS } from "@/dummies/dummy-transactions";

const TransactionsPage = () => {
    const transactions = DUMMY_TRANSACTIONS;
    return <ul>
        {transactions.map(t => <TransactionComponent transaction={t} key={t.id} />)}
    </ul>;
}

export default TransactionsPage;
