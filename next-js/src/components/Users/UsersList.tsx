import UserItem from "@/components/Users/UserItem";
import User from "@/models/user";

const UsersList: React.FC<{items: User[]}> = ({items}) => {
    return <ul>
        {items.map(u => <UserItem user={u} key={u._id} />)}
    </ul>;
}

export default UsersList;
