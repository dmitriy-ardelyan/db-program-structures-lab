package vtp.lab.forms.titles;

import vtp.lab.services.DataBaseService;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ManageTitles {
    private JList titlesList;
    private JPanel rootPanel;
    private JTextField newTitleField;
    private JButton addTitleButton;
    private JButton deleteTitleButton;
    private JButton cancelButton;
    JFrame jFrame;

    public JTextField getNewTitleField() {
        return newTitleField;
    }

    public JButton getAddTitleButton() {
        return addTitleButton;
    }

    public JButton getDeleteTitleButton() {
        return deleteTitleButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JList getTitlesList() {
        return titlesList;
    }

    public ManageTitles() {
        ManageTitlesController manageTitlesController = new ManageTitlesController(this);
    }

    public void formSetup() {
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        titlesList.setBorder(border);

        jFrame = new JFrame("Manage Job Titles");
        jFrame.setContentPane(rootPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void getCompanyTitlesList(){
        String[] titlesListArray = DataBaseService.getJobTitles().toArray(new String[0]);
        titlesList.setListData(titlesListArray);
    }
}
