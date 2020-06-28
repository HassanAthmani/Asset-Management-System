package asset_management_system.assets.maintenancePop;

import asset_management_system.assets.assetPop.AssetPopController;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import org.json.simple.parser.ParseException;

public class MaintenancePopController implements Initializable {

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
    private JFXTextField maintenanceDate;

    @FXML
    private JFXTextField reason;

    @FXML
    private Button returnToAssets;

    @FXML
    private Button deferAsset;

    @FXML
    private ImageView qr_code;

    @FXML
    private Label mainLbl;

    notification notify = new notification();

    @FXML
    void toAssets(ActionEvent event) throws SQLException {
        String assetid = assetID.getText();
        if (!assetID.getText().isEmpty()) {

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

                assetID.clear();
                assetName.clear();
                assetCode.clear();
                assetDetails.clear();
                workerName.clear();
                workerID.clear();
                category.clear();
                additionDate.clear();
                maintenanceDate.clear();
                qr_code.setImage(null);

                assetID.setEditable(false);
                assetName.setEditable(false);
                assetCode.setEditable(false);
                assetDetails.setEditable(false);
                workerName.setEditable(false);
                workerID.setEditable(false);
                category.setEditable(false);
                additionDate.setEditable(false);
                maintenanceDate.setEditable(false);

                reason.clear();

                notify.flash(returnToAssets, " ASSET HAS BEEN RETURNED ");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AssetPopController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            int response = JOptionPane.showConfirmDialog(
                    null, "No field should be empty", "ERROR", JOptionPane.DEFAULT_OPTION);
        }

    }

    @FXML
    void toDeferred(ActionEvent event) throws SQLException, IOException, FileNotFoundException, ParseException {
        if (!reason.getText().isEmpty() && !assetID.getText().isEmpty()) {

            String assetid = assetID.getText();
            String assetname = assetName.getText();
            String assetcode = assetCode.getText();
            String assetdetails = assetDetails.getText();
            //String workername = workerName.getText();
            //String workerid = workerID.getText();
            String cat = category.getText();
            String additiondate = additionDate.getText();
            // String maintenance = maintenanceDate.getText();

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
                // String assetid = assetID.getText();
                //String assetname = assetName.getText();
                //String assetcode = assetCode.getText();
                // String assetdetails = assetDetails.getText();
                // int workername = Integer.valueOf(workerID.getText());
                //  String cat = category.getText();
                //  String additiondate = additionDate.getText();

                String sql = "INSERT INTO `asset_management_system`.`deferred_asset` (`assetID`, `assetName`, `assetCode`, `assetDetails`, `workerName`, `workerID`,`categoryID`,`additionDate`,`deferredDate`,`reason`) VALUES (" + assetid + ",'" + assetname + "','" + assetcode + "','" + assetdetails + "','" + name + "'," + workerid + ",'" + cat + "','" + additiondate + "',CURDATE(),'" + toUpperCase(reason.getText()) + "');";

                statement.executeUpdate(sql);

                String sql2 = "DELETE FROM `asset_management_system`.`asset_maintenance` WHERE `assetID` = " + assetID.getText();

                statement.executeUpdate(sql2);

                connection.close();

                assetID.clear();
                assetName.clear();
                assetCode.clear();
                assetDetails.clear();
                workerName.clear();
                workerID.clear();
                category.clear();
                additionDate.clear();
                maintenanceDate.clear();
                qr_code.setImage(null);
                reason.clear();

                assetID.setEditable(false);
                assetName.setEditable(false);
                assetCode.setEditable(false);
                assetDetails.setEditable(false);
                workerName.setEditable(false);
                workerID.setEditable(false);
                category.setEditable(false);
                additionDate.setEditable(false);
                maintenanceDate.setEditable(false);

                reason.clear();
                notify.flash(deferAsset, " ASSET HAS BEEN DEFERRED ");

            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
            }

        } else if (reason.getText().isEmpty()) {
            int response = JOptionPane.showConfirmDialog(
                    null, "Please enter reason for deferring the asset", "ERROR!", JOptionPane.DEFAULT_OPTION);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mainLbl.setStyle("-fx-font-family: 'Lobster', cursive; -fx-font-size: 20; -fx-font-weight: bold;");

        assetID.setEditable(false);
        assetName.setEditable(false);
        assetCode.setEditable(false);
        assetDetails.setEditable(false);
        workerName.setEditable(false);
        workerID.setEditable(false);
        category.setEditable(false);
        additionDate.setEditable(false);
        maintenanceDate.setEditable(false);

        reason.clear();

        QR_Creator maker = new QR_Creator();

        try {
            json_read nw = new json_read();
            nw.setTomaintenance(assetID, assetName, assetCode, assetDetails, workerName, workerID, category, additionDate, maintenanceDate);

            maker.QRGen(assetID.getText(), assetCode.getText(), assetName.getText());
            String path = "Asset" + assetID.getText() + ".png";
            Image imageObject;
            imageObject = new Image(new FileInputStream(path));
            // ImageView image = new ImageView(imageObject);  
            qr_code.setImage(imageObject);

        } catch (WriterException ex) {
            Logger.getLogger(MaintenancePopController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MaintenancePopController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MaintenancePopController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MaintenancePopController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
