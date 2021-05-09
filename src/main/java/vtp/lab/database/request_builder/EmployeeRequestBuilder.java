package vtp.lab.database.request_builder;

public class EmployeeRequestBuilder implements IEmployeeRequestBuilder {

    private StringBuilder request = new StringBuilder();

    @Override
    public IEmployeeRequestBuilder create() {
        request.append("SELECT first_name, last_name, surname, tts.title, salary FROM hr_db.employee as e " +
                "inner join hr_db.titles_list as tts on e.title = tts.id");
        return this;
    }

    @Override
    public IEmployeeRequestBuilder setSurname(String surname) {
        addWhereStatementIfNeeded();
        if (!surname.isEmpty()) {
            String surnameSqlParameter = String.format(" surname like '%s%s%s' AND", "%", surname, "%");
            request.append(surnameSqlParameter);
        }
        return this;
    }

    @Override
    public IEmployeeRequestBuilder setTitle(String title) {
        addWhereStatementIfNeeded();
        if (!title.isEmpty()) {
            String titleSqlParameter = String.format(" tts.title like '%s' AND", title);
            request.append(titleSqlParameter);
        }
        return this;
    }

    @Override
    public IEmployeeRequestBuilder setMinSalary(String minSalary) {
        addWhereStatementIfNeeded();
        if (!minSalary.isEmpty()) {
            String minSalarySqlParameter = String.format(" salary>'%s' AND", minSalary);
            request.append(minSalarySqlParameter);
        }
        return this;
    }

    @Override
    public IEmployeeRequestBuilder setMaxSalary(String maxSalary) {
        addWhereStatementIfNeeded();
        if (!maxSalary.isEmpty()) {
            String maxSalarySqlParameter = String.format(" salary<'%s' AND", maxSalary);
            request.append(maxSalarySqlParameter);
        }
        return this;
    }

    @Override
    public String build() {
        int length = request.toString().length();
        String result = request.toString();
        if (result.contains(" AND")) {
            result = request.toString().substring(0, length - 4);
        }
        return result;
    }

    private void addWhereStatementIfNeeded() {
        if (!request.toString().contains("WHERE")) {
            request.append(" WHERE");
        }
    }
}
