package vtp.lab.forms.new_employee;

import vtp.lab.forms.BaseFormController;
import vtp.lab.models.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRegistrationController extends BaseFormController {

    EmployeeRegistration employeeRegistration;
    Employee employee;


    EmployeeRegistrationController(EmployeeRegistration employeeRegistration) {
        this.employeeRegistration = employeeRegistration;
        this.employeeRegistration.saveButton.addActionListener(this);
        this.employeeRegistration.cancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.employeeRegistration.cancelButton) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }

        if (e.getSource() == this.employeeRegistration.saveButton) {
            try {
                Connection connection = DriverManager.getConnection(dataBaseConnectionString, user, password);

                String query = "INSERT INTO employee (first_name,last_name,parent_name,title,salary) values('" +
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
