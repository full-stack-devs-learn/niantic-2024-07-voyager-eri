import './ProductsPage.css'
import ProductCardContainer from '../product-card-container/ProductCardContainer'

export default function ProductsPage()
{
    return (
        <>
        <header className="container mt-4">
            <h1>Products</h1>
        </header>
        <ProductCardContainer></ProductCardContainer>
        </>
    )
}