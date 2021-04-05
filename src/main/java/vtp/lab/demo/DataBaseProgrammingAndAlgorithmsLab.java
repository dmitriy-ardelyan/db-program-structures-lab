package vtp.lab.demo;

import vtp.lab.forms.landing_form.LandingForm;
import javax.swing.*;

public class DataBaseProgrammingAndAlgorithmsLab {
    public static void main(String[] args) {
        LandingForm landingForm = new LandingForm();
        JFrame frame = new JFrame("Human Resources Management System");
        frame.setContentPane(landingForm.getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700,700);
        frame.setVisible(true);
    }
}
