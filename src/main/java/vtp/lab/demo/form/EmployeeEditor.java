package vtp.lab.demo.form;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EmployeeEditor {
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

    EmployeeEditor(){
        super();
        EmployeeEditorController employeeEditorController = new EmployeeEditorController(this);
    }

    public static void main(String[] args) {
        formSetup();
    }

    public static void formSetup(){
        EmployeeEditor employeeEditor = new EmployeeEditor();
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        employeeEditor.firstNameTextField.setBorder(border);
        employeeEditor.lastNameTextField.setBorder(border);
        employeeEditor.parentNameTextField.setBorder(border);
        employeeEditor.titleTextField.setBorder(border);
        employeeEditor.salaryTextField.setBorder(border);
        JFrame frame = new JFrame("Questions Editor");
        frame.setContentPane(employeeEditor.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public boolean isQuestionFormFilled(){
        boolean result = true;
        if(this.firstNameTextField.getText().length()==0) result = false;
        if(this.lastNameTextField.getText().length()==0) result = false;
        if(this.parentNameTextField.getText().length()==0) result = false;
        if(this.titleTextField.getText().length()==0) result = false;
        if(this.salaryTextField.getText().length()==0) result = false;
        return result;
    }

}
