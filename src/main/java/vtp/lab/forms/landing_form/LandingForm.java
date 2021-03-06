package vtp.lab.forms.landing_form;

import javax.swing.*;

public class LandingForm {
    private JLabel headerTextField;
    private JButton addEmployeesButton;
    private JButton manageSubordinatesButton;
    private JButton manageResponsibilitiesButton;
    private JPanel rootPanel;
    private JButton manageEmployeeButton;
    private JButton manageCompanyJobTitlesButton;

    public JButton getManageCompanyJobTitlesButton() {
        return manageCompanyJobTitlesButton;
    }

    public JButton getAddEmployeesButton() {
        return addEmployeesButton;
    }

    public JButton getManageSubordinatesButton() {
        return manageSubordinatesButton;
    }

    public JButton getManageResponsibilitiesButton() {
        return manageResponsibilitiesButton;
    }

    public JButton getManageEmployeeButton() {
        return manageEmployeeButton;
    }

    public JPanel getRootPanel(){
        return rootPanel;
    }

    public LandingForm() {
        LandingFormController landingFormController = new LandingFormController(this);
    }
}
