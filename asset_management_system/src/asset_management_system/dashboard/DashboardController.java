package asset_management_system.dashboard;

import asset_management_system.usedAlot.json_read;
import com.jfoenix.controls.JFXButton;
import java.io.FileNotFoundException;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;


public class DashboardController implements Initializable {
    public Connection connection;
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private ObservableList<dashboard> data;


    
     @FXML
    private VBox vbox;

    @FXML
    private VBox assets_pane;

    @FXML
    private Label assetsLbl;
    
    @FXML
    private VBox totalCost_pane;

    @FXML
    private Label totalCostLbl;
    
    @FXML
    private VBox workers_pane;

    @FXML
    private Label workersLbl;
    
    @FXML
    private VBox withUsers_pane;

    @FXML
    private Label withWorkersLbl;
    
     @FXML
    private AnchorPane pieChart_pane;

    @FXML
    private PieChart catChart;

    @FXML
    private AnchorPane tableView_pane;
    
    @FXML
    private JFXButton profile_btn;
    
     @FXML
    private JFXButton workers_btn;
     
     @FXML
    private JFXButton assetWorkers_btn;
     
      @FXML
    private JFXButton asset_btn;
      
       @FXML
    private TableView<dashboard> tbleView_dash;

    @FXML
    private TableColumn<dashboard, String> assetName_clmn;

    @FXML
    private TableColumn<dashboard, String> assetCode_clmn;

    @FXML
    private TableColumn<dashboard, String> addedOn_clmn;

    @FXML
    private TableColumn<dashboard, String> addedBy_clmn;

    @FXML
    private TableColumn<dashboard, String> category_clmn;

    @FXML
    private TableColumn<dashboard, String> cost_clmn;
    
    @FXML
    private Label assets_rst;
    
      @FXML
    private Label my_profile;
      
      @FXML
    private Label workers_rst;
      
      @FXML
    private Label withWorkers_rst;
      
    @FXML
    private ImageView backToDashboard;
    
    @FXML
    private ImageView closeApp;
    
    @FXML
    void ToProfile(ActionEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
         Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/profile/profile.fxml"));
           Scene newScene = new Scene(sceneFxml);
           
           newScene.getStylesheets().add("/asset_management_system/css/tabpane.css"); 

            //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //setting scene on stage
            window.setScene(newScene);
            window.show();
            window.centerOnScreen();

    }
     
     @FXML
     public void closeAppWindow(MouseEvent event){
         //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            window.close();
     }

    @FXML
    void imageClicked(MouseEvent event) throws IOException {
         //you can use #onMousePressed or #orMouseClicked
         Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/login/login.fxml"));
           Scene newScene = new Scene(sceneFxml);

            //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //setting scene on stage
            window.setScene(newScene);
            window.show();
            window.centerOnScreen();

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
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM assets");

            while (rs.next()) {
                //get string from db
                //GONNA USE AN IF STATEMENT HERE TO TRY MIXING WITH DATA FROM ANOTHER TABLE
                data.add(new dashboard(rs.getString(2), rs.getString(3), rs.getString(8), rs.getString(5), rs.getString(7), rs.getString(9)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        assetName_clmn.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        assetCode_clmn.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        addedOn_clmn.setCellValueFactory(new PropertyValueFactory<>("additionDate"));
        addedBy_clmn.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        category_clmn.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        cost_clmn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        tbleView_dash.setItems(null);
        tbleView_dash.setItems(data);

    }
     
  ////////  https://www.mysqltutorial.org/mysql-count/
     public void countData() throws SQLException, IOException, FileNotFoundException, ParseException{
          //DB connection details
        try {
            String dbName = "asset_management_system";
            String userName="root";
            String password="";
            Class.forName("com.mysql.jdbc.Driver");
            

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            json_read jsonReader=new json_read();
            String id=jsonReader.profile_id();
            
      
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM assets");
            ResultSet rs1 = connection.createStatement().executeQuery("SELECT workerName FROM worker_details WHERE workerID= "+id);
            ResultSet rs2 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM worker_details");
            ResultSet rs3 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM assigned_asset");
            
             while (rs.next()) {                               
                assets_rst.setText(rs.getString(1));
            }
             while (rs1.next()) {                               
                my_profile.setText(rs1.getString(1));
            }
             while (rs2.next()) {                               
                workers_rst.setText(rs2.getString(1));
            }
             while (rs3.next()) {                               
                withWorkers_rst.setText(rs3.getString(1));
            }
             connection.close();
    
            
            

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }
     }   
     
     public void styling(){
         // System.out.print(assets_rst.getText());
        String f="-fx-font-family: 'Bebas Neue', cursive; -fx-font-size: 20;";
        //NORMAL LABELS
       assetsLbl.setStyle(f);
       totalCostLbl.setStyle(f);
       workersLbl.setStyle(f);
       withWorkersLbl.setStyle(f);
       
       //LBLS WITH DB RESULTS
       String rst="-fx-font-family: 'Lobster', cursive; -fx-font-size: 16;";
       assets_rst.setStyle(rst);
       my_profile.setStyle(rst);
       workers_rst.setStyle(rst);
       withWorkers_rst.setStyle(rst);
       
       //BUTTON FONTS 
       String btn="-fx-font-family: 'Yanone Kaffeesatz', sans-serif; -fx-font-size: 16;";
       profile_btn.setStyle(btn);
       workers_btn.setStyle(btn);
      assetWorkers_btn.setStyle(btn);
      asset_btn.setStyle(btn);
     }
     
     @FXML
    void goToWorkers(ActionEvent event) throws IOException {
          //you can use #onMousePressed or #orMouseClicked
         Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/workers/workers.fxml"));
           Scene newScene = new Scene(sceneFxml);
           

            //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //setting scene on stage
            window.setScene(newScene);
            window.show();
            window.centerOnScreen();

    }
    
     @FXML
    void goToAssets(ActionEvent event) throws IOException {
         //you can use #onMousePressed or #orMouseClicked
         Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/assets/assets.fxml"));
           Scene newScene = new Scene(sceneFxml);
           
           newScene.getStylesheets().add("/asset_management_system/css/tabpane.css"); 

            //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            //setting scene on stage
            window.setScene(newScene);
            window.show();
            window.centerOnScreen();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        styling();
        try {
            LoadDataFrmDB();
            countData();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      
      
    }    
    
}
