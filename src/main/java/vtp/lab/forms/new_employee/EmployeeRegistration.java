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
     JTextArea surnameTextField;
     JTextArea salaryTextField;
    private JPanel topHeaderSubPanel;
    JLabel topLabel;
    JComboBox titleDropDown;
    JFrame jFrame;

    public JComboBox getTitleDropDown() {
        return titleDropDown;
    }

    public EmployeeRegistration(){
        EmployeeRegistrationController employeeRegistrationController = new EmployeeRegistrationController(this);
    }

    public void formSetup(){
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        firstNameTextField.setBorder(border);
        lastNameTextField.setBorder(border);
        surnameTextField.setBorder(border);
        salaryTextField.setBorder(border);

        jFrame = new JFrame("New Employee");
        jFrame.setContentPane(rootPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setSize(550,350);
        jFrame.setVisible(true);
    }

    public void closeForm(){
        jFrame.dispose();
    }

}
