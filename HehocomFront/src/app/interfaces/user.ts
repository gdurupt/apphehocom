import { Site } from "./site";

export interface User {
    id:number,
    username:string,
    secondname:string,
    email:string,
    tel:string,
    dateInscription:Date,
    status:string,
    site:Site[]
}
