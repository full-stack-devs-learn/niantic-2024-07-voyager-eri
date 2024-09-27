import axios from 'axios'

class CategoryService
{
    baseUrl = 'http://localhost:8080/categories'

    async getAllCategories()
    {
        const response = await axios.get(this.baseUrl);
        return response.data;
    }

    async getById(categoryId)
    {
        const response = await axios.get(`${this.baseUrl}/${categoryId}`);
        return response.data;
    }

    async add(category)
    {
        const response = await axios.post(this.baseUrl, category);
        return response.data;
    }

    async update(categoryId, category)
    {
        await axios.put(`${this.baseUrl}/${categoryId}`, category)
    }

    async delete(categoryId)
    {
        await axios.delete(`${this.baseUrl}/${categoryId}`)
    }
}

const categoryService = new CategoryService()
export default categoryService;