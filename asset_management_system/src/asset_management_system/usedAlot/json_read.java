
package asset_management_system.usedAlot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class json_read {
    
    public String profile_id() throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();
        String name;
      
         Object obj = parser.parse(new FileReader("worker_id.json"));
         JSONObject jsonObject = (JSONObject)obj;
         name = (String)jsonObject.get("worker_id");
          
      
      return name;
   }
                
       
    }
    
    
