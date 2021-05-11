package vtp.lab.forms.manage_responsibilities;

import vtp.lab.services.DataBaseService;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ManageResponsibilities {
    private JPanel rootPanel;
    private JList responsibilitiesList;
    private JLabel newResponsibilityLabel;
    private JTextField newResponsibilityTextField;
    private JButton addNewResponsibilityButton;
    private JButton deleteButton;
    private JButton cancelButton;
    JFrame jFrame;

    public JList getResponsibilitiesList() {
        return responsibilitiesList;
    }

    public JTextField getNewResponsibilityTextField() {
        return newResponsibilityTextField;
    }

    public JButton getAddNewResponsibilityButton() {
        return addNewResponsibilityButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public ManageResponsibilities() {
        ManageResponsibilitiesController manageResponsibilitiesController = new ManageResponsibilitiesController(this);
    }

    public void getResponsibilities() {
        String[] responsibilities = DataBaseService.getResponsibilities().toArray(new String[0]);
        responsibilitiesList.setListData(responsibilities);
    }

    public void formSetup() {
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        responsibilitiesList.setBorder(border);

        jFrame = new JFrame("Manage Responsibilities");
        jFrame.setContentPane(rootPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
