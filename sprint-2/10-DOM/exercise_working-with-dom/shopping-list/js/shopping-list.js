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
    groceryItemDiv.setAttribute("onmouseover", "hoverCheckmark(this)");
    groceryItemDiv.setAttribute("onmouseout", "hoverOutCheckmark(this)");
    parent.appendChild(groceryItemDiv);

    const checkboxSpan = buildCheckbox(groceryItemDiv);
    buildGroceryName(groceryItem, groceryItemDiv);

    buildQuantity(groceryItem, groceryItemDiv);
    clickToComplete(groceryItem, groceryItemDiv, checkboxSpan);
}

function hoverCheckmark(groceryItemDiv)
{
    groceryItemDiv.classList.add("hover");
}

function hoverOutCheckmark(groceryItemDiv)
{
    groceryItemDiv.classList.remove("hover");
}

function buildGroceryName(groceryItem, parent)
{
    const groceryNameDiv = document.createElement("div");
    groceryNameDiv.textContent = groceryItem.title;

    parent.appendChild(groceryNameDiv);
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

/**
 * This function will be called when the button is clicked. You will need to get a reference
 * to every list item and add the class completed to each one
 */
function markAllCompleted() 
{
    const groceryList = document.querySelectorAll(".list-item");
    const checkmarks = document.querySelectorAll(".checkmark");

    groceryList.forEach(div => {
        div.classList.toggle("complete-all");
    });

    checkmarks.forEach(div => {
        div.classList.toggle("completed");
    })
}

function clickToComplete(groceryItem, parent, checkboxSpan)
{
    parent.addEventListener("change", function() {
        parent.classList.toggle("complete-all");
        checkboxSpan.classList.toggle("completed");
        groceryItem.isComplete = (!groceryItem.isComplete) ? true : false;
    }, false);
}

// TODO: STRIKETHROUGH IS ON QUANTITY TOO

displayListTitle();
displayGroceries();

