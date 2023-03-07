import Account from "@/models/account";

const AccountItem: React.FC<{account: Account}> = ({account}) => {
    return <ul>
        <h3>{account.title}</h3>
        <div><span>Bank name: </span>{account.bankName}</div>
        <div><span>Owners: </span>{account.owners.map(o => o.userName).join(', ')}</div>
    </ul>
}

export default AccountItem;