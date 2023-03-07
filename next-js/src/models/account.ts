import User from "./user";

export default class Account {
    id: string | undefined;
    title: string;
    bankName: string;
    owners: User[] = [];

    constructor(
        title: string,
        bankName: string,
        id?: string,
        owners?: User[] | User
    ) {
        this.id = id;
        this.title = title;
        this.bankName = bankName;
        if (owners instanceof User) {
            this.owners = [owners];
        } else {
            this.owners = owners || [];
        }
    }
}