package asset_management_system.assets;

import asset_management_system.usedAlot.json_code;
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
    private VBox vbox;

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

    @FXML
    void addNew(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/asset_management_system/assets/addAsset/addAsset.fxml"));
        Scene newScene = new Scene(root);

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
    void minimizeIt(ActionEvent event) {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        // is stage minimizable into task bar. (true | false)
        stage.setIconified(true);

    }

    @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();

    }

    @FXML
    void imageClicked(MouseEvent event) throws IOException {
        //you can use #onMousePressed or #orMouseClicked
        Parent sceneFxml = FXMLLoader.load(getClass().getResource("/asset_management_system/dashboard/dashboard.fxml"));
        Scene newScene = new Scene(sceneFxml);

        //getting stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //setting scene on stage
        window.setScene(newScene);
        window.show();

    }

    //FOR DEFERRED ASSETS
    @FXML
    void loadFromDB3(MouseEvent event) throws SQLException {
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

                                stage.setScene(scene);

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

    //FOR ALL ASSETS
    @FXML
    void loadFromDB(MouseEvent event) throws SQLException {
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
                            
                            json_code nw=new json_code();
                            nw.AllItem_json(id, name,code, details, worker, workerid, categoryid, additiondate, cost);
                            

                            //// POP UP ///////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/assets/all_assetsPop/all_assetsPop.fxml").openStream());
                                Scene scene = new Scene(root);

                                stage.setScene(scene);

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
    @FXML
    void loadFromDB1(MouseEvent event) throws SQLException {
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
                            
                            json_code nw=new json_code();
                            nw.AllItem_json(id, name,code, details, worker, workerid, categoryid, additiondate, cost);
                            

                            //// POP UP ///////
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent root;
                            try {
                                root = fxmlLoader.load(getClass().getResource("/asset_management_system/assets/assetPop/assetPop.fxml").openStream());
                                Scene scene = new Scene(root);

                                stage.setScene(scene);

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

    //FOR ASSETS IN MAINTENANCE
    @FXML
    void loadFromDB2(MouseEvent event) throws SQLException {
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

                            //DATA 
                            //System.out.println(p.getMac_address());  String.valueOf(p.getId())
                            //PREVENT USER FROM EDITING TEXTFIELDS                               
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
