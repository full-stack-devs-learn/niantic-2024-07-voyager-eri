let categoryService;
let productService;
let addFormScreen;
let addForm;
let selectedCatId;

document.addEventListener("DOMContentLoaded", function() {
    categoryService = new CategoryService();
    productService = new ProductsService();

    addFormScreen = document.getElementById("add-form-screen");
    addForm = document.getElementById("add-form");

    document.getElementById("add-button").addEventListener("click", showForm);
    document.getElementById("cancel-button").addEventListener("click", cancelAdd);
    document.getElementById("save-button").addEventListener("click", addCategory);

    showCategoryOptions();
})

function loadProducts()
{
    productService.getProductsByCategory(selectedCatId)
        .then(products => {
            const productContainer = document.getElementById('categories-container');
            productContainer.innerHTML = '';

            products.forEach(product => {
                const template = document.getElementById('category-template').content.cloneNode(true);
                template.getElementById('category-header').innerText = product.productName;

                productContainer.appendChild(template);
            });
        })
}

function showCategoryOptions()
{
    const categorySelectionContainer = document.getElementById('category-select-container');
    categoryService.getAllCategories()
                   .then(categories => {
                    categories.forEach(category => {
                        const option = document.createElement("option");
                        option.textContent = category.categoryName;
                        option.value = category.categoryId;
                        categorySelectionContainer.appendChild(option);
                    })
                   });

                   categorySelectionContainer.addEventListener("change", (event => {
                    selectedCatId = event.target.value;
                    loadProducts();
                    }));
}

function showForm()
{
    addFormScreen.classList.remove("d-none");
}

function cancelAdd(event)
{
    event.preventDefault();
    addFormScreen.classList.add("d-none");
}

function addCategory(event)
{
    event.preventDefault();
    event.stopPropagation()

    addForm.classList.add("was-validated");

    if(addForm.checkValidity()){

        addFormScreen.classList.add("d-none");
        addForm.classList.remove("was-validated");
    }
}