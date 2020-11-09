/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system;

import asset_management_system.usedAlot.notification;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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

    notification notify = new notification();

    public Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void start(Stage stage) throws Exception {

        //Parent root = FXMLLoader.load(getClass().getResource("/asset_management_system/reports/reports.fxml"));
        
        Parent root = FXMLLoader.load(getClass().getResource("/asset_management_system/login/login.fxml"));
        
        //Parent root = FXMLLoader.load(getClass().getResource("/asset_management_system/dashboard/dashboard.fxml"));

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

        File file = new File(".//json");
        File file1 = new File(".//files");

        // if the directory does not exist, create it
        if (!file.exists() && !file1.exists()) {
            System.out.println("creating directory: " + file.getName() + " and " + file1.getName());
            boolean result = false;

            try {

                file.mkdir();
                file1.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
                notification notify = new notification();
                notify.flash(root, "REQUIRED FILES NOT CREATED. PLEASE RESTART APP");
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
/////////////////////////////////////////////////////////////////////////////////
        String user = "root";
        String passw = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, user, passw);
            String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'asset_management_system'";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // ResultSet resultSet = connection.getMetaData().getCatalogs();
            //iterate each catalog in the ResultSet
            while (resultSet.next()) {
                // Get the database name, which is at position 1
                String databaseName = "asset_management_system";
                System.out.println(resultSet.getString(1));
                if (databaseName.equals(resultSet.getString(1))) {

                    //notify.flash(root, "DATABASE IS AVAILABLE");
                } else {
                    notify.flash(root, "AN ERROR OCCURED CHECK IF DATABASE IS AVAILABLE");

                }
            }
            resultSet.close();
        } catch (ClassNotFoundException | SQLException ex) {
            notify.flash(root, "SQL ERROR OCCURED CHECK IF DATABASE IS AVAILABLE");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
