import TransactionItem from "@/components/Transactions/TransactionItem";
import Transaction from "@/models/transaction";

const TransactionsList: React.FC<{items: Transaction[]}> = ({items}) => {
    return <ul>
        {items.map(t => <TransactionItem transaction={t} key={t.id} />)}
    </ul>;
}

export default TransactionsList;
