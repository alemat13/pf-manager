import User from "./user";

export default interface Account {
    _id?: string;
    title: string;
    bankName: string;
    owners: User[];
}