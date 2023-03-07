import UsersList from "@/components/Users/UsersList";
import DUMMY_USERS from "@/dummies/dummy-users";

const UsersPage = () => {
    return <UsersList items={DUMMY_USERS} />;
}

export default UsersPage;
