import DUMMY_ACCOUNTS from "@/dummies/dummy-accounts";
import DUMMY_TRANSACTIONS from "@/dummies/dummy-transactions";
import DUMMY_USERS from "@/dummies/dummy-users";
import AccountRepository from "@/repository/account-repository";
import TransactionRepository from "@/repository/transaction-repository";
import UserRepository from "@/repository/user-repository";

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
      user: new UserRepository(),
      account: new AccountRepository(),
      transaction: new TransactionRepository(),
    };

    for (const repo of [
      repositories.user,
      repositories.account,
      repositories.transaction
    ]) {
      try {
        await repo.drop()
      } catch (error) {
        console.log('Already droped');
      }
    };

    DUMMY_USERS.forEach(async (user) => await repositories.user.save(user));

    DUMMY_ACCOUNTS.forEach(
      async (account) => {
        await repositories.account.save(account);
      }
    );

    DUMMY_TRANSACTIONS.forEach(
      async (transaction) => {
        await repositories.transaction.save(transaction);
      }
    );
  } catch (error) {
    throw error;
  }
  return { props: { state: "success" } };
}
