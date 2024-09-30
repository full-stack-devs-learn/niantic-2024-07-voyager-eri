import { Berry } from "./berry" 

export class PokeapiResponse
{
    count!: number
    next?: string
    previous?: string
    results!: Berry[]
}