package asset_management_system.assets;

import asset_management_system.usedAlot.assetSearch;
import asset_management_system.usedAlot.json_code;
import asset_management_system.usedAlot.json_read;
import asset_management_system.usedAlot.mover;
import asset_management_system.usedAlot.notification;
import asset_management_system.workers.WorkersController;
import com.itextpdf.text.BadElementException;
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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;

public class AssetsController implements Initializable {

    public Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    String userName = "root";
    String password = "";

    public ObservableList<all_assets> data;
    public ObservableList<assets> data1;
    public ObservableList<asset_in_maintenance> data2;
    public ObservableList<deferred_assets> data3;

    @FXML
    private ImageView open;

    @FXML
    private ImageView send;

    @FXML
    private Button printer; //ALL ASSETS

    @FXML
    private Button printer1; //AVAILABLE ASSETS

    @FXML
    private Button printer2; //ASSETS IN MAINTENANCE

    @FXML
    private Button printer3; //DEFERRED ASSETS

    @FXML
    private VBox vbox;

    @FXML
    private Tab pane0;

    @FXML
    private Tab pane1;

    @FXML
    private Tab pane2;

    @FXML
    private Tab pane3;

    /////   ALL ASSETS   //////////////   
    @FXML
    private TableView<all_assets> all_assets_tbl;

    @FXML
    private TableColumn<all_assets, String> asset_id;

    @FXML
    private TableColumn<all_assets, String> asset_name;

    @FXML
    private TableColumn<all_assets, String> asset_code;

    @FXML
    private TableColumn<all_assets, String> asset_details101;

    @FXML
    private TableColumn<all_assets, String> worker_name;

    @FXML
    private TableColumn<all_assets, String> worker_id;

    @FXML
    private TableColumn<all_assets, String> category_id;

    @FXML
    private TableColumn<all_assets, String> addition_date;

    @FXML
    private TableColumn<all_assets, String> cost;

    @FXML
    private TextField search;

    @FXML
    private ChoiceBox choice;

    //////////////////////////////////////////////////////
    ///// ASSETS TABLE ////////////
    @FXML
    private TableView<assets> assets_tbl;

    @FXML
    private TableColumn<assets, String> asset1_id;

    @FXML
    private TableColumn<assets, String> asset1_name;

    @FXML
    private TableColumn<assets, String> asset1_code;

    @FXML
    private TableColumn<assets, String> asset1_details;

    @FXML
    private TableColumn<assets, String> worker1_name;

    @FXML
    private TableColumn<assets, String> worker1_id;

    @FXML
    private TableColumn<assets, String> category1_id;

    @FXML
    private TableColumn<assets, String> addition1_date;

    @FXML
    private TableColumn<assets, String> cost1;

    @FXML
    private ChoiceBox choice1;

    @FXML
    private TextField search1;

    //////////////////////////////////////////////////////////////////
    //////// MAINTENANCE //////////////
    @FXML
    private TableView<asset_in_maintenance> maintenance_tbl;

    @FXML
    private TableColumn<asset_in_maintenance, String> asset2_id;

    @FXML
    private TableColumn<asset_in_maintenance, String> asset2_name;

    @FXML
    private TableColumn<asset_in_maintenance, String> asset2_code;

    @FXML
    private TableColumn<asset_in_maintenance, String> asset2_details;

    @FXML
    private TableColumn<asset_in_maintenance, String> worker2_name;

    @FXML
    private TableColumn<asset_in_maintenance, String> worker2_id;

    @FXML
    private TableColumn<asset_in_maintenance, String> category2_id;

    @FXML
    private TableColumn<asset_in_maintenance, String> addition2_date;

    @FXML
    private TableColumn<asset_in_maintenance, String> maintenance2_date;

    @FXML
    private TextField search2;

    @FXML
    private ChoiceBox choice2;

    //////////////////////////////////////////////////////////////////
    /////DEFERRED ASSTES ///////////
    @FXML
    private TableView<deferred_assets> deferred_tbl;

    @FXML
    private TableColumn<deferred_assets, String> asset3_id;

    @FXML
    private TableColumn<deferred_assets, String> asset3_name;

    @FXML
    private TableColumn<deferred_assets, String> asset3_code;

    @FXML
    private TableColumn<deferred_assets, String> asset3_details;

    @FXML
    private TableColumn<deferred_assets, String> worker3_name;

    @FXML
    private TableColumn<deferred_assets, String> worker3_id;

    @FXML
    private TableColumn<deferred_assets, String> category3_id;

    @FXML
    private TableColumn<deferred_assets, String> addition3_date;

    @FXML
    private TableColumn<deferred_assets, String> deferred3_date;

    @FXML
    private TableColumn<deferred_assets, String> reason3;

    @FXML
    private TableColumn columnEdit3;

    @FXML
    private TableColumn columnEdit;

    @FXML
    private TableColumn columnEdit1;

    @FXML
    private TableColumn columnEdit2;

    @FXML
    private ChoiceBox choice3;

    @FXML
    private TextField search3;

    @FXML
    private ImageView backToDashboard;

    @FXML
    private Button addAsset;

    private double xOffset = 0;
    private double yOffset = 0;

    notification notify = new notification();

    @FXML
    void send_file(MouseEvent event) throws IOException {
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

    }

    @FXML
    void open_file(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //Set extension filter
        /* FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");*/
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
    void addNew(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/asset_management_system/assets/addAsset/addAsset.fxml"));
        Scene newScene = new Scene(root);
        newScene.setFill(Color.ALICEBLUE);

        stage.setScene(newScene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.setResizable(false);
        stage.resizableProperty();

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        newScene.setFill(Color.ALICEBLUE);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();

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
            FileUtils.deleteDirectory(new File(".//files"));
        } catch (IOException ex) {
            notify.flash(backToDashboard, "AN ERROR EXPERIENCED WHEN REMOVING SOME FILES");
        }
        window.close();

    }

    @FXML
    void imageClicked(MouseEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
        mover movingWindow = new mover();
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/dashboard/dashboard.fxml"));
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

    /////////////////////// START ////////////////////////////////////////////
    //ALL ASSETS
    public void load() throws SQLException {
        //DB connection details

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM assets");

            while (rs.next()) {
                //get string from db
                data.add(new all_assets(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        asset_id.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        asset_name.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        asset_code.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        asset_details101.setCellValueFactory(new PropertyValueFactory<>("assetDetails"));
        worker_name.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        worker_id.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        category_id.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        addition_date.setCellValueFactory(new PropertyValueFactory<>("additionDate"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        all_assets_tbl.setItems(null);
        all_assets_tbl.setItems(data);

        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<all_assets, String>, TableCell<all_assets, String>> cellFactory = (param) -> {

            //make the tablecell containing button
            final TableCell<all_assets, String> cell = new TableCell<all_assets, String>() {

                //override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Now we create the action button
                        final Button editButton = new Button("+");
                        //attach listener on button
                        editButton.setOnAction(event -> {

                            //Extract the clicked object
                            all_assets p = getTableView().getItems().get(getIndex());

                            String id = String.valueOf(p.getAssetID());
                            String name = String.valueOf(p.getAssetName());
                            String code = String.valueOf(p.getAssetCode());
                            String details = String.valueOf(p.getAssetDetails());
                            String worker = String.valueOf(p.getWorkerName());
                            String workerid = String.valueOf(p.getWorkerID());
                            String categoryid = String.valueOf(p.getCategoryID());
                            String additiondate = String.valueOf(p.getAdditionDate());
                            String cost = String.valueOf(p.getCost());

                            json_code nw = new json_code();
                            nw.AllItem_json(id, name, code, details, worker, workerid, categoryid, additiondate, cost);

                            //// POP UP ///////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/assets/all_assetsPop/all_assetsPop.fxml").openStream());
                                Scene scene = new Scene(root);
                                scene.setFill(Color.ALICEBLUE);

                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();
                                stage.setOnCloseRequest(e -> {
                                    try {
                                        load();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ////// POP UP///////

                        });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        //set the custom factory to action column
        columnEdit.setCellFactory(cellFactory);

    }

    //FOR ASSETS
    public void load1() throws SQLException {
        //DB connection details

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data1 = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM available_assets");

            while (rs.next()) {
                //get string from db
                data1.add(new assets(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        asset1_id.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        asset1_name.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        asset1_code.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        asset1_details.setCellValueFactory(new PropertyValueFactory<>("assetDetails"));
        worker1_name.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        worker1_id.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        category1_id.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        addition1_date.setCellValueFactory(new PropertyValueFactory<>("additionDate"));
        cost1.setCellValueFactory(new PropertyValueFactory<>("cost"));

        assets_tbl.setItems(null);
        assets_tbl.setItems(data1);

        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<assets, String>, TableCell<assets, String>> cellFactory = (param) -> {

            //make the tablecell containing button
            final TableCell<assets, String> cell = new TableCell<assets, String>() {

                //override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Now we create the action button
                        final Button editButton = new Button("+");
                        //attach listener on button
                        editButton.setOnAction(event -> {

                            //Extract the clicked object
                            assets p = getTableView().getItems().get(getIndex());

                            String id = String.valueOf(p.getAssetID());
                            String name = String.valueOf(p.getAssetName());
                            String code = String.valueOf(p.getAssetCode());
                            String details = String.valueOf(p.getAssetDetails());
                            String worker = String.valueOf(p.getWorkerName());
                            String workerid = String.valueOf(p.getWorkerID());
                            String categoryid = String.valueOf(p.getCategoryID());
                            String additiondate = String.valueOf(p.getAdditionDate());
                            String cost = String.valueOf(p.getCost());

                            json_code nw = new json_code();
                            nw.AllItem_json(id, name, code, details, worker, workerid, categoryid, additiondate, cost);

                            //// POP UP ///////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/assets/assetPop/assetPop.fxml").openStream());
                                Scene scene = new Scene(root);
                                scene.setFill(Color.ALICEBLUE);

                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();
                                stage.setOnCloseRequest(e -> {
                                    try {
                                        load1();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ////// POP UP///////

                        });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        //set the custom factory to action column
        columnEdit1.setCellFactory(cellFactory);

    }

    // MAINTENANCE
    public void load2() throws SQLException {
        //DB connection details

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data2 = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM asset_maintenance");

            while (rs.next()) {
                //get string from db
                data2.add(new asset_in_maintenance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        asset2_id.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        asset2_name.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        asset2_code.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        asset2_details.setCellValueFactory(new PropertyValueFactory<>("assetDetails"));
        worker2_name.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        worker2_id.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        category2_id.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        addition2_date.setCellValueFactory(new PropertyValueFactory<>("additionDate"));
        maintenance2_date.setCellValueFactory(new PropertyValueFactory<>("maintenanceDate"));

        maintenance_tbl.setItems(null);
        maintenance_tbl.setItems(data2);

        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<asset_in_maintenance, String>, TableCell<asset_in_maintenance, String>> cellFactory = (param) -> {

            //make the tablecell containing button
            final TableCell<asset_in_maintenance, String> cell = new TableCell<asset_in_maintenance, String>() {

                //override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Now we create the action button
                        final Button editButton = new Button("+");
                        //attach listener on button
                        editButton.setOnAction(event -> {

                            //Extract the clicked object
                            asset_in_maintenance p = getTableView().getItems().get(getIndex());

                            String id = String.valueOf(p.getAssetID());
                            String name = String.valueOf(p.getAssetName());
                            String code = String.valueOf(p.getAssetCode());
                            String details = String.valueOf(p.getAssetDetails());
                            String worker = String.valueOf(p.getWorkerName());
                            String workerid = String.valueOf(p.getWorkerID());
                            String categoryid = String.valueOf(p.getCategoryID());
                            String additiondate = String.valueOf(p.getAdditionDate());
                            String maintenancedate = String.valueOf(p.getMaintenanceDate());

                            json_code nw = new json_code();
                            nw.maintenanceItem_json(id, name, code, details, worker, workerid, categoryid, additiondate, maintenancedate);

                            //// POP UP ///////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/assets/maintenancePop/maintenancePop.fxml").openStream());
                                Scene scene = new Scene(root);
                                scene.setFill(Color.ALICEBLUE);

                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();
                                stage.setOnCloseRequest(e -> {
                                    try {
                                        load2();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ////// POP UP///////                          
                        });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        //set the custom factory to action column
        columnEdit2.setCellFactory(cellFactory);

    }

    //DEFERRED ASSETS
    public void load3() throws SQLException {
        //DB connection details

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data3 = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM deferred_asset");

            while (rs.next()) {
                //get string from db
                data3.add(new deferred_assets(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        asset3_id.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        asset3_name.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        asset3_code.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        asset3_details.setCellValueFactory(new PropertyValueFactory<>("assetDetails"));
        worker3_name.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        worker3_id.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        category3_id.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        addition3_date.setCellValueFactory(new PropertyValueFactory<>("additionDate"));
        deferred3_date.setCellValueFactory(new PropertyValueFactory<>("deferredDate"));
        reason3.setCellValueFactory(new PropertyValueFactory<>("reason"));

        deferred_tbl.setItems(null);
        deferred_tbl.setItems(data3);

        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<deferred_assets, String>, TableCell<deferred_assets, String>> cellFactory = (param) -> {

            //make the tablecell containing button
            final TableCell<deferred_assets, String> cell = new TableCell<deferred_assets, String>() {

                //override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Now we create the action button
                        final Button editButton = new Button("+");
                        //attach listener on button
                        editButton.setOnAction(event -> {

                            //Extract the clicked object
                            deferred_assets p = getTableView().getItems().get(getIndex());

                            //DATA 
                            //System.out.println(p.getMac_address());  String.valueOf(p.getId())
                            String id = String.valueOf(p.getAssetID());
                            String name = String.valueOf(p.getAssetName());
                            String code = String.valueOf(p.getAssetCode());
                            String details = String.valueOf(p.getAssetDetails());
                            String worker = String.valueOf(p.getWorkerName());
                            String workerid = String.valueOf(p.getWorkerID());
                            String categoryid = String.valueOf(p.getCategoryID());
                            String additiondate = String.valueOf(p.getAdditionDate());
                            String deferreddate = String.valueOf(p.getDeferredDate());
                            String reason = String.valueOf(p.getReason());

                            json_code nw = new json_code();
                            nw.deferredItem_json(id, name, code, details, worker, workerid, categoryid, additiondate, deferreddate, reason);

                            ///// POP UP ////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/assets/deferred_pop/deferred_pop.fxml").openStream());
                                Scene scene = new Scene(root);
                                scene.setFill(Color.ALICEBLUE);

                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();
                                stage.setOnCloseRequest(e -> {
                                    try {
                                        load3();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            ///// POP UP ///////
                        });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        //set the custom factory to action column
        columnEdit3.setCellFactory(cellFactory);

    }

    //////////////////////// END /////////////////////////////////////////////
    ////////////////////////   START  ////////////////////////////////////////
    //ALL ASSETS
    public void loada(String searchVal, String table, String colmnVal) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `asset_management_system`.`" + table + "` WHERE (`" + colmnVal + "` LIKE '%" + searchVal + "%')");

            while (rs.next()) {
                //get string from db
                data.add(new all_assets(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
            connection.close();

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        asset_id.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        asset_name.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        asset_code.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        asset_details101.setCellValueFactory(new PropertyValueFactory<>("assetDetails"));
        worker_name.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        worker_id.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        category_id.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        addition_date.setCellValueFactory(new PropertyValueFactory<>("additionDate"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        all_assets_tbl.setItems(null);
        all_assets_tbl.setItems(data);

        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<all_assets, String>, TableCell<all_assets, String>> cellFactory = (param) -> {

            //make the tablecell containing button
            final TableCell<all_assets, String> cell = new TableCell<all_assets, String>() {

                //override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Now we create the action button
                        final Button editButton = new Button("+");
                        //attach listener on button
                        editButton.setOnAction(event -> {

                            //Extract the clicked object
                            all_assets p = getTableView().getItems().get(getIndex());

                            String id = String.valueOf(p.getAssetID());
                            String name = String.valueOf(p.getAssetName());
                            String code = String.valueOf(p.getAssetCode());
                            String details = String.valueOf(p.getAssetDetails());
                            String worker = String.valueOf(p.getWorkerName());
                            String workerid = String.valueOf(p.getWorkerID());
                            String categoryid = String.valueOf(p.getCategoryID());
                            String additiondate = String.valueOf(p.getAdditionDate());
                            String cost = String.valueOf(p.getCost());

                            json_code nw = new json_code();
                            nw.AllItem_json(id, name, code, details, worker, workerid, categoryid, additiondate, cost);

                            //// POP UP ///////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/assets/all_assetsPop/all_assetsPop.fxml").openStream());
                                Scene scene = new Scene(root);
                                scene.setFill(Color.ALICEBLUE);

                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();
                                stage.setOnCloseRequest(e -> {
                                    try {
                                        load();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ////// POP UP///////

                        });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        //set the custom factory to action column
        columnEdit.setCellFactory(cellFactory);
    }

    //AVAILABLE ASSETS
    public void loada1(String searchVal, String table, String colmnVal) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";
            String userName = "root";
            String password = "";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data1 = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `asset_management_system`.`" + table + "` WHERE (`" + colmnVal + "` LIKE '%" + searchVal + "%')");
            while (rs.next()) {
                //get string from db
                data1.add(new assets(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        asset1_id.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        asset1_name.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        asset1_code.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        asset1_details.setCellValueFactory(new PropertyValueFactory<>("assetDetails"));
        worker1_name.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        worker1_id.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        category1_id.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        addition1_date.setCellValueFactory(new PropertyValueFactory<>("additionDate"));
        cost1.setCellValueFactory(new PropertyValueFactory<>("cost"));

        assets_tbl.setItems(null);
        assets_tbl.setItems(data1);

        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<assets, String>, TableCell<assets, String>> cellFactory = (param) -> {

            //make the tablecell containing button
            final TableCell<assets, String> cell = new TableCell<assets, String>() {

                //override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Now we create the action button
                        final Button editButton = new Button("+");
                        //attach listener on button
                        editButton.setOnAction(event -> {

                            //Extract the clicked object
                            assets p = getTableView().getItems().get(getIndex());

                            String id = String.valueOf(p.getAssetID());
                            String name = String.valueOf(p.getAssetName());
                            String code = String.valueOf(p.getAssetCode());
                            String details = String.valueOf(p.getAssetDetails());
                            String worker = String.valueOf(p.getWorkerName());
                            String workerid = String.valueOf(p.getWorkerID());
                            String categoryid = String.valueOf(p.getCategoryID());
                            String additiondate = String.valueOf(p.getAdditionDate());
                            String cost = String.valueOf(p.getCost());

                            json_code nw = new json_code();
                            nw.AllItem_json(id, name, code, details, worker, workerid, categoryid, additiondate, cost);

                            //// POP UP ///////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/assets/assetPop/assetPop.fxml").openStream());
                                Scene scene = new Scene(root);
                                scene.setFill(Color.ALICEBLUE);

                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();
                                stage.setOnCloseRequest(e -> {
                                    try {
                                        load1();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ////// POP UP///////

                        });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        //set the custom factory to action column
        columnEdit1.setCellFactory(cellFactory);

    }

    //MAINTENANCE
    public void loada2(String searchVal, String table, String colmnVal) throws SQLException {
        //DB connection details

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data2 = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `asset_management_system`.`" + table + "` WHERE (`" + colmnVal + "` LIKE '%" + searchVal + "%')");

            while (rs.next()) {
                //get string from db
                data2.add(new asset_in_maintenance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        asset2_id.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        asset2_name.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        asset2_code.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        asset2_details.setCellValueFactory(new PropertyValueFactory<>("assetDetails"));
        worker2_name.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        worker2_id.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        category2_id.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        addition2_date.setCellValueFactory(new PropertyValueFactory<>("additionDate"));
        maintenance2_date.setCellValueFactory(new PropertyValueFactory<>("maintenanceDate"));

        maintenance_tbl.setItems(null);
        maintenance_tbl.setItems(data2);

        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<asset_in_maintenance, String>, TableCell<asset_in_maintenance, String>> cellFactory = (param) -> {

            //make the tablecell containing button
            final TableCell<asset_in_maintenance, String> cell = new TableCell<asset_in_maintenance, String>() {

                //override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Now we create the action button
                        final Button editButton = new Button("+");
                        //attach listener on button
                        editButton.setOnAction(event -> {

                            //Extract the clicked object
                            asset_in_maintenance p = getTableView().getItems().get(getIndex());

                            String id = String.valueOf(p.getAssetID());
                            String name = String.valueOf(p.getAssetName());
                            String code = String.valueOf(p.getAssetCode());
                            String details = String.valueOf(p.getAssetDetails());
                            String worker = String.valueOf(p.getWorkerName());
                            String workerid = String.valueOf(p.getWorkerID());
                            String categoryid = String.valueOf(p.getCategoryID());
                            String additiondate = String.valueOf(p.getAdditionDate());
                            String maintenancedate = String.valueOf(p.getMaintenanceDate());

                            json_code nw = new json_code();
                            nw.maintenanceItem_json(id, name, code, details, worker, workerid, categoryid, additiondate, maintenancedate);

                            //// POP UP ///////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/assets/maintenancePop/maintenancePop.fxml").openStream());
                                Scene scene = new Scene(root);
                                scene.setFill(Color.ALICEBLUE);

                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();
                                stage.setOnCloseRequest(e -> {
                                    try {
                                        load2();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ////// POP UP///////                          
                        });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        //set the custom factory to action column
        columnEdit2.setCellFactory(cellFactory);

    }

    //DEFERRED ASSETS
    public void loada3(String searchVal, String table, String colmnVal) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String dbName = "asset_management_system";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            data3 = FXCollections.observableArrayList();
            //Execute query and store result in a resultset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `asset_management_system`.`" + table + "` WHERE (`" + colmnVal + "` LIKE '%" + searchVal + "%')");

            while (rs.next()) {
                //get string from db
                data3.add(new deferred_assets(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }

        //set cell value factor to tableview.
        //PropertyValue Factory must be set the same with the one set in model class.
        asset3_id.setCellValueFactory(new PropertyValueFactory<>("assetID"));
        asset3_name.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        asset3_code.setCellValueFactory(new PropertyValueFactory<>("assetCode"));
        asset3_details.setCellValueFactory(new PropertyValueFactory<>("assetDetails"));
        worker3_name.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        worker3_id.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        category3_id.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        addition3_date.setCellValueFactory(new PropertyValueFactory<>("additionDate"));
        deferred3_date.setCellValueFactory(new PropertyValueFactory<>("deferredDate"));
        reason3.setCellValueFactory(new PropertyValueFactory<>("reason"));

        deferred_tbl.setItems(null);
        deferred_tbl.setItems(data3);

        //Lets create a cell factory to insert a button in every row.
        Callback<TableColumn<deferred_assets, String>, TableCell<deferred_assets, String>> cellFactory = (param) -> {

            //make the tablecell containing button
            final TableCell<deferred_assets, String> cell = new TableCell<deferred_assets, String>() {

                //override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //Now we create the action button
                        final Button editButton = new Button("+");
                        //attach listener on button
                        editButton.setOnAction(event -> {

                            //Extract the clicked object
                            deferred_assets p = getTableView().getItems().get(getIndex());

                            //DATA 
                            //System.out.println(p.getMac_address());  String.valueOf(p.getId())
                            String id = String.valueOf(p.getAssetID());
                            String name = String.valueOf(p.getAssetName());
                            String code = String.valueOf(p.getAssetCode());
                            String details = String.valueOf(p.getAssetDetails());
                            String worker = String.valueOf(p.getWorkerName());
                            String workerid = String.valueOf(p.getWorkerID());
                            String categoryid = String.valueOf(p.getCategoryID());
                            String additiondate = String.valueOf(p.getAdditionDate());
                            String deferreddate = String.valueOf(p.getDeferredDate());
                            String reason = String.valueOf(p.getReason());

                            json_code nw = new json_code();
                            nw.deferredItem_json(id, name, code, details, worker, workerid, categoryid, additiondate, deferreddate, reason);

                            ///// POP UP ////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/assets/deferred_pop/deferred_pop.fxml").openStream());
                                Scene scene = new Scene(root);
                                scene.setFill(Color.ALICEBLUE);

                                stage.setScene(scene);

                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                stage.setResizable(false);
                                stage.resizableProperty();
                                stage.setOnCloseRequest(e -> {
                                    try {
                                        load3();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });

                                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                                scene.setFill(Color.ALICEBLUE);
                                //stage.initStyle(StageStyle.UNDECORATED);
                                stage.showAndWait();

                            } catch (IOException ex) {
                                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            ///// POP UP ///////
                        });
                        //Setting created button
                        setGraphic(editButton);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        //set the custom factory to action column
        columnEdit3.setCellFactory(cellFactory);
    }

    ///////////////////////////////  END  ///////////////////////////////////
    public void refresh(String name) throws SQLException {

        if ("all assets".equals(name)) {
            load();

        } else if ("available assets".equals(name)) {
            load1();

        } else if ("maintenance".equals(name)) {
            load2();

        } else if ("deferred assets".equals(name)) {
            load3();

        }
    }

    ///////////// START PRINTING ////////////////////////////////////////////
    //ALL ASSETS PRINTER
    @FXML
    void printData(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException, ParseException {
        Document my_pdf_report = new Document();

        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./files/ALL ASSETS REPORT.pdf"));
        my_pdf_report.open();

        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(9);
        my_report_table.setWidthPercentage(100);
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET CODE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET DETAILS", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("CATEGORY ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ADDITION DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("COST", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.completeRow();

        //create a cell object
        PdfPCell table_cell;
        Paragraph space = new Paragraph("  ");
        Float indent = 10.0f;
        Paragraph title = new Paragraph(" MOTIVATION CHARITABLE TRUST ");
        Paragraph tableName = new Paragraph("TABLE NAME:  ALL ASSETS TABLE ");
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
        for (all_assets o : all_assets_tbl.getItems()) {

            // System.err.println(columnA.getCellData(o));
            String id = asset_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(id, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String name = asset_name.getCellData(o);
            table_cell = new PdfPCell(new Phrase(name, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String code = asset_code.getCellData(o);
            table_cell = new PdfPCell(new Phrase(code, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String details = asset_details101.getCellData(o);
            table_cell = new PdfPCell(new Phrase(details, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String workerName = worker_name.getCellData(o);
            table_cell = new PdfPCell(new Phrase(workerName, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String workerID = worker_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(workerID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String catID = category_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(catID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String addDate = addition_date.getCellData(o);
            table_cell = new PdfPCell(new Phrase(addDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String cost0 = cost.getCellData(o);
            table_cell = new PdfPCell(new Phrase(cost0, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

        }
        /* Attach report table to PDF */
        my_pdf_report.add(my_report_table);
        my_pdf_report.close();

        notify.flash(printer, " DOCUMENT HAS BEEN CREATED ");

    }

    //AVAILABLE ASSETS PRINTER
    @FXML
    void printData1(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException, ParseException {
        Document my_pdf_report = new Document();

        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./files/AVAILABLE ASSETS REPORT.pdf"));
        my_pdf_report.open();

        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(9);
        my_report_table.setWidthPercentage(100);
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET CODE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET DETAILS", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("CATEGORY ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ADDITION DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("COST", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.completeRow();

        //create a cell object
        PdfPCell table_cell;
        Paragraph space = new Paragraph("  ");
        Float indent = 10.0f;
        Paragraph title = new Paragraph(" MOTIVATION CHARITABLE TRUST ");
        Paragraph tableName = new Paragraph("TABLE NAME:  CURRENT ASSETS ");
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
        for (assets o : assets_tbl.getItems()) {

            // System.err.println(columnA.getCellData(o));
            String id = asset1_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(id, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String name = asset1_name.getCellData(o);
            table_cell = new PdfPCell(new Phrase(name, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String code = asset1_code.getCellData(o);
            table_cell = new PdfPCell(new Phrase(code, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String details = asset1_details.getCellData(o);
            table_cell = new PdfPCell(new Phrase(details, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String workerName = worker1_name.getCellData(o);
            table_cell = new PdfPCell(new Phrase(workerName, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String workerID = worker1_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(workerID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String catID = category1_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(catID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String addDate = addition1_date.getCellData(o);
            table_cell = new PdfPCell(new Phrase(addDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String cost0 = cost1.getCellData(o);
            table_cell = new PdfPCell(new Phrase(cost0, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

        }
        /* Attach report table to PDF */
        my_pdf_report.add(my_report_table);
        my_pdf_report.close();

        notify.flash(printer, " DOCUMENT HAS BEEN CREATED ");
    }

    //ASSET IN MAINTENANCE PRINTER
    @FXML
    void printData2(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException, ParseException {
        Document my_pdf_report = new Document();

        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./files/ASSETS IN MAINTENANCE REPORT.pdf"));
        my_pdf_report.open();

        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(9);
        my_report_table.setWidthPercentage(100);
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET CODE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET DETAILS", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("CATEGORY ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ADDITION DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("MAINTENANCE DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7))));
        my_report_table.completeRow();

        //create a cell object
        PdfPCell table_cell;
        Paragraph space = new Paragraph("  ");
        Float indent = 10.0f;
        Paragraph title = new Paragraph(" MOTIVATION CHARITABLE TRUST ");
        Paragraph tableName = new Paragraph("TABLE NAME:  MAINTENANCE TABLE ");
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
        for (asset_in_maintenance o : maintenance_tbl.getItems()) {

            // System.err.println(columnA.getCellData(o));
            String id = asset2_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(id, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String name = asset2_name.getCellData(o);
            table_cell = new PdfPCell(new Phrase(name, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String code = asset2_code.getCellData(o);
            table_cell = new PdfPCell(new Phrase(code, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String details = asset2_details.getCellData(o);
            table_cell = new PdfPCell(new Phrase(details, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String workerName = worker2_name.getCellData(o);
            table_cell = new PdfPCell(new Phrase(workerName, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String workerID = worker2_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(workerID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String catID = category2_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(catID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String addDate = addition2_date.getCellData(o);
            table_cell = new PdfPCell(new Phrase(addDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String cost0 = maintenance2_date.getCellData(o);
            table_cell = new PdfPCell(new Phrase(cost0, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

        }
        /* Attach report table to PDF */
        my_pdf_report.add(my_report_table);
        my_pdf_report.close();

        notify.flash(printer, " DOCUMENT HAS BEEN CREATED ");

    }

    //// DEFERRED ASSET PRINTER
    @FXML
    void printData3(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException, ParseException {
        Document my_pdf_report = new Document();

        PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./files/DEFERRED ASSET REPORT.pdf"));
        my_pdf_report.open();

        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(10);
        my_report_table.setWidthPercentage(100);
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET CODE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ASSET DETAILS", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER NAME", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("WORKER ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("CATEGORY ID", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("ADDITION DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("DEFERRED DATE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.addCell(new PdfPCell(new Phrase("REASON", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8))));
        my_report_table.completeRow();

        //create a cell object
        PdfPCell table_cell;
        Paragraph space = new Paragraph("  ");
        Float indent = 10.0f;
        Paragraph title = new Paragraph(" MOTIVATION CHARITABLE TRUST ");
        Paragraph tableName = new Paragraph("TABLE NAME:  DEFERRED ASSETS TABLE ");
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
        for (deferred_assets o : deferred_tbl.getItems()) {

            // System.err.println(columnA.getCellData(o));
            String id = asset3_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(id, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String name = asset3_name.getCellData(o);
            table_cell = new PdfPCell(new Phrase(name, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String code = asset3_code.getCellData(o);
            table_cell = new PdfPCell(new Phrase(code, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String details = asset3_details.getCellData(o);
            table_cell = new PdfPCell(new Phrase(details, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String workerName = worker3_name.getCellData(o);
            table_cell = new PdfPCell(new Phrase(workerName, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String workerID = worker3_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(workerID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String catID = category3_id.getCellData(o);
            table_cell = new PdfPCell(new Phrase(catID, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String addDate = addition3_date.getCellData(o);
            table_cell = new PdfPCell(new Phrase(addDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String deferredDate = deferred3_date.getCellData(o);
            table_cell = new PdfPCell(new Phrase(deferredDate, FontFactory.getFont(FontFactory.HELVETICA, 8)));
            my_report_table.addCell(table_cell);

            String reason = reason3.getCellData(o);
            table_cell = new PdfPCell(new Phrase(reason, FontFactory.getFont(FontFactory.HELVETICA, 7)));
            my_report_table.addCell(table_cell);

        }
        /* Attach report table to PDF */
        my_pdf_report.add(my_report_table);
        my_pdf_report.close();

        notify.flash(printer, " DOCUMENT HAS BEEN CREATED ");

    }

    //////////////// END PRINTING ////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pane0.setOnSelectionChanged(e -> {
            try {
                load();

            } catch (SQLException ex) {
                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        pane1.setOnSelectionChanged(e -> {
            try {
                load1();

            } catch (SQLException ex) {
                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        pane2.setOnSelectionChanged(e -> {
            try {

                load2();

            } catch (SQLException ex) {
                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        pane3.setOnSelectionChanged(e -> {
            try {

                load3();

            } catch (SQLException ex) {
                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //ALL ASSETS
        choice.getItems().addAll(" ", "ASSET ID", "ASSET NAME", "ASSET CODE", "ASSET DETAILS", "WORKER NAME", "WORKER ID", "CATEGORY", "ADDITION DATE", "COST");
        choice.setValue(" ");

        //AVAILABLE ASSETS
        choice1.getItems().addAll(" ", "ASSET ID", "ASSET NAME", "ASSET CODE", "ASSET DETAILS", "WORKER NAME", "WORKER ID", "CATEGORY", "ADDITION DATE", "COST");
        choice1.setValue(" ");

        //MAINTENANCE
        choice2.getItems().addAll(" ", "ASSET ID", "ASSET NAME", "ASSET CODE", "ASSET DETAILS", "WORKER NAME", "WORKER ID", "CATEGORY", "ADDITION DATE", "MAINTENANCE DATE");
        choice2.setValue(" ");

        //DEFERRED ASSETS
        choice3.getItems().addAll(" ", "ASSET ID", "ASSET NAME", "ASSET CODE", "ASSET DETAILS", "WORKER NAME", "WORKER ID", "CATEGORY", "ADDITION DATE", "DEFERRED DATE", "REASON");
        choice3.setValue(" ");

        if (search.getText().isEmpty()) {

            try {
                load();
            } catch (SQLException ex) {
                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (search1.getText().isEmpty()) {
            try {
                load1();
            } catch (SQLException ex) {
                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (search2.getText().isEmpty()) {
            try {
                load2();
            } catch (SQLException ex) {
                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (search3.getText().isEmpty()) {
            try {
                load3();
            } catch (SQLException ex) {
                Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        ///// SEARCH
        /////ALL ASSETS
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            //choice.getItems().addAll("ASSET ID", "ASSET NAME", "ASSET CODE", "ASSET DETAILS", "WORKER NAME", "WORKER ID", "CATEGORY", "ADDITION DATE", "COST");

            String tbl = "assets";
            if (oldValue != newValue) {
                try {

                    if (!search.getText().isEmpty()) {

                        String BoxValue = choice.getValue().toString();

                        if ("ASSET ID".equals(BoxValue)) {
                            String clmn = "assetID";
                            loada(search.getText(), tbl, clmn);

                        } else if ("ASSET NAME".equals(BoxValue)) {
                            String clmn = "assetName";
                            loada(search.getText(), tbl, clmn);
                        } else if ("ASSET CODE".equals(BoxValue)) {
                            String clmn = "assetCode";
                            loada(search.getText(), tbl, clmn);
                        } else if ("ASSET DETAILS".equals(BoxValue)) {
                            String clmn = "assetDetails";
                            loada(search.getText(), tbl, clmn);
                        } else if ("WORKER NAME".equals(BoxValue)) {
                            String clmn = "workerName";
                            loada(search.getText(), tbl, clmn);
                        } else if ("WORKER ID".equals(BoxValue)) {
                            String clmn = "workerID";
                            loada(search.getText(), tbl, clmn);
                        } else if ("CATEGORY".equals(BoxValue)) {
                            String clmn = "categoryID";
                            loada(search.getText(), tbl, clmn);
                        } else if ("ADDITION DATE".equals(BoxValue)) {
                            String clmn = "additionDate";
                            loada(search.getText(), tbl, clmn);
                        } else if ("COST".equals(BoxValue)) {
                            String clmn = "cost";
                            loada(search.getText(), tbl, clmn);
                        } else if (" ".equals(BoxValue)) {

                            try {
                                refresh("all assets");
                                // System.out.print("no empty search");
                            } catch (SQLException ex) {
                                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        if (search.getText().isEmpty()) {
                            try {
                                refresh("all assets");
                                // System.out.print("no empty search");
                            } catch (SQLException ex) {
                                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(assetSearch.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        ////////////

        /////AVAILABLE ASSETS
        search1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            //choice.getItems().addAll("ASSET ID", "ASSET NAME", "ASSET CODE", "ASSET DETAILS", "WORKER NAME", "WORKER ID", "CATEGORY", "ADDITION DATE", "COST");

            String tbl = "available_assets";
            if (oldValue != newValue) {
                try {

                    if (!search1.getText().isEmpty()) {

                        String BoxValue = choice1.getValue().toString();

                        if ("ASSET ID".equals(BoxValue)) {
                            String clmn = "assetID";
                            loada1(search1.getText(), tbl, clmn);

                        } else if ("ASSET NAME".equals(BoxValue)) {
                            String clmn = "assetName";
                            loada1(search1.getText(), tbl, clmn);
                        } else if ("ASSET CODE".equals(BoxValue)) {
                            String clmn = "assetCode";
                            loada1(search1.getText(), tbl, clmn);
                        } else if ("ASSET DETAILS".equals(BoxValue)) {
                            String clmn = "assetDetails";
                            loada1(search1.getText(), tbl, clmn);
                        } else if ("WORKER NAME".equals(BoxValue)) {
                            String clmn = "workerName";
                            loada1(search1.getText(), tbl, clmn);
                        } else if ("WORKER ID".equals(BoxValue)) {
                            String clmn = "workerID";
                            loada1(search1.getText(), tbl, clmn);
                        } else if ("CATEGORY".equals(BoxValue)) {
                            String clmn = "categoryID";
                            loada1(search1.getText(), tbl, clmn);
                        } else if ("ADDITION DATE".equals(BoxValue)) {
                            String clmn = "additionDate";
                            loada1(search1.getText(), tbl, clmn);
                        } else if ("COST".equals(BoxValue)) {
                            String clmn = "cost";
                            loada1(search1.getText(), tbl, clmn);
                        } else if (" ".equals(BoxValue)) {

                            try {
                                refresh("available assets");
                                // System.out.print("no empty search");
                            } catch (SQLException ex) {
                                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        if (search1.getText().isEmpty()) {
                            try {
                                refresh("available assets");
                                // System.out.print("no empty search");
                            } catch (SQLException ex) {
                                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(assetSearch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AssetsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        /////////////

        /////MAINTENANCE
        search2.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            //choice.getItems().addAll("ASSET ID", "ASSET NAME", "ASSET CODE", "ASSET DETAILS", "WORKER NAME", "WORKER ID", "CATEGORY", "ADDITION DATE", "COST");

            String tbl = "asset_maintenance";
            if (oldValue != newValue) {
                try {

                    if (!search2.getText().isEmpty()) {

                        String BoxValue = choice2.getValue().toString();

                        if ("ASSET ID".equals(BoxValue)) {
                            String clmn = "assetID";
                            loada2(search2.getText(), tbl, clmn);

                        } else if ("ASSET NAME".equals(BoxValue)) {
                            String clmn = "assetName";
                            loada2(search2.getText(), tbl, clmn);
                        } else if ("ASSET CODE".equals(BoxValue)) {
                            String clmn = "assetCode";
                            loada2(search2.getText(), tbl, clmn);
                        } else if ("ASSET DETAILS".equals(BoxValue)) {
                            String clmn = "assetDetails";
                            loada2(search2.getText(), tbl, clmn);
                        } else if ("WORKER NAME".equals(BoxValue)) {
                            String clmn = "workerName";
                            loada2(search2.getText(), tbl, clmn);
                        } else if ("WORKER ID".equals(BoxValue)) {
                            String clmn = "workerID";
                            loada2(search2.getText(), tbl, clmn);
                        } else if ("CATEGORY".equals(BoxValue)) {
                            String clmn = "categoryID";
                            loada2(search2.getText(), tbl, clmn);
                        } else if ("ADDITION DATE".equals(BoxValue)) {
                            String clmn = "additionDate";
                            loada2(search2.getText(), tbl, clmn);
                        } else if ("MAINTENANCE DATE".equals(BoxValue)) {
                            String clmn = "maintenanceDate";
                            loada2(search2.getText(), tbl, clmn);
                        } else if (" ".equals(BoxValue)) {

                            try {
                                refresh("maintenance");
                                // System.out.print("no empty search");
                            } catch (SQLException ex) {
                                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        if (search2.getText().isEmpty()) {
                            try {
                                refresh("maintenance");
                                // System.out.print("no empty search");
                            } catch (SQLException ex) {
                                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(assetSearch.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        //////////

        /////DEFERRED
        search3.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            //choice.getItems().addAll("ASSET ID", "ASSET NAME", "ASSET CODE", "ASSET DETAILS", "WORKER NAME", "WORKER ID", "CATEGORY", "ADDITION DATE", "COST");

            String tbl = "deferred_asset";
            if (oldValue != newValue) {
                try {

                    if (!search3.getText().isEmpty()) {

                        String BoxValue = choice3.getValue().toString();

                        if ("ASSET ID".equals(BoxValue)) {
                            String clmn = "assetID";
                            loada3(search3.getText(), tbl, clmn);

                        } else if ("ASSET NAME".equals(BoxValue)) {
                            String clmn = "assetName";
                            loada3(search3.getText(), tbl, clmn);
                        } else if ("ASSET CODE".equals(BoxValue)) {
                            String clmn = "assetCode";
                            loada3(search3.getText(), tbl, clmn);
                        } else if ("ASSET DETAILS".equals(BoxValue)) {
                            String clmn = "assetDetails";
                            loada3(search3.getText(), tbl, clmn);
                        } else if ("WORKER NAME".equals(BoxValue)) {
                            String clmn = "workerName";
                            loada3(search3.getText(), tbl, clmn);
                        } else if ("WORKER ID".equals(BoxValue)) {
                            String clmn = "workerID";
                            loada3(search3.getText(), tbl, clmn);
                        } else if ("CATEGORY".equals(BoxValue)) {
                            String clmn = "categoryID";
                            loada3(search3.getText(), tbl, clmn);
                        } else if ("ADDITION DATE".equals(BoxValue)) {
                            String clmn = "additionDate";
                            loada3(search3.getText(), tbl, clmn);
                        } else if ("DEFERRED DATE".equals(BoxValue)) {
                            String clmn = "deferredDate";
                            loada3(search3.getText(), tbl, clmn);
                        } else if ("REASON".equals(BoxValue)) {
                            String clmn = "reason";
                            loada3(search3.getText(), tbl, clmn);
                        } else if (" ".equals(BoxValue)) {

                            try {
                                refresh("deferred assets");
                                // System.out.print("no empty search");
                            } catch (SQLException ex) {
                                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        if (search3.getText().isEmpty()) {
                            try {
                                refresh("deferred assets");
                                // System.out.print("no empty search");
                            } catch (SQLException ex) {
                                Logger.getLogger(WorkersController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(assetSearch.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

    }

}
