package vtp.lab.forms.manage_employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageEmployeeController implements ActionListener {

    private ManageEmployeeForm manageEmployeeForm;

    public ManageEmployeeController(ManageEmployeeForm manageEmployeeForm) {
        this.manageEmployeeForm = manageEmployeeForm;
        this.manageEmployeeForm.getApplyFiltersButton().addActionListener(this);
        this.manageEmployeeForm.getCancelUserButton().addActionListener(this);
        manageEmployeeForm.createTable();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == manageEmployeeForm.getApplyFiltersButton()){
            String nameFilters = manageEmployeeForm.getNameFilterLabel().getText();
        }

        if (e.getSource() == manageEmployeeForm.getCancelUserButton()) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }
    }
}
