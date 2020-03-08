
package asset_management_system.profile;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class profile {
    


    
    private final StringProperty assetName;
    private final StringProperty assetCode ;
    private final StringProperty assigned_date;
    private final StringProperty workerName;
    private final StringProperty assetID;
    private final StringProperty transID;
    private final StringProperty phoneNo;
    private final StringProperty email;
    
    

    //Constructor
    public profile(String transID, String workerName,String phoneNo, String email,String assetID,String assetName, String assetCode, String assigned_date) {

        this.assetName= new SimpleStringProperty(assetName);
        this.assetCode = new SimpleStringProperty(assetCode);
        this.assigned_date = new SimpleStringProperty(assigned_date);
        this.workerName = new SimpleStringProperty(workerName);
        this.assetID = new SimpleStringProperty(assetID);
        this.transID = new SimpleStringProperty(transID);
        this.phoneNo = new SimpleStringProperty(phoneNo);
        this.email = new SimpleStringProperty(email);

    }

    //Getters
    public String getAssetName() {
        return assetName.get();
    }

    public String getAssetCode() {
        return assetCode.get();
    }

    public String getAssigned_date() {
        return assigned_date.get();
    }

    public String getWorkerName() {
        return workerName.get();
    }

    public String getAssetID() {
        return assetID.get();
    }

    public String getTransID() {
        return transID.get();
    }
    
     public String getPhoneNo() {
        return phoneNo.get();
    }
     
      public String getEmail() {
        return email.get();
    }

    //Setters
    public void setAssetName(String Value) {
        assetName.set(Value);
    }

    public void setAssetCode(String Value) {
        assetCode.set(Value);
    }

    public void setAssigned_date(String Value) {
        assigned_date.set(Value);
    }

    public void setWorkerName(String Value) {
        workerName.set(Value);
    }

    public void setAssetID(String Value) {
       assetID.set(Value);
    }

    public void setTransID(String Value) {
        transID.set(Value);
    }
    
    public void setPhoneNo(String Value) {
        phoneNo.set(Value);
    }
    
    public void setEmail(String Value) {
        email.set(Value);
    }

    //Property values
    public StringProperty assetNameProperty() {
        return assetName;
    }

    public StringProperty assetCodeProperty() {
        return assetCode;
    }

    public StringProperty assigned_dateProperty() {
        return assigned_date;
    }

    public StringProperty workerNameProperty() {
        return workerName;
    }

    public StringProperty assetIDProperty() {
        return assetID;
    }

    public StringProperty transIDProperty() {
        return transID;
    }
    
    public StringProperty  phoneNoProperty() {
        return  phoneNo;
    }
    
    public StringProperty  emailProperty() {
        return  email;
    }
    
}

  

    

