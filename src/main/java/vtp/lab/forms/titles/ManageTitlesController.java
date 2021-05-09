package vtp.lab.forms.titles;

import vtp.lab.services.DataBaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageTitlesController implements ActionListener {

    public ManageTitles manageTitles;

    public ManageTitlesController(ManageTitles manageTitles) {
        this.manageTitles = manageTitles;
        this.manageTitles.getCompanyTitlesList();
        this.manageTitles.getAddTitleButton().addActionListener(this);
        this.manageTitles.getCancelButton().addActionListener(this);
        this.manageTitles.getDeleteTitleButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == manageTitles.getCancelButton()) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }

        if (e.getSource() == manageTitles.getAddTitleButton()) {
            String newTitle = manageTitles.getNewTitleField().getText();
            int result = DataBaseService.insertNewJobTitle(newTitle);
            manageTitles.getCompanyTitlesList();
            if (result == 0) {
                JOptionPane.showMessageDialog(null,
                        "Title already exists",
                        "Warning",
                        JOptionPane.PLAIN_MESSAGE);
            }
            manageTitles.getNewTitleField().setText("");
        }

        if (e.getSource() == manageTitles.getDeleteTitleButton()){
            String titleForDeletion = manageTitles.getTitlesList().getSelectedValue().toString();
            DataBaseService.deleteJobTitle(titleForDeletion);
            manageTitles.getCompanyTitlesList();
        }
    }
}
