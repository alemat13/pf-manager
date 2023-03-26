import { ObjectId } from "mongodb";

export default class User {
    _id?: ObjectId;
    userName: string;
    emailAdress?: string;
    constructor( userName: string, id?: ObjectId, emailAdress?: string) {
        this._id = id;
        this.userName = userName;
        this.emailAdress = emailAdress;
    }
}