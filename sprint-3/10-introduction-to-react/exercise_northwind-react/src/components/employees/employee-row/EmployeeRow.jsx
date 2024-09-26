import './EmployeeRow.css'
import EmployeeDetails from '../employee-details/EmployeeDetails';
import { useState } from "react"

export default function EmployeeRow({firstName, lastName, id, title, salary, notes})
{
    const imageUrl = `images/employees/${id}.webp`

    const [details, setDetails] = useState("hide");



    return(
        <>
        {details === "hide" && 
            <div className="employee-row" onClick={() => setDetails("show")}>
                <img id="employee-image" src={imageUrl} />
                <div className="employee-name">
                    <h1>{firstName} {lastName}</h1>
                    <h6>{title}</h6>
                </div>
            </div>
        }

        {details === "show" &&
            <EmployeeDetails key={id}
                firstName={firstName}
                lastName={lastName}
                id={id}
                title={title}
                salary={salary}
                notes={notes}
                onClose={() => setDetails("hide")}
            ></EmployeeDetails>
        }
        </>
    )
}