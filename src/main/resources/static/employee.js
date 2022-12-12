var oldEmployees;
function showEmployeeFromDepartment (department) {

    if (oldEmployees !== undefined) {
        oldEmployees.forEach(employee => {
            employee.style.display = 'none';
        })
    }

    const employees = document.querySelectorAll('.' + department);
    employees.forEach(employee => {
        employee.style.display = null;
    })
    oldEmployees = employees;
}