package vtp.lab.demo.form;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeEditorController implements ActionListener {

    EmployeeEditor employeeEditor;
    Employee employee;

    EmployeeEditorController(EmployeeEditor employeeEditor){
        this.employeeEditor = employeeEditor;
        this.employeeEditor.saveButton.addActionListener(this);
        this.employeeEditor.cancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.employeeEditor.cancelButton){
                JOptionPane.showMessageDialog(null,
                        "Cancel not implemented",
                        "Warning",
                        JOptionPane.PLAIN_MESSAGE);
        }

        //Save Question action.
        if(e.getSource()== this.employeeEditor.saveButton) {
            JOptionPane.showMessageDialog(null,
                    "Saving employee",
                    "Warning",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

}
