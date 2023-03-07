import AccountItem from "@/components/Accounts/AccountItem";
import { DUMMY_ACCOUNTS } from "@/dummies/dummy-accounts";

const AccountsPage = () => {
    const accounts = DUMMY_ACCOUNTS;
    return <ul>
        {accounts.map(a => <AccountItem account={a} key={a.id} />)}
    </ul>;
}

export default AccountsPage;
