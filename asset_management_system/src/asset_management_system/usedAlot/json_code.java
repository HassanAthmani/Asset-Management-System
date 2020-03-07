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
                 
       /* JSONObject workerObject = new JSONObject(); 
        workerObject.put("worker", id_info);*/
         
        
         
        /*Add worker to list
        JSONArray workerList = new JSONArray();
        workerList.add(id_info);*/
       
         
        //Write JSON file
        try (FileWriter file = new FileWriter(new File("worker_id.json"))) {
            
 
            file.write(id_info.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
