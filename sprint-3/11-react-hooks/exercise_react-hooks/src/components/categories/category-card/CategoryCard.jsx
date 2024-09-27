import categoryService from '../../../services/category-service'
import './CategoryCard.css'
import { PencilFill, XCircleFill } from 'react-bootstrap-icons'
import { useState } from 'react'

export default function CategoryCard({id, category, onCategorySelected, onCategoryDeleted, onCategoryUpdated})
{
    const imageUrl = `images/categories/${id}.webp`
    const [categoryToUpdate, setCategoryToUpdate] = useState(0);
    
    const categoryClicked = () => {
        onCategorySelected(category)       
    }

    async function deleteCategory(event)
    {
        event.stopPropagation();

        await categoryService.delete(id);

        onCategoryDeleted(id);
    }

    async function updateCategory()
    {
        onCategoryUpdated(id);
    }


    return(
        <div className="card category-card" onClick={categoryClicked}>
            <div id="category-header" className="card-header">{category}</div>
            <div id="category-body" className="card-body">
                <img id="category-image" src={imageUrl} />
            </div>
            <div className="card-footer">
                <XCircleFill onClick={deleteCategory} color="red" />
                <PencilFill onClick={updateCategory}/>
            </div>
        </div>
    )
}