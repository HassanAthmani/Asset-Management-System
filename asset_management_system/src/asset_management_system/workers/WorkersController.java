package asset_management_system.workers;

import asset_management_system.dashboard.DashboardController;
import asset_management_system.usedAlot.checkPosition;
import asset_management_system.usedAlot.json_read;
import asset_management_system.usedAlot.mover;
import asset_management_system.usedAlot.notification;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;

public class WorkersController implements Initializable {

    public Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public ObservableList<workers> data;

    @FXML
    public TableColumn<workers, String> firstname_clmn;

    @FXML
    public TableColumn<workers, String> secondName_clmn;

    @FXML
    public TableColumn<workers, String> phoneNumber_clmn;

    @FXML
    public TableColumn<workers, String> emailAddress_clmn;

    @FXML
    public TableColumn<workers, String> natID_clmn;

    @FXML
    public TableColumn<workers, String> department_clmn;

    @FXML
    public TableColumn<workers, String> location_clmn;

    @FXML
    public TableView<workers> tbleView;

    @FXML
    private TextField searchBox;

    @FXML
    private ChoiceBox checkBox;

    @FXML
    private JFXButton addWorker;

    @FXML
    private Button printer;

    @FXML
    private ImageView closeApp;

    @FXML
    private ImageView backToDashboard;

    String dbName = "asset_management_system";
    String userName = "root";
    String password = "";

    private double xOffset = 0;
    private double yOffset = 0;
    
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
            notify.flash(printer, "AN ERROR EXPERIENCED WHEN REMOVING SOME FILES");
        }

    }

    @FXML
    void open_file(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //Set extension filter
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
    void imageClicked(MouseEvent event) throws IOException {
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

        /*Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - 316.0) / 2);
        window.setY((primScreenBounds.getHeight() - 339.0) / 2);*/
 /* System.out.println("X "+window.getX());
        System.out.println("Y "+ window.getY());*/
        window.show();

        window.centerOnScreen();

        //x 210.0
        //Y 44.000001311302185
    }

    @FXML
    void minimizeIt(MouseEvent event) {
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setIconified(true);
    }

    @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
         try {
            FileUtils.deleteDirectory(new File(".//json"));
            //FileUtils.deleteDirectory(new File(".//files"));
        } catch (IOException ex) {
            notify.flash(printer, "AN ERROR EXPERIENCED WHEN REMOVING SOME FILES");
        }
        window.close();

    }

    @FXML
    void printData(ActionEvent event) throws FileNotFoundException, DocumentException, IOException, ParseException, SQLException {

        Document my_pdf_report = new Document();

        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./files/WORKERS.pdf"));
        my_pdf_report.open();

        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(7);
        my_report_table.setWidthPercentage(100);
        my_report_table.addCell(new PdfPCell(new Phrase("FIRST NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("SECOND NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("PHONE NUMBER", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("EMAIL ADDRESS", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("NATIONAL ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("DEPARTMENT", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("LOCATION", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));

        my_report_table.completeRow();

        //create a cell object
        PdfPCell table_cell;
        Paragraph space = new Paragraph("  ");
        Float indent = 10.0f;
        Paragraph title = new Paragraph(" MOTIVATION CHARITABLE TRUST ");
        Paragraph tableName = new Paragraph("TABLE NAME:  WORKERS TABLE ");
        my_pdf_report.add(title/*.setIndentationLeft(indent)*/);
        my_pdf_report.add(space);
        my_pdf_report.add(tableName);

        try {
            json_read jsonReader = new json_read();
            String id = jsonReader.profile_id();

            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM worker_details WHERE workerID= " + id);

            while (rs.next()) {

                Paragraph workerid = new Paragraph("WORKER ID: " + rs.getString(1));
                Paragraph workername = new Paragraph("WORKER NAME: " + rs.getString(2));
                Paragraph email = new Paragraph("WORKER EMAIL: " + rs.getString(5));
                Paragraph department = new Paragraph("WORKER DEPARTMENT: " + rs.getString(7));

                my_pdf_report.add(workerid);
                my_pdf_report.add(workername);
                my_pdf_report.add(email);
                my_pdf_report.add(department);
                my_pdf_report.add(space);
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("MyQRCode.png");
        for (workers o : tbleView.getItems()) {

            // System.err.println(columnA.getCellData(o));
            String fname = firstname_clmn.getCellData(o);
            table_cell = new PdfPCell(new Phrase(fname, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String sname = secondName_clmn.getCellData(o);
            table_cell = new PdfPCell(new Phrase(sname, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String phoneNo = phoneNumber_clmn.getCellData(o);
            table_cell = new PdfPCell(new Phrase(phoneNo, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String emailAdd = emailAddress_clmn.getCellData(o);
            table_cell = new PdfPCell(new Phrase(emailAdd, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String nationalID = natID_clmn.getCellData(o);
            table_cell = new PdfPCell(new Phrase(nationalID, FontFactory.getFont(FontFactory.HELVETICA, 7)));
            my_report_table.addCell(table_cell);

            String dept = department_clmn.getCellData(o);
            table_cell = new PdfPCell(new Phrase(dept, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String location = location_clmn.getCellData(o);
            table_cell = new PdfPCell(new Phrase(location, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

        }
        /* Attach report table to PDF */
        my_pdf_report.add(my_report_table);
        my_pdf_report.close();

        notification notify = new notification();
        notify.flash(printer, " DOCUMENT HAS BEEN CREATED ");

    }

    public void LoadDataFrmDB() throws SQLException {
        //DB connection details
        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM worker_details");

            while (rs.next()) {
                //get string from db
                //GONNA USE AN IF STATEMENT HERE TO TRY MIXING WITH DATA FROM ANOTHER TABLE
                data.add(new workers(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        firstname_clmn.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        secondName_clmn.setCellValueFactory(new PropertyValueFactory<>("workerLastName"));
        phoneNumber_clmn.setCellValueFactory(new PropertyValueFactory<>("workerTell"));
        emailAddress_clmn.setCellValueFactory(new PropertyValueFactory<>("workerEmail"));
        natID_clmn.setCellValueFactory(new PropertyValueFactory<>("workerNationalID"));
        department_clmn.setCellValueFactory(new PropertyValueFactory<>("department"));
        location_clmn.setCellValueFactory(new PropertyValueFactory<>("location"));

        tbleView.setItems(null);
        tbleView.setItems(data);

        connection.close();

    }

    @FXML
    void add(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/asset_management_system/workers/addWorker/register.fxml").openStream());
        Scene scene = new Scene(root);
        scene.setFill(Color.ALICEBLUE);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.setResizable(false);
        stage.resizableProperty();
        stage.setOnCloseRequest(e -> {
            try {
                LoadDataFrmDB();
            } catch (SQLException ex) {
                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        mover movingWindow = new mover();
        movingWindow.moving(root, stage);

        scene.setFill(Color.ALICEBLUE);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();

    }

    public void popClose() {
        try {
            LoadDataFrmDB();
        } catch (SQLException ex) {
            Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loada(String searchVal, String colmnVal) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset

            try {

                ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `asset_management_system`.`worker_details` WHERE (`" + colmnVal + "` LIKE '%" + searchVal + "%')");

                while (rs.next()) {
                    //get string from db
                    //GONNA USE AN IF STATEMENT HERE TO TRY MIXING WITH DATA FROM ANOTHER TABLE
                    data.add(new workers(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                }
                //set cell value factor to tableview.
                //PropertyValue Factory must be set the same with the one set in model class.
                firstname_clmn.setCellValueFactory(new PropertyValueFactory<>("workerName"));
                secondName_clmn.setCellValueFactory(new PropertyValueFactory<>("workerLastName"));
                phoneNumber_clmn.setCellValueFactory(new PropertyValueFactory<>("workerTell"));
                emailAddress_clmn.setCellValueFactory(new PropertyValueFactory<>("workerEmail"));
                natID_clmn.setCellValueFactory(new PropertyValueFactory<>("workerNationalID"));
                department_clmn.setCellValueFactory(new PropertyValueFactory<>("department"));
                location_clmn.setCellValueFactory(new PropertyValueFactory<>("location"));

                tbleView.setItems(null);
                tbleView.setItems(data);

            } catch (SQLException ex) {
                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        } catch (SQLException ex) {
            Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void loadAll(MouseEvent event) {
        /* try {
             LoadDataFrmDB();
         } catch (SQLException ex) {
             Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
         }*/

    }

    @FXML
    void loadAll2(MouseEvent event) {
        /*try {
             LoadDataFrmDB();
         } catch (SQLException ex) {
             Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
         }*/

    }

    @FXML
    void sample(MouseEvent event) {
        /* try {
             LoadDataFrmDB();
         } catch (SQLException ex) {
             Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
         }*/

    }

    public void checkPosition() throws SQLException, IOException, FileNotFoundException, ParseException, ParseException {

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
                addWorker.setVisible(false);

            } else {
                while (rs.next()) {
                    System.out.println("string" + rs.getString(1));
                    //"PROJECT MANAGER", "ACCOUNTANT", "REGIONAL DIRECTOR", "PROJECT OFFICER", "TECHNICAL OFFICER", "RSD OFFICER", "LOGISTICS OFFICER","STAFF");
                    if (rs.getString(1).equals("REGIONAL DIRECTOR") || rs.getString(1).equals("PROJECT MANAGER") || rs.getString(1).equals("LOGISTICS OFFICER")) {
                        addWorker.setVisible(true);

                    } else {
                        addWorker.setVisible(false);

                    }
                    position = rs.getString(1);
                }
                System.out.println("string" + position + id);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            LoadDataFrmDB();
            //checkPosition();
            checkPosition checkin=new checkPosition();
            checkin.checkPosition(addWorker);
        } catch (SQLException | IOException | ParseException ex) {
            Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
        }

        checkBox.getItems().addAll("first name", "second name", "phone number", "email", "national ID", "location", "department");
        checkBox.setValue("first name");
        //String checkBoxValue=checkBox.getValue().toString();

        // System.out.print(checkBoxValue);
        if (searchBox.getText().isEmpty()) {

            try {
                LoadDataFrmDB();
            } catch (SQLException ex) {
                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        ///THA MAGIC HAPPENS
        searchBox.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (oldValue != newValue) {

                if (!searchBox.getText().isEmpty()) {

                    String BoxValue = checkBox.getValue().toString();

                    if ("first name".equals(BoxValue)) {
                        String clmn = "workerName";
                        loada(searchBox.getText(), clmn);
                    } else if ("second name".equals(BoxValue)) {
                        String clmn = "workerLastName";
                        loada(searchBox.getText(), clmn);
                    } else if ("phone number".equals(BoxValue)) {
                        String clmn = "workerTell";
                        loada(searchBox.getText(), clmn);
                    } else if ("email".equals(BoxValue)) {
                        String clmn = "workerEmail";
                        loada(searchBox.getText(), clmn);
                    } else if ("national ID".equals(BoxValue)) {
                        String clmn = "workerNationalID";
                        loada(searchBox.getText(), clmn);
                    } else if ("department".equals(BoxValue)) {
                        String clmn = "department";
                        loada(searchBox.getText(), clmn);
                    } else if ("location".equals(BoxValue)) {
                        String clmn = "location";
                        loada(searchBox.getText(), clmn);
                    }

                } else {    ///( searchBox.getText().isEmpty() )
                    try {
                        LoadDataFrmDB();
                        // System.out.print("no empty search");
                    } catch (SQLException ex) {
                        Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });

    }

}
