
package asset_management_system.usedAlot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class json_read {
    
    public String profile_id() throws FileNotFoundException, IOException, ParseException{
        
        JSONParser parser = new JSONParser();
        String name;
      
         Object obj = parser.parse(new FileReader(".//json//worker_id.json"));
         JSONObject jsonObject = (JSONObject)obj;
         name = (String)jsonObject.get("worker_id");
          
      
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
        
      
         Object obj = parser.parse(new FileReader("worker_profile.json"));
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
    
    
