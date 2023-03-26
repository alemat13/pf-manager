import User from "@/models/user";
import { ObjectId } from "mongodb";

const DUMMY_USERS = [
    new User('Jean', new ObjectId(), 'jean@gmail.com'),
    new User('Jacques', new ObjectId())
];

export default DUMMY_USERS;