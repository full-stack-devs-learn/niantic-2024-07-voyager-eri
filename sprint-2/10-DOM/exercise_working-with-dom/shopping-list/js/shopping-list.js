let service;
let list = []
let allItemsIncomplete = true;

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
    const groceryListContainer = document.getElementById("shopping-list");

    list.forEach(groceryItem => {
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
    drag();
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
    const editButtonDiv = document.createElement("button");
    editButtonDiv.classList.add("edit-button");
    editButtonDiv.textContent = "Edit";
    parent.appendChild(editButtonDiv);

    const deleteButtonDiv = document.createElement("button");
    deleteButtonDiv.classList.add("delete-button");
    parent.appendChild(deleteButtonDiv);

    const editIconDiv = document.createElement("img");
    editIconDiv.setAttribute("src", "/img/pen-to-square-solid.svg");
    editIconDiv.setAttribute("width", "10px");
    editButtonDiv.appendChild(editIconDiv);

    const deleteIconDiv = document.createElement("img");
    deleteIconDiv.setAttribute("src", "/img/trash-solid.svg");
    deleteIconDiv.setAttribute("width", "10px");
    deleteButtonDiv.appendChild(deleteIconDiv);
}

function hover(groceryItemDiv)
{
    groceryItemDiv.classList.add("hover");
}

function hoverOut(groceryItemDiv)
{
    groceryItemDiv.classList.remove("hover");
}


function buildMarkAllButton()
{
    const buttonContainer = document.getElementById("mark-all");
    buttonContainer.innerHTML = "Mark All Complete";
    buttonContainer.addEventListener("click", () => {
        if(allItemsIncomplete)
        {
            markAllCompleted();
            buttonContainer.innerHTML = "Mark All Incomplete";
            allItemsIncomplete = false;
        }
        else
        {
            markAllIncompleted();
            buttonContainer.innerHTML = "Mark All Complete";
            allItemsIncomplete = true;
        }
    });
}

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

function markAllIncompleted()
{
    const groceryList = document.querySelectorAll(".list-item");
    const checkmarks = document.querySelectorAll(".checkmark");

    groceryList.forEach(div => {
        div.classList.remove("completed");
    });

    checkmarks.forEach(div => {
        div.classList.remove("checkmark-completed");
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

function drag()
{
    const groceryList = document.querySelectorAll(".list-item"), current = null;
    
    groceryList.forEach(i =>
    {
        i.draggable = true
        i.addEventListener('dragenter', dragEnter)
        i.addEventListener('dragover', dragOver)
        i.addEventListener('dragleave', dragLeave)
        i.addEventListener('drop', drop);
    });
}

function dragEnter(e)
{
    e.preventDefault();
    e.target.classList.add('drag-over');
}

function dragOver(e)
{
    e.preventDefault();
    e.target.classList.add('drag-over');
}

function dragLeave(e, groceryItemDiv)
{
    e.target.classList.remove('drag-over');
    
    e.target.appendChild(groceryItemDiv);
}

function drop(e)
{
    e.target.classList.remove('drag-over');
}

document.addEventListener("DOMContentLoaded", () => {
    service = new ShoppingService();
    list = service.getShoppingList();

    displayListTitle();
    displayGroceries();
    buildMarkAllButton();
});