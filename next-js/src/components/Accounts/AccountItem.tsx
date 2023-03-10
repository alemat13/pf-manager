import Account from "@/models/account";
import Card from "../UI/Card";

const AccountItem: React.FC<{ account: Account }> = ({ account }) => {
  return (
    <ul>
      <Card>
        <h3>{account.title}</h3>
        <div>
          <span>Bank name: </span>
          {account.bankName}
        </div>
        <div>
          <span>Owners: </span>
          {account.owners.map((o) => o.userName).join(", ")}
        </div>
      </Card>
    </ul>
  );
};

export default AccountItem;
