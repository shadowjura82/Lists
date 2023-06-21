package com.example.list;

import com.example.list.exceptions.EmployeeAlreadyAddedException;
import com.example.list.exceptions.EmployeeNotFoundException;
import com.example.list.exceptions.EmployeeStorageIsFullException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName) {
        try {
            return employeeService.addEmployee(firstName, lastName);
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "/remove")
    public Employee deleteEmployee(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName) {
        try {
            return employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "/list")
    public List<Employee> fullEmployeesList() {
        return employeeService.fullEmployeesList();
    }
}