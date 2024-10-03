import { useEffect, useState } from "react";
import { useLocation, useSearchParams } from "react-router-dom";
import productService from "../../services/product-service";
import categoryService from "../../services/category-service";
import { Product } from "../../../models/product";
import { Category } from "../../../models/category";

export default function ProductsSearch()
{
    const [categories, setCategories] = useState<Category[]>([]);
    const [filteredProducts, setFilteredProducts] = useState<Product[]>([]);
    const [searchName, setSearchName] = useState("");
    const [searchCategory, setSearchCategory] = useState("");
    const [searchParams, setSearchParams] = useSearchParams();

    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const minPrice = params.get("minPrice") ?? 0;
    const maxPrice = params.get("maxPrice") ?? 0;
    const name = params.get("name") ?? "";
    const catId = params.get("catId") ?? 0;

    useEffect(() => { loadAllProducts() }, [searchParams]);
    useEffect(() => { loadCategories() }, []);

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

    async function loadCategories()
    {
        const response = await categoryService.getAllCategories();
        setCategories(response);
    }

    function searchHandler(event: any)
    {
        event.preventDefault();

        if(params.has("name"))
        {
            params.delete("name");
        }
        if(searchName.length != 0)
        {
            params.append("name", searchName);
        }

        if(params.has("catId"))
        {
            params.delete("catId");
        }
        if(searchCategory.length != 0)
        {
            params.append("catId", searchCategory);
        }

        setSearchParams(params.toString());
    }

    return (
        <>
        <h2>Search for Products</h2>
        <form onSubmit={searchHandler}>
            <input className="form-control" type="search" placeholder="Search for product name" onChange={(e) => {setSearchName(e.target.value)}}></input>
            <select className="form-select" onChange={(e) => {setSearchCategory(e.target.value)}}>
                <option value="0">Select a category</option>
                {
                    categories.map((category: Category) => (
                        <option value={category.categoryId}>{category.categoryName}</option>
                    ))
                }
            </select>
            <button className="btn btn-primary" type="submit">Search</button>
        </form>
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