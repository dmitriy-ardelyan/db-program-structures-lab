package vtp.lab.services;

import vtp.lab.models.Employee;

import java.util.ArrayList;

public class EmployeeService {
    public static int deleteEmployee(Employee employee) {
        return DataBaseService.deleteEmployee(employee);
    }

    public static int updateEmployee(int employeeId, Employee updatedEmployeeModel) {
        return DataBaseService.updateEmployee(employeeId, updatedEmployeeModel);
    }

    public static ArrayList<Employee> getAllEmployees() {
        return DataBaseService.getAllEmployees();
    }

    public static ArrayList<Employee> searchEmployeesByParameters(String surname, String title, String minSalary, String maxSalary) {
        return DataBaseService.getEmployeesByParameters(surname, title, minSalary, maxSalary);
    }
}
