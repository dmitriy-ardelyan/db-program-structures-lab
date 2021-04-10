package vtp.lab.forms.manage_employee;

import vtp.lab.models.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ManageEmployeeForm {
    private JPanel rootPanel;
    private JPanel northSubPanel;
    private JPanel southSubPanel;
    private JPanel centerSubPanel;
    private JLabel headerLabel;
    private JPanel filtersSubPanel;
    private JPanel userPanel;
    private JLabel nameFilterLabel;
    private JLabel titleFilterLabel;
    JButton updateUserButton;
    JButton deleteUserButton;
    JButton cancelUserButton;
    private JTextField nameFilterTextField;
    private JTextField titleFilterTextField;
    private JLabel minSalaryLabel;
    private JTextField minSalaryTextField;
    private JLabel maxSalaryLabel;
    private JTextField maxSalaryTextField;
    private JButton applyFiltersButton;
    private JTable searchResultTable;
    JFrame jFrame;

    public JButton getCancelUserButton() {
        return cancelUserButton;
    }

    public JLabel getNameFilterLabel() {
        return nameFilterLabel;
    }

    public JButton getApplyFiltersButton() {
        return applyFiltersButton;
    }

    public ManageEmployeeForm() {
        super();
        ManageEmployeeController manageEmployeeController = new ManageEmployeeController(this);
    }

    public void displayHeader(String headerString) {
        this.headerLabel.setText(headerString);
    }

    public void formSetup() {
        jFrame = new JFrame("Manage Employee");
        jFrame.setContentPane(this.rootPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void createTable() {
        searchResultTable.setModel(new DefaultTableModel(
                null,
                new String[]{"First Name", "Last Name", "Surname", "Title", "Salary"}
        ));
        fillTableWithAllEmployees();
    }

    private void fillTableWithAllEmployees(){
        DefaultTableModel model = (DefaultTableModel) searchResultTable.getModel();
        ArrayList<Employee> employees = Employee.getAllEmployees();

        model.setRowCount(0);
        for (Employee employee: employees){
            model.addRow(new Object[]{
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSurname(),
                    employee.getTitle(),
                    employee.getSalary()
            });
        }
    }
}
