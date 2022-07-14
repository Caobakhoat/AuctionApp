import { Item } from "./item.model";
import { User } from "./user.model";

export interface Auction {
    id: number;
    item: Item;
    initPrice:number;
    currentPrice:number;
    host:User;
    timeStart:String;
    timeEnd:String;
    status:number;
}