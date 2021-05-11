package vtp.lab.forms.manage_responsibilities;

import vtp.lab.services.DataBaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageResponsibilitiesController implements ActionListener {

    private ManageResponsibilities manageResponsibilities;

    public ManageResponsibilitiesController(ManageResponsibilities manageResponsibilities) {
        this.manageResponsibilities = manageResponsibilities;
        this.manageResponsibilities.getResponsibilities();
        this.manageResponsibilities.getCancelButton().addActionListener(this);
        this.manageResponsibilities.getDeleteButton().addActionListener(this);
        this.manageResponsibilities.getAddNewResponsibilityButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == manageResponsibilities.getCancelButton()) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }

        if (e.getSource() == manageResponsibilities.getAddNewResponsibilityButton()) {
            String responsibility = manageResponsibilities.getNewResponsibilityTextField().getText();
            int result = DataBaseService.insertNewResponsibility(responsibility);
            manageResponsibilities.getResponsibilities();
            if (result == 0) {
                JOptionPane.showMessageDialog(null,
                        "Responsibility already exists",
                        "Warning",
                        JOptionPane.PLAIN_MESSAGE);
            }
            manageResponsibilities.getNewResponsibilityTextField().setText("");
        }

        if (e.getSource() == manageResponsibilities.getDeleteButton()){
            String responsibility = manageResponsibilities.getResponsibilitiesList().getSelectedValue().toString();
            DataBaseService.deleteResponsibility(responsibility);
            manageResponsibilities.getResponsibilities();
        }
    }
}
