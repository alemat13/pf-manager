import Account from "@/models/account";
import DUMMY_USERS from "./dummy-users";

const [jean, jacques] = DUMMY_USERS;

const DUMMY_ACCOUNTS: Account[] = [
    { title: 'Boursorama Alex', bankName: 'Boursorama', owners: [jean] },
    { title: 'HSBC Joint', bankName: 'HSBC', owners: [jean, jacques] },
]

export default DUMMY_ACCOUNTS;