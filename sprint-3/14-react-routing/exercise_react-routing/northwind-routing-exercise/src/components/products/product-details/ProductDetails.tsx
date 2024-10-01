import { useParams } from "react-router-dom";

export default function ProductDetails()
{
    const { id } = useParams();

    return (
        <>
        <h4>Product Details for Product Id: {id}</h4>
        </>
    )
}