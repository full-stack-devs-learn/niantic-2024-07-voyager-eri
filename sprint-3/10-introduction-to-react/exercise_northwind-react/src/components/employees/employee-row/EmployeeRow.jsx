import './EmployeeRow.css'

export default function EmployeeRow({firstName, lastName, id, title})
{
    const imageUrl = `images/employees/${id}.webp`

    return(
        <div className="employee-row">
          <img id="employee-image" src={imageUrl} />
          <div className="employee-name">
            <h1>{firstName} {lastName}</h1>
            <h6>{title}</h6>
          </div>
        </div>
    )
}