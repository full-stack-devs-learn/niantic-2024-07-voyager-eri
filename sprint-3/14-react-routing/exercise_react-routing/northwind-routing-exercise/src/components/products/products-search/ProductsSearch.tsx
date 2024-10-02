import { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import productService from "../../services/product-service";
import { Product } from "../../../models/product";
import categoryService from "../../services/category-service";
import { Category } from "../../../models/category";

export default function ProductsSearch()
{
    const [filteredProducts, setFilteredProducts] = useState<Product[]>([]);
    // const [categories, setCategories] = useState<Category[]>([]);
    // const [priceFilterMin, setPriceFilterMin] = useState<number>(0);
    // const [categoryFilter, setCategoryFilter] = useState<number>(0);

    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const minPrice = params.get("minPrice") ?? 0;
    const maxPrice = params.get("maxPrice") ?? 0;
    const name = params.get("name") ?? "";
    const catId = params.get("catId") ?? 0;

    // useEffect(() => { loadProductsByCategory() }, [catId]);
    // useEffect(() => { loadCategories() }, []);
    useEffect(() => { loadAllProducts() }, []);

    // async function loadProductsByCategory()
    // {
    //     const response = await productService.getByCategoryId(+catId);
    //     setProducts(response);
    // }

    async function loadAllProducts()
    {
        let response = await productService.getAllProducts();

        if(+catId !== 0)
        {
            response = response.filter((product:Product) => +product.categoryId == +catId);
        }

        if(+maxPrice !== 0)
        {
            response = response.filter((product: Product) => +product.unitPrice <= +maxPrice);
        }
        const results = response.filter((product: Product) => (+product.unitPrice >= +minPrice) && (product.name.toLowerCase().includes(name)));
        setFilteredProducts(results);
    }

    // async function loadCategories()
    // {
    //     const response = await categoryService.getAllCategories();
    //     setCategories(response);
    // }

    async function searchHandler(event: any)
    {
        event.preventDefault();
        
    }


    return (
        <>
        <h2>Search for Products</h2>
        {/* <form onSubmit={searchHandler} method="get" action="">
            <select className="form-select" onChange={(e) => {setCategoryFilter(+e.target.value)}}>
                <option value="0">No category selected</option>
                {
                    categories.map((category: Category) => (
                        <option value={category.categoryId}>{category.categoryName}</option>
                    ))
                }
            </select>
            <div>
                <label htmlFor="minPriceFilter">$</label>
                <input type="text" placeholder="Min" className="form-control" name="minPriceFilter" onChange={(e) => {setPriceFilterMin(+e.target.value)}}/>
                <span>to</span>
                <input type="text" placeholder="Max" className="form-control" />
                <button type="submit" className="btn btn-primary">Search</button>
            </div>
        </form> */}
        <table className="table mt-5">
            <thead>
                <tr>
                    <th>Product Id</th>
                    <th>Name</th>
                    <th>Category id</th>
                    <th>Quantity per unit</th>
                    <th>Unit price</th>
                </tr>
            </thead>
            <tbody>
            {
                filteredProducts.map((product: Product) => (
                    <tr>
                        <td>{product.id}</td>
                        <td>{product.name}</td>
                        <td>{product.categoryId}</td>
                        <td>{product.quantityPerUnit}</td>
                        <td>${product.unitPrice}</td>
                    </tr>
                ))
            }
            </tbody>
        </table>
        </>
    )
}