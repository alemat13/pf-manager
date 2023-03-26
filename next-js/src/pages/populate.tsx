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
  try {
    const repositories = {
      user: new MongoRepository<User>("users"),
      account: new MongoRepository<Account>("accounts"),
      transaction: new MongoRepository<Transaction>("transactions"),
    };

    for (const repo of [repositories.user, repositories.account, repositories.transaction]) {
      try {
        await repo.drop()
      } catch (error) {
        console.log('Already droped');
      }
    };

    DUMMY_USERS.forEach(async (user) => await repositories.user.create(user));

    DUMMY_ACCOUNTS.forEach(
      async (account) => {
        await repositories.account.create(account);
      }
    );

    DUMMY_TRANSACTIONS.forEach(
      async (transaction) => {
        await repositories.transaction.create(transaction);
      }
    );
  } catch (error) {
    throw error;
  }
  return { props: { state: "success" } };
}
