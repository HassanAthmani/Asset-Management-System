package asset_management_system.dashboard;

import asset_management_system.usedAlot.json_read;
import asset_management_system.usedAlot.mover;
import asset_management_system.usedAlot.notification;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;

public class DashboardController implements Initializable {

    public Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private ObservableList<dashboard> data;

    @FXML
    private VBox vbox;

    @FXML
    private AnchorPane asset_pane;

    @FXML
    private VBox totalCost_pane;

    @FXML
    private VBox workers_pane;

    @FXML
    private VBox withUsers_pane;

    @FXML
    private AnchorPane pieChart_pane;

    @FXML
    private PieChart catChart;

    @FXML
    private AnchorPane tableView_pane;

    @FXML
    private ImageView profile_btn;

    @FXML
    private ImageView workers_btn;

    @FXML
    private ImageView assetWorkers_btn;

    @FXML
    private ImageView asset_btn;

    @FXML
    private TableView<dashboard> tbleView_dash;

    @FXML
    private TableColumn<dashboard, String> assetName_clmn;

    @FXML
    private TableColumn<dashboard, String> assetCode_clmn;

    @FXML
    private TableColumn<dashboard, String> addedOn_clmn;

    @FXML
    private TableColumn<dashboard, String> addedBy_clmn;

    @FXML
    private TableColumn<dashboard, String> category_clmn;

    @FXML
    private TableColumn<dashboard, String> cost_clmn;

    @FXML
    private Label assets_rst;

    @FXML
    private Label my_profile;

    @FXML
    private Label workers_rst;

    @FXML
    private Label withWorkers_rst;

    @FXML
    private ImageView backToDashboard;

    @FXML
    private ImageView closeApp;

    @FXML
    private Label dashboardLabel;

    @FXML
    private ImageView open;

    @FXML
    private ImageView send;

    PreparedStatement ps;

    ArrayList<Integer> cell = new ArrayList<Integer>();
    ArrayList<String> name = new ArrayList<String>();
    List<String> A = new ArrayList<String>();
    
    notification notify=new notification();
    
    ObservableList<PieChart.Data> piechartdata;

    mover movingWindow = new mover();

    public void loadData() throws SQLException {

        A.add("LAPTOP");
        A.add("PROJECTOR");
        A.add("CAMERA");
        A.add("MOBILE PHONE");
        A.add("PRINTER");
        A.add("KITCHEN ITEM");
        A.add("MACHINERY ITEM");
        
        piechartdata=FXCollections.observableArrayList();

        for (int i = 0; i < A.size(); i++) {
            
            try {
                String dbName = "asset_management_system";
                String userName = "root";
                String password = "";
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);

                ps = connection.prepareStatement("SELECT COUNT(*) FROM assets WHERE categoryID='"+A.get(i)+"'");
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    piechartdata.add(new PieChart.Data(A.get(i), rs.getInt(1)));
                    name.add(A.get(i));
                    cell.add(rs.getInt(1));

                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

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
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
        fileChooser.setInitialDirectory(new File("C:\\Bit_torrent"));

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
    void ToProfile(MouseEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/profile/profile.fxml"));
        Scene newScene = new Scene(sceneFxml);
        newScene.setFill(Color.ALICEBLUE);
        newScene.getStylesheets().add("/asset_management_system/css/tabpane.css");

        //newScene.getStylesheets().add("/asset_management_system/css/tabpane.css");
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        movingWindow.moving(sceneFxml, window);

        //setting scene on stage
        window.setScene(newScene);
        window.show();
        window.centerOnScreen();

    }

    @FXML
    public void closeAppWindow(MouseEvent event) {
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FileUtils.deleteDirectory(new File(".//json"));
            FileUtils.deleteDirectory(new File(".//files"));
        } catch (IOException ex) {
            notify.flash(backToDashboard, "AN ERROR EXPERIENCED WHEN REMOVING SOME FILES");
        }

        window.close();
    }

    @FXML
    public void minimizeWindow(MouseEvent event) {
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setIconified(true);
    }

    @FXML
    void imageClicked(MouseEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/login/login.fxml"));
        Scene newScene = new Scene(sceneFxml);
        newScene.setFill(Color.ALICEBLUE);

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        movingWindow.moving(sceneFxml, window);

        //setting scene on stage
        window.setScene(newScene);
        window.show();
        window.centerOnScreen();

    }

    public void LoadDataFrmDB() throws SQLException {
        //DB connection details
        try {
            String dbName = "asset_management_system";
            String userName = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM assets");

            while (rs.next()) {
                //get string from db
                //GONNA USE AN IF STATEMENT HERE TO TRY MIXING WITH DATA FROM ANOTHER TABLE
                data.add(new dashboard(rs.getString(2), rs.getString(3), rs.getString(8), rs.getString(5), rs.getString(7), rs.getString(9)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        assetName_clmn.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        assetCode_clmn.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        addedOn_clmn.setCellValueFactory(new PropertyValueFactory<>("additionDate"));
        addedBy_clmn.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        category_clmn.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        cost_clmn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        tbleView_dash.setItems(null);
        tbleView_dash.setItems(data);

    }

    ////////  https://www.mysqltutorial.org/mysql-count/
    public void countData() throws SQLException, IOException, FileNotFoundException, ParseException {
        //DB connection details
        try {
            String dbName = "asset_management_system";
            String userName = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            json_read jsonReader = new json_read();
            String id = jsonReader.profile_id();

            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM assets");
            ResultSet rs1 = connection.createStatement().executeQuery("SELECT workerName FROM worker_details WHERE workerID= " + id);
            ResultSet rs2 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM worker_details");
            ResultSet rs3 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM assigned_asset");

            while (rs.next()) {
                assets_rst.setText(rs.getString(1) + " ASSETS");
            }
            while (rs1.next()) {
                my_profile.setText(rs1.getString(1) + "'S PROFILE ");
            }
            while (rs2.next()) {
                workers_rst.setText(rs2.getString(1) + " WORKERS");
            }
            while (rs3.next()) {
                withWorkers_rst.setText(rs3.getString(1) + " ASSIGNED");
            }
            connection.close();

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }
    }

    public void styling() {
        // System.out.print(assets_rst.getText());
        String f = "-fx-font-family: 'Bebas Neue', cursive; -fx-font-size: 20;";
        //NORMAL LABELS
        //assetsLbl.setStyle(f);
        //totalCostLbl.setStyle(f);
        //workersLbl.setStyle(f);
        //withWorkersLbl.setStyle(f);

        //LBLS WITH DB RESULTS
        String rst = "-fx-font-family: 'Lobster', cursive; -fx-font-size: 16;";
        assets_rst.setStyle(f);
        my_profile.setStyle(f);
        workers_rst.setStyle(f);
        withWorkers_rst.setStyle(f);
        dashboardLabel.setStyle("-fx-font-family: 'Lobster', cursive; -fx-font-size: 30;");

        //BUTTON FONTS 
        String btn = "-fx-font-family: 'Yanone Kaffeesatz', sans-serif; -fx-font-size: 16;";
        /* profile_btn.setStyle(btn);
        workers_btn.setStyle(btn);
        assetWorkers_btn.setStyle(btn);
        asset_btn.setStyle(btn);*/
    }

    @FXML
    void goToWorkers(MouseEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/workers/workers.fxml"));
        Scene newScene = new Scene(sceneFxml);
        newScene.setFill(Color.ALICEBLUE);
        newScene.getStylesheets().add("/asset_management_system/css/tabpane.css");

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        movingWindow.moving(sceneFxml, window);

        //setting scene on stage
        window.setScene(newScene);
        window.show();
        window.centerOnScreen();

    }

    @FXML
    void goToAssets(MouseEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/assets/assets.fxml"));
        Scene newScene = new Scene(sceneFxml);
        newScene.setFill(Color.ALICEBLUE);

        newScene.getStylesheets().add("/asset_management_system/css/tabpane.css");

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        movingWindow.moving(sceneFxml, window);

        //setting scene on stage
        window.setScene(newScene);
        window.show();
        window.centerOnScreen();

    }

    @FXML
    void openWithWorkers(MouseEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/withUsers/withUsers.fxml"));
        Scene newScene = new Scene(sceneFxml);
        newScene.setFill(Color.ALICEBLUE);

        newScene.getStylesheets().add("/asset_management_system/css/tabpane.css");

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        movingWindow.moving(sceneFxml, window);

        //setting scene on stage
        window.setScene(newScene);
        window.show();
        window.centerOnScreen();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        styling();
        try {
            LoadDataFrmDB();
            countData();
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        catChart.setData(piechartdata);

    }

}
