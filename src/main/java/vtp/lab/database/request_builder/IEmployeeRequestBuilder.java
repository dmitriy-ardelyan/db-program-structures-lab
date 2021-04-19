package vtp.lab.database.request_builder;

public interface IEmployeeRequestBuilder {

    IEmployeeRequestBuilder create();
    IEmployeeRequestBuilder setSurname(String surname);
    IEmployeeRequestBuilder setTitle(String title);
    IEmployeeRequestBuilder setMinSalary(String minSalary);
    IEmployeeRequestBuilder setMaxSalary(String maxSalary);
    String build();
}
