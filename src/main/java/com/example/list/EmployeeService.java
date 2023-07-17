package com.example.list;

import java.util.List;

public interface EmployeeService {

    public Employee addEmployee(String firstName, String lastName);

    public Employee deleteEmployee(String firstName, String lastName);

    public Employee findEmployee(String firstName, String lastName);

    public List<Employee> fullEmployeesList();
}
