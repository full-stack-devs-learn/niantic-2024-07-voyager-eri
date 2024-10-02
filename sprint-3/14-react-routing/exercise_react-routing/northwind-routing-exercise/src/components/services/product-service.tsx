import axios from "axios";

class ProductService
{
    baseUrl = "http://localhost:8080/products";

    async getByCategoryId(categoryId: number)
    {
        const response = await axios.get(this.baseUrl + `?catId=${categoryId}`);
        return response.data;
    }

    async getByProductId(productId: number)
    {
        const response = await axios.get(this.baseUrl + `/${productId}`);
        return response.data;
    }

    async getAllProducts()
    {
        const response = await axios.get(this.baseUrl);
        return response.data;
    }
}

export default new ProductService();