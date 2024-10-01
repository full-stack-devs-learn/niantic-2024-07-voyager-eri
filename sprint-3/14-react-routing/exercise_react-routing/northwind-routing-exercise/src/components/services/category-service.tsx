import axios from "axios";

class CategoryService
{
    baseUrl = "http://localhost:8080/categories"

    async getAllCategories()
    {
        const response = await axios.get(this.baseUrl);
        return response.data;
    }
}

export default new CategoryService();