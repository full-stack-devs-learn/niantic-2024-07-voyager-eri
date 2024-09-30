import axios from "axios";
import { PokeapiResponse } from "../models/pokeapi-response";

class PokeapiService
{
    async getAllBerries(pageCount: number): Promise<PokeapiResponse>
    {
        const response = await axios.get<PokeapiResponse>(`https://pokeapi.co/api/v2/berry?offset=${pageCount}&limit=20`);
        return response.data
    }
}

const pokeapiService = new PokeapiService();
export default pokeapiService;