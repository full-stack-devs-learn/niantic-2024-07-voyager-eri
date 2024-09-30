import './BerryPage.css'
import pokeapiService from '../../services/pokeapi-service';
import { Berry } from '../../models/berry';
import { useEffect, useState } from 'react'

export default function BerryPage()
{
    const [berries, setBerries] = useState<Berry[]>([]);
    const [pageCount, setPageCount] = useState(0);

    // useEffect is NOT an async function
    useEffect(() => {
      callGetBerries()
    }, [pageCount]);
  
    async function callGetBerries()
    {
      const response = await pokeapiService.getAllBerries(pageCount);
      setBerries(response.results);
    }

    const pagePrevious = () =>
    {
      setPageCount(pageCount - 20);
    }

    const pageNext = () =>
    {
      setPageCount(pageCount + 20);
    }

    return (
        <>
        <table>
        {
          berries.map((berry: Berry) => (
            <tr>
              <td>{berry.name}</td>
              <td>{berry.url}</td>
            </tr>
          ))
        }
        </table>
        <button onClick={pagePrevious}>Previous</button>
        <button onClick={pageNext}>Next</button>
        </>
    )
}