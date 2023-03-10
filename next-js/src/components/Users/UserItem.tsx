import User from "@/models/user";
import Card from "../UI/Card";

const UserItem: React.FC<{ user: User }> = ({ user }) => {
  return (
    <ul>
      <Card>
        <h3>{user.userName}</h3>
        <span>Email address: </span>
        {user.emailAdress && (
          <a href={`mailto://${user.emailAdress}`}>{user.emailAdress}</a>
        )}
        {!user.emailAdress && <p>Unknown</p>}
      </Card>
    </ul>
  );
};

export default UserItem;
