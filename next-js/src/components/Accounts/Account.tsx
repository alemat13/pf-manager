import { Account } from "@/models/account";

const AccountComponent: React.FC<{account: Account}> = ({account}) => {
    return <div>
        <h3>{account.title}</h3>
        <span>Bank name: </span>{account.bankName}
        <span>Owners: </span>{account.owners.map(o => o.userName).join(', ')}
    </div>
}

export default AccountComponent;