import axios from "axios";

class ProductService
{
    baseUrl = "http://localhost:8080/products";

    async getByCategoryId(categoryId: number)
    {
        const response = await axios.get(this.baseUrl + `?catId=${categoryId}`);
        return response.data;
    }
}

export default new ProductService();