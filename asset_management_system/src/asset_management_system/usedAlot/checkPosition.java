/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.usedAlot;

import asset_management_system.workers.WorkersController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import org.json.simple.parser.ParseException;

/**
 *
 * @author User
 */
public class checkPosition {
     public Connection connection;
    
     public void checkPosition(Button addAsset) throws SQLException, IOException, FileNotFoundException, ParseException, ParseException {

        try {
            json_read jsonReader = new json_read();
            String dbName = "asset_management_system";
            String userName = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            String id = jsonReader.profile_id();
            String position = "";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);

            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT position FROM `position` WHERE workerID=" + id);

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
                addAsset.setVisible(false);

            } else {
                while (rs.next()) {
                    System.out.println("string" + rs.getString(1));
                    //"PROJECT MANAGER", "ACCOUNTANT", "REGIONAL DIRECTOR", "PROJECT OFFICER", "TECHNICAL OFFICER", "RSD OFFICER", "LOGISTICS OFFICER","STAFF");
                    if (rs.getString(1).equals("REGIONAL DIRECTOR") || rs.getString(1).equals("PROJECT MANAGER") || rs.getString(1).equals("LOGISTICS OFFICER")) {
                        addAsset.setVisible(true);

                    } else {
                        addAsset.setVisible(false);

                    }
                    position = rs.getString(1);
                }
                System.out.println("string" + position + id);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void checkPosBox(CheckBox  assign) throws SQLException, IOException, FileNotFoundException, ParseException, ParseException {

        try {
            json_read jsonReader = new json_read();
            String dbName = "asset_management_system";
            String userName = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            String id = jsonReader.profile_id();
            String position = "";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);

            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT position FROM `position` WHERE workerID=" + id);

            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
                assign.setVisible(false);

            } else {
                while (rs.next()) {
                    System.out.println("string" + rs.getString(1));
                    //"PROJECT MANAGER", "ACCOUNTANT", "REGIONAL DIRECTOR", "PROJECT OFFICER", "TECHNICAL OFFICER", "RSD OFFICER", "LOGISTICS OFFICER","STAFF");
                    if (rs.getString(1).equals("REGIONAL DIRECTOR") || rs.getString(1).equals("PROJECT MANAGER") || rs.getString(1).equals("LOGISTICS OFFICER")) {
                        assign.setVisible(true);

                    } else {
                        assign.setVisible(false);

                    }
                    position = rs.getString(1);
                }
                System.out.println("string" + position + id);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
