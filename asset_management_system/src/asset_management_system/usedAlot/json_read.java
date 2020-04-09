
package asset_management_system.usedAlot;

import asset_management_system.assets.assetPop.AssetPopController;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class json_read {
    
    String userName = "root";
    String password = "";
    public Connection connection;
    
    public String profile_id() throws FileNotFoundException, IOException, ParseException{
        
        JSONParser parser = new JSONParser();
        String name;
      
         Object obj = parser.parse(new FileReader(".//json//worker_id.json"));
         JSONObject jsonObject = (JSONObject)obj;
         name = (String)jsonObject.get("worker_id");
          
      
      return name;
   }
    
    public String asset_id() throws FileNotFoundException, IOException, ParseException{
        
        JSONParser parser = new JSONParser();
        String name;
      
         Object obj = parser.parse(new FileReader(".//json//addedAssetID.json"));
         JSONObject jsonObject = (JSONObject)obj;
         name = (String)jsonObject.get("asset_id");
          
      
      return name;
   }
    
    
    
      public void backToDB() throws FileNotFoundException, IOException, ParseException, SQLException{
           JSONParser parser = new JSONParser();
        String id;
        String name;
        String lastName;
        String tell;
        String email;
        String natID;
        String dept;
        String location;
        String pas;
        
      
         Object obj = parser.parse(new FileReader(".//json//worker_profile.json"));
         JSONObject jsonObject = (JSONObject)obj;
         id = (String)jsonObject.get("worker_id");
         name = (String)jsonObject.get("workerName");
        lastName = (String)jsonObject.get("workerLastName");
         tell = (String)jsonObject.get("workerTell");
         email = (String)jsonObject.get("workerEmail");
         natID = (String)jsonObject.get("workerNationalID");
         dept = (String)jsonObject.get("department");
         location = (String)jsonObject.get("location");
         pas = (String)jsonObject.get("pass_word");
         
         DBOps backtoDB=new DBOps();
         backtoDB.ProfileaddData(id,name, lastName, tell, email, natID, dept, location, pas);
          
      } 
      
      
      public String profile_password() throws FileNotFoundException, IOException, ParseException{
        
        JSONParser parser = new JSONParser();
        String pas;
      
         Object obj = parser.parse(new FileReader(".//json//worker_profile.json"));
         JSONObject jsonObject = (JSONObject)obj;
         pas = (String)jsonObject.get("pass_word");
          
      
      return pas;
   }
      
      
      public void setToDeferred(JFXTextField id,JFXTextField name,JFXTextField code,JFXTextField details,JFXTextField worker,JFXTextField workerid,JFXTextField category1,JFXTextField date1,JFXTextField date2,JFXTextField reason1) throws FileNotFoundException, IOException, ParseException, SQLException{
           JSONParser parser = new JSONParser();
           
         Object obj = parser.parse(new FileReader(".//json//deferredItem.json"));
         JSONObject jsonObject = (JSONObject)obj;
         
        
        
      
        String assetID = (String)jsonObject.get("assetID");
        String assetName = (String)jsonObject.get("assetName");
        String assetCode = (String)jsonObject.get("assetCode");
        String assetDetails = (String)jsonObject.get("assetDetails");
        String workerName = (String)jsonObject.get("workerName");
        String workerID = (String)jsonObject.get("workerID");
        String category = (String)jsonObject.get("category");
        String additionDate = (String)jsonObject.get("additionDate");
        String deferredDate = (String)jsonObject.get("deferredDate");
        String reason = (String)jsonObject.get("reason");
        
         String path="Asset12.png";
             Image imageObject = new Image(new FileInputStream(path));
             
            // ImageView image = new ImageView(imageObject);  
          //  qr_code.setImage(imageObject);
        
        id.setText(assetID);
        name.setText(assetName);
        code.setText(assetCode);
        details.setText(assetDetails);
        worker.setText(workerName);
        workerid.setText(workerID);
        category1.setText(category);
        date1.setText(additionDate);
        date2.setText(deferredDate);
        reason1.setText(reason);
         
        
          
      } 
      
      public void setToAllItems(JFXTextField id,JFXTextField name,JFXTextField code,JFXTextField details,JFXTextField worker,JFXTextField workerid,JFXTextField category1,JFXTextField date1,JFXTextField cost) throws FileNotFoundException, IOException, ParseException, SQLException{
           JSONParser parser = new JSONParser();
           
         Object obj = parser.parse(new FileReader(".//json//AllItem.json"));
         JSONObject jsonObject = (JSONObject)obj;
         
        
        
      
        String assetID = (String)jsonObject.get("assetID");
        String assetName = (String)jsonObject.get("assetName");
        String assetCode = (String)jsonObject.get("assetCode");
        String assetDetails = (String)jsonObject.get("assetDetails");
        String workerName = (String)jsonObject.get("workerName");
        String workerID = (String)jsonObject.get("workerID");
        String category = (String)jsonObject.get("category");
        String additionDate = (String)jsonObject.get("additionDate");
        String cost1 = (String)jsonObject.get("cost");
        
        
         String path="Asset12.png";
             Image imageObject = new Image(new FileInputStream(path));
             
            // ImageView image = new ImageView(imageObject);  
           // qr_code.setImage(imageObject);
        
        id.setText(assetID);
        name.setText(assetName);
        code.setText(assetCode);
        details.setText(assetDetails);
        worker.setText(workerName);
        workerid.setText(workerID);
        category1.setText(category);
        date1.setText(additionDate);
        cost.setText(cost1);
        
         
        
          
      } 
      
      
      public void setToWithUser(JFXTextField id,JFXTextField workerID,JFXTextField workerName,JFXTextField workerTell,JFXTextField workerEmail,JFXTextField assetID,JFXTextField assetName,JFXTextField assetCode,JFXTextField assigned_date,JFXTextField assignedBy) throws FileNotFoundException, IOException, ParseException, SQLException{
           JSONParser parser = new JSONParser();
           
         Object obj = parser.parse(new FileReader(".//json//WithUser.json"));
         JSONObject jsonObject = (JSONObject)obj;
         
        
        
      
        String transID = (String)jsonObject.get("transID");
        String workerID1 = (String)jsonObject.get("workerID");
        String workerName1 = (String)jsonObject.get("workerName");
        String workerTell1 = (String)jsonObject.get("workerTell");
        String workerEmail1 = (String)jsonObject.get("workerEmail");
        String assetID1 = (String)jsonObject.get("assetID");
        String assetName1 = (String)jsonObject.get("assetName");
        String assetCode1 = (String)jsonObject.get("assetCode");
        String assignedDate = (String)jsonObject.get("assignedDate");
        String assignedBy1=(String)jsonObject.get("assignedBy");
       
        
         String path="Asset12.png";
             Image imageObject = new Image(new FileInputStream(path));
             
            // ImageView image = new ImageView(imageObject);  
          //  qr_code.setImage(imageObject);
        
        id.setText(transID);
        workerID.setText(workerID1);
        workerName.setText(workerName1);
        workerTell.setText(workerTell1);
        workerEmail.setText(workerEmail1);
        assetID.setText(assetID1);
        assetName.setText(assetName1);
        assetCode.setText(assetCode1);
        assigned_date.setText(assignedDate);
        assignedBy.setText(assignedBy1);
         
        
          
      } 
      
      public void AssetPopBackToDB() throws FileNotFoundException, IOException, ParseException, SQLException{
           JSONParser parser = new JSONParser();
        String assetid;
        String assetname;
        String assetcode;
        String assetdetails;
        String workername;
        String workerid;
        String cat;
        String additiondate;
        String cost;
        
      
         Object obj = parser.parse(new FileReader(".//json//assetPop.json"));
         JSONObject jsonObject = (JSONObject)obj;
         assetid = (String)jsonObject.get("assetID");
         assetname = (String)jsonObject.get("assetName");
        assetcode = (String)jsonObject.get("assetCode");
         assetdetails = (String)jsonObject.get("assetDetails");
         workername = (String)jsonObject.get("workerName");
         workerid = (String)jsonObject.get("workerID");
         cat = (String)jsonObject.get("category");
         additiondate= (String)jsonObject.get("additionDate");
         cost = (String)jsonObject.get("cost");
         try{
             
             Class.forName("com.mysql.jdbc.Driver");
              String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
             connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement();
             
             String sql = "INSERT INTO `asset_management_system`.`available_assets` (`assetID`, `assetName`, `assetCode`, `assetDetails`, `workerName`, `workerID`,`categoryID`,`additionDate`,`cost`) VALUES (" + assetid + ",'" + assetname + "','" + assetcode + "','" + assetdetails + "','" + workername + "'," + workerid + ",'" + cat + "','"+additiondate+"','" + cost + "');";

                statement.executeUpdate(sql);
                 }  catch (ClassNotFoundException ex) {
                Logger.getLogger(AssetPopController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
      } 
      
       public void setTomaintenance(JFXTextField id,JFXTextField name,JFXTextField code,JFXTextField details,JFXTextField worker,JFXTextField workerid,JFXTextField category1,JFXTextField date1,JFXTextField maintenace) throws FileNotFoundException, IOException, ParseException, SQLException{
           JSONParser parser = new JSONParser();
           
         Object obj = parser.parse(new FileReader(".//json//maintenance.json"));
         JSONObject jsonObject = (JSONObject)obj;
         
        
        
      
        String assetID = (String)jsonObject.get("assetID");
        String assetName = (String)jsonObject.get("assetName");
        String assetCode = (String)jsonObject.get("assetCode");
        String assetDetails = (String)jsonObject.get("assetDetails");
        String workerName = (String)jsonObject.get("workerName");
        String workerID = (String)jsonObject.get("workerID");
        String category = (String)jsonObject.get("category");
        String additionDate = (String)jsonObject.get("additionDate");
        String maintenanceDate = (String)jsonObject.get("maintenanceDate");
        
        
         String path="Asset12.png";
             Image imageObject = new Image(new FileInputStream(path));
             
            // ImageView image = new ImageView(imageObject);  
           // qr_code.setImage(imageObject);
        
        id.setText(assetID);
        name.setText(assetName);
        code.setText(assetCode);
        details.setText(assetDetails);
        worker.setText(workerName);
        workerid.setText(workerID);
        category1.setText(category);
        date1.setText(additionDate);
        maintenace.setText(maintenanceDate);
        
         
        
          
      } 
      
      /*
      id_info.put("worker_id", id);
         id_info.put("workerName", firstname);
          id_info.put("workerLastName", lastname);
           id_info.put("workerTell", phoneno);
            id_info.put("workerEmail", email);
             id_info.put("workerNationalID", nationalID);
              id_info.put("department", dept);
               id_info.put("location", location);
                id_info.put("pass_word", pas);
                 
      */
    }
    
    
