import EmployeeRow from "../employee-row/EmployeeRow"
import EmployeeDetails from "../employee-details/EmployeeDetails";
import { employees } from '../../../data'
import { useState } from "react"

export default function EmployeesPage()
{
    const [selectedEmployeeId, setSelectedEmployeeId] = useState(0);

    const employeeSelected = (id) => {
        setSelectedEmployeeId(id);
        const selectedEmployee = employees.find(employee => employee.employeeId == id);
        console.log(id);
        console.log(selectedEmployee);
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