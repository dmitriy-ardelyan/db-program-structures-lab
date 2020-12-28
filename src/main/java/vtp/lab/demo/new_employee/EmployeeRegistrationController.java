package vtp.lab.demo.new_employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRegistrationController implements ActionListener {

    EmployeeRegistration employeeRegistration;
    Employee employee;

    EmployeeRegistrationController(EmployeeRegistration employeeRegistration) {
        this.employeeRegistration = employeeRegistration;
        this.employeeRegistration.saveButton.addActionListener(this);
        this.employeeRegistration.cancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.employeeRegistration.cancelButton) {
            JOptionPane.showMessageDialog(null,
                    "Cancel not implemented",
                    "Warning",
                    JOptionPane.PLAIN_MESSAGE);
        }

        if (e.getSource() == this.employeeRegistration.saveButton) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr_db",
                        "root",
                        "root521313");

                String query = "INSERT INTO employee values('" +
                        this.employeeRegistration.firstNameTextField.getText() +
                        "','" + this.employeeRegistration.lastNameTextField.getText() +
                        "','" + this.employeeRegistration.parentNameTextField.getText() +
                        "','" + this.employeeRegistration.titleTextField.getText() +
                        "','" + Double.valueOf(this.employeeRegistration.salaryTextField.getText()) + "')";

                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(query);
                if (result == 0) {
                    JOptionPane.showMessageDialog(null,
                            "Account already exists",
                            "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "User succesfully added",
                            "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
