package vtp.lab.services;

import vtp.lab.models.Employee;

public class EmployeeService {
    public static int deleteEmployee(Employee employee){
        return DataBaseService.deleteEmployee(employee);
    }
}
