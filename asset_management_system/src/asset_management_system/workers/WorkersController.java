
package asset_management_system.workers;

import asset_management_system.usedAlot.mover;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class WorkersController implements Initializable {
     public Connection connection;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public ObservableList<workers> data;
    
    @FXML
    public TableColumn<workers, String> firstname_clmn;

    @FXML
    public TableColumn<workers, String> secondName_clmn;

    @FXML
    public TableColumn<workers, String> phoneNumber_clmn;

    @FXML
    public TableColumn<workers, String> emailAddress_clmn;

    @FXML
    public TableColumn<workers, String> natID_clmn;

    @FXML
    public TableColumn<workers, String> department_clmn;

    @FXML
    public TableColumn<workers, String> location_clmn;
    
    @FXML
    public TableView<workers> tbleView;

    @FXML
    private TextField searchBox;

    @FXML
    private ChoiceBox checkBox;
    
     @FXML
    private JFXButton addWorker;
     
     private double xOffset = 0;
    private double yOffset = 0;
    
     @FXML
    private ImageView closeApp;
     
      @FXML
    private ImageView backToDashboard;
      
      
      
      
    @FXML
    void imageClicked(MouseEvent event) throws IOException {
         //you can use #onMousePressed or #orMouseClicked
         Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/dashboard/dashboard.fxml"));
           Scene newScene = new Scene(sceneFxml);

            //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
             mover movingWindow=new mover();
             movingWindow.moving(sceneFxml, window);

            //setting scene on stage
            window.setScene(newScene);
            
            window.show();
            window.centerOnScreen();

    }
    
    @FXML
    void minimizeIt (MouseEvent event){
        //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setIconified(true);
    }


    

    @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

    }


    @FXML
    void add(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader();
        Parent root=fxmlLoader.load(getClass().getResource("/asset_management_system/workers/addWorker/register.fxml").openStream());
        Scene scene=new Scene(root);
        
        stage.setScene(scene);
        
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
         stage.setResizable(false);
        stage.resizableProperty();
        stage.setOnCloseRequest(e -> {
                                    try {
                                         LoadDataFrmDB();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
        
         root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });  
         scene.setFill(Color.ALICEBLUE);         
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();
        

    }
    
     public void LoadDataFrmDB() throws SQLException {
        //DB connection details
        try {
            
            String dbName = "asset_management_system";
            String userName="root";
            String password="";
            Class.forName("com.mysql.jdbc.Driver");
            

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM worker_details");

            while (rs.next()) {
                //get string from db
                //GONNA USE AN IF STATEMENT HERE TO TRY MIXING WITH DATA FROM ANOTHER TABLE
                data.add(new workers(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
       firstname_clmn.setCellValueFactory(new PropertyValueFactory<>("workerName"));
       secondName_clmn.setCellValueFactory(new PropertyValueFactory<>("workerLastName"));
       phoneNumber_clmn.setCellValueFactory(new PropertyValueFactory<>("workerTell"));
       emailAddress_clmn.setCellValueFactory(new PropertyValueFactory<>("workerEmail"));
       natID_clmn.setCellValueFactory(new PropertyValueFactory<>("workerNationalID"));
       department_clmn.setCellValueFactory(new PropertyValueFactory<>("department"));
       location_clmn.setCellValueFactory(new PropertyValueFactory<>("location"));

        tbleView.setItems(null);
        tbleView.setItems(data);
        
        connection.close();

    }
     
     public void loada(String searchVal,String colmnVal){
         try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";
            String userName="root";
            String password="";            
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            
            
            try {
               
              ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `asset_management_system`.`worker_details` WHERE (`"+colmnVal+"` LIKE '%"+searchVal+"%')");
                
                while (rs.next()) {
                //get string from db
                //GONNA USE AN IF STATEMENT HERE TO TRY MIXING WITH DATA FROM ANOTHER TABLE
                data.add(new workers(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8)));
                }
                 //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
       firstname_clmn.setCellValueFactory(new PropertyValueFactory<>("workerName"));
       secondName_clmn.setCellValueFactory(new PropertyValueFactory<>("workerLastName"));
       phoneNumber_clmn.setCellValueFactory(new PropertyValueFactory<>("workerTell"));
       emailAddress_clmn.setCellValueFactory(new PropertyValueFactory<>("workerEmail"));
       natID_clmn.setCellValueFactory(new PropertyValueFactory<>("workerNationalID"));
       department_clmn.setCellValueFactory(new PropertyValueFactory<>("department"));
       location_clmn.setCellValueFactory(new PropertyValueFactory<>("location"));

        tbleView.setItems(null);
        tbleView.setItems(data);
            
            } catch (SQLException ex) {
                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }       
         catch (SQLException ex) {
                       Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                   }

       
         
     }
     
     
     
      @FXML
    void loadAll(MouseEvent event) {
        /* try {
             LoadDataFrmDB();
         } catch (SQLException ex) {
             Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
         }*/

    }
    
     @FXML
    void loadAll2(MouseEvent event) {
         /*try {
             LoadDataFrmDB();
         } catch (SQLException ex) {
             Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
         }*/


    }
    
       @FXML
    void sample(MouseEvent event) {
       /* try {
             LoadDataFrmDB();
         } catch (SQLException ex) {
             Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
         }*/

    }

     
     
   
    
     

    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
         try {
             LoadDataFrmDB();
         } catch (SQLException ex) {
             Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         
        checkBox.getItems().addAll("first name","second name","phone number","email","national ID","location","department");
        checkBox.setValue("first name");
        //String checkBoxValue=checkBox.getValue().toString();
       
       // System.out.print(checkBoxValue);
       
       if (searchBox.getText().isEmpty()) {

            try {
                LoadDataFrmDB();
            } catch (SQLException ex) {
                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } 
        
        ///THA MAGIC HAPPENS
         searchBox.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,String newValue) -> {
           
           if(oldValue != newValue ){                              
               
               if(!searchBox.getText().isEmpty()){
                   
                   String BoxValue=checkBox.getValue().toString();
                   
                   if("first name".equals(BoxValue)){
                       String clmn="workerName";                      
                       loada( searchBox.getText(),clmn);
                   }
                   else if("second name".equals(BoxValue)){
                       String clmn="workerLastName";                      
                       loada( searchBox.getText(),clmn);
                   }
                   else if("phone number".equals(BoxValue)){
                       String clmn="workerTell";                      
                       loada( searchBox.getText(),clmn);
                   }
                   else if("email".equals(BoxValue)){
                       String clmn="workerEmail";                      
                       loada( searchBox.getText(),clmn);
                   }
                   else if("national ID".equals(BoxValue)){
                       String clmn="workerNationalID";                      
                       loada( searchBox.getText(),clmn);
                   }
                   else if("department".equals(BoxValue)){
                       String clmn="department";                      
                       loada( searchBox.getText(),clmn);
                   }
                   else if("location".equals(BoxValue)){
                       String clmn="location";                      
                       loada( searchBox.getText(),clmn);
                   }        
               
           }
         
               else {    ///( searchBox.getText().isEmpty() )
                   try {
                       LoadDataFrmDB();
                       // System.out.print("no empty search");
                   } catch (SQLException ex) {
                       Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
           
           
       });
         
         
         
        
        
    }    
    
}
