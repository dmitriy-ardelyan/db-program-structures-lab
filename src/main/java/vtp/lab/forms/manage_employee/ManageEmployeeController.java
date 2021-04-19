package vtp.lab.forms.manage_employee;

import vtp.lab.models.Employee;

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

        if (e.getSource() == manageEmployeeForm.getApplyFiltersButton()) {
            String surname = manageEmployeeForm.getSurnameFilterTextField().getText();
            String title = manageEmployeeForm.getTitleFilterTextField().getText();
            String minSalary = manageEmployeeForm.getMinSalaryTextField().getText();
            String maxSalary = manageEmployeeForm.getMaxSalaryTextField().getText();
            manageEmployeeForm.searchForEmployee(surname, title, minSalary, maxSalary);
        }

        if (e.getSource() == manageEmployeeForm.getCancelUserButton()) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }
    }
}
