package vtp.lab.services;

import main.java.vtp.lab.PropertiesLoader;
import main.java.vtp.lab.PropertiesSupplier;
import vtp.lab.database.request_builder.EmployeeRequestBuilder;
import vtp.lab.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataBaseService {

    private static Connection connection;
    private static String dataBaseConnectionString;
    private static String user;
    private static String password;

    static {
        if (Objects.isNull(dataBaseConnectionString)) {
            PropertiesLoader.loadGlobalProperties(DataBaseService.class, "db.properties");
        }
        dataBaseConnectionString = PropertiesSupplier.getProperty("connection.string");
        user = PropertiesSupplier.getProperty("user");
        password = PropertiesSupplier.getProperty("password");
    }

    private DataBaseService() {

    }

    public static synchronized Connection getDataBaseConnection() throws SQLException {
        if (Objects.isNull(connection)) {
            connection = DriverManager.getConnection(dataBaseConnectionString, user, password);
        }
        return connection;
    }

    public static ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Connection connection = getDataBaseConnection();
            EmployeeRequestBuilder employeeRequestBuilder = new EmployeeRequestBuilder();
            String query = employeeRequestBuilder.create().build();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("surname"),
                        resultSet.getString("title"),
                        resultSet.getFloat("salary")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }

    public static List<String> getAllEmployeesSurnames() {
        ArrayList<Employee> employees = getAllEmployees();
        return employees
                .stream()
                .map(Employee::getSurname)
                .collect(Collectors.toList());
    }

    public static ArrayList<Employee> getEmployeesByParameters(String surname, String title, String minSalary, String maxSalary) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Connection connection = getDataBaseConnection();
            EmployeeRequestBuilder employeeRequestBuilder = new EmployeeRequestBuilder();
            String exactQuery = employeeRequestBuilder
                    .create()
                    .setSurname(surname)
                    .setTitle(title)
                    .setMinSalary(minSalary)
                    .setMaxSalary(maxSalary)
                    .build();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(exactQuery);
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("surname"),
                        resultSet.getString("title"),
                        resultSet.getFloat("salary")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }

    public static ArrayList<String> getJobTitles() {
        ArrayList<String> jobTitles = new ArrayList<>();
        try {
            Connection connection = getDataBaseConnection();
            String query = "SELECT title FROM hr_db.titles_list";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jobTitles.add(resultSet.getString("title"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jobTitles;
    }

    public static int insertNewJobTitle(String newTitle) {
        int result = 0;
        try {
            Connection connection = DataBaseService.getDataBaseConnection();

            String query = "INSERT INTO titles_list (title) values('" + newTitle + "')";

            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static int insertNewResponsibility(String responsibility) {
        int result = 0;
        try {
            Connection connection = DataBaseService.getDataBaseConnection();

            String query = "INSERT INTO responsibilities_list (name) values('" + responsibility + "')";

            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static int deleteJobTitle(String titleForDeletion) {
        int result = 0;
        try {
            Connection connection = DataBaseService.getDataBaseConnection();
            String query = "DELETE FROM hr_db.titles_list where title ='" + titleForDeletion + "'";
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static int deleteResponsibility(String responsibility) {
        int result = 0;
        try {
            Connection connection = DataBaseService.getDataBaseConnection();
            String query = "DELETE FROM hr_db.responsibilities_list where name ='" + responsibility + "'";
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static int deleteEmployee(Employee employee) {
        int result = 0;
        try {
            Connection connection = DataBaseService.getDataBaseConnection();
            String query = "DELETE FROM hr_db.employee where id = " + getEmployeeId(employee);
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static int getEmployeeId(Employee employee) {
        int id = 0;
        try {
            Connection connection = DataBaseService.getDataBaseConnection();
            String query = "SELECT e.id FROM hr_db.employee as e \n" +
                    "inner join hr_db.titles_list as tts \n" +
                    "on e.title = tts.id\n" +
                    "where tts.title = '%s'\n" +
                    "AND first_name = '%s'\n" +
                    "AND last_name = '%s'\n" +
                    "AND surname = '%s'";
            String exactQuery = String.format(
                    query,
                    employee.getTitle(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSurname());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(exactQuery);
            while (resultSet.next()) {
                id = Integer.valueOf(resultSet.getString("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    private static int getEmployeeIdBySurname(String surname) {
        int id = 0;
        try {
            Connection connection = DataBaseService.getDataBaseConnection();
            String query = "SELECT id FROM hr_db.employee where surname LIKE '%s'";
            String exactQuery = String.format(
                    query, surname);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(exactQuery);
            while (resultSet.next()) {
                id = Integer.valueOf(resultSet.getString("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public static int updateEmployee(int employeeId, Employee updatedEmployeeModel) {
        int result = 0;
        try {
            Connection connection = DataBaseService.getDataBaseConnection();
            String defaultRequest = "UPDATE hr_db.employee\n" +
                    "SET first_name = '%s', \n" +
                    "last_name = '%s', \n" +
                    "surname = '%s',\n" +
                    "title = (select id from hr_db.titles_list where title = '%s'),\n" +
                    "salary = '%s'\n" +
                    "WHERE id = '%s'";
            String exactQuery = String.format(
                    defaultRequest,
                    updatedEmployeeModel.getFirstName(),
                    updatedEmployeeModel.getLastName(),
                    updatedEmployeeModel.getSurname(),
                    updatedEmployeeModel.getTitle(),
                    updatedEmployeeModel.getSalary(),
                    employeeId
            );
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(exactQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static ArrayList<String> getResponsibilities() {
        ArrayList<String> responsibilities = new ArrayList<>();
        try {
            Connection connection = getDataBaseConnection();
            String query = "SELECT name FROM hr_db.responsibilities_list";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                responsibilities.add(resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return responsibilities;
    }

    public static void setSubordinateForManager(String newSubordinate, String manager) {
        int subordinateId = getEmployeeIdBySurname(newSubordinate);
        int managerId = getEmployeeIdBySurname(manager);
        insertManagerEmployeeRelationship(subordinateId, managerId);
    }

    private static int insertManagerEmployeeRelationship(int subordinateId, int managerId) {
        int result = 0;
        try {
            Connection connection = DataBaseService.getDataBaseConnection();

            String query = "Insert into subordinates(manager_id,subordinate_id)\n" +
                    "values(%s,%s)";
            String customQuery = String.format(query, managerId, subordinateId);

            Statement statement = connection.createStatement();
            result = statement.executeUpdate(customQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static List<String> getSubordinates(String manager) {
        List<String> subordinates = new ArrayList<>();
        try {
            Connection connection = getDataBaseConnection();
            String query = "select surname from employee where id in (\n" +
                    "select s.subordinate_id from employee e inner join \n" +
                    "subordinates s on e.id = s.manager_id\n" +
                    "where e.surname LIKE '%s')";
            String customQuery = String.format(query, manager);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(customQuery);
            while (resultSet.next()) {
                subordinates.add(resultSet.getString("surname"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subordinates;
    }

    public static String getSuperriorManager(String subordinate) {
        int subordinateId = getEmployeeIdBySurname(subordinate);
        String superriorManager = null;
        try {
            Connection connection = getDataBaseConnection();
            String query = "select surname from employee where id = " +
                    "(select manager_id from subordinates where subordinate_id = '%s')";
            String customQuery = String.format(query, subordinateId);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(customQuery);
            while (resultSet.next()) {
                superriorManager = resultSet.getString("surname");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return superriorManager;
    }
}
