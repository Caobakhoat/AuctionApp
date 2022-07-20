import {User} from "./user";
import {Item} from "./item";

export interface Auction{
    id:string;
    userCreate:User;
    item:Item;
    initPrice:string;
    timeStart:string;
    timeEnd:string;
    status:string;
}