package vtp.lab.forms.manage_subordinates;

import vtp.lab.listeners.ManageResponsibilitiesSelectionListener;
import vtp.lab.services.DataBaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubordinatesFormController implements ActionListener {

    private SubordinatesForm subordinatesForm;

    public SubordinatesFormController(SubordinatesForm subordinatesForm) {
        this.subordinatesForm = subordinatesForm;
        this.subordinatesForm.getAddSubordinateComboBox().addActionListener(this);
        this.subordinatesForm.getManagerComboBox().addActionListener(this);
        this.subordinatesForm.getAddSubordinateButton().addActionListener(this);
        this.subordinatesForm.getRemoveSubordinateButton().addActionListener(this);
        this.subordinatesForm.getAddResponsibilityButton().addActionListener(this);
        this.subordinatesForm.getCloseButton().addActionListener(this);
        this.subordinatesForm.getRemoveResponsibilityButton().addActionListener(this);

        ManageResponsibilitiesSelectionListener manageResponsibilitiesSelectionListener =
                new ManageResponsibilitiesSelectionListener(this.subordinatesForm);
        this.subordinatesForm
                .getSubordinatesList()
                .getSelectionModel()
                .addListSelectionListener(manageResponsibilitiesSelectionListener);

        this.subordinatesForm.fillData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == subordinatesForm.getManagerComboBox()) {
            String manager = subordinatesForm.getManagerComboBox().getSelectedItem().toString();
            if (!manager.isEmpty()) {
                subordinatesForm.displaySubordinates(manager);
                subordinatesForm.setValuesForAddSubordinatesDropDown(manager);
            }
        }

        if (e.getSource() == subordinatesForm.getAddSubordinateButton()) {
            String newSubordinate = subordinatesForm.getAddSubordinateComboBox().getSelectedItem().toString();
            String manager = subordinatesForm.getManagerComboBox().getSelectedItem().toString();
            DataBaseService.setSubordinateForManager(newSubordinate, manager);
            subordinatesForm.setValuesForAddSubordinatesDropDown(manager);
            subordinatesForm.displaySubordinates(manager);
        }

        if (e.getSource() == subordinatesForm.getRemoveSubordinateButton()) {
            String subordinateToRemove = subordinatesForm.getSubordinatesList().getSelectedValue().toString();
            String manager = subordinatesForm.getManagerComboBox().getSelectedItem().toString();
            DataBaseService.deleteSubordinateOfManager(subordinateToRemove, manager);
            subordinatesForm.setValuesForAddSubordinatesDropDown(manager);
            subordinatesForm.displaySubordinates(manager);
        }

        if (e.getSource() == subordinatesForm.getAddResponsibilityButton()) {
            String responsibilityToAdd = subordinatesForm.getAddResponsibilityComboBox().getSelectedItem().toString();
            String employee = subordinatesForm.getSubordinatesList().getSelectedValue().toString();
            DataBaseService.addResponsibilityToEmployee(responsibilityToAdd, employee);
            subordinatesForm.setupSubordinateResponsibilities();
            subordinatesForm.setupAddResponsibilityDropDownValues();
        }

        if (e.getSource() == subordinatesForm.getRemoveResponsibilityButton()) {
            String responsibilityToRemove = subordinatesForm.getResponsibilitiesList().getSelectedValue().toString();
            String employee = subordinatesForm.getSubordinatesList().getSelectedValue().toString();
            DataBaseService.deleteResponsibilityFromEmployee(responsibilityToRemove, employee);
            subordinatesForm.setupSubordinateResponsibilities();
            subordinatesForm.setupAddResponsibilityDropDownValues();
        }

        if (e.getSource() == subordinatesForm.getCloseButton()) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }
    }
}
