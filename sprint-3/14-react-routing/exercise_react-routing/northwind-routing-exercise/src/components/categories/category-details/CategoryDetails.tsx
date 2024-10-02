import { useParams } from "react-router-dom";
import { useEffect,useState } from "react";
import { Product } from "../../../models/product";
import productService from "../../services/product-service";

export default function CategoryDetails()
{
    const [products, setProducts] = useState<Product[]>([]);
    const params = useParams();
    const categoryId = params.categoryId ?? 0;

    useEffect(() => { loadProducts() }, []);

    async function loadProducts()
    {
        const response = await productService.getByCategoryId(+categoryId);
        setProducts(response);
    }

    return (
        <>
        <h2>Category Details for Category Id: {categoryId}</h2>
        <table className="table mt-5">
            <thead>
                <th>Product Id</th>
                <th>Name</th>
                <th>Quantity per unit</th>
                <th>Unit price</th>
            </thead>
            <tbody>
            {
                products.map((product: Product) => (
                    <tr>
                        <td>{product.id}</td>
                        <td>{product.name}</td>
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