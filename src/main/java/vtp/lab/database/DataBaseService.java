package vtp.lab.database;

import main.java.vtp.lab.PropertiesLoader;
import main.java.vtp.lab.PropertiesSupplier;
import vtp.lab.models.Employee;

import javax.swing.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

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
            String query = "SELECT first_name, last_name, surname, tts.title, salary FROM hr_db.employee as e " +
                    "inner join hr_db.titles_list as tts on e.title = tts.id";
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
}
