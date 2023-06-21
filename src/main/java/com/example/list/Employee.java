package kurs1LowLevel;

public class Employee {
    private String fullName;
    private int department;
    private float salary;
    private int id;

    public static int counterId = 1;

    public String getFullName() {
        return fullName;
    }

    public int getDepartment() {
        return department;
    }

    public float getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Employee(String fullName, int department, int salary) {
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
        this.id = counterId++;
    }

    @Override
    public String toString() {
        return "Ф.И.О.: " + this.getFullName() + " Отдел: " + this.getDepartment() + " Зарплата: " + this.getSalary();
    }
}
