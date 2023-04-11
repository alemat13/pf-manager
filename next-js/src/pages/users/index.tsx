import UsersList from "@/components/Users/UsersList";
import User from "@/models/user";
import UserRepository from "@/repository/user-repository";

const UsersPage: React.FC<{items: User[]}> = ({items}) => {
    return <UsersList items={items} />;
}

export async function getServerSideProps() {
    const userRepo = new UserRepository();
    const users = await userRepo.find();
    return {
        props : {
            items: users
        }
    }
}

export default UsersPage;
