import {User} from "./user";
import {Item} from "./item";

export interface Auction{
    id:string;
    userCreate:User;
    item:Item;
    initPrice:number;
    timeStart:string;
    timeEnd:string;
    status:number;
}