package com.example.list;

import com.example.list.exceptions.EmployeeAlreadyAddedException;
import com.example.list.exceptions.EmployeeNotFoundException;
import com.example.list.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees = new ArrayList<>();
    private final int MAX_EMPLOYEES = 20;

    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() >= MAX_EMPLOYEES)
            throw new EmployeeStorageIsFullException("Ошибка! В базе уже зарегистрировано максимально допустимое количество сотрудников");
        if (employees.contains(employee))
            throw new EmployeeAlreadyAddedException("Ошибка! Такой пользователь уже зарегистрирован в базе");
        employees.add(employee);
        return employee;
    }

    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee))
            throw new EmployeeNotFoundException("Ошибка! Сотрудник с таким именем и фамилией не найден");
        employees.remove(employee);
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee))
            throw new EmployeeNotFoundException("Ошибка! Сотрудник с таким именем и фамилией не найден");
        return employee;
    }

    public List<Employee> fullEmployeesList() {
        return employees;
    }
}