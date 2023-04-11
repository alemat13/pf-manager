import AccountsList from "@/components/Accounts/AccountsList";
import Account from "@/models/account";
import AccountRepository from "@/repository/account-repository";
import UserRepository from "@/repository/user-repository";

const AccountsPage: React.FC<{ items: Account[] }> = ({ items }) => {
    return <AccountsList items={items} />;
}

export async function getServerSideProps() {
    const accountRepo = new AccountRepository();
    const accounts = await accountRepo.find();
    return {
        props: {
            items: accounts
        }
    }
}

export default AccountsPage;
