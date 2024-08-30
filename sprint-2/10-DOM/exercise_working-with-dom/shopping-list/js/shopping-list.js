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
        createGroceryDiv(groceryItem, groceryListContainer);
    });
}

function createGroceryDiv(groceryItem, parent)
{
    const groceryDiv = document.createElement("div");

    groceryDiv.classList.add("list-item");
    groceryDiv.textContent = groceryItem.title;

    parent.appendChild(groceryDiv);

    buildQuantity(groceryItem, groceryDiv);
    // clickToComplete(groceryItem);
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

/**
 * This function will be called when the button is clicked. You will need to get a reference
 * to every list item and add the class completed to each one
 */
function markAllCompleted() 
{
    const divs = document.querySelectorAll(".list-item");
    divs.forEach(div => {
        div.classList.add("complete");
    });
}

function clickToComplete(groceryItem)
{
    groceryItem.addEventListener('click', complete())

    function complete()
    {
        if(groceryItem.isComplete == false)
        {
            groceryItem.isComplete = true;
        }
    }
}


displayListTitle();
displayGroceries();

