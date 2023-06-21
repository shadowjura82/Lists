package kurs1LowLevel;

import java.util.Arrays;
import java.util.Objects;

public class EmployeeBook {
    private Employee[] employees;

    public EmployeeBook(Employee[] employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        String result = "";
        for (Employee emp : employees)
            if (emp != null)
                result += "Ф.И.О.: " + emp.getFullName() + " Отдел: " + emp.getDepartment() + " Зарплата: " +
                        String.format("%.2f", emp.getSalary()) + "\n";
        return result;
    }

    public String toString(int departmentID) {
        String result = "";
        for (Employee emp : employees)
            if (emp != null && emp.getDepartment() == departmentID)
                result += "Ф.И.О.: " + emp.getFullName() + " Зарплата: " + String.format("%.2f", emp.getSalary()) + "\n";
        return result;
    }

    public float salarySumm() {
        int summ = 0;
        for (Employee emp : employees)
            if (emp != null)
                summ += emp.getSalary();
        return summ;
    }

    public Employee minSalaryEmployee() {
        int min = 0;
        for (int i = 0; i < employees.length; i++)
            if (employees[i] != null && employees[i].getSalary() < employees[min].getSalary()) min = i;
        return employees[0];
    }

    public Employee maxSalaryEmployee() {
        int max = 0;
        for (int i = 0; i < employees.length; i++)
            if (employees[i] != null && employees[i].getSalary() > employees[max].getSalary()) max = i;
        return employees[max];
    }

    public float averageSalary() {
        int count = 0;
        for (Employee emp : employees) {
            if (emp != null) count++;
        }
        return (float) salarySumm() / count;
    }

    public String getFullName() {
        String result = "";
        for (Employee emp : employees)
            if (emp != null)
                result += emp.getFullName() + "\n";
        return result;
    }

    public void indexingOfSalary(int percent) {
        if (percent < 0) {
            percent = Math.abs(percent);
            for (Employee emp : employees)
                if (emp != null)
                    emp.setSalary(emp.getSalary() - (emp.getSalary() / 100 * percent));
        } else {
            percent = Math.abs(percent);
            for (Employee emp : employees)
                if (emp != null)
                    emp.setSalary(emp.getSalary() + (emp.getSalary() / 100 * percent));
        }
    }

    public void indexingOfSalary(int percent, int departmentID) {
        if (percent < 0) {
            percent = Math.abs(percent);
            for (Employee emp : employees)
                if (emp != null && emp.getDepartment() == departmentID)
                    emp.setSalary(emp.getSalary() - (emp.getSalary() / 100 * percent));
        } else {
            percent = Math.abs(percent);
            for (Employee emp : employees)
                if (emp != null && emp.getDepartment() == departmentID)
                    emp.setSalary(emp.getSalary() + (emp.getSalary() / 100 * percent));
        }
    }

    public void departmentMethods(int departmentID) {
        int count = 0;
        int index = 0;
        for (Employee emp : employees)
            if (emp != null && emp.getDepartment() == departmentID) count++;
        Employee[] bufEmpMass = new Employee[count];
        for (Employee employee : employees)
            if (employee != null && employee.getDepartment() == departmentID) {
                bufEmpMass[index] = employee;
                index++;
            }
        EmployeeBook bufEmpBook = new EmployeeBook(bufEmpMass);
        System.out.print("Сотрудник с минимальной зарплатой: " + bufEmpBook.minSalaryEmployee().toString() + "\n");
        System.out.print("Сотрудник с максимальной зарплатой: " + bufEmpBook.maxSalaryEmployee().toString() + "\n");
        System.out.print("Суммарная зарплата за месяц: " + bufEmpBook.salarySumm() + "\n");
        System.out.printf("Средняя зарплата всех сотрудников отдела: %.2f\n", bufEmpBook.averageSalary());
        indexingOfSalary(350, 1);
        System.out.println("Список всех сотрудников: \n" + this.toString());
        System.out.println("Список всех сотрудников отдела 1: \n" + this.toString(1));
    }

    public void salaryAnalyzer(int number) {
        System.out.println("Cотрудники с зарплатой меньше числа:");
        for (Employee emp : employees)
            if (emp != null && emp.getSalary() < number)
                System.out.println("ID: " + emp.getId() + " Ф.И.О.: " + emp.getFullName() + " Зарплата: " +
                        String.format("%.2f", emp.getSalary()));
        System.out.println("Cотрудники с зарплатой больше (или равно) числа:");
        for (Employee emp : employees)
            if (emp != null && emp.getSalary() >= number)
                System.out.println("ID: " + emp.getId() + " Ф.И.О.: " + emp.getFullName() + " Зарплата: " +
                        String.format("%.2f", emp.getSalary()));
    }

    public void addEmployee(Employee employee) {
        int index = nextAvailable();
        employees[index] = employee;
    }

    public void deleteEmployee(String fullName) {
        for (int i = 0; i < employees.length; i++)
            if (employees[i] != null && employees[i].getFullName().equals(fullName)) employees[i] = null;
    }

    public void deleteEmployee(String fullName, int id) {
        for (int i = 0; i < employees.length; i++)
            if (employees[i] != null && employees[i].getFullName().equals(fullName) && employees[i].getId() == id)
                employees[i] = null;
    }

    public void deleteEmployee(int id) {
        for (int i = 0; i < employees.length; i++)
            if (employees[i] != null && employees[i].getId() == id) employees[i] = null;
    }

    private int nextAvailable() {
        for (int i = 0; i < employees.length; i++)
            if (employees[i] == null)
                return i;
        Employee[] mas = new Employee[employees.length];
        for (int i = 0; i < employees.length; i++)
            mas[i] = employees[i];
        employees = new Employee[employees.length + 1];
        for (int i = 0; i < mas.length; i++)
            employees[i] = mas[i];
        return employees.length - 1;
    }

    public void changeEmployeeSalary(String fullName, int newSalary) {
        for (Employee emp : employees)
            if (emp != null && emp.getFullName().equals(fullName)) emp.setSalary(newSalary);
    }

    public void changeEmployeeDepartment(String fullName, int newDepartmentID) {
        for (Employee emp : employees)
            if (emp != null && emp.getFullName().equals(fullName)) emp.setDepartment(newDepartmentID);
    }

    public void fullNameByDepartment() {
        String result;
        for (int i = 1; i <= 5; i++) {
            System.out.println("Список сотрудников отдела №" + i);
            for (Employee emp : employees)
                if (emp != null && emp.getDepartment() == i) System.out.println(emp.getFullName());
        }
    }
}