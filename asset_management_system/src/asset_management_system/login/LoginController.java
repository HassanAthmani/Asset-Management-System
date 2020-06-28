package asset_management_system.login;

import asset_management_system.usedAlot.emailValidation;
import asset_management_system.usedAlot.escapeChar;
import asset_management_system.usedAlot.json_code;
import asset_management_system.usedAlot.mover;
import asset_management_system.usedAlot.notification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    public Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private VBox vbox;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton loginbtn;

    @FXML
    private JFXButton forgotPassBtn;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton registerBtn;

    @FXML
    private ImageView closeApp;

    notification notify = new notification();

    @FXML
    public void closeAppWindow(MouseEvent event) {
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.close();
    }

    @FXML
    void forgotPass(ActionEvent event) throws IOException {
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/login/forgottenPass/forgottenPass.fxml"));
        Scene newScene = new Scene(sceneFxml);

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //setting scene on stage
        window.setScene(newScene);
        window.show();

    }

    @FXML
    void login(ActionEvent event) throws SQLException {
        String email = username.getText();
        String pass = password.getText();
        escapeChar nw = new escapeChar();
        String emailEscape = nw.escapeChar1(email);
        // String emailEscape=email;

        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            /*int response = JOptionPane.showConfirmDialog(
                    null, "Please enter information in the fields provided", "Login Failed!", JOptionPane.DEFAULT_OPTION);*/
            notify.flash(loginbtn, "Please enter information in the fields provided");

        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                String dbName = "inventory_project";
                String user = "root";
                String passw = "";

                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, user, passw);

                try {
                    //String sql = "SELECT * FROM login WHERE username = ? and password = ?";
                    String sql = "SELECT * FROM `asset_management_system`.`worker_details` WHERE (workerEmail LIKE '%" + emailEscape + "%') AND (pass_word LIKE '%" + pass + "%')";
                    // String sql="SELECT * FROM `asset_management_system`.`worker_details` WHERE (workerEmail LIKE '%"+emailEscape+"%') AND (pass_word ='"+pass+"')";

                    preparedStatement = connection.prepareStatement(sql);
                    // preparedStatement.setString(1, usrname );
                    //preparedStatement.setString(2, pass);
                    resultSet = preparedStatement.executeQuery();
                    if (!resultSet.next()) {

                        //TimeUnit.SECONDS.sleep(2);  
                        //ACCESS NOT GRANTED
                        /*int response = JOptionPane.showConfirmDialog(
                                null, "Please enter correct username and Password", "Login Failed!", JOptionPane.DEFAULT_OPTION);*/
                        notify.flash(loginbtn, "Please enter correct username and Password");

                    } else {

                        //TimeUnit.SECONDS.sleep(2);  
                        //ACCESS GRANTED 
                        String json_id = "SELECT * FROM `asset_management_system`.`worker_details` WHERE (workerEmail LIKE '%" + emailEscape + "%')";
                        ResultSet rs = connection.createStatement().executeQuery(json_id);

                        while (rs.next()) {

                            json_code cod = new json_code();
                            cod.json_id(rs.getString(1));
                        }

                        /*int response = JOptionPane.showConfirmDialog(
                    null, "Login Successful", "Success", JOptionPane.DEFAULT_OPTION);*/
                        notification notify = new notification();
                        notify.flash(registerBtn, " LOGIN SUCCESSFUL ");

                        //setting scene variable
                        connection.close();
                        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/dashboard/dashboard.fxml"));
                        Scene newScene = new Scene(sceneFxml);
                        newScene.setFill(Color.ALICEBLUE);
                        newScene.getStylesheets().add("/asset_management_system/css/tabpane.css");

                        //getting stage
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        mover movingWindow = new mover();
                        movingWindow.moving(sceneFxml, window);

                        //setting scene on stage
                        window.setScene(newScene);
                        window.show();
                        window.centerOnScreen();

                    }
                } catch (HeadlessException | IOException | SQLException e) {
                    System.out.println("Error:" + e);
                }

            } catch (ClassNotFoundException ex) {

                System.err.println("Error: " + ex);
            }

        }

    }

    @FXML
    void register(ActionEvent event) throws IOException {
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/login/register/register.fxml"));
        Scene newScene = new Scene(sceneFxml);

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //setting scene on stage
        window.setScene(newScene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbox.setStyle("-fx-background-color: #28d474;\n"
                + "-fx-padding: 10;\n"
                + "-fx-spacing:8;\n"
        );

        emailValidation valid = new emailValidation();
        valid.loginVal(username, "workerEmail", loginbtn);
    }

}
