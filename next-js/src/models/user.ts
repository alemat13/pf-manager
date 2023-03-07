export default class User {
    id: string | undefined;
    userName: string;
    emailAdress?: string;
    constructor( userName: string, id?: string, emailAdress?: string) {
        this.id = id;
        this.userName = userName;
        this.emailAdress = emailAdress;
    }
}