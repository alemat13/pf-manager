import AccountsList from "@/components/Accounts/AccountsList";
import DUMMY_ACCOUNTS from "@/dummies/dummy-accounts";

const AccountsPage = () => {
    return <AccountsList items={DUMMY_ACCOUNTS} />;
}

export default AccountsPage;
