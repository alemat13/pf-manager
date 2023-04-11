import TransactionsList from "@/components/Transactions/TransactionsList";
import Transaction from "@/models/transaction";
import TransactionRepository from "@/repository/transaction-repository";

const TransactionsPage: React.FC<{items: Transaction[]}> = ({items}) => {
    return <TransactionsList items={items}/>
}

export async function getServerSideProps() {
    const transactionRepo = new TransactionRepository();
    const transactions = await transactionRepo.find();
    return {
        props : {
            items: transactions
        }
    }
}

export default TransactionsPage;
