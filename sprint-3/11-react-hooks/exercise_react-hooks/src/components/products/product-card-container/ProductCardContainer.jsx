import { useState, useEffect } from 'react';
import ProductCard from '../product-card/ProductCard';
import './ProductCardContainer.css';
import productService from '../../../services/product-service';

export default function ProductCardContainer()
{
    const [products, setProducts] = useState([]);

    useEffect(() => {
        productService.getAllProducts()
            .then(data => {
                setProducts(data);
            })
    })

    return(
        <>
        <main className="container mt-4 categories-container" id="products-container">
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
    )
}