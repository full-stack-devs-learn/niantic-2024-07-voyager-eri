class ProductsService {

    baseUrl = `${config.baseUrl}/products?catId=`;

    getProductsByCategory(selectedCatId)
    {
        return axios.get(this.baseUrl + selectedCatId)
                    .then(response => {
                        return response.data;
                    });
    }

}