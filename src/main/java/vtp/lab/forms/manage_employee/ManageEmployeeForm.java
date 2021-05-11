package vtp.lab.forms.manage_employee;

import vtp.lab.models.Employee;
import vtp.lab.services.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ManageEmployeeForm {

    private final static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    private JPanel rootPanel;
    private JPanel northSubPanel;
    private JPanel southSubPanel;
    private JPanel centerSubPanel;
    private JLabel headerLabel;
    private JPanel filtersSubPanel;
    private JPanel userPanel;
    private JLabel titleFilterLabel;
    private JButton updateUserButton;
    private JButton deleteUserButton;
    private JButton cancelUserButton;
    private JTextField surnameFilterTextField;
    private JTextField titleFilterTextField;
    private JLabel minSalaryLabel;
    private JTextField minSalaryTextField;
    private JLabel maxSalaryLabel;
    private JTextField maxSalaryTextField;
    private JButton applyFiltersButton;
    private JTable searchResultTable;
    private JLabel surnameLabel;
    private JComboBox titleComboBox;
    private JButton fetchFromDbButton;
    private JButton addEmployeeButton;
    private JPanel userDetailsPanel;
    private JTextField editFirstNameTextField;
    private JLabel editFirstNameLabel;
    private JTextField editLastNameTextField;
    private JLabel lastNameLabel;
    private JLabel editSurnameLabel;
    private JTextField editSurnameTextField;
    private JLabel editTitleLabel;
    private JComboBox editTitleComboBox;
    private JLabel editSalaryLabel;
    private JTextField editSalaryTextField;
    JFrame jFrame;

    public JButton getUpdateUserButton() {
        return updateUserButton;
    }

    public JTable getSearchResultTable() {
        return searchResultTable;
    }

    public JButton getAddEmployeeButton() {
        return addEmployeeButton;
    }

    public JButton getFetchFromDbButton() {
        return fetchFromDbButton;
    }

    public JButton getDeleteUserButton() {
        return deleteUserButton;
    }

    public JComboBox getTitleComboBox() {
        return titleComboBox;
    }

    public JTextField getSurnameFilterTextField() {
        return surnameFilterTextField;
    }

    public JTextField getTitleFilterTextField() {
        return titleFilterTextField;
    }

    public JTextField getMinSalaryTextField() {
        return minSalaryTextField;
    }

    public JTextField getMaxSalaryTextField() {
        return maxSalaryTextField;
    }

    public JButton getCancelUserButton() {
        return cancelUserButton;
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
        jFrame.setLocationRelativeTo(null);
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

    public void searchForEmployee(String surname, String title, String minSalary, String maxSalary) {
        if (surname.isEmpty() & title.isEmpty() & minSalary.isEmpty() & maxSalary.isEmpty()) {
            fillTableWithAllEmployees();
        } else {
            DefaultTableModel model = (DefaultTableModel) searchResultTable.getModel();
            ArrayList<Employee> employees = EmployeeService.searchEmployeesByParameters(surname, title, minSalary, maxSalary);
            fillModelWithEmployees(model, employees);
        }
    }

    public void fillTableWithAllEmployees() {
        DefaultTableModel model = (DefaultTableModel) searchResultTable.getModel();
        ArrayList<Employee> employees = EmployeeService.getAllEmployees();
        fillModelWithEmployees(model, employees);
    }

    public Employee getSelectedEmployee(){
        int selectedRow = searchResultTable.getSelectedRow();
        String firstName = searchResultTable.getValueAt(selectedRow,0).toString();
        String lastName = searchResultTable.getValueAt(selectedRow,1).toString();
        String surname = searchResultTable.getValueAt(selectedRow,2).toString();
        String title = searchResultTable.getValueAt(selectedRow,3).toString();
        Float salary = Float.valueOf(searchResultTable.getValueAt(selectedRow,4).toString());
        Employee employee = new Employee(firstName,lastName,surname,title,salary);
        return employee;
    }
    private void fillModelWithEmployees(DefaultTableModel model, ArrayList<Employee> employees) {

        model.setRowCount(0);
        for (Employee employee : employees) {
            model.addRow(new Object[]{
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSurname(),
                    employee.getTitle(),
                    decimalFormat.format(employee.getSalary())
            });
        }
    }

    public void setupEditValues() {
        Employee employee = getSelectedEmployee();
        this.editFirstNameTextField.setText(employee.getFirstName());
        this.editLastNameTextField.setText(employee.getLastName());
        this.editSurnameTextField.setText(employee.getSurname());
        this.editSalaryTextField.setText(String.valueOf(decimalFormat.format(employee.getSalary())));
        this.editTitleComboBox.getModel().setSelectedItem(employee.getTitle());
    }

    public Employee getUpdatedEmployee() {
        Employee employee = new Employee(
                editFirstNameTextField.getText(),
                editLastNameTextField.getText(),
                editSurnameTextField.getText(),
                editTitleComboBox.getSelectedItem().toString(),
                Float.valueOf(editSalaryTextField.getText()));
        return employee;
    }
}
