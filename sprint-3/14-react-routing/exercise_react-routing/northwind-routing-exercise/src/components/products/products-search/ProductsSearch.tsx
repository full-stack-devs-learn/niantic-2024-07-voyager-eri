import { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";

export default function ProductsSearch()
{
    const [products, setProducts] = useState<any>()

    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const minPrice = params.get("minPrice") ?? 0;
    const maxPrice = params.get("maxPrice") ?? 0;
    const name = params.get("name") ?? "";
    const catId = params.get("catId") ?? 1;

    return (
        <>
        <p>List of Products</p>
        <p>{minPrice}</p>
        </>
    )
}