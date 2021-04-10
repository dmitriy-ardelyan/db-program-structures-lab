package vtp.lab.forms.new_employee;

import vtp.lab.database.DataBaseService;
import vtp.lab.models.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }

        if (e.getSource() == this.employeeRegistration.saveButton) {
            try {
                Connection connection = DataBaseService.getDataBaseConnection();

                String query = "INSERT INTO employee (first_name,last_name,surname,title,salary) values('" +
                        this.employeeRegistration.firstNameTextField.getText() +
                        "','" + this.employeeRegistration.lastNameTextField.getText() +
                        "','" + this.employeeRegistration.surnameTextField.getText() +
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
