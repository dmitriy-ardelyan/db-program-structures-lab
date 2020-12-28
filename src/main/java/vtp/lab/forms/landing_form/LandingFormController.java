package vtp.lab.forms.landing_form;

import vtp.lab.forms.new_employee.EmployeeRegistration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingFormController implements ActionListener {

    LandingForm landingForm;

    public LandingFormController(LandingForm landingForm) {
        this.landingForm = landingForm;
        this.landingForm.getManageEmployeesButton().addActionListener(this);
        this.landingForm.getManageSubordinatesButton().addActionListener(this);
        this.landingForm.getManageResponsibilitiesButton().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == landingForm.getManageEmployeesButton()){
            EmployeeRegistration employeeRegistration = new EmployeeRegistration();
            employeeRegistration.formSetup();
        }
    }
}
