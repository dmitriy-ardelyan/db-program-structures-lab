package vtp.lab.listeners;

import vtp.lab.forms.manage_employee.ManageEmployeeForm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CustomListSelectionListener implements ListSelectionListener {

    private ManageEmployeeForm manageEmployeeForm;

    public CustomListSelectionListener(ManageEmployeeForm manageEmployeeForm) {
        this.manageEmployeeForm = manageEmployeeForm;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        DefaultListSelectionModel model = (DefaultListSelectionModel) e.getSource();
        int selectionIndex = model.getMinSelectionIndex();
        if (selectionIndex >= 0) {
            manageEmployeeForm.setupEditValues();
        }
    }
}
