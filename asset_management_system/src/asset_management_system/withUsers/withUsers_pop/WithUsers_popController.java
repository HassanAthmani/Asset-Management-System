package asset_management_system.withUsers.withUsers_pop;

import asset_management_system.usedAlot.QR_Creator;
import asset_management_system.usedAlot.json_code;
import asset_management_system.usedAlot.json_read;
import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class WithUsers_popController implements Initializable {

    public Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    String userName = "root";
    String password = "";

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField workerID;

    @FXML
    private JFXTextField workerName;

    @FXML
    private JFXTextField workerTell;

    @FXML
    private JFXTextField workerEmail;

    @FXML
    private JFXTextField assetID;

    @FXML
    private JFXTextField assetName;

    @FXML
    private JFXTextField assetCode;

    @FXML
    private JFXTextField assigned_date;

    @FXML
    private JFXTextField assignedBy;

    @FXML
    private ImageView qr_code;

    @FXML
    private Button maintenance;

    @FXML
    private Button deferBtn;

    @FXML
    private Button assetsBtn;

    @FXML
    private JFXTextField reason;

    @FXML
    private CheckBox deferCheck;

    @FXML
    private Label reasonLbl;
    
    @FXML
    private Label title;


    @FXML
    void ToAssets(ActionEvent event) throws SQLException {

        String assetid = assetID.getText();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO `asset_management_system`.`available_assets` SELECT * FROM `asset_management_system`.`assets` WHERE assetID=" + assetid + " ;";

            statement.executeUpdate(sql);

            String sql2 = "DELETE FROM `asset_management_system`.`asset_maintenance` WHERE assetID = " + assetid;

            statement.executeUpdate(sql2);

            connection.close();

            id.clear();
            assetID.clear();
            assetName.clear();
            assetCode.clear();
            workerTell.clear();
            workerName.clear();
            workerID.clear();
            workerEmail.clear();
            assigned_date.clear();
            assignedBy.clear();
            qr_code.setImage(null);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WithUsers_popController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void ToMaintenance(ActionEvent event) throws SQLException, IOException, FileNotFoundException, ParseException {

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

            //GETTING ASSSET DETAILS CAT AND ADDITIONDATE
            /// THEN DOING THE SQL STUFF 
            String getAsset = "SELECT assetDetails,categoryID,additionDate FROM `asset_management_system`.`assets` WHERE assetID='" + assetID.getText() + "'";
            ResultSet rs = connection.createStatement().executeQuery(getWorker);

            while (rs.next()) {
                String assetdetails = rs.getString(1);
                String cat = rs.getString(2);
                String additiondate = rss.getString(3);

                String sql = "INSERT INTO `asset_management_system`.`asset_maintenance` (`assetID`, `assetName`, `assetCode`, `assetDetails`, `workerName`, `workerID`,`categoryID`,`additionDate`,`maintenanceDate`) VALUES (" + assetid + ",'" + assetname + "','" + assetcode + "','" + assetdetails + "','" + name + "'," + workerid + ",'" + cat + "','" + additiondate + "',CURDATE());";

                statement.executeUpdate(sql);

                String sql2 = "DELETE FROM `asset_management_system`.`available_assets` WHERE assetID= " + assetID.getText();

                statement.executeUpdate(sql2);

            }

            connection.close();

            id.clear();
            assetID.clear();
            assetName.clear();
            assetCode.clear();
            workerTell.clear();
            workerName.clear();
            workerID.clear();
            workerEmail.clear();
            assigned_date.clear();
            assignedBy.clear();
            qr_code.setImage(null);

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }

    }

    @FXML
    void check(ActionEvent event) {

        if (deferCheck.isSelected()) {
            deferBtn.setVisible(true);
            reason.setVisible(true);
            maintenance.setVisible(false);
            reasonLbl.setVisible(true);
            assetsBtn.setVisible(false);
        } else {
            deferBtn.setVisible(false);
            reason.setVisible(false);
            maintenance.setVisible(true);
            reasonLbl.setVisible(false);
            assetsBtn.setVisible(true);
        }

    }

    @FXML
    void defer(ActionEvent event) throws SQLException, IOException, FileNotFoundException, ParseException {

        if (!reason.getText().isEmpty()) {

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

                //GETTING ASSSET DETAILS CAT AND ADDITIONDATE
                /// THEN DOING THE SQL STUFF 
                String getAsset = "SELECT assetDetails,categoryID,additionDate FROM `asset_management_system`.`assets` WHERE assetID='" + assetID.getText() + "'";
                ResultSet rs = connection.createStatement().executeQuery(getWorker);

                while (rs.next()) {

                    String assetdetails = rs.getString(1);
                    String cat = rs.getString(2);
                    String additiondate = rs.getString(3);

                    String sql = "INSERT INTO `asset_management_system`.`deferred_asset` (`assetID`, `assetName`, `assetCode`, `assetDetails`, `workerName`, `workerID`,`categoryID`,`additionDate`,`deferredDate`,`reason`) VALUES (" + assetid + ",'" + assetname + "','" + assetcode + "','" + assetdetails + "','" + name + "'," + workerid + ",'" + cat + "','" + additiondate + "',CURDATE(),'" + reason.getText() + "');";

                    statement.executeUpdate(sql);

                    String sql2 = "DELETE FROM `asset_management_system`.`available_assets` WHERE assetID= " + assetID.getText();

                    statement.executeUpdate(sql2);
                }

                connection.close();

                id.clear();
                assetID.clear();
                assetName.clear();
                assetCode.clear();
                workerTell.clear();
                workerName.clear();
                workerID.clear();
                workerEmail.clear();
                assigned_date.clear();
                assignedBy.clear();
                qr_code.setImage(null);

            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
            }

        } else if (reason.getText().isEmpty()) {
            int response = JOptionPane.showConfirmDialog(
                    null, "Please enter information in the field provided", "ERROR!", JOptionPane.DEFAULT_OPTION);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        reasonLbl.setVisible(false);
        reason.setVisible(false);
        deferBtn.setVisible(false);

        assetID.setEditable(false);
        assetName.setEditable(false);
        assetCode.setEditable(false);
        workerTell.setEditable(false);
        workerName.setEditable(false);
        workerID.setEditable(false);
        workerEmail.setEditable(false);
        assigned_date.setEditable(false);
        assignedBy.setEditable(false);
        id.setEditable(false);
       
       // title.setStyle("-fx-font-family: 'Oswald', sans-serif; -fx-font-size: 20;");
       
        title.setStyle("-fx-font-family: 'Bebas Neue', cursive;");

        json_read nw = new json_read();
        try {
            nw.setToWithUser(id, workerID, workerName, workerTell, workerEmail, assetID, assetName, assetCode, assigned_date, assignedBy);

            QR_Creator maker = new QR_Creator();
            maker.QRGen(assetID.getText(), assetCode.getText(), assetName.getText());

            String path = "Asset" + assetID.getText() + ".png";
            Image imageObject = new Image(new FileInputStream(path));

            qr_code.setImage(imageObject);

        } catch (IOException ex) {
            Logger.getLogger(WithUsers_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(WithUsers_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WithUsers_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            Logger.getLogger(WithUsers_popController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
