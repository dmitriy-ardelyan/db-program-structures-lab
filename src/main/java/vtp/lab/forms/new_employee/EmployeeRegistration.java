package vtp.lab.forms.new_employee;

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
    JFrame jFrame;

    public EmployeeRegistration(){
        EmployeeRegistrationController employeeRegistrationController = new EmployeeRegistrationController(this);
    }

    public void formSetup(){
        EmployeeRegistration employeeRegistration = new EmployeeRegistration();
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        employeeRegistration.firstNameTextField.setBorder(border);
        employeeRegistration.lastNameTextField.setBorder(border);
        employeeRegistration.parentNameTextField.setBorder(border);
        employeeRegistration.titleTextField.setBorder(border);
        employeeRegistration.salaryTextField.setBorder(border);

        jFrame = new JFrame("New Employee");
        jFrame.setContentPane(employeeRegistration.rootPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void closeForm(){
        jFrame.dispose();
    }

}
