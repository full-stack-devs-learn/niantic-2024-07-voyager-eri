import productService from '../../../services/product-service';
import './ProductCard.css';
import { XCircleFill } from 'react-bootstrap-icons';

export default function ProductCard({id, productName, onProductDeleted})
{
    async function deleteProduct(event)
    {
        event.stopPropagation();

        await productService.delete(id);

        onProductDeleted(id);
    }
    return(
        <div className="card category-card">
            <div id="category-header" className="card-header">{productName}</div>
            <div id="category-body" className="card-body"></div>
            <div className="card-footer">
                <XCircleFill onClick={deleteProduct} color="red"></XCircleFill>
            </div>
        </div>
    )
}