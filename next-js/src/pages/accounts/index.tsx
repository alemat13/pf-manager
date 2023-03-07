import AccountComponent from "@/components/Accounts/Account";
import { DUMMY_ACCOUNTS } from "@/dummies/dummy-accounts";

const AccountsPage = () => {
    const accounts = DUMMY_ACCOUNTS;
    return <ul>
        {accounts.map(a => <AccountComponent account={a} key={a.id} />)}
    </ul>;
}

export default AccountsPage;
