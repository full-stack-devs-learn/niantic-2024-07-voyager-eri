// define the SWAPI people service logic here

class PeopleService
{
    baseUrl = `${config.baseUrl}/people?limit=10&page=`

    // write a function that allows you to request a page
    // of 10 people- how can you request page 1,2, 3 etc?
    // consider using async/await with your axios request

    async getPeopleByPage(pageNum)
    {
        const response = await axios.get(this.baseUrl + pageNum);
        return response.data;
    }
}