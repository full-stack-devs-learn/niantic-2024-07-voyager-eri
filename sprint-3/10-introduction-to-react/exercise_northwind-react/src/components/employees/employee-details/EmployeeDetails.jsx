import './EmployeeDetails.css'

export default function EmployeeDetails({firstName, lastName, id, title, salary, notes, onClose})
{
    const imageUrl = `images/employees/${id}.webp`

    return(
        <>
        <div className="details-container">
            <img id="employee-image" width={200} height={200} src={imageUrl} />
            <div>
                <h1>{firstName} {lastName}</h1>
                <p>Title: {title}</p>
                <p>Salary: {salary}</p>
                <p>Notes: {notes}</p>
            </div>
        </div>
        <button className="btn btn-dark" onClick={onClose}>Close</button>
        </>
    )
}