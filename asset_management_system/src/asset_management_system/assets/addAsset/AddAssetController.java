package asset_management_system.assets.addAsset;

import asset_management_system.usedAlot.DBOps;
import asset_management_system.usedAlot.QR_Creator;
import asset_management_system.usedAlot.checkDetails;
import asset_management_system.usedAlot.json_read;
import asset_management_system.usedAlot.notification;
import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;

public class AddAssetController implements Initializable {

    public Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private JFXTextField assetName;

    @FXML
    private JFXTextField assetCode;

    @FXML
    private JFXTextField assetDetails;

    @FXML
    private JFXTextField workerID;

    @FXML
    private JFXTextField workerName;

    @FXML
    private JFXTextField cost;

    @FXML
    private ImageView qr_code;

    @FXML
    public Button addAsset;

    @FXML
    private Button clear;

    @FXML
    private ChoiceBox category;

    @FXML
    private Label addAssetLbl;

    @FXML
    void clearFields(ActionEvent event) {

        assetName.clear();
        assetCode.clear();
        assetDetails.clear();
        cost.clear();
        qr_code.setImage(null);
        addAsset.setVisible(true);
        clear.setVisible(false);

    }

    //nw.addAsset(assetName.getText(), assetCode.getText(), assetDetails.getText(), workerName.getText(), workerID.getText(), category.getValue().toString(), cost.getText());
    @FXML
    void add(ActionEvent event) throws SQLException, IOException, FileNotFoundException, ParseException, WriterException {
        DBOps nw = new DBOps();
        if (assetName.getText().isEmpty() || assetCode.getText().isEmpty() || assetDetails.getText().isEmpty() || workerName.getText().isEmpty() || workerID.getText().isEmpty() || category.getValue().toString().isEmpty() || cost.getText().isEmpty()) {

            int response = JOptionPane.showConfirmDialog(
                    null, "No filed should be empty", "ERROR", JOptionPane.DEFAULT_OPTION);

        } else if (!assetName.getText().isEmpty() || !assetCode.getText().isEmpty() || !assetDetails.getText().isEmpty() || !workerName.getText().isEmpty() || !workerID.getText().isEmpty() || !category.getValue().toString().isEmpty() || !cost.getText().isEmpty()) {

            nw.addAsset(assetName.getText(), assetCode.getText(), assetDetails.getText(), workerName.getText(), workerID.getText(), category.getValue().toString(), cost.getText());

            json_read jsonReader = new json_read();
            String id = jsonReader.asset_id();

            QR_Creator maker = new QR_Creator();
            maker.QRGen(id, assetCode.getText(), assetName.getText());

            // Image image=new Image(getClass().getResource("C:/Users/User/Documents/GitHub/Asset-Management-System/asset_management_system/qr_images/Asset"+id+".png"));
            //C:\Users\User\Documents\GitHub\Asset-Management-System\asset_management_system\qr_images
            /* InputStream inStream = getClass().getResourceAsStream("Asset"+id+".png");*/
            String path = "Asset" + id + ".png";
            Image imageObject = new Image(new FileInputStream(path));
            notification notify = new notification();
            notify.flash(addAsset, " ASSET HAS BEEN ADDED ");

            // ImageView image = new ImageView(imageObject);  
            qr_code.setImage(imageObject);
            clear.setVisible(true);
            addAsset.setVisible(false);

        }

    }

    public void fill_data() throws SQLException {
        json_read jsonReader = new json_read();
        try {
            String id = jsonReader.profile_id();
            String dbName = "asset_management_system";
            String userName = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM worker_details WHERE workerID= " + id);

            while (rs.next()) {

                //PUT DATA INTO THE TEXTFIELD
                // workerID_txtfield.setText(rs.getString(1));
                workerID.setText(rs.getString(1));
                workerName.setText(rs.getString(2));

                workerID.setEditable(false);
                workerName.setEditable(false);

            }

            // TODO
        } catch (IOException ex) {
            Logger.getLogger(AddAssetController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddAssetController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddAssetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addAssetLbl.setStyle("-fx-font-family: 'Lobster', cursive; -fx-font-size: 20; -fx-font-weight: bold;");
        try {
            fill_data();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AddAssetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        category.getItems().addAll("LAPTOP", "PROJECTOR", "CAMERA", "MOBILE PHONE", "PRINTER", "KITCHEN ITEM", "MACHINERY ITEM");
        category.setValue(" ");

        checkDetails nw = new checkDetails();
        nw.AssetCodechecker(assetCode, "assetCode", addAsset);
        nw.assetCost(cost, addAsset);

        clear.setVisible(false);
        workerName.setEditable(false);
        workerID.setEditable(false);
    }

}
