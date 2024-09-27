import { useState } from 'react'
import CategoryAdd from '../category-add/CategoryAdd'
import CategoryCardContainer from '../category-card-container/CategoryCardContainer'
import './CategoriesPage.css'

export default function CategoriesPage()
{
    const [action, setAction] = useState("list");

    const updateCategory = (categoryId) => {
        setAction("edit");
    }

    return (
        <div className='container'>
            <header className="mt-4">
                <h1>Categories</h1>
            </header>
            <button className="btn btn-danger" onClick={()=> setAction("add")}>Add</button>

            {action === "list" && <CategoryCardContainer categoryToUpdate={updateCategory}></CategoryCardContainer>}
            {action === "add" && <CategoryAdd onCancel={()=>setAction("list")}
                                              onCategoryAdded={()=>setAction("list")}
                                              action="add"
                ></CategoryAdd>}
            {action === "edit" && <CategoryAdd onCancel={()=>setAction("list")}
                                              onCategoryAdded={()=>setAction("list")}
                                              action="edit"
                ></CategoryAdd>}
        </div>
    )
}