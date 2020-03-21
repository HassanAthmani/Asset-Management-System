//
package asset_management_system.usedAlot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;

public class DBOps {
    String userName="root";
    String password="";
    public Connection connection;
    
    public  void addData(String firstname,String secondname,String phoneno,String natid,String email,String department,String location) throws SQLException, InterruptedException{
         try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();

            

            String sql = "INSERT INTO `asset_management_system`.`worker_details` (`workerID`, `workerName`, `workerLastName`, `workerTell`, `workerEmail`, `workerNationalID`,`department`,`location`,`pass_word`) VALUES (NULL,'" + firstname + "','" + secondname + "','" + phoneno + "','" + email + "','"+natid+"','"+department+"','"+location+"',' ' );";
            
            TimeUnit.SECONDS.sleep(1);  
            String mover="INSERT INTO `asset_management_system`.`current_workers` SELECT * FROM `asset_management_system`.`worker_details` WHERE assetCode='"+phoneno+"'";

            statement.executeUpdate(sql);   
            statement.executeUpdate(mover); 
            
            connection.close();
          
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
        
    }
    
    public  void ProfileaddData(String id,String firstname,String secondname,String phoneno,String email,String natid,String department,String location,String pas) throws SQLException{
         try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();

            

            String sql = "INSERT INTO `asset_management_system`.`current_workers` (`workerID`, `workerName`, `workerLastName`, `workerTell`, `workerEmail`, `workerNationalID`,`department`,`location`,`pass_word`) VALUES ('" + id + "','" + firstname + "','" + secondname + "','" + phoneno + "','" + email + "','"+natid+"','"+department+"','"+location+"','" + pas + " ' );";

            statement.executeUpdate(sql);    
            
            String updater="UPDATE `asset_management_system`.`worker_details` SET workerName='"+firstname+"' ,workerLastName='"+secondname+"', workerTell= '"+phoneno+"', workerEmail='"+email+"', workerNationalID='"+natid+"', department='"+department+"', location='"+location+"', pass_word='"+pas+"' WHERE workerID="+id;
            
            statement.executeUpdate(updater); 
            connection.close();
          
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
        
    }
    
  
    
    public void deleteRecord(String id) throws SQLException{
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();

            

            String sql = "DELETE FROM `asset_management_system`.`current_workers` WHERE workerID= "+id;

            statement.executeUpdate(sql);     
            
            connection.close();
          
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
        
        
    }
    
    public void addAsset(String assetName,String assetCode,String assetDetails,String workerName,String workerID,String category,String cost) throws SQLException{
         try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();

            

            String sql = "INSERT INTO `asset_management_system`.`assets` (`assetID`, `assetName`, `assetCode`, `assetDetails`, `workerName`, `workerID`,`categoryID`,`additionDate`,`cost`) VALUES (NULL,'" + assetName + "'," + assetCode + ",'" + assetDetails + "','" + workerName + "','"+ workerID +"','"+category+"',"+" CURDATE()"+",'" + cost+ " ' );";

            statement.executeUpdate(sql);   
            
            String getAssetData="SELECT * FROM `asset_management_system`.`assets` WHERE assetCode='"+assetCode+"'";
            
            String mover="INSERT INTO `asset_management_system`.`available_assets` SELECT * FROM `asset_management_system`.`assets` WHERE assetCode='"+assetCode+"'";
            
            statement.executeUpdate(mover);  
            ResultSet rs = connection.createStatement().executeQuery(getAssetData); 
             
             while (rs.next()) {  
                    
                json_code cod=new json_code();
                cod.json_assetID(rs.getString(1));
                }
            
            connection.close();
          
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }
    
}

/*String email= email1.getText();
        String EMAIL_PATTERN = 
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (!email.matches(EMAIL_PATTERN)) {
       //System.out.print(" does not match");
       int response = JOptionPane.showConfirmDialog(
        null,"the email is not valid","Required Input",JOptionPane.DEFAULT_OPTION); 
       return true;
   }
   else{
       //System.out.print("it does match"); 
       return false;*/
