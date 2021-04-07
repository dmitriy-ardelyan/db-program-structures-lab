package vtp.lab.forms;

import main.java.vtp.lab.PropertiesLoader;
import main.java.vtp.lab.PropertiesSupplier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class BaseFormController implements ActionListener {

    protected String dataBaseConnectionString;
    protected String user;
    protected String password;

    public BaseFormController() {
        PropertiesLoader.loadGlobalProperties(this.getClass(),"db.properties");
        if (Objects.isNull(dataBaseConnectionString)){
            PropertiesLoader.loadGlobalProperties(this.getClass(),"db.properties");
        }
        dataBaseConnectionString = PropertiesSupplier.getProperty("connection.string");
        user = PropertiesSupplier.getProperty("user");
        password = PropertiesSupplier.getProperty("password");
    }

    public void actionPerformed(ActionEvent e) {

    }
}
