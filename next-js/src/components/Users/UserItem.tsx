import User from "@/models/user";

const UserItem: React.FC<{user: User}> = ({user}) => {
    return <ul>
        <h3>{user.userName}</h3>
        <span>Email address: </span>
        {user.emailAdress && <a href={`mailto://${user.emailAdress}`}>{user.emailAdress}</a>}
        {!user.emailAdress && <p>Unknown</p>}
    </ul>
}

export default UserItem;