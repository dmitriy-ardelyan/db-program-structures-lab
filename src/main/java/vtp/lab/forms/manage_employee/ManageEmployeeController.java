package vtp.lab.forms.manage_employee;

import vtp.lab.forms.new_employee.EmployeeRegistration;
import vtp.lab.services.DataBaseService;
import vtp.lab.models.Employee;
import vtp.lab.services.EmployeeService;

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
        this.manageEmployeeForm.getFetchFromDbButton().addActionListener(this);
        this.manageEmployeeForm.getDeleteUserButton().addActionListener(this);
        this.manageEmployeeForm.getAddEmployeeButton().addActionListener(this);
        setValuesForTitlesDropDown();
        manageEmployeeForm.createTable();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == manageEmployeeForm.getApplyFiltersButton()) {
            String surname = manageEmployeeForm.getSurnameFilterTextField().getText();
            String title = manageEmployeeForm.getTitleComboBox().getSelectedItem().toString();
            String minSalary = manageEmployeeForm.getMinSalaryTextField().getText();
            String maxSalary = manageEmployeeForm.getMaxSalaryTextField().getText();
            manageEmployeeForm.searchForEmployee(surname, title, minSalary, maxSalary);
        }

        if (e.getSource() == manageEmployeeForm.getFetchFromDbButton()) {
            manageEmployeeForm.fillTableWithAllEmployees();
        }

        if (e.getSource() == manageEmployeeForm.getCancelUserButton()) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }

        if (e.getSource() == manageEmployeeForm.getDeleteUserButton()) {
            Employee employee = manageEmployeeForm.getSelectedEmployee();
            EmployeeService.deleteEmployee(employee);
            manageEmployeeForm.fillTableWithAllEmployees();
        }

        if (e.getSource() == manageEmployeeForm.getAddEmployeeButton()) {
            EmployeeRegistration employeeRegistration = new EmployeeRegistration();
            employeeRegistration.formSetup();
        }
    }

    private void setValuesForTitlesDropDown() {
        String[] topics = DataBaseService.getJobTitles().toArray(new String[0]);
        this.manageEmployeeForm.getTitleComboBox().addItem("");
        for (String str : topics) {
            this.manageEmployeeForm.getTitleComboBox().addItem(str);
        }
    }
}
