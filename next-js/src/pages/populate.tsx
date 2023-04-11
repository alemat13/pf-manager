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

    for (const user of DUMMY_USERS) {
      await repositories.user.save(user);
    }

    for (const account of DUMMY_ACCOUNTS) {
      await repositories.account.save(account);
    }


    for (const transaction of DUMMY_TRANSACTIONS) {
      await repositories.transaction.save(transaction);
    }

  } catch (error) {
    throw error;
  }
  return { props: { state: "success" } };
}
