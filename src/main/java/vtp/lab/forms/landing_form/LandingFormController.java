package vtp.lab.forms.landing_form;

import vtp.lab.forms.manage_employee.ManageEmployeeForm;
import vtp.lab.forms.new_employee.EmployeeRegistration;
import vtp.lab.forms.titles.ManageTitles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingFormController implements ActionListener {

    LandingForm landingForm;

    public LandingFormController(LandingForm landingForm) {
        this.landingForm = landingForm;

        this.landingForm.getAddEmployeesButton().addActionListener(this);
        this.landingForm.getManageSubordinatesButton().addActionListener(this);
        this.landingForm.getManageResponsibilitiesButton().addActionListener(this);
        this.landingForm.getManageEmployeeButton().addActionListener(this);
        this.landingForm.getManageCompanyJobTitlesButton().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == landingForm.getAddEmployeesButton()) {
            EmployeeRegistration employeeRegistration = new EmployeeRegistration();
            employeeRegistration.formSetup();
        }

        if (e.getSource() == landingForm.getManageEmployeeButton()) {
            ManageEmployeeForm manageEmployeeForm = new ManageEmployeeForm();
            manageEmployeeForm.formSetup();
        }

        if (e.getSource() == landingForm.getManageCompanyJobTitlesButton()) {
            ManageTitles manageTitles = new ManageTitles();
            manageTitles.formSetup();
        }
    }
}
