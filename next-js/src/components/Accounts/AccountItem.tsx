import { Account } from "@/models/account";

const AccountItem: React.FC<{account: Account}> = ({account}) => {
    return <ul>
        <h3>{account.title}</h3>
        <span>Bank name: </span>{account.bankName}
        <span>Owners: </span>{account.owners.map(o => o.userName).join(', ')}
    </ul>
}

export default AccountItem;