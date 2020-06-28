/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author User
 */
public class Asset_management_system extends Application {
    //https://stackoverflow.com/questions/34033119/how-to-make-transparent-scene-and-stage-in-javafx

    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/asset_management_system/dashboard/dashboard.fxml"));
        
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("http://fonts.googleapis.com/css?family=New+Rocker");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Bebas+Neue&display=swap");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Lobster&display=swap");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz&display=swap");
        
        scene.getStylesheets().add("/asset_management_system/css/tabpane.css"); 
        stage.setScene(scene);
         stage.setResizable(false);
        stage.resizableProperty();
        root.setStyle("-fx-background-color: transparent;");
        stage.initStyle(StageStyle.UNDECORATED);

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
        //scene.setFill(Color.TRANSPARENT);
      scene.setFill(Color.ALICEBLUE);
        
        
       
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.centerOnScreen();
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
