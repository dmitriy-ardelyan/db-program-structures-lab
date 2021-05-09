package vtp.lab.forms.new_employee;

import vtp.lab.services.DataBaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRegistrationController implements ActionListener {

    EmployeeRegistration employeeRegistration;

    EmployeeRegistrationController(EmployeeRegistration employeeRegistration) {
        this.employeeRegistration = employeeRegistration;
        this.employeeRegistration.saveButton.addActionListener(this);
        this.employeeRegistration.cancelButton.addActionListener(this);
        setValuesForTitlesDropDown();
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

                String firstName = this.employeeRegistration.firstNameTextField.getText();
                String lastName = this.employeeRegistration.lastNameTextField.getText();
                String surname = this.employeeRegistration.surnameTextField.getText();
                String title = this.employeeRegistration.titleDropDown.getSelectedItem().toString();
                Float salary = Float.valueOf(this.employeeRegistration.salaryTextField.getText());

                String queryBlueprint = "insert into employee(first_name,last_name,surname,title,salary)\n" +
                        "values('%s','%s','%s',(select id from titles_list where title='%s'),%f)";
                String query = String.format(queryBlueprint, firstName, lastName, surname, title, salary);

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
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void setValuesForTitlesDropDown() {
        String[] topics = DataBaseService.getJobTitles().toArray(new String[0]);
        for (String str : topics) {
            this.employeeRegistration.getTitleDropDown().addItem(str);
        }
    }
}
