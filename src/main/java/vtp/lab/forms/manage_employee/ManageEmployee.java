package vtp.lab.forms.manage_employee;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

public class ManageEmployee {
    private JPanel rootPanel;
    private JPanel testNavRootPanel;
    private JPanel northSubPanel;
    private JPanel southSubPanel;
    private JPanel centerSubPanel;
    private JLabel headerLabel;
    private JPanel filtersSubPanel;
    private JPanel testBodyPanel;
    private JLabel nameFilterLabel;
    private JLabel titleFilterLabel;
    JLabel questionLabel;
    JButton answer2Button;
    JButton answer3Button;
    JButton answer4Button;
    JLabel questionInfoLabel;
    JButton startTestButton;
    JButton confirmAnswerButton;
    JButton nextQuestionButton;
    private JTextField nameFilterTextField;
    private JTextField titleFilterTextField;
    private JLabel minSalaryLabel;
    private JTextField minSalaryTextField;
    private JLabel maxSalaryLabel;
    private JTextField maxSalaryTextField;
    private JButton applyFiltersButton;
    JFrame jFrame;

    public ManageEmployee(){
        super();

    }

    public void displayHeader(String headerString){
        this.headerLabel.setText(headerString);
    }

    public void formSetup(){
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        jFrame = new JFrame("Manage Employee");
        jFrame.setContentPane(this.rootPanel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
