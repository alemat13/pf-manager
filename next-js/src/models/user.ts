export default class User {
    _id: string | undefined;
    userName: string;
    emailAdress?: string;
    constructor( userName: string, id?: string, emailAdress?: string) {
        this._id = id;
        this.userName = userName;
        this.emailAdress = emailAdress;
    }
}