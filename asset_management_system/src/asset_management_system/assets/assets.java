
package asset_management_system.assets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class assets {
     private final StringProperty assetID;
    private final StringProperty assetName ;
    private final StringProperty assetCode;
    private final StringProperty assetDetails;
    private final StringProperty workerName;
    private final StringProperty workerID;
    private final StringProperty categoryID;
    private final StringProperty additionDate;
    private final StringProperty cost;
   

    //Constructor
    public assets(String assetID, String assetName, String assetCode, String assetDetails, String workerName, String workerID,String categoryID,String additionDate,String cost) {

        this.assetID= new SimpleStringProperty(assetID);
        this.assetName = new SimpleStringProperty(assetName);
        this.assetCode = new SimpleStringProperty(assetCode);
        this.assetDetails = new SimpleStringProperty(assetDetails);
        this.workerName = new SimpleStringProperty(workerName);
        this.workerID = new SimpleStringProperty(workerID);
        this.categoryID = new SimpleStringProperty(categoryID);
        this.additionDate = new SimpleStringProperty(additionDate);
        this.cost = new SimpleStringProperty(cost);
       

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

    public String getAssetDetails() {
        return assetDetails.get();
    }

    public String getWorkerName() {
        return workerName.get();
    }

    public String getWorkerID() {
        return workerID.get();
    }
    
    public String getCategoryID() {
        return categoryID.get();
    }
    
    public String getAdditionDate() {
        return additionDate.get();
    }
    
    public String getCost() {
        return cost.get();
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

    public void setAssetDetails(String Value) {
        assetDetails.set(Value);
    }

    public void setWorkerName(String Value) {
       workerName.set(Value);
    }

    public void setWorkerID(String Value) {
        workerID.set(Value);
    }
    
    public void setCategoryID(String Value) {
        categoryID.set(Value);
    }
     
    public void setAdditionDate(String Value) {
        additionDate.set(Value);
    }
    
     public void setCost(String Value) {
        cost.set(Value);
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

    public StringProperty assetDetailsProperty() {
        return assetDetails;
    }

    public StringProperty workerNameProperty() {
        return workerName;
    }

    public StringProperty workerIDProperty() {
        return workerID;
    }
    
    public StringProperty  categoryIDProperty() {
        return  categoryID;
    }
    
    public StringProperty additionDateProperty() {
        return additionDate;
    }
    
    public StringProperty costProperty() {
        return cost;
    }
    
    
    
}
