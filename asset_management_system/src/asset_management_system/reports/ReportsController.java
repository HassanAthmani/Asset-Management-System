package asset_management_system.reports;

import asset_management_system.assets.AssetsController;
import asset_management_system.dashboard.DashboardController;
import asset_management_system.usedAlot.json_code;
import asset_management_system.usedAlot.mover;
import asset_management_system.usedAlot.notification;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;

public class ReportsController implements Initializable {

    @FXML
    private ImageView closeApp;

    @FXML
    private Button assigned_assets;

    @FXML
    private Button deferred_assets;

    @FXML
    private Button asset_in_maintenance;

   

    @FXML
    private Button total_assets;

    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Label report_lbl;

    notification notify = new notification();
    json_code cod = new json_code();
    GaussianBlur gaussianBlur = new GaussianBlur();

    public void effect_on() {
        gaussianBlur.setRadius(10.5);
        anchor.setEffect(gaussianBlur);

    }

    public void effect_off() {
        anchor.setEffect(null);
    }

    public void reportGen() {
        try {
            //you can use #onMousePressed or #orMouseClicked
            mover movingWindow = new mover();

            //getting stage
            Stage window = new Stage();
            Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/reports/reportCreator/reportCreator.fxml"));
            Scene newScene = new Scene(sceneFxml);
            newScene.getStylesheets().add("/asset_management_system/reports/datePick.css");

            window.setScene(newScene);
            newScene.setFill(Color.ALICEBLUE);
            window.initModality(Modality.WINDOW_MODAL);
            window.initOwner(asset_in_maintenance.getScene().getWindow());
            window.setResizable(false);
            window.resizableProperty();
            //window.initStyle(StageStyle.UNDECORATED);
            effect_on();

            window.setOnCloseRequest(e -> {

                effect_off();

            });

            //setting scene on stage
            movingWindow.moving(sceneFxml, window);
            window.setScene(newScene);
            window.showAndWait();
            window.centerOnScreen();

        } catch (IOException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void closeAppWindow(MouseEvent event) {
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.close();
    }

    @FXML
    void assignedReport(ActionEvent event) {

        cod.reportInfo("assignedReport");
        reportGen();

    }

    @FXML
    void deferredReport(ActionEvent event) {

        cod.reportInfo("deferredReport");
        reportGen();

    }

    @FXML
    void maintenanceReport(ActionEvent event) {

        cod.reportInfo("maintenanceReport");
        reportGen();

    }

    @FXML
    void totalAssets(ActionEvent event) {

        cod.reportInfo("totalAssets");
        reportGen();
    }
    
    public void styling(){
        
        String f = "-fx-font-family: 'Bebas Neue', cursive; -fx-font-size: 20; -fx-background-color: #42dc5c;";
        total_assets.setStyle(f);
        assigned_assets.setStyle(f);
        deferred_assets.setStyle(f);
        asset_in_maintenance.setStyle(f);
        report_lbl.setStyle("-fx-font-family: 'Lobster', cursive; -fx-font-size: 30;");
    }

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        styling();
    }

}
