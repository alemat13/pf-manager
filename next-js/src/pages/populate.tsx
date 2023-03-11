import DUMMY_ACCOUNTS from "@/dummies/dummy-accounts";
import DUMMY_TRANSACTIONS from "@/dummies/dummy-transactions";
import DUMMY_USERS from "@/dummies/dummy-users";
import Account from "@/models/account";
import { PartialObject } from "@/models/partial-object";
import Transaction from "@/models/transaction";
import User from "@/models/user";
import MongoRepository from "@/repository/mongo-db-repository";

const PopulatePage: React.FC<{ state: string; error?: any }> = ({
  state,
  error,
}) => {
  const result = JSON.stringify({ state, error });
  return <div>{result}</div>;
};

export default PopulatePage;

export async function getServerSideProps() {
  function stripObject<T extends {_id: string}>(obj: T) {
    return {_id: obj._id} as PartialObject<T>
  }
  function stripObjects<T extends {_id: string}>(objs: T[]) {
    return objs.map(o => stripObject(o));
  }
  try {
    const repositories = {
      user: new MongoRepository<User>("users"),
      account: new MongoRepository<Account>("accounts"),
      transaction: new MongoRepository<Transaction>("transaction"),
    };
    DUMMY_USERS.forEach(async (user) => await repositories.user.create(user));
    DUMMY_ACCOUNTS.forEach(
      async (account) => {
        const owners = account.owners.map(u => ({ _id: u._id } as User));
        await repositories.account.create({ ...account, owners: stripObjects(owners) })
      }
    );
    DUMMY_TRANSACTIONS.forEach(
      async (transaction) => {
        const children = transaction.children.map(t => ({ _id: t._id } as Transaction));
        let account = transaction.account;
        if(account) {
          account = {_id: account._id} as Account;
        }
        await repositories.transaction.create({ ...transaction, children, account } as Transaction);
      }
    );
  } catch (error) {
    return { props: { state: "error", error } };
  }
  return { props: { state: "success" } };
}
