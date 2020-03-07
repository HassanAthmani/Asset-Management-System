
package asset_management_system.assets;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AssetsController implements Initializable {
    
    
    @FXML
    private VBox vbox;

    @FXML
    private AnchorPane cost;

    @FXML
    private TableView<?> all_assets_tbl;

    @FXML
    private TableColumn<?, ?> asset_id;

    @FXML
    private TableColumn<?, ?> asset_name;

    @FXML
    private TableColumn<?, ?> asset_code;

    @FXML
    private TableColumn<?, ?> asset_details;

    @FXML
    private TableColumn<?, ?> worker_name;

    @FXML
    private TableColumn<?, ?> worker_id;

    @FXML
    private TableColumn<?, ?> category_id;

    @FXML
    private TableColumn<?, ?> addition_date;

    @FXML
    private TextField search;

    @FXML
    private ChoiceBox<?> choice;

    @FXML
    private TableView<?> assets_tbl;

    @FXML
    private TableColumn<?, ?> asset1_id;

    @FXML
    private TableColumn<?, ?> asset1_name;

    @FXML
    private TableColumn<?, ?> asset1_code;

    @FXML
    private TableColumn<?, ?> asset1_details;

    @FXML
    private TableColumn<?, ?> worker1_name;

    @FXML
    private TableColumn<?, ?> worker1_id;

    @FXML
    private TableColumn<?, ?> category1_id;

    @FXML
    private TableColumn<?, ?> addition1_date;

    @FXML
    private TableColumn<?, ?> cost1;

    @FXML
    private ChoiceBox<?> choice1;

    @FXML
    private TextField search1;

    @FXML
    private TableView<?> maintenance_tbl;

    @FXML
    private TableColumn<?, ?> asset2_id;

    @FXML
    private TableColumn<?, ?> asset2_name;

    @FXML
    private TableColumn<?, ?> asset2_code;

    @FXML
    private TableColumn<?, ?> asset2_details;

    @FXML
    private TableColumn<?, ?> worker2_name;

    @FXML
    private TableColumn<?, ?> worker2_id;

    @FXML
    private TableColumn<?, ?> category2_id;

    @FXML
    private TableColumn<?, ?> addition2_date;

    @FXML
    private TableColumn<?, ?> maintenance2_date;

    @FXML
    private TextField search2;

    @FXML
    private ChoiceBox<?> choice2;

    @FXML
    private TableView<?> deferred_tbl;

    @FXML
    private ChoiceBox<?> choice3;

    @FXML
    private TextField search3;
    
    @FXML
    private ImageView backToDashboard;

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


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
