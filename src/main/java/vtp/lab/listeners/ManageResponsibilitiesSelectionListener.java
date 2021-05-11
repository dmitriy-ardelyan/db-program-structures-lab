package vtp.lab.listeners;

import vtp.lab.forms.manage_subordinates.SubordinatesForm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManageResponsibilitiesSelectionListener implements ListSelectionListener {

    private SubordinatesForm subordinatesForm;

    public ManageResponsibilitiesSelectionListener(SubordinatesForm subordinatesForm) {
        this.subordinatesForm = subordinatesForm;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

            DefaultListSelectionModel model = (DefaultListSelectionModel) e.getSource();
            int selectionIndex = model.getMinSelectionIndex();
            if (selectionIndex >= 0) {
                subordinatesForm.setupSubordinateResponsibilities();
                subordinatesForm.setupAddResponsibilityDropDownValues();
            }
    }
}
