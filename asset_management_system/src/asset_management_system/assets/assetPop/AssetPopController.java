package asset_management_system.assets.assetPop;

import asset_management_system.usedAlot.QR_Creator;
import asset_management_system.usedAlot.json_code;
import asset_management_system.usedAlot.json_read;
import asset_management_system.usedAlot.notification;
import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;

public class AssetPopController implements Initializable {

    String userName = "root";
    String password = "";
    public Connection connection;

    @FXML
    private JFXTextField assetID;

    @FXML
    private JFXTextField assetName;

    @FXML
    private JFXTextField assetCode;

    @FXML
    private JFXTextField assetDetails;

    @FXML
    private JFXTextField workerName;

    @FXML
    private JFXTextField workerID;

    @FXML
    private JFXTextField category;

    @FXML
    private JFXTextField additionDate;

    @FXML
    private JFXTextField cost;

    @FXML
    private Button maintenance;

    @FXML
    private Button defer;

    @FXML
    private Button edit;

    @FXML
    private Button save;

    @FXML
    private Button cancel;

    @FXML
    private CheckBox deferCheck;

    @FXML
    private JFXTextField reason;

    @FXML
    private ImageView qr_code;

    @FXML
    private JFXTextField assignTo;

    @FXML
    private CheckBox assignCheck;

    @FXML
    private Button assign;

    @FXML
    private Label assignLabel;

    @FXML
    private Label assignID;

    @FXML
    private Label assetLbl;

    notification notify = new notification();

    @FXML
    void giveAsset(ActionEvent event) throws SQLException {
        if (!assignTo.getText().isEmpty() && !assetID.getText().isEmpty()) {
            try {

                String assetid = assetID.getText();
                String assetname = assetName.getText();
                String assetcode = assetCode.getText();
                String workerid = workerID.getText();
                String cat = category.getText();
                String additiondate = additionDate.getText();
                String cosst = cost.getText();

                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
                connection = DriverManager.getConnection(url, userName, password);
                Statement statement = connection.createStatement();

                ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM `asset_management_system`.`current_workers` WHERE workerID=" + assignTo.getText() + " ;");
                int quan;
                int a = 1;
                while (rs.next()) {

                    String str = rs.getString(1);
                    quan = Integer.parseInt(str);
                    System.out.println(quan);
                    if (quan == a) {

                        ResultSet rss = connection.createStatement().executeQuery("SELECT * FROM `asset_management_system`.`current_workers` WHERE workerID=" + assignTo.getText() + " ;");

                        while (rss.next()) {

                            String sql = "INSERT INTO `asset_management_system`.`assigned_asset` (`ID`,`workerID`,`workerName`, `workerTell`, `workerEmail`, `assetID`, `assetName`,`assetCode`,`assigned_date`,`assignedBy`) VALUES (null,'" + assignTo.getText() + "','" + rss.getString(2) + "','" + rss.getString(4) + "','" + rss.getString(5) + "'," + assetid + ",'" + assetname + "','" + assetcode + "',CURDATE(),'" + workerid + "');";

                            statement.executeUpdate(sql);

                            String sql2 = "DELETE FROM `asset_management_system`.`available_assets` WHERE assetID =" + assetid + " ;";

                            statement.executeUpdate(sql2);

                            notify.flash(assign, " ASSET HAS BEEN ASSIGNED TO " + rss.getString(2));

                            assetID.clear();
                            assetName.clear();
                            assetCode.clear();
                            assetDetails.clear();
                            workerName.clear();
                            workerID.clear();
                            category.clear();
                            additionDate.clear();
                            cost.clear();
                            qr_code.setImage(null);

                            assetID.setEditable(false);
                            assetName.setEditable(false);
                            assetCode.setEditable(false);
                            assetDetails.setEditable(false);
                            workerName.setEditable(false);
                            workerID.setEditable(false);
                            category.setEditable(false);
                            additionDate.setEditable(false);
                            cost.setEditable(false);

                        }

                    } else {
                        int response = JOptionPane.showConfirmDialog(null, "Worker ID does not exist", "ERROR", JOptionPane.DEFAULT_OPTION);
                    }
                }

                connection.close();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AssetPopController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            int response = JOptionPane.showConfirmDialog(null, "Please check the fields,they should not be empty", "ERROR", JOptionPane.DEFAULT_OPTION);

        }

    }

    @FXML
    void checkAssign(ActionEvent event) {
        if (assignCheck.isSelected()) {
            deferCheck.setVisible(false);
            defer.setVisible(false);
            reason.setVisible(false);
            maintenance.setVisible(false);
            edit.setVisible(false);
            assign.setVisible(true);
            assignTo.setVisible(true);
            assignLabel.setVisible(true);
            assignID.setVisible(true);
        } else {
            defer.setVisible(false);
            reason.setVisible(false);
            maintenance.setVisible(true);
            edit.setVisible(true);
            deferCheck.setVisible(false);
            assign.setVisible(false);
            deferCheck.setVisible(true);
            assignTo.setVisible(false);
            assignLabel.setVisible(false);
            assignID.setVisible(false);
        }

    }

    @FXML
    void cancelInfo(ActionEvent event) throws SQLException, IOException, FileNotFoundException, ParseException {
        save.setVisible(false);
        cancel.setVisible(false);
        edit.setVisible(true);
        deferCheck.setVisible(true);
        maintenance.setVisible(true);
        assignCheck.setVisible(true);

        json_read nw = new json_read();
        nw.AssetPopBackToDB();
        notify.flash(cancel, " NO CHANGES HAVE BEEN MADE. ");

    }

    @FXML
    void editInfo(ActionEvent event) throws SQLException {
        if(!assetID.getText().isEmpty()){

        String assetid = assetID.getText();
        String assetname = assetName.getText();
        String assetcode = assetCode.getText();
        String assetdetails = assetDetails.getText();
        String workername = workerName.getText();
        String workerid = workerID.getText();
        String cat = category.getText();
        String additiondate = additionDate.getText();
        String cosst = cost.getText();

        json_code nw = new json_code();
        nw.assetPop_json(assetid, assetname, assetcode, assetdetails, workername, workerid, cat, additiondate, cosst);

        assetID.setEditable(false);
        assetName.setEditable(true);
        assetCode.setEditable(true);
        assetDetails.setEditable(true);
        workerName.setEditable(true);
        workerID.setEditable(true);
        category.setEditable(true);
        additionDate.setEditable(false);
        cost.setEditable(true);

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();

            String sql = "DELETE FROM `asset_management_system`.`available_assets` WHERE assetID =" + assetid + " ;";

            statement.executeUpdate(sql);

            connection.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AssetPopController.class.getName()).log(Level.SEVERE, null, ex);
        }

        save.setVisible(true);
        cancel.setVisible(true);
        edit.setVisible(false);
        deferCheck.setVisible(false);
        maintenance.setVisible(false);
        assignCheck.setVisible(false);
        }
        else {
            notify.flash(edit,"ASSET ID IS NOT AVAILABLE");
        }

    }

    @FXML
    void saveInfo(ActionEvent event) throws SQLException {

        if (assetID.getText().isEmpty() || assetName.getText().isEmpty() || assetCode.getText().isEmpty() || assetDetails.getText().isEmpty() || workerName.getText().isEmpty() || workerID.getText().isEmpty() || category.getText().isEmpty() || additionDate.getText().isEmpty() || cost.getText().isEmpty()) {

            int response = JOptionPane.showConfirmDialog(
                    null, "Please enter information in the field provided", "ERROR!", JOptionPane.DEFAULT_OPTION);

        } else if (!assetID.getText().isEmpty() && !assetName.getText().isEmpty() && !assetCode.getText().isEmpty() && !assetDetails.getText().isEmpty() && !workerName.getText().isEmpty() && !workerID.getText().isEmpty() && !category.getText().isEmpty() && !additionDate.getText().isEmpty() && !cost.getText().isEmpty()) {

            String assetid = assetID.getText();
            String assetname = assetName.getText();
            String assetcode = assetCode.getText();
            String assetdetails = assetDetails.getText();
            String workername = workerName.getText();
            String workerid = workerID.getText();
            String cat = category.getText();
            String additiondate = additionDate.getText();
            String cosst = cost.getText();

            try {

                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
                connection = DriverManager.getConnection(url, userName, password);
                Statement statement = connection.createStatement();

                String sql = "INSERT INTO `asset_management_system`.`available_assets` (`assetID`, `assetName`, `assetCode`, `assetDetails`, `workerName`, `workerID`,`categoryID`,`additionDate`,`cost`) VALUES (" + assetid + ",'" + assetname + "','" + assetcode + "','" + assetdetails + "','" + workername + "'," + workerid + ",'" + cat + "',CURDATE(),'" + cosst + "');";

                statement.executeUpdate(sql);

                String sql2 = "UPDATE `asset_management_system`.`assets` SET assetName='" + assetname + "', assetCode='" + assetcode + "',assetDetails='" + assetdetails + "', workerName='" + workername + "', workerID=" + workerid + ",categoryID='" + cat + "', additionDate= CURDATE(),cost=" + cosst + " WHERE assetID= " + assetID.getText();

                statement.executeUpdate(sql2);

                notify.flash(save, " ASSET DETAILS HAVE BEEN CHANGED. ");

                connection.close();

                assetID.setEditable(false);
                assetName.setEditable(false);
                assetCode.setEditable(false);
                assetDetails.setEditable(false);
                workerName.setEditable(false);
                workerID.setEditable(false);
                category.setEditable(false);
                additionDate.setEditable(false);
                cost.setEditable(false);

                save.setVisible(false);
                cancel.setVisible(false);
                edit.setVisible(true);
                deferCheck.setVisible(true);
                maintenance.setVisible(true);
                assignCheck.setVisible(true);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AssetPopController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    void toDeferred(ActionEvent event) throws SQLException, IOException, FileNotFoundException, ParseException {

        if (!reason.getText().isEmpty() && !assetID.getText().isEmpty()) {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
                connection = DriverManager.getConnection(url, userName, password);
                Statement statement = connection.createStatement();

                //GETTING WORKER INFO
                json_read read = new json_read();
                String workerid = read.profile_id();
                String getWorker = "SELECT workerName FROM `asset_management_system`.`current_workers` WHERE workerID='" + workerid + "'";
                ResultSet rss = connection.createStatement().executeQuery(getWorker);

                while (rss.next()) {

                    ///GETS WORKER NAME
                    String name = rss.getString(1);
                    json_code nw = new json_code();
                    nw.json_assetID(name);
                }
                json_read nw = new json_read();
                String name = nw.asset_id();
                String assetid = assetID.getText();
                String assetname = assetName.getText();
                String assetcode = assetCode.getText();
                String assetdetails = assetDetails.getText();

                String cat = category.getText();
                String additiondate = additionDate.getText();

                String sql = "INSERT INTO `asset_management_system`.`deferred_asset` (`assetID`, `assetName`, `assetCode`, `assetDetails`, `workerName`, `workerID`,`categoryID`,`additionDate`,`deferredDate`,`reason`) VALUES (" + assetid + ",'" + assetname + "','" + assetcode + "','" + assetdetails + "','" + name + "'," + workerid + ",'" + cat + "','" + additiondate + "',CURDATE(),'" + reason.getText() + "');";

                statement.executeUpdate(sql);

                String sql2 = "DELETE FROM `asset_management_system`.`available_assets` WHERE assetID= " + assetID.getText();

                statement.executeUpdate(sql2);
                notify.flash(defer, " ASSET HAS BEEN DEFERRED ");

                connection.close();

                assetID.clear();
                assetName.clear();
                assetCode.clear();
                assetDetails.clear();
                workerName.clear();
                workerID.clear();
                category.clear();
                additionDate.clear();
                cost.clear();
                qr_code.setImage(null);

                assetID.setEditable(false);
                assetName.setEditable(false);
                assetCode.setEditable(false);
                assetDetails.setEditable(false);
                workerName.setEditable(false);
                workerID.setEditable(false);
                category.setEditable(false);
                additionDate.setEditable(false);
                cost.setEditable(false);

            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
            }

        } else if (reason.getText().isEmpty() && assetID.getText().isEmpty()) {
            int response = JOptionPane.showConfirmDialog(
                    null, "Please enter information in the field provided", "ERROR!", JOptionPane.DEFAULT_OPTION);

        }

    }

    @FXML
    void toMaintenance(ActionEvent event) throws SQLException, IOException, FileNotFoundException, ParseException {

        if (!assetID.getText().isEmpty()) {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
                connection = DriverManager.getConnection(url, userName, password);
                Statement statement = connection.createStatement();

                //GETTING WORKER INFO
                json_read read = new json_read();
                String workerid = read.profile_id();
                String getWorker = "SELECT workerName FROM `asset_management_system`.`current_workers` WHERE workerID='" + workerid + "'";
                ResultSet rss = connection.createStatement().executeQuery(getWorker);

                while (rss.next()) {

                    String name = rss.getString(1);
                    json_code nw = new json_code();
                    nw.json_assetID(name);
                }
                json_read nw = new json_read();
                String name = nw.asset_id();
                String assetid = assetID.getText();
                String assetname = assetName.getText();
                String assetcode = assetCode.getText();
                String assetdetails = assetDetails.getText();
                int workername = Integer.valueOf(workerID.getText());
                String cat = category.getText();
                String additiondate = additionDate.getText();

                String sql = "INSERT INTO `asset_management_system`.`asset_maintenance` (`assetID`, `assetName`, `assetCode`, `assetDetails`, `workerName`, `workerID`,`categoryID`,`additionDate`,`maintenanceDate`) VALUES (" + assetid + ",'" + assetname + "','" + assetcode + "','" + assetdetails + "','" + name + "'," + workerid + ",'" + cat + "','" + additiondate + "',CURDATE());";

                statement.executeUpdate(sql);

                String sql2 = "DELETE FROM `asset_management_system`.`available_assets` WHERE assetID= " + assetID.getText();

                statement.executeUpdate(sql2);

                notify.flash(maintenance, " ASSET HAS BEEN HAS BEEN SENT TO MAINTENANCE ");

                connection.close();

                assetID.clear();
                assetName.clear();
                assetCode.clear();
                assetDetails.clear();
                workerName.clear();
                workerID.clear();
                category.clear();
                additionDate.clear();
                cost.clear();
                qr_code.setImage(null);

                assetID.setEditable(false);
                assetName.setEditable(false);
                assetCode.setEditable(false);
                assetDetails.setEditable(false);
                workerName.setEditable(false);
                workerID.setEditable(false);
                category.setEditable(false);
                additionDate.setEditable(false);
                cost.setEditable(false);

            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
            }
        } else {
            int response = JOptionPane.showConfirmDialog(
                    null, "The fields should not be empty", "ERROR", JOptionPane.DEFAULT_OPTION);

        }
    }

    @FXML
    void check(ActionEvent event) {
        if (deferCheck.isSelected()) {
            defer.setVisible(true);
            reason.setVisible(true);
            maintenance.setVisible(false);
            edit.setVisible(false);
            assign.setVisible(false);
        } else {
            defer.setVisible(false);
            reason.setVisible(false);
            maintenance.setVisible(true);
            edit.setVisible(true);
            assign.setVisible(false);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        assetLbl.setStyle("-fx-font-family: 'Lobster', cursive; -fx-font-size: 20; -fx-font-weight: bold;");

        defer.setVisible(false);
        reason.setVisible(false);
        save.setVisible(false);
        cancel.setVisible(false);
        assign.setVisible(false);
        assignTo.setVisible(false);

        assignLabel.setVisible(false);
        assignID.setVisible(false);

        json_read nw = new json_read();
        try {
            nw.setToAllItems(assetID, assetName, assetCode, assetDetails, workerName, workerID, category, additionDate, cost);

            assetID.setEditable(false);
            assetName.setEditable(false);
            assetCode.setEditable(false);
            assetDetails.setEditable(false);
            workerName.setEditable(false);
            workerID.setEditable(false);
            category.setEditable(false);
            additionDate.setEditable(false);
            cost.setEditable(false);

            QR_Creator maker = new QR_Creator();
            maker.QRGen(assetID.getText(), assetCode.getText(), assetName.getText());

            String path = "Asset" + assetID.getText() + ".png";
            Image imageObject = new Image(new FileInputStream(path));

            // ImageView image = new ImageView(imageObject);  
            qr_code.setImage(imageObject);

        } catch (IOException ex) {
            Logger.getLogger(AssetPopController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AssetPopController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AssetPopController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            Logger.getLogger(AssetPopController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
