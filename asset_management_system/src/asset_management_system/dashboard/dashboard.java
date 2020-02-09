package asset_management_system.dashboard;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class dashboard {

    
    private final StringProperty assetName;
    private final StringProperty assetCode ;
    private final StringProperty additionDate;
    private final StringProperty workerName;
    private final StringProperty categoryID;
    private final StringProperty cost;

    //Constructor
    public dashboard(String assetName, String assetCode, String additionDate, String workerName, String categoryID, String cost) {

        this.assetName= new SimpleStringProperty(assetName);
        this.assetCode = new SimpleStringProperty(assetCode);
        this.additionDate = new SimpleStringProperty(additionDate);
        this.workerName = new SimpleStringProperty(workerName);
        this.categoryID = new SimpleStringProperty(categoryID);
        this.cost = new SimpleStringProperty(cost);

    }

    //Getters
    public String getAssetName() {
        return assetName.get();
    }

    public String getAssetCode() {
        return assetCode.get();
    }

    public String getadditionDate() {
        return additionDate.get();
    }

    public String getWorkerName() {
        return workerName.get();
    }

    public String getCategoryID() {
        return categoryID.get();
    }

    public String getCost() {
        return cost.get();
    }

    //Setters
    public void setAssetName(String Value) {
        assetName.set(Value);
    }

    public void setAssetCode(String Value) {
        assetCode.set(Value);
    }

    public void setAdditionDate(String Value) {
        additionDate.set(Value);
    }

    public void setWorkerName(String Value) {
        workerName.set(Value);
    }

    public void setCategoryID(String Value) {
       categoryID.set(Value);
    }

    public void setCost(String Value) {
        cost.set(Value);
    }

    //Property values
    public StringProperty assetNameProperty() {
        return assetName;
    }

    public StringProperty assetCodeProperty() {
        return assetCode;
    }

    public StringProperty additionDateProperty() {
        return additionDate;
    }

    public StringProperty workerNameProperty() {
        return workerName;
    }

    public StringProperty categoryIDProperty() {
        return categoryID;
    }

    public StringProperty costProperty() {
        return cost;
    }
    
}

  
