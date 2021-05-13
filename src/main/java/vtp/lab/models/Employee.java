package vtp.lab.models;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private String surname;
    private String title;
    private float salary;

    public Employee(String first_name, String last_name, String surname, String title, float salary) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.surname = surname;
        this.title = title;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Float.compare(employee.salary, salary) == 0 && firstName.equals(employee.firstName) && lastName.equals(employee.lastName) && surname.equals(employee.surname) && title.equals(employee.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, surname, title, salary);
    }
}
