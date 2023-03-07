import { Account } from "@/models/account";
import DUMMY_USERS from "./dummy-users";

const [jean, jacques] = DUMMY_USERS;

export const DUMMY_ACCOUNTS = [
    new Account('Boursorama Alex', 'Boursorama', '1', jean),
    new Account('HSBC Joint', 'HSBC', '2', [jean, jacques]),
]