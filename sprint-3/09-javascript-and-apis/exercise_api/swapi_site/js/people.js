// code here is the logic to manage the home (or people) page
let peopleService;
let pageNum = 1;

document.addEventListener('DOMContentLoaded', () => {
    peopleService = new PeopleService();

    // add logic to display a list of people from the SWAPI
    // note that it will defautl to displaying 10 people
    // at a time.

    // look at the API and discover how you can access
    // page 2 or page 3

    // can you add previous and next buttons to the page
    // to navigate between pages?

    peopleService = new PeopleService();
    togglePrevBtn();
    getPageNum();
    loadPeople();
})

function togglePrevBtn()
{
    const prev = document.getElementById("prev");
    
    if (pageNum == 1)
    {
        prev.setAttribute("disabled", "");
    }
    else
    {
        prev.removeAttribute("disabled", "");
    }
}

function getPageNum()
{
    const prev = document.getElementById("prev");
    const next = document.getElementById("next");

    prev.addEventListener("click", () => {
        pageNum--;
        togglePrevBtn();
        loadPeople(pageNum);
    })

    next.addEventListener("click", () => {
        pageNum++;
        togglePrevBtn();
        loadPeople(pageNum);
    })
}

function loadPeople()
{
    peopleService.getPeopleByPage(pageNum)
        .then(response => {
            const peopleContainer = document.getElementById("people-container");
            peopleContainer.innerHTML = "";

            response.results.forEach(person => {
                const personName = document.createElement("p");
                personName.innerHTML = person.name;
                peopleContainer.appendChild(personName);
            })
        })
}