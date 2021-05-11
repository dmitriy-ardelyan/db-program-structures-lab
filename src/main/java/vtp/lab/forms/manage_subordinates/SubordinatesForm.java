package vtp.lab.forms.manage_subordinates;

import vtp.lab.services.DataBaseService;
import vtp.lab.services.EmployeeService;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubordinatesForm {
    private JLabel titleLabel;
    private JPanel mainPanel;
    private JLabel selectManagerLabel;
    private JComboBox managerComboBox;
    private JLabel addSubirdubateLabel;
    private JComboBox addSubordinateComboBox;
    private JScrollPane scrollPane;
    private JList subordinatesList;
    private JScrollPane responsibilitiesScrollPane;
    private JList responsibilitiesList;
    private JLabel addResponsibilityLabel;
    private JComboBox addResponsibilityComboBox;
    private JButton closeButton;
    private JButton addResponsibilityButton;
    private JButton addSubordinateButton;
    private JButton removeSubordinate;
    private JButton removeResponsibilityButton;
    JFrame jFrame;

    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getAddResponsibilityButton() {
        return addResponsibilityButton;
    }

    public JButton getAddSubordinateButton() {
        return addSubordinateButton;
    }

    public JComboBox getAddSubordinateComboBox() {
        return addSubordinateComboBox;
    }

    public JComboBox getManagerComboBox() {
        return managerComboBox;
    }

    public SubordinatesForm() {
        SubordinatesFormController subordinatesFormController = new SubordinatesFormController(this);
    }

    public void formSetup() {
        jFrame = new JFrame("Manage Subordinates");
        jFrame.setContentPane(this.mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(700,500);
        jFrame.setVisible(true);
    }

    public void fillData() {
        List<String> employees = DataBaseService.getAllEmployeesSurnames();
        setValuesForManagersDropDown();
    }

    public void setValuesForAddSubordinatesDropDown(String manager) {

        List<String> allEmployees = DataBaseService.getAllEmployeesSurnames();
        allEmployees.remove(manager);

        String superiorManager = DataBaseService.getSuperriorManager(manager);
        allEmployees.remove(superiorManager);

        List<String> subordinatesList = DataBaseService.getSubordinates(manager);
        for (String str : subordinatesList){
            allEmployees.remove(str);
        }

        String[] subordinates = allEmployees.toArray(new String[0]);
        addSubordinateComboBox.removeAllItems();
        addSubordinateComboBox.addItem("");
        for (String str : subordinates) {
            addSubordinateComboBox.addItem(str);
        }
    }

    private void setValuesForManagersDropDown() {
        String[] managers = DataBaseService.getAllEmployeesSurnames().toArray(new String[0]);
        managerComboBox.addItem("");
        for (String str : managers) {
            managerComboBox.addItem(str);
        }
    }

    public void displaySubordinates(String manager) {
        String[] subordinates = DataBaseService.getSubordinates(manager).toArray(new String[0]);
        subordinatesList.setListData(subordinates);
    }
}
