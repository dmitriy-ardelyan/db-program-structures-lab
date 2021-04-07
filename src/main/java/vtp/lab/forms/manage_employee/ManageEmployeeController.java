package vtp.lab.forms.manage_employee;

import main.java.vtp.lab.PropertiesLoader;
import vtp.lab.forms.BaseFormController;
import vtp.lab.forms.landing_form.LandingForm;
import vtp.lab.forms.new_employee.EmployeeRegistration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ManageEmployeeController extends BaseFormController {

    ManageEmployee manageEmployeeForm;

    public ManageEmployeeController(ManageEmployee manageEmployeeForm) {
        this.manageEmployeeForm = manageEmployeeForm;
        this.manageEmployeeForm.getApplyFiltersButton().addActionListener(this);

        PropertiesLoader.loadGlobalProperties(this.getClass(),"db.properties");
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == manageEmployeeForm.getApplyFiltersButton()){

            //Select * from hr_db.employee
            //Connection connection = DriverManager.getConnection(dataBaseConnectionString, user, password);

            /*String query = "INSERT INTO employee (first_name,last_name,parent_name,title,salary) values('" +
                    this.employeeRegistration.firstNameTextField.getText() +
                    "','" + this.employeeRegistration.lastNameTextField.getText() +
                    "','" + this.employeeRegistration.parentNameTextField.getText() +
                    "','" + this.employeeRegistration.titleTextField.getText() +
                    "','" + Double.valueOf(this.employeeRegistration.salaryTextField.getText()) + "')";*/

            //Statement statement = connection.createStatement();


            /*JOptionPane.showMessageDialog(null,
                    "WIP",
                    "WIP",
                    JOptionPane.PLAIN_MESSAGE);*/
        }
    }
}
