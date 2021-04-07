package vtp.lab.forms.landing_form;

import vtp.lab.forms.BaseFormController;
import vtp.lab.forms.manage_employee.ManageEmployee;
import vtp.lab.forms.new_employee.EmployeeRegistration;

import java.awt.event.ActionEvent;

public class LandingFormController extends BaseFormController {

    LandingForm landingForm;

    public LandingFormController(LandingForm landingForm) {
        this.landingForm = landingForm;
        this.landingForm.getAddEmployeesButton().addActionListener(this);
        this.landingForm.getManageSubordinatesButton().addActionListener(this);
        this.landingForm.getManageResponsibilitiesButton().addActionListener(this);
        this.landingForm.getManageEmployeeButton().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == landingForm.getAddEmployeesButton()){
            EmployeeRegistration employeeRegistration = new EmployeeRegistration();
            employeeRegistration.formSetup();
        }

        if (e.getSource() == landingForm.getManageEmployeeButton()){
            ManageEmployee manageEmployee = new ManageEmployee();
            manageEmployee.formSetup();
        }
    }
}
