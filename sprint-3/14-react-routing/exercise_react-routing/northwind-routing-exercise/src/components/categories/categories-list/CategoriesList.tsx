import { useEffect, useState } from "react"
import { Link } from "react-router-dom";
import categoryService from "../../services/category-service";
import { Category } from "../../../models/category";

export default function CategoriesList()
{
    const [categories, setCategories] = useState<Category[]>([]);

    useEffect(() => { loadCategories() }, []);

    async function loadCategories()
    {
        const response = await categoryService.getAllCategories();
        setCategories(response);
    }

    return (
        <>
        <h2>List of Categories</h2>
        
        <table className="table table-hover mt-5">
            <thead>
                <th>Category Id</th>
                <th>Name</th>
                <th>Description</th>
            </thead>
            <tbody>
            {
                categories.map((category: Category) => (
                    <tr>
                        <td>{category.categoryId}</td>
                        <td><Link to={`/categories/${category.categoryId}`}>{category.categoryName}</Link></td>
                        <td>{category.description}</td>
                    </tr>
                ))
            }
            </tbody>
        </table>
        </>
    )
}