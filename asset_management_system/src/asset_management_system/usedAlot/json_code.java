/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.usedAlot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author User
 */
public class json_code {
    
    public void json_id(String id){
     //Add worker id
        JSONObject id_info = new JSONObject();
        id_info.put("worker_id", id);
        
       
        //Write JSON file
        try (FileWriter file = new FileWriter(new File(".//json//worker_id.json"))) {
            
 
            file.write(id_info.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void json_profile(String id, String firstname, String lastname, String phoneno, String email, String nationalID, String dept, String location, String pas ){
     //Add worker id
        JSONObject id_info = new JSONObject();
        id_info.put("worker_id", id);
         id_info.put("workerName", firstname);
          id_info.put("workerLastName", lastname);
           id_info.put("workerTell", phoneno);
            id_info.put("workerEmail", email);
             id_info.put("workerNationalID", nationalID);
              id_info.put("department", dept);
               id_info.put("location", location);
                id_info.put("pass_word", pas);
                 
             
         
        //Write JSON file
        try (FileWriter file = new FileWriter(new File(".//json//worker_profile.json"))) {
            
 
            file.write(id_info.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void json_assetID(String id){
     //Add worker id
        JSONObject id_info = new JSONObject();
        id_info.put("asset_id", id);
        
       
        //Write JSON file
        try (FileWriter file = new FileWriter(new File(".//json//addedAssetID.json"))) {
            
 
            file.write(id_info.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
