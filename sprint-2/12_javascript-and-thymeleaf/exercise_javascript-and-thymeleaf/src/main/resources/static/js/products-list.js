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

        url = `/products/category/${selectedCatId}`;
        fetch(url).then(response => {
            if(response.status === 200)
            {
                return response.text();
            }
            throw new Error(response);
        }).then(data => {
            container.innerHTML = data;
    
            // data.forEach(product => {
            //     displayProduct(product, container)
            // })
        }).catch(error => {
            console.log(error)
        });
    }))
}