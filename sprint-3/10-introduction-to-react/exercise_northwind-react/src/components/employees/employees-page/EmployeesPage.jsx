import EmployeeRow from "../employee-row/EmployeeRow"
import { employees } from '../../../data'
import { useState } from "react"

export default function EmployeesPage()
{
    const [setSelectedEmployeeId] = useState(0);

    const employeeSelected = (id) =>
    {
        setSelectedEmployeeId(id);
        console.log(id);
    }

    return (
        <>
            <header className="container mt-4">
                <h1>Employees</h1>
            </header>

            <main className="container my-4" id="employees-container">
                {
                    employees.map((employee) => (
                        <EmployeeRow key={employee.employeeId}
                        firstName={employee.firstName}
                        lastName={employee.lastName}
                        id={employee.employeeId}
                        title={employee.title}
                        onEmployeeSelected={employeeSelected}
                        ></EmployeeRow>
                    ))
                }
            </main>
        </>
    )
}