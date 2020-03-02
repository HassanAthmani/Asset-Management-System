
package asset_management_system.workers;

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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class WorkersController implements Initializable {
     public Connection connection;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private ObservableList<workers> data;
    
    @FXML
    private TableColumn<workers, String> firstname_clmn;

    @FXML
    private TableColumn<workers, String> secondName_clmn;

    @FXML
    private TableColumn<workers, String> phoneNumber_clmn;

    @FXML
    private TableColumn<workers, String> emailAddress_clmn;

    @FXML
    private TableColumn<workers, String> natID_clmn;

    @FXML
    private TableColumn<workers, String> department_clmn;

    @FXML
    private TableColumn<workers, String> location_clmn;
    
    @FXML
    private TableView<workers> tbleView;

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
     
     
    public void reloader(){
        java.util.TimerTask task = new java.util.TimerTask() {
        int prevCount = 0; // you can declare it static
        @Override
        public void run() {
            String dbName = "asset_management_system";
            String userName="root";
            String password="";
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
            }            

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            } catch (SQLException ex) {
                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ResultSet rs = connection.prepareStatement("Select Count(*) FROM worker_details").executeQuery();
                int count = rs.getInt(1);
                System.out.println("Count diff:"+ (prevCount-count));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };
    java.util.Timer timer = new java.util.Timer(true);// true to run timer as daemon thread
    timer.schedule(task, 0, 1000);// Run task every 5 second
    try {
        Thread.sleep(5000); // Cancel task after 1 minute.
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
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
         
         reloader();
        
    }    
    
}