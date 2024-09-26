import './ProductsList.css'
import productService from '../../../services/product-service';
import { useState, useEffect } from 'react';
import ProductCard from '../product-card/ProductCard'

export default function ProductsList({categoryId})
{
    const [products, setProducts] = useState([]);

    // call the products api - getProductsByCategoryId
    useEffect(() => {
        productService.getProductsByCategoryId(categoryId).then(data => {
            setProducts(data);
        })
    }, [categoryId])

    return (
        <>
        {categoryId !== 0 &&
            <>
            <h3>Products for category: {categoryId}</h3>
            <main className="container my-4 categories-container" id="products-container">
            {
                products.map((product) => (
                    <ProductCard key={product.id}
                        productName={product.name}
                        id={product.id}
                        ></ProductCard>
                ))
            }
            </main>
            </>
        }
        </>
    )
}