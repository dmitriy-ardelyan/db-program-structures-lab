package vtp.lab.forms.manage_employee;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Random;

public class ManageEmployee {
    private JPanel rootPanel;
    private JPanel testNavRootPanel;
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

    public ManageEmployee() {
        super();
        ManageEmployeeController manageEmployeeController = new ManageEmployeeController(this);
    }

    public void displayHeader(String headerString){
        this.headerLabel.setText(headerString);
    }

    public void formSetup(){
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        jFrame = new JFrame("Manage Employee");
        jFrame.setContentPane(this.rootPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JButton getApplyFiltersButton() {
        return applyFiltersButton;
    }
}
