const service = new ShoppingService();

// add pageTitle

// add groceries

/**
 * This function will get a reference to the title and set its text to the value
 * of the pageTitle variable that was set above.
 */
function displayListTitle() 
{
    const pageTitle = document.getElementById("title");
    pageTitle.textContent = service.getListName();
}

/**
 * This function will loop over the array of groceries that was set above and add them to the DOM.
 */
function displayGroceries() 
{
    const groceries = service.getShoppingList();
    const groceryListContainer = document.getElementById("shopping-list");

    groceries.forEach(groceryItem => {
        createGroceryItemDiv(groceryItem, groceryListContainer);
    });
}

function createGroceryItemDiv(groceryItem, parent)
{
    const groceryItemDiv = document.createElement("label");
    groceryItemDiv.classList.add("list-item");

    groceryItemDiv.setAttribute("onmouseover", "hover(this)");
    groceryItemDiv.setAttribute("onmouseout", "hoverOut(this)");

    parent.appendChild(groceryItemDiv);

    const checkboxSpan = buildCheckbox(groceryItemDiv);

    const groceryNameDiv = buildGroceryName(groceryItem, groceryItemDiv);
    buildQuantity(groceryItem, groceryItemDiv);
    buildOptionsButton(groceryItemDiv);
    clickToComplete(groceryItem, groceryItemDiv, checkboxSpan);

    addGroceryItem(parent);
    deleteGroceryItem(groceryItemDiv);
    editGroceryItem(groceryItemDiv, groceryNameDiv);

}

function buildGroceryName(groceryItem, parent)
{
    const groceryNameDiv = document.createElement("div");
    groceryNameDiv.classList.add("grocery-name");
    groceryNameDiv.textContent = groceryItem.title;

    parent.appendChild(groceryNameDiv);
    return groceryNameDiv;
}

function buildQuantity(groceryItem, parent)
{
    const quantityDiv = document.createElement("div");
    quantityDiv.classList.add("quantity-container");
    buildQuantityLabel(quantityDiv);

    const textNode = document.createTextNode(groceryItem.quantity);
    quantityDiv.appendChild(textNode);

    parent.appendChild(quantityDiv);
}

function buildQuantityLabel(parent)
{
    const quantityLabelDiv = document.createElement("span");
    quantityLabelDiv.classList.add("super");
    quantityLabelDiv.textContent = "Quantity";

    parent.appendChild(quantityLabelDiv);
}

function buildCheckbox(parent)
{
    const checkboxDiv = document.createElement("input");
    const checkboxSpan = document.createElement("span");

    checkboxDiv.setAttribute("type", "checkbox");
    checkboxDiv.classList.add("hideDefaultCheckbox");
    checkboxSpan.classList.add("checkmark");
    
    parent.appendChild(checkboxDiv);
    parent.appendChild(checkboxSpan);

    return checkboxSpan;
}

function buildOptionsButton(parent)
{
    const editDiv = document.createElement("button");
    editDiv.classList.add("edit-button");
    editDiv.textContent = "Edit";
    parent.appendChild(editDiv);

    const deleteDiv = document.createElement("button");
    deleteDiv.classList.add("delete-button");
    deleteDiv.textContent = "Delete";
    parent.appendChild(deleteDiv);

    // const deleteButtonDiv = document.createElement("img");
    // deleteButtonDiv.setAttribute("src", "/img/trash-solid.svg");
    // deleteButtonDiv.setAttribute("width", "10px");
    // deleteDiv.appendChild(deleteButtonDiv);
}

function hover(groceryItemDiv)
{
    groceryItemDiv.classList.add("hover");
}

function hoverOut(groceryItemDiv)
{
    groceryItemDiv.classList.remove("hover");
}

/**
 * This function will be called when the button is clicked. You will need to get a reference
 * to every list item and add the class completed to each one
 */
function markAllCompleted() 
{
    const groceryList = document.querySelectorAll(".list-item");
    const checkmarks = document.querySelectorAll(".checkmark");

    groceryList.forEach(div => {
        div.classList.add("completed");
    });

    checkmarks.forEach(div => {
        div.classList.add("checkmark-completed");
    })
}

function clickToComplete(groceryItem, parent, checkboxSpan)
{
    checkboxSpan.addEventListener("click", () => {
        parent.classList.toggle("completed");
        checkboxSpan.classList.toggle("checkmark-completed");
        groceryItem.isComplete = (!groceryItem.isComplete) ? true : false;
    }, false);
}

function addGroceryItem(groceryListContainer)
{
    const groceryNameInput = document.getElementById("grocery-name");
    const groceryQuantityInput = document.getElementById("grocery-quantity");

    const addButton = document.getElementById("add");

    addButton.addEventListener("click", () => {
        const groceryName = groceryNameInput.value.trim();
        const groceryQuantity = groceryQuantityInput.value.trim();

        if (groceryName !== "")
        {
            const groceryItem = {
                title: groceryName,
                quantity: groceryQuantity
            };

            createGroceryItemDiv(groceryItem, groceryListContainer);
            groceryNameInput.value = "";
            groceryQuantityInput.value = "";
        }
    });
}

function editGroceryItem(parent, groceryNameDiv)
{
    const editButton = parent.querySelector(".edit-button");
    editButton.addEventListener("click", () => {
        const span = parent.children[2];
        const input = document.createElement("input");
        input.classList.add("edit-grocery-item");

        if (editButton.textContent == "Edit")
        {
            input.type = "text";
            input.value = groceryNameDiv.innerText;
            
            parent.appendChild(input);
            parent.insertBefore(input, span);
            groceryNameDiv.remove();
            editButton.textContent = "Save";
        }
        else if (editButton.textContent == "Save")
        {
            groceryNameDiv.textContent = span.value;
            parent.insertBefore(groceryNameDiv, span);
            parent.removeChild(span);
            editButton.textContent = "Edit";
        }
    })
}

function deleteGroceryItem(groceryItemDiv)
{
    const deleteButton = groceryItemDiv.querySelector(".delete-button");
    deleteButton.addEventListener("click", () => {
        groceryItemDiv.remove();
    })
}



displayListTitle();
displayGroceries();