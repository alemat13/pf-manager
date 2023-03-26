import Account from "@/models/account";
import { ObjectId } from "mongodb";
import DUMMY_USERS from "./dummy-users";

const [jean, jacques] = DUMMY_USERS;

const DUMMY_ACCOUNTS = [
    new Account('Boursorama Alex', 'Boursorama', new ObjectId(), [jean._id!]),
    new Account('HSBC Joint', 'HSBC', new ObjectId(), [jean._id!, jacques._id!]),
]

export default DUMMY_ACCOUNTS;