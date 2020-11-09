package asset_management_system.reports.reportCreator;

import asset_management_system.usedAlot.json_read;
import asset_management_system.usedAlot.notification;
import asset_management_system.usedAlot.special_char;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.sql.SQLException;

public class ReportCreatorController implements Initializable {

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    private CheckBox barCheck;

    @FXML
    private CheckBox pieCheck;

    @FXML
    private Button printer;

    @FXML
    private Label purpose;

    @FXML
    private ImageView closeApp;
    
    @FXML
    private Label gen_lbl;
    
    creator make=new creator();
notification notify = new notification();

    @FXML
    void closeAppWindow(MouseEvent event) {

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.close();
    }


    public void assigned(String from,String to) throws SQLException, DocumentException, IOException, FileNotFoundException, ParseException {
       make.assigned_assets(from, to, printer);
    }

    public void deferred(String from,String to) throws DocumentException, IOException, FileNotFoundException, ParseException, SQLException {
        make.deferred_assets(from, to, printer);

    }

    public void maintenance(String from,String to) throws DocumentException, IOException, FileNotFoundException, ParseException, SQLException {
        make.assets_maintenance(from, to, printer);

    }

    public void users(String from,String to) {

    }

    public void total(String from,String to) throws SQLException, ClassNotFoundException, DocumentException, IOException, FileNotFoundException, ParseException, ClassNotFoundException, ClassNotFoundException{
        make.all_assets(from, to, printer);

    }

    @FXML
    void printData(ActionEvent event) throws SQLException, DocumentException, IOException, ClassNotFoundException, FileNotFoundException, ParseException {
        special_char checkChar =new special_char();
        if((fromDate.getValue()==null || toDate.getValue()==null) || (checkChar.DateHasChar(String.valueOf(fromDate.getValue()))&&checkChar.DateHasChar(String.valueOf(toDate.getValue())))==true){
            notify.flash(toDate, "PLEASE SET THE DATES AND DO NOT USE ALPHABETS.");
            
        }
        else{

        LocalDate from = fromDate.getValue();
        LocalDate to = toDate.getValue();

        if (purpose.getText().equals("ASSIGNED ASSETS REPORT")) {
            assigned(String.valueOf(from),String.valueOf(to));

        } else if (purpose.getText().equals("DEFERRED ASSETS REPORT")) {
            deferred(String.valueOf(from),String.valueOf(to));

        } else if (purpose.getText().equals("ASSETS IN MAINTENANCE REPORT")) {
            maintenance(String.valueOf(from),String.valueOf(to));

        } else if (purpose.getText().equals("USERS REPORT")) {
            users(String.valueOf(from),String.valueOf(to));

        } else if (purpose.getText().equals("ALL ASSETS REPORT")) {
            total(String.valueOf(from),String.valueOf(to));

        }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gen_lbl.setStyle("-fx-font-family: 'Bebas Neue', cursive; -fx-font-size: 30;");
        purpose.setStyle("-fx-font-family: 'Bebas Neue', cursive; -fx-font-size: 20;");

        json_read jsonReader = new json_read();
        try {
            String purp = jsonReader.purpose();

            if (purp.equals("assignedReport")) {

                purpose.setText("ASSIGNED ASSETS REPORT");

            } else if (purp.equals("deferredReport")) {

                purpose.setText("DEFERRED ASSETS REPORT");

            } else if (purp.equals("maintenanceReport")) {

                purpose.setText("ASSETS IN MAINTENANCE REPORT");

            } else if (purp.equals("usersReport")) {

                purpose.setText("USERS REPORT");

            } else if (purp.equals("totalAssets")) {

                purpose.setText("ALL ASSETS REPORT");

            }
            barCheck.setVisible(false);
            pieCheck.setVisible(false);

        } catch (IOException ex) {
            Logger.getLogger(ReportCreatorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportCreatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
