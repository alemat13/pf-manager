import AccountsList from "@/components/Accounts/AccountsList";
import { useDataObjects } from "@/hooks/use-data";
import Account from "@/models/account";
import User from "@/models/user";
import MongoRepository from "@/repository/mongo-db-repository";
import useSWR from 'swr';

const AccountsPage: React.FC<{ items: Account[] }> = ({ items }) => {
    return <AccountsList items={items} />;
}

export async function getServerSideProps() {
    const accountRepo = new MongoRepository<Account>('accounts');
    const usersRepo = new MongoRepository<User>('users');
    const accounts = await accountRepo.findAll();
    const allUsers = await usersRepo.findAll();
    return {
        props: {
            items: accounts.map(account => ({
                ...account,
                _id: account._id?.toString(),
                owners: allUsers.filter(u => {
                    console.log({account, u});
                    return (account.owners_id.find(owner_id => owner_id === u._id) !== undefined)
                })
            }))
        }
    }
}

export default AccountsPage;
