package asset_management_system.dashboard;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class DashboardController implements Initializable {

    
     @FXML
    private VBox vbox;

    @FXML
    private AnchorPane assets_pane;

    @FXML
    private Label assetsLbl;
    
    @FXML
    private AnchorPane totalCost_pane;

    @FXML
    private Label totalCostLbl;
    
    @FXML
    private AnchorPane workers_pane;

    @FXML
    private Label workersLbl;
    
    @FXML
    private AnchorPane withUsers_pane;

    @FXML
    private Label withWorkersLbl;
    
     @FXML
    private AnchorPane pieChart_pane;

    @FXML
    private PieChart catChart;

    @FXML
    private AnchorPane tableView_pane;
    
    @FXML
    private JFXButton totalCost_btn;
    
     @FXML
    private JFXButton workers_btn;
     
     @FXML
    private JFXButton assetWorkers_btn;
     
      @FXML
    private JFXButton asset_btn;
      
       @FXML
    private TableView<?> tbleView_dash;

    @FXML
    private TableColumn<?, ?> assetName_clmn;

    @FXML
    private TableColumn<?, ?> assetCode_clmn;

    @FXML
    private TableColumn<?, ?> addedOn_clmn;

    @FXML
    private TableColumn<?, ?> addedBy_clmn;

    @FXML
    private TableColumn<?, ?> category_clmn;

    @FXML
    private TableColumn<?, ?> cost_clmn;
    
    @FXML
    private Label assets_rst;
    
      @FXML
    private Label cost_rst;
      
      @FXML
    private Label workers_rst;
      
      @FXML
    private Label withWorkers_rst;


   

     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String f="-fx-font-family: 'Bebas Neue', cursive; -fx-font-size: 20;";
        //NORMAL LABELS
       assetsLbl.setStyle(f);
       totalCostLbl.setStyle(f);
       workersLbl.setStyle(f);
       withWorkersLbl.setStyle(f);
       
       //LBLS WITH DB RESULTS
       String rst="-fx-font-family: 'Lobster', cursive; -fx-font-size: 20;";
       assets_rst.setStyle(rst);
       cost_rst.setStyle(rst);
       workers_rst.setStyle(rst);
       withWorkers_rst.setStyle(rst);
       
       //BUTTON FONTS 
       String btn="-fx-font-family: 'Yanone Kaffeesatz', sans-serif; -fx-font-size: 16;";
       totalCost_btn.setStyle(btn);
       workers_btn.setStyle(btn);
      assetWorkers_btn.setStyle(btn);
      asset_btn.setStyle(btn);
    }    
    
}
