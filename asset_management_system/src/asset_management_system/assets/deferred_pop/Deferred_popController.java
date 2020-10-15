/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.assets.deferred_pop;

import asset_management_system.assets.all_assetsPop.All_assetsPopController;
import asset_management_system.usedAlot.QR_Creator;
import asset_management_system.usedAlot.json_read;
import asset_management_system.usedAlot.notification;
import com.google.zxing.WriterException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Deferred_popController implements Initializable {
    
    @FXML
    private JFXTextField assetID;

    @FXML
    private JFXTextField assetName;

    @FXML
    private JFXTextField assetDetails;

    @FXML
    private JFXTextField category;

    @FXML
    private JFXTextField additionDate;

    @FXML
    private JFXTextField deferredDate;

    @FXML
    private JFXTextField reason;

    @FXML
    private JFXTextField assetCode;

    @FXML
    private JFXTextField workerName;

    @FXML
    private JFXTextField workerID;

    @FXML
    private ImageView closeApp;
    
    @FXML
    private ImageView qr_code;
    
    @FXML
    private Label deferredLbl;
    
     @FXML
    private Button printData;
     
     notification notify = new notification();
     
      @FXML
    void printStuff(ActionEvent event) throws FileNotFoundException, DocumentException {
        try {
            Document my_pdf_report = new Document();

            
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("./files/ASSET_" + assetID.getText() + ".pdf"));
            my_pdf_report.open(); //MOST IMPORTANT
            Paragraph title = new Paragraph(" MOTIVATION CHARITABLE TRUST ");
            Paragraph cost1 = new Paragraph(" DEFERRED ASSET ");
            Paragraph assetid = new Paragraph(" ASSET ID: " + assetID.getText());
            Paragraph assetname = new Paragraph(" ASSET NAME: " + assetName.getText());
            Paragraph assetcode = new Paragraph(" ASSET CODE: " + assetCode.getText());
            Paragraph assetdetails = new Paragraph(" ASSET DETAILS: " + assetDetails.getText());
            Paragraph workername = new Paragraph(" WORKER NAME: " + workerName.getText());
            Paragraph workerid = new Paragraph(" WORKER ID: " + workerID.getText());
            Paragraph catid = new Paragraph(" CATEGORY ID: " + category.getText());            
            Paragraph additiondate = new Paragraph(" ADDITION DATE: " + additionDate.getText());
            Paragraph name = new Paragraph(" ASSET ");

            my_pdf_report.add(title);
            my_pdf_report.add(name);
            my_pdf_report.add(assetname);
            my_pdf_report.add(assetcode);
            my_pdf_report.add(assetid);
            my_pdf_report.add(assetdetails);
            my_pdf_report.add(catid);
            my_pdf_report.add(workername);
            my_pdf_report.add(workerid);
            my_pdf_report.add(cost1);
            my_pdf_report.add(additiondate);

            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(".//qrCode//Asset" + assetID.getText() + ".png");
            my_pdf_report.add(image);

            my_pdf_report.close();
            notify.flash(printData, "ASSET DETAILS HAVE BEEN PRINTED.");

        } catch (BadElementException ex) {
            Logger.getLogger(Deferred_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Deferred_popController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 @FXML
    void closeAppWindow(MouseEvent event) {
        //getting stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

    }
    ///////////// image iwekwe kwa ile function itafill the fields
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        deferredLbl.setStyle("-fx-font-family: 'Lobster', cursive; -fx-font-size: 30; -fx-font-weight: bold;");
        json_read nw=new json_read();
        
        try {
            nw.setToDeferred(assetID, assetName, assetCode, assetDetails, workerName, workerID, category, additionDate, deferredDate, reason);
            
             assetID.setEditable(false);
             assetName.setEditable(false);
             assetCode.setEditable(false);
             assetDetails.setEditable(false);
             workerName.setEditable(false);
             workerID.setEditable(false);
             reason.setEditable(false);
             additionDate.setEditable(false);
             deferredDate.setEditable(false);
             reason.setEditable(false);
             
              QR_Creator maker=new QR_Creator();
             maker.QRGen(assetID.getText(), assetCode.getText(), assetName.getText());
             
             String path=".//qrCode//Asset"+assetID.getText()+".png";
             Image imageObject = new Image(new FileInputStream(path));
             
            // ImageView image = new ImageView(imageObject);  
             qr_code.setImage(imageObject);
           
            
        } catch (IOException ex) {
            Logger.getLogger(Deferred_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Deferred_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Deferred_popController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            Logger.getLogger(Deferred_popController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
