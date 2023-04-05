import AccountsList from "@/components/Accounts/AccountsList";
import Account from "@/models/account";
import AccountRepository from "@/repository/account-repository";
import UserRepository from "@/repository/user-repository";

const AccountsPage: React.FC<{ items: Account[] }> = ({ items }) => {
    return <AccountsList items={items} />;
}

export async function getServerSideProps() {
    const accountRepo = new AccountRepository();
    const usersRepo = new UserRepository();
    const accounts = await accountRepo.find();
    const allUsers = await usersRepo.find();
    return {
        props: {
            items: accounts.map(account => ({
                ...account,
                _id: account._id?.toString(),
                owners_test: account.owners,
            }))
        }
    }
}

export default AccountsPage;
