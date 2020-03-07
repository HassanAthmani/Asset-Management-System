/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.workers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class workers {
    
    public final StringProperty workerName;
    public final StringProperty workerLastName ;
    public final StringProperty workerTell;
    public final StringProperty workerEmail;
    public final StringProperty workerNationalID;
    public final StringProperty department;
    public final StringProperty location;

    //Constructor
    public workers(String workerName, String workerLastName, String workerTell, String workerEmail, String workerNationalID, String department,String location) {

        this.workerName= new SimpleStringProperty(workerName);
        this.workerLastName = new SimpleStringProperty(workerLastName);
        this.workerTell = new SimpleStringProperty(workerTell);
        this.workerEmail = new SimpleStringProperty(workerEmail);
        this.workerNationalID = new SimpleStringProperty(workerNationalID);
        this.department = new SimpleStringProperty(department);
         this.location = new SimpleStringProperty(location);

    }

    //Getters
    public String getWorkerName() {
        return workerName.get();
    }

    public String getWorkerLastName() {
        return workerLastName.get();
    }

    public String getWorkerTell() {
        return workerTell.get();
    }

    public String getWorkerEmail() {
        return workerEmail.get();
    }

    public String getWorkerNationalID() {
        return workerNationalID.get();
    }

    public String getDepartment() {
        return department.get();
    }
    
     public String getLocation() {
        return location.get();
    }

    //Setters
    public void setWorkerName(String Value) {
        workerName.set(Value);
    }

    public void setWorkerLastName(String Value) {
        workerLastName.set(Value);
    }

    public void setWorkerTell(String Value) {
       workerTell.set(Value);
    }

    public void setWorkerEmail(String Value) {
        workerEmail.set(Value);
    }

    public void setWorkerNationalID(String Value) {
       workerNationalID.set(Value);
    }

    public void setDepartment(String Value) {
        department.set(Value);
    }
    
     public void setLocation(String Value) {
        location.set(Value);
    }

    //Property values
    public StringProperty workerNameProperty() {
        return workerName;
    }

    public StringProperty workerLastNameProperty() {
        return workerLastName;
    }

    public StringProperty workerTellProperty() {
        return workerTell;
    }

    public StringProperty workerEmailProperty() {
        return workerEmail;
    }

    public StringProperty workerNationalIDProperty() {
        return workerNationalID;
    }

    public StringProperty departmentProperty() {
        return department;
    }
    
    public StringProperty locationProperty() {
        return location;
    }
    
    
}
