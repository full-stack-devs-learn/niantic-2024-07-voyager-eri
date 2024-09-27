import { useState, useEffect } from 'react';
import CategoryCard from '../category-card/CategoryCard'
import './CategoryCardContainer.css'
import categoryService from '../../../services/category-service';
import ProductsList from '../../products/products-list/ProductsList';

export default function CategoryCardContainer({categoryToUpdate})
{
    const [selectedCategory, setSelectedCategory] = useState("None Selected");
    const [selectedCategoryId, setSelectedCategoryId] = useState(0);
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        categoryService.getAllCategories().then(data => {
            setCategories(data);   
        })

    }, 
    // an emptry array says that I only want
    // this code to run one time before the first render
    [])
   

    const categorySelected = (name) =>
    {
        setSelectedCategory(name);

        const categoryId = categories.filter(cat => cat.categoryName === name)[0].categoryId;

        setSelectedCategoryId(categoryId);
        console.log(name)
    }

    const categoryDeleted = (categoryId) => {
        const newList = categories.filter(category => category.categoryId !== categoryId);
        setCategories(newList)
    }

    const categoryUpdated = (categoryId) => {
        categoryToUpdate(categoryId);
    }

    return(
        <>
        <h5>Selected Category: {selectedCategory}</h5>
        <main className="container mt-4 categories-container g-0" id="categories-container">
        {
            categories.map((category) => (
                <CategoryCard key={category.categoryId} 
                    category={category.categoryName} 
                    id={category.categoryId}
                    onCategorySelected={categorySelected}
                    onCategoryDeleted={categoryDeleted}
                    onCategoryUpdated={categoryUpdated}
                    ></CategoryCard>
            ))
        }
        </main>
        <ProductsList categoryId={selectedCategoryId}></ProductsList>
        </>
    )
}