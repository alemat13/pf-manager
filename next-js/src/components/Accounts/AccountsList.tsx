import AccountItem from "@/components/Accounts/AccountItem";
import Account from "@/models/account";

const AccountsList: React.FC<{items: Account[]}> = ({items}) => {
    return <ul>
        {items.map(a => <AccountItem account={a} key={a._id!.toString()} />)}
    </ul>;
}

export default AccountsList;
