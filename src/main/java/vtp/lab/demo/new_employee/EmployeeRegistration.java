package vtp.lab.demo.new_employee;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EmployeeRegistration {
    JPanel rootPanel;
    private JPanel westLabelsPanel;
    JPanel eastFieldsPanel;
    private JPanel southButtonsPanel;
    private JPanel northHeaderPanel;
    JButton saveButton;
    JButton cancelButton;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel parentNameLabel;
    private JLabel titleLabel;
    private JLabel salaryLabel;
     JTextArea firstNameTextField;
     JTextArea lastNameTextField;
     JTextArea parentNameTextField;
     JTextArea titleTextField;
     JTextArea salaryTextField;
    private JPanel topHeaderSubPanel;
    JLabel topLabel;

    EmployeeRegistration(){
        EmployeeRegistrationController employeeRegistrationController = new EmployeeRegistrationController(this);
    }

    public static void main(String[] args) {
        formSetup();
    }

    public static void formSetup(){
        EmployeeRegistration employeeRegistration = new EmployeeRegistration();
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        employeeRegistration.firstNameTextField.setBorder(border);
        employeeRegistration.lastNameTextField.setBorder(border);
        employeeRegistration.parentNameTextField.setBorder(border);
        employeeRegistration.titleTextField.setBorder(border);
        employeeRegistration.salaryTextField.setBorder(border);
        JFrame frame = new JFrame("Questions Editor");
        frame.setContentPane(employeeRegistration.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
