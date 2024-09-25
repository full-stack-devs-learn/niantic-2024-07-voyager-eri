import productService from '../../../services/product-service';
import './ProductCard.css';
import { XCircleFill } from 'react-bootstrap-icons';

export default function ProductCard({id, productName})
{
    return(
        <div className="card category-card">
            <div id="category-header" className="card-header">{productName}</div>
            <div id="category-body" className="card-body"></div>
            <div className="card-footer">
                <XCircleFill></XCircleFill>
            </div>
        </div>
    )
}