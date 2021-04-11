import { Site } from "./site";

export interface Hebergement {
    id:number,
    ip:string,
    nameHberger:string,
    type:string,
    hebergeur:string,
    sites : Site[]
}
