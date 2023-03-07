import { Transaction } from "@/models/transaction";

const TransactionComponent: React.FC<{ transaction: Transaction }> = ({
  transaction,
}) => {
  return (
    <div>
      <h3>{transaction.title}</h3>
      <div>
        <span>Account: </span>
        <span>{transaction.account?.bankName}</span>
      </div>
      <div>
        <span>Amount: </span>
        <span>{transaction.amount}</span>
      </div>
      <div>
        <span>Description: </span>
        <span>{transaction.description}</span>
      </div>
      <div>
        <span>Transaction date: </span>
        <span>{transaction.transactionDate.toDateString()}</span>
      </div>
    </div>
  );
};

export default TransactionComponent;
