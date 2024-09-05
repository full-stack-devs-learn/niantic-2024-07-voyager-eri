document.addEventListener("DOMContentLoaded", () => {
    loadPage();
})

function loadPage()
{
    const container = document.getElementById("product-container");
    const category = document.getElementById("category");
    let selectedCatId;
    let url;

    category.addEventListener("change", (event => {
        selectedCatId = event.target.value;

        url = `api/products/category/${selectedCatId}`;
        fetch(url).then(response => {
            return response.json();
        })
            .then(data => {
                const container = document.getElementById("product-container");
                container.innerHTML = "";
        
                data.forEach(product => {
                    displayProduct(product, container)
                })
            })
            .catch(error => {
                console.log(error)
            });
    }))
    
}

function displayProduct(product, table)
{
    const row = document.createElement("tr");
    const idCell = document.createElement("td");
    const productNameCell = document.createElement("td");
    const priceCell = document.createElement("td");
    const buttonsCell = document.createElement("td");

    idCell.textContent = product.productId;
    productNameCell.textContent = product.productName;
    priceCell.textContent = "$" + product.unitPrice.toFixed(2);

    const editLink = document.createElement("a");
    editLink.classList.add("btn");
    editLink.classList.add("btn- warning");
    editLink.classList.add("me-1");
    editLink.textContent = "Edit";
    editLink.href = `/products/${product.productId}/edit`

    const deleteLink = document.createElement("a");
    deleteLink.classList.add("btn");
    deleteLink.classList.add("btn-danger");
    deleteLink.textContent = "Delete";
    deleteLink.href = `/products/${product.productId}/delete`

    buttonsCell.appendChild(editLink);
    buttonsCell.appendChild(deleteLink);

    row.appendChild(idCell);
    row.appendChild(productNameCell);
    row.appendChild(priceCell);
    row.appendChild(buttonsCell);

    table.appendChild(row);
}