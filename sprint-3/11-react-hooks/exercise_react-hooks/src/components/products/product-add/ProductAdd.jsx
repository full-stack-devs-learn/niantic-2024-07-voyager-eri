import { useState } from "react";
import productService from "../../../services/product-service";

export default function ProductAdd({onCancel, onProductAdded})
{
    const [name, setName] = useState('');
    const [categoryId, setCategoryId] = useState('');
    const [quantityPerUnit, setQuantityPerUnit] = useState('');
    const [unitPrice, setUnitPrice] = useState('');

    async function addProductHandler(event)
    {
        event.preventDefault();

        const newProduct = {
            name: name,
            categoryId: categoryId,
            quantityPerUnit: quantityPerUnit,
            unitPrice: unitPrice
        }

        await productService.add(newProduct)

        onProductAdded()
    }

    return (
        <div className="container">
            <h2>Add New Product</h2>
            <form onSubmit={addProductHandler}>
                <div className="row">
                    <label htmlFor="product-name">Product name</label>
                    <input type="text" className="form-control" name="product-name" id="product-name"
                        onChange={(e) => setName(e.target.value)}
                    />
                </div>

                <div className="row">
                    <label htmlFor="category-id">Category id</label>
                    <input type="number" className="form-control" name="category-id" id="category-id"
                        onChange={(e) => setCategoryId(e.target.value)}
                    />
                </div>

                <div className="row">
                    <label htmlFor="quantity-per-unit">Quantity per unit</label>
                    <input type="number" className="form-control" name="quantity-per-unit" id="quantity-per-unit"
                        onChange={(e) => setQuantityPerUnit(e.target.value)}
                    />
                </div>

                <div className="row">
                    <label htmlFor="unit-price">Unit price</label>
                    <input type="number" className="form-control" name="unit-price" id="unit-price"
                        onChange={(e) => setUnitPrice(e.target.value)}
                    />
                </div>

                <button className="btn btn-danger mr-3" type="submit">Add Product</button>
                <button className="btn btn-dark" type="cancel" onClick={onCancel}>Cancel</button>
            </form>
        </div>
    )
}