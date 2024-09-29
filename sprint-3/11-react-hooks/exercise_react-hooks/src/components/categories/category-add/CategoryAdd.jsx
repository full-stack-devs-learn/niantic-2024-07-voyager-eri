import { useState, useEffect } from "react";
import categoryService from "../../../services/category-service"

export default function CategoryAdd({onCancel, onCategoryAdded, action, categoryId})
{
    const [categoryName, setCategoryName] = useState('');
    const [description, setDescription] = useState('');
    const [category, setCategory] = useState("");

    async function addCategoryHandler(event)
    {
        event.preventDefault()

        const newCategory = {
            categoryName: categoryName,
            description: description
        }

        await categoryService.add(newCategory)
        onCategoryAdded()
    }

    async function editCategoryHandler(event)
    {
        event.preventDefault()

        const newCategory = {
            categoryName: categoryName,
            description: description
        }

        await categoryService.update(categoryId, newCategory)
        onCategoryAdded()
    }

    useEffect(() => {
        categoryService.getById(categoryId).then(data => {
            setCategory(data);
            setCategoryName(data.categoryName);
            setDescription(data.description);
        })
    }, [categoryId])

    return (
        <>
        <div className="container">
            {action === "add" && <h1>Add new category</h1>}
            {action === "edit" && <h1>Edit category</h1>}
            <form onSubmit={action === "add" ? addCategoryHandler : editCategoryHandler}>
                <div className="row">
                    <label htmlFor="category-name">Category Name:</label>
                    <input type="text" className="form-control" name="category-name" id="category-name" defaultValue={category.categoryName}
                        onChange={e => setCategoryName(e.target.value)}
                    />
                </div>

                <div className="row">
                    <label htmlFor="description">Description:</label>
                    <textarea className="form-control" name="description" id="description" defaultValue={category.description}
                        onChange={e => setDescription(e.target.value)} />
                </div>
                <button className="btn btn-danger mr-3" type="submit">Submit</button>
                <button className="btn btn-dark" type="cancel" onClick={onCancel}>Cancel</button>
            </form>
        </div>
        </>
    )
}