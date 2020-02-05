/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author User
 */
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


   

     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       assetsLbl.setStyle("-fx-font-family: 'Bebas Neue', cursive; -fx-font-size: 20;");
       totalCostLbl.setStyle("-fx-font-family:'Bebas Neue', cursive; -fx-font-size: 20;");
       workersLbl.setStyle("-fx-font-family: 'Bebas Neue', cursive; -fx-font-size: 20;");
       withWorkersLbl.setStyle("-fx-font-family: 'Bebas Neue', cursive; -fx-font-size: 20;");
    }    
    
}
