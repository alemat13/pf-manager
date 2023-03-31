import User from "@/models/user";
import MongoRepository from "./mongo-repository";

export default class UserRepository extends MongoRepository<User, User> {
    getDTO(item: User): User {
        return item;
    }
    getFromDTO(dto: User): User {
        return dto;
    }
    constructor() {
        super('users');
    }
}