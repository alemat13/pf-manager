import { User } from "@/models/user";

const UserComponent: React.FC<{user: User}> = ({user}) => {
    return <div>
        <h3>{user.userName}</h3>
        <span>Email address: </span>
        {user.emailAdress && <a href={`mailto://${user.emailAdress}`}>{user.emailAdress}</a>}
        {!user.emailAdress && <p>Unknown</p>}
    </div>
}

export default UserComponent;