package asset_management_system.withUsers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class withUsers {
      private final StringProperty assetID;
    private final StringProperty assetName ;
    private final StringProperty assetCode;
    private final StringProperty workerTell;
    private final StringProperty workerName;
    private final StringProperty workerID;
    private final StringProperty workerEmail;
    private final StringProperty transID;
    private final StringProperty assignedDate;
    private final StringProperty assignedBy;
   

    //Constructor
    public withUsers(String transID,String workerID, String workerName, String workerTell,String workerEmail, String assetID, String assetName, String assetCode, String assignedDate,String assignedBy) {

        this.assetID= new SimpleStringProperty(assetID);
        this.assetName = new SimpleStringProperty(assetName);
        this.assetCode = new SimpleStringProperty(assetCode);
        this.workerTell = new SimpleStringProperty(workerTell);
        this.workerName = new SimpleStringProperty(workerName);
        this.workerID = new SimpleStringProperty(workerID);
        this.workerEmail = new SimpleStringProperty(workerEmail);
        this.transID = new SimpleStringProperty(transID);
        this.assignedDate = new SimpleStringProperty(assignedDate);
        this.assignedBy = new SimpleStringProperty(assignedBy);
       

    }

    //Getters
    public String getAssetID() {
        return assetID.get();
    }

    public String getAssetName() {
        return assetName.get();
    }

    public String getAssetCode() {
        return assetCode.get();
    }

    public String getWorkerTell() {
        return workerTell.get();
    }

    public String getWorkerName() {
        return workerName.get();
    }

    public String getWorkerID() {
        return workerID.get();
    }
    
    public String getWorkerEmail() {
        return workerEmail.get();
    }
    
    public String getTransID() {
        return transID.get();
    }
    
    public String getAssignedDate() {
        return assignedDate.get();
    }
    
    public String getAssignedBy() {
        return assignedBy.get();
    }
    
     

    //Setters
    public void setAssetID(String Value) {
        assetID.set(Value);
    }

    public void setAssetName(String Value) {
        assetName.set(Value);
    }

    public void setAssetCode(String Value) {
       assetCode.set(Value);
    }

    public void setWorkerTell(String Value) {
        workerTell.set(Value);
    }

    public void setWorkerName(String Value) {
       workerName.set(Value);
    }

    public void setWorkerID(String Value) {
        workerID.set(Value);
    }
    
    public void setWorkerEmail(String Value) {
        workerEmail.set(Value);
    }
     
    public void setTransID(String Value) {
        transID.set(Value);
    }
    
     public void setAssignedDate(String Value) {
        assignedDate.set(Value);
    }  
     
     public void setAssignedBy(String Value) {
        assignedBy.set(Value);
    }  
    
     

    //Property values
    public StringProperty assetIDProperty() {
        return assetID;
    }

    public StringProperty assetNameProperty() {
        return assetName;
    }

    public StringProperty assetCodeProperty() {
        return assetCode;
    }

    public StringProperty workerTellProperty() {
        return workerTell;
    }

    public StringProperty workerNameProperty() {
        return workerName;
    }

    public StringProperty workerIDProperty() {
        return workerID;
    }
    
    public StringProperty  workerEmailProperty() {
        return  workerEmail;
    }
    
    public StringProperty transIDProperty() {
        return transID;
    }
    
    public StringProperty assignedDateProperty() {
        return assignedDate;
    }
    
    public StringProperty assignedByProperty() {
        return assignedBy;
    }
    
    
}
