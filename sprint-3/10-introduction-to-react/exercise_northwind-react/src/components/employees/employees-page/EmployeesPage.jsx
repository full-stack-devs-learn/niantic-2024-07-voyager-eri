import EmployeeRow from "../employee-row/EmployeeRow"
import { employees } from '../../../data'

export default function EmployeesPage()
{
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
                        ></EmployeeRow>
                    ))
                }
            </main>
        </>
    )
}