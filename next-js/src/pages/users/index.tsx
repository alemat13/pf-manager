import UserItem from "@/components/Users/UserItem";
import DUMMY_USERS from "@/dummies/dummy-users";

const UsersPage = () => {
    const users = DUMMY_USERS;
    return <ul>
        {users.map(u => <UserItem user={u} key={u.id} />)}
    </ul>;
}

export default UsersPage;
