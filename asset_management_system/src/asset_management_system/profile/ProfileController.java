/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.profile;

import asset_management_system.usedAlot.DBOps;
import asset_management_system.usedAlot.checkDetails;
import asset_management_system.usedAlot.json_code;
import asset_management_system.usedAlot.json_read;
import asset_management_system.usedAlot.mover;
import asset_management_system.usedAlot.notification;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;

public class ProfileController implements Initializable {

    public Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private ObservableList<profile> data;

    @FXML
    private JFXTextField phoneNo_txtfield;

    @FXML
    private JFXTextField email_txtfield;

    @FXML
    private JFXTextField nationalID_txtfield;

    @FXML
    private JFXTextField workerID_txtfield;

    @FXML
    private JFXPasswordField current_pass;

    @FXML
    private JFXPasswordField new_pass;

    @FXML
    private JFXPasswordField confirm_pass;

    @FXML
    private JFXTextField pas_text;

    @FXML
    private TableView<profile> tble_view;

    @FXML
    private TableColumn<profile, String> id_column;

    @FXML
    private TableColumn<profile, String> name_column;

    @FXML
    private TableColumn<profile, String> phoneNo_column;

    @FXML
    private TableColumn<profile, String> email_column;

    @FXML
    private TableColumn<profile, String> assetID_column;

    @FXML
    private TableColumn<profile, String> assetName_column;

    @FXML
    private TableColumn<profile, String> assetCode_column;

    @FXML
    private TableColumn<profile, String> assignedDate_column;

    @FXML
    private ImageView imageviewer;

    @FXML
    private Label firstName_lbl;

    @FXML
    private Label lastName_lbl;

    @FXML
    private ImageView closeApp;

    @FXML
    private JFXTextField secondName_txtfield;

    @FXML
    private JFXTextField firstName_txtfield;

    @FXML
    private ImageView backToDashboard;

    @FXML
    private JFXTextField department_txtfield;

    @FXML
    private JFXTextField location_txtfield;

    @FXML
    private ImageView cancelChanges;

    @FXML
    private ImageView editInfo;

    @FXML
    private ImageView saveInfo;

    @FXML
    private ImageView min;

    @FXML
    private Label name2_lbl;

    @FXML
    private Label name1_lbl;
    
    notification notify=new notification();

    @FXML
    void send_file(MouseEvent event) {
        try {
            //you can use #onMousePressed or #orMouseClicked
            mover movingWindow = new mover();

            //getting stage
            Stage window = new Stage();
            Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/sendMail/sendMail.fxml"));
            Scene newScene = new Scene(sceneFxml);

            window.setScene(newScene);
            newScene.setFill(Color.ALICEBLUE);
            window.initModality(Modality.WINDOW_MODAL);
            window.initOwner(((Node) event.getSource()).getScene().getWindow());
            window.setResizable(false);
            window.resizableProperty();
            window.initStyle(StageStyle.UNDECORATED);

            //setting scene on stage
            movingWindow.moving(sceneFxml, window);
            window.setScene(newScene);
            window.showAndWait();
            window.centerOnScreen();

        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void open_file(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //Set extension filter
        /*FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");*/
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF");
        fileChooser.getExtensionFilters().addAll(extFilterPDF);
        fileChooser.setTitle("PICK FILE ");
        fileChooser.setInitialDirectory(new File("C:\\Users\\User\\Desktop\\Asset_Management_System\\files"));

        //Show open file dialog
        File file = fileChooser.showOpenDialog(window);

        if (file != null) {
            /*Image image = new Image(file.toURI().toString());
             imager.setImage(image);*/
            System.out.println(file.toURI().toString());
            if (file.toString().endsWith(".pdf")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
            }
        }

    }

    @FXML
    void minimize(MouseEvent event) {
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setIconified(true);

    }

    @FXML
    void cancelAll(MouseEvent event) {
        json_read back = new json_read();
        try {
            back.backToDB();
            fill_data();

            saveInfo.setVisible(false);
            current_pass.setVisible(false);
            new_pass.setVisible(false);
            confirm_pass.setVisible(false);
            secondName_txtfield.setVisible(false);
            firstName_txtfield.setVisible(false);
            name1_lbl.setVisible(false);
            name2_lbl.setVisible(false);
            // changePass.setVisible(false);
            pas_text.setVisible(false);
            cancelChanges.setVisible(false);
            editInfo.setVisible(true);

            current_pass.setEditable(false);
            phoneNo_txtfield.setEditable(false);
            email_txtfield.setEditable(false);
            nationalID_txtfield.setEditable(false);
            department_txtfield.setEditable(false);
            location_txtfield.setEditable(false);

        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void back(MouseEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/dashboard/dashboard.fxml"));
        Scene newScene = new Scene(sceneFxml);
        newScene.setFill(Color.ALICEBLUE);

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mover movingWindow = new mover();
        movingWindow.moving(sceneFxml, window);

        //setting scene on stage
        window.setScene(newScene);

        window.show();
        System.out.println("X " + window.getX());
        System.out.println("Y " + window.getY());
        /*window.setX(210.0);
        window.setY(44.0);*/
        window.centerOnScreen();

    }

    @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FileUtils.deleteDirectory(new File(".//json"));
            //FileUtils.deleteDirectory(new File(".//files"));
        } catch (IOException ex) {
            notify.flash(closeApp, "AN ERROR EXPERIENCED WHEN REMOVING SOME FILES");
        }

        window.close();

    }

    @FXML
    void editStuff(MouseEvent event) throws IOException, FileNotFoundException, ParseException, SQLException {
        json_read jsonReader = new json_read();
        json_code jsonGet = new json_code();

        String id = jsonReader.profile_id();

        jsonGet.json_profile(current_pass.getText(), firstName_lbl.getText(), lastName_lbl.getText(), phoneNo_txtfield.getText(), email_txtfield.getText(), nationalID_txtfield.getText(), department_txtfield.getText(), location_txtfield.getText(), pas_text.getText());

        DBOps deleteData = new DBOps();
        deleteData.deleteRecord(id);

        editInfo.setVisible(false);
        saveInfo.setVisible(true);
        //current_pass.setVisible(true);
        new_pass.setVisible(true);
        confirm_pass.setVisible(true);
        secondName_txtfield.setVisible(true);
        firstName_txtfield.setVisible(true);
        name1_lbl.setVisible(true);
        name2_lbl.setVisible(true);
        cancelChanges.setVisible(true);
        // changePass.setVisible(true);
        current_pass.setEditable(true);
        phoneNo_txtfield.setEditable(true);
        email_txtfield.setEditable(true);
        nationalID_txtfield.setEditable(true);
        department_txtfield.setEditable(true);
        location_txtfield.setEditable(true);

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
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM current_workers WHERE workerID= " + id);

            while (rs.next()) {

                //PUT DATA INTO THE TEXTFIELD
                // workerID_txtfield.setText(rs.getString(1));
                current_pass.setText(rs.getString(1));
                firstName_lbl.setText(rs.getString(2));
                lastName_lbl.setText(rs.getString(3));
                phoneNo_txtfield.setText(rs.getString(4));
                email_txtfield.setText(rs.getString(5));
                nationalID_txtfield.setText(rs.getString(6));
                department_txtfield.setText(rs.getString(7));
                location_txtfield.setText(rs.getString(8));
                pas_text.setText(rs.getString(9));
                secondName_txtfield.setText(rs.getString(3));
                firstName_txtfield.setText(rs.getString(2));

                //DISABLE THE TEXTFIELDS
                //workerID_txtfield.setEditable(false);
                //firstName_lbl.set
                //lastName_lbl
                current_pass.setEditable(false);
                phoneNo_txtfield.setEditable(false);
                email_txtfield.setEditable(false);
                nationalID_txtfield.setEditable(false);
                department_txtfield.setEditable(false);
                location_txtfield.setEditable(false);

            }

            // TODO
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadFromDB() throws SQLException, IOException, FileNotFoundException, ParseException {
        //DB connection details
        try {
            json_read jsonReader = new json_read();

            String id = jsonReader.profile_id();
            String dbName = "asset_management_system";
            String userName = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM assigned_asset WHERE workerID=" + id);

            while (rs.next()) {
                //get string from db
                //GONNA USE AN IF STATEMENT HERE TO TRY MIXING WITH DATA FROM ANOTHER TABLE
                data.add(new profile(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        id_column.setCellValueFactory(new PropertyValueFactory<>("transID"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        phoneNo_column.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        email_column.setCellValueFactory(new PropertyValueFactory<>("email"));
        assetID_column.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        assetName_column.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        assetCode_column.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        assignedDate_column.setCellValueFactory(new PropertyValueFactory<>("assigned_date"));

        tble_view.setItems(null);
        tble_view.setItems(data);

    }

    @FXML
    void saveStuff(MouseEvent event) throws IOException, FileNotFoundException, ParseException, SQLException {

        json_read stuff = new json_read();
        String password = stuff.profile_password();
        DBOps addData = new DBOps();

        if (firstName_txtfield.getText().isEmpty() || secondName_txtfield.getText().isEmpty() || phoneNo_txtfield.getText().isEmpty() || email_txtfield.getText().isEmpty() || nationalID_txtfield.getText().isEmpty() || department_txtfield.getText().isEmpty() || location_txtfield.getText().isEmpty()) {
            int response = JOptionPane.showConfirmDialog(
                    null, "input required,no field should be empty", "error", JOptionPane.DEFAULT_OPTION);
        } else if (!Objects.equals(new_pass.getText(), confirm_pass.getText())) {
            int response = JOptionPane.showConfirmDialog(
                    null, "Please check the password fields, values should be similar or both password fields should be empty", "CRITICAL", JOptionPane.DEFAULT_OPTION);

        } else if ((new_pass.getText().isEmpty() && confirm_pass.getText().isEmpty()) && (!firstName_txtfield.getText().isEmpty() && !secondName_txtfield.getText().isEmpty() && !phoneNo_txtfield.getText().isEmpty() && !email_txtfield.getText().isEmpty() && !nationalID_txtfield.getText().isEmpty() && !department_txtfield.getText().isEmpty() && !location_txtfield.getText().isEmpty())) {

            //jsonGet.json_profile(current_pass.getText(), firstName_lbl.getText(),lastName_lbl.getText(),phoneNo_txtfield.getText(), email_txtfield.getText(),nationalID_txtfield.getText(), department_txtfield.getText(), location_txtfield.getText(),pas_text.getText());
            addData.ProfileaddData(current_pass.getText(),toUpperCase(firstName_txtfield.getText()), toUpperCase(secondName_txtfield.getText()), phoneNo_txtfield.getText(), email_txtfield.getText(), nationalID_txtfield.getText(), toUpperCase(department_txtfield.getText()), toUpperCase(location_txtfield.getText()), password);

            fill_data();
            editInfo.setVisible(true);
            saveInfo.setVisible(false);
            //current_pass.setVisible(false);
            new_pass.setVisible(false);
            confirm_pass.setVisible(false);
            secondName_txtfield.setVisible(false);
            firstName_txtfield.setVisible(false);
            name1_lbl.setVisible(false);
            name2_lbl.setVisible(false);
            // changePass.setVisible(false);
            cancelChanges.setVisible(false);

            ///NOT EDITABLE
            current_pass.setEditable(false);
            phoneNo_txtfield.setEditable(false);
            email_txtfield.setEditable(false);
            nationalID_txtfield.setEditable(false);
            department_txtfield.setEditable(false);
            location_txtfield.setEditable(false);
            notify.flash(saveInfo," PROFILE DATA HAS SUCCESSFULLY CHANGED ");

        } else if ((!new_pass.getText().isEmpty() && !confirm_pass.getText().isEmpty()) && (Objects.equals(new_pass.getText(), confirm_pass.getText()) && (!firstName_txtfield.getText().isEmpty() && !secondName_txtfield.getText().isEmpty() && !phoneNo_txtfield.getText().isEmpty() && !email_txtfield.getText().isEmpty() && !nationalID_txtfield.getText().isEmpty() && !department_txtfield.getText().isEmpty() && !location_txtfield.getText().isEmpty()))) {

            addData.ProfileaddData(current_pass.getText(), toUpperCase(firstName_txtfield.getText()), toUpperCase(secondName_txtfield.getText()), phoneNo_txtfield.getText(), email_txtfield.getText(), nationalID_txtfield.getText(), toUpperCase(department_txtfield.getText()), toUpperCase(location_txtfield.getText()), confirm_pass.getText());

            fill_data();
            editInfo.setVisible(true);
            saveInfo.setVisible(false);
            //current_pass.setVisible(false);
            new_pass.setVisible(false);
            confirm_pass.setVisible(false);
            secondName_txtfield.setVisible(false);
            firstName_txtfield.setVisible(false);
            name1_lbl.setVisible(false);
            name2_lbl.setVisible(false);
            // changePass.setVisible(false);
            cancelChanges.setVisible(false);

            //NOT EDITABLE
            current_pass.setEditable(false);
            phoneNo_txtfield.setEditable(false);
            email_txtfield.setEditable(false);
            nationalID_txtfield.setEditable(false);
            department_txtfield.setEditable(false);
            location_txtfield.setEditable(false);
            new_pass.clear();
            confirm_pass.clear();
            
            
            notify.flash(saveInfo," PASSWORD AND PROFILE DATA HAS SUCCESSFULLY CHANGED ");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fill_data();
            loadFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        saveInfo.setVisible(false);
        current_pass.setVisible(false);
        new_pass.setVisible(false);
        confirm_pass.setVisible(false);
        secondName_txtfield.setVisible(false);
        firstName_txtfield.setVisible(false);
        name1_lbl.setVisible(false);
        name2_lbl.setVisible(false);
        //changePass.setVisible(false);
        pas_text.setVisible(false);
        cancelChanges.setVisible(false);

        checkDetails nw = new checkDetails();
        nw.profile_checker(phoneNo_txtfield, "workerTell", saveInfo);
        nw.profile_checker(nationalID_txtfield, "workerNationalID", saveInfo);

        nw.profile_checker2(firstName_txtfield, saveInfo);
        nw.profile_checker2(secondName_txtfield, saveInfo);
        nw.profile_checker2(department_txtfield, saveInfo);
        nw.profile_checker2(location_txtfield, saveInfo);

    }

}
