import UserComponent from "@/components/Users/User";
import DUMMY_USERS from "@/dummies/dummy-users";

const UsersPage = () => {
    const users = DUMMY_USERS;
    return <ul>
        {users.map(u => <UserComponent user={u} key={u.id} />)}
    </ul>;
}

export default UsersPage;
