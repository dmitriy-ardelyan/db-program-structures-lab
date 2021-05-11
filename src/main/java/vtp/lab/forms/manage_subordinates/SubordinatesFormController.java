package vtp.lab.forms.manage_subordinates;

import vtp.lab.services.DataBaseService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubordinatesFormController implements ActionListener {

    private SubordinatesForm subordinatesForm;

    public SubordinatesFormController(SubordinatesForm subordinatesForm) {
        this.subordinatesForm = subordinatesForm;
        this.subordinatesForm.getAddSubordinateComboBox().addActionListener(this);
        this.subordinatesForm.getManagerComboBox().addActionListener(this);
        this.subordinatesForm.getAddSubordinateButton().addActionListener(this);
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
    }
}
