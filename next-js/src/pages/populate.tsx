import DUMMY_ACCOUNTS from "@/dummies/dummy-accounts";
import DUMMY_TRANSACTIONS from "@/dummies/dummy-transactions";
import DUMMY_USERS from "@/dummies/dummy-users";
import Account from "@/models/account";
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
  function stripObject<T extends { _id?: string }>(obj: T) {
    return { _id: obj._id } as T;
  }
  function stripObjects<T extends { _id?: string }>(objs: T[]) {
    return objs.map(o => stripObject(o));
  }
  try {
    const repositories = {
      user: new MongoRepository<User>("users"),
      account: new MongoRepository<Account>("accounts"),
      transaction: new MongoRepository<Transaction>("transactions"),
    };

    await Object.values(repositories).forEach(async r => {
      try {
        await r.drop()
      } catch (error) {
        console.log('Already droped');
      }
    });

    DUMMY_USERS.forEach(async (user) => await repositories.user.create(user));

    DUMMY_ACCOUNTS.forEach(
      async (account) => {
        await repositories.account.create({ ...account, owners: stripObjects(account.owners) })
      }
    );

    DUMMY_TRANSACTIONS.forEach(
      async (transaction) => {
        await repositories.transaction.create({
          ...transaction,
          children: stripObjects(transaction.children),
          account: transaction.account ? stripObject(transaction.account) : undefined
        } as Transaction);
      }
    );
  } catch (error) {
    throw error;
  }
  return { props: { state: "success" } };
}
