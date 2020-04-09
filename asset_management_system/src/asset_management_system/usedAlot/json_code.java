
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.usedAlot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class json_code {

    public void json_id(String id) {
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

    public void json_profile(String id, String firstname, String lastname, String phoneno, String email, String nationalID, String dept, String location, String pas) {
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

    public void json_assetID(String id) {
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

    public void deferredItem_json(String assetID, String assetName, String assetCode, String assetDetails, String workerName, String workerID, String category, String additionDate, String deferredDate, String reason) {
        //Add worker id
        JSONObject id_info = new JSONObject();
        id_info.put("assetID", assetID);
        id_info.put("assetName", assetName);
        id_info.put("assetCode", assetCode);
        id_info.put("assetDetails", assetDetails);
        id_info.put("workerName", workerName);
        id_info.put("workerID", workerID);
        id_info.put("category", category);
        id_info.put("additionDate", additionDate);
        id_info.put("deferredDate", deferredDate);
        id_info.put("reason", reason);

        //Write JSON file
        try (FileWriter file = new FileWriter(new File(".//json//deferredItem.json"))) {

            file.write(id_info.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AllItem_json(String assetID, String assetName, String assetCode, String assetDetails, String workerName, String workerID, String category, String additionDate, String cost) {
        //Add worker id
        /* File file1 = new File(".//json//AllItem.json"); 
     
     if(file1.delete()) 
        { */
        System.out.println("File deleted successfully");
        JSONObject id_info = new JSONObject();
        id_info.put("assetID", assetID);
        id_info.put("assetName", assetName);
        id_info.put("assetCode", assetCode);
        id_info.put("assetDetails", assetDetails);
        id_info.put("workerName", workerName);
        id_info.put("workerID", workerID);
        id_info.put("category", category);
        id_info.put("additionDate", additionDate);
        id_info.put("cost", cost);
        //Write JSON file
        try (FileWriter file = new FileWriter(new File(".//json//AllItem.json"))) {

            file.write(id_info.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        /* } 
     else
     {
         System.out.println("Failed to delete the file"); 
     }*/

    }

    public void WithUser_json(String transID, String workerID, String workerName, String workerTell, String workerEmail, String assetID, String assetName, String assetCode, String assignedDate, String assignedBy) {
        //Add worker id
        JSONObject id_info = new JSONObject();
        id_info.put("transID", transID);
        id_info.put("workerID", workerID);
        id_info.put("workerName", workerName);
        id_info.put("workerTell", workerTell);
        id_info.put("workerEmail", workerEmail);
        id_info.put("assetID", assetID);
        id_info.put("assetName", assetName);
        id_info.put("assetCode", assetCode);
        id_info.put("assignedDate", assignedDate);
        id_info.put("assignedBy", assignedBy);

        //Write JSON file
        try (FileWriter file = new FileWriter(new File(".//json//WithUser.json"))) {

            file.write(id_info.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void assetPop_json(String assetID, String assetName, String assetCode, String assetDetails, String workerName, String workerID, String category, String additionDate, String cost) {
        //Add worker id
        JSONObject id_info = new JSONObject();
        id_info.put("assetID", assetID);
        id_info.put("workerID", workerID);
        id_info.put("workerName", workerName);
        id_info.put("category", category);
        id_info.put("assetDetails", assetDetails);
        id_info.put("assetID", assetID);
        id_info.put("assetName", assetName);
        id_info.put("assetCode", assetCode);
        id_info.put("additionDate", additionDate);
        id_info.put("cost", cost);

        //Write JSON file
        try (FileWriter file = new FileWriter(new File(".//json//assetPop.json"))) {

            file.write(id_info.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void maintenanceItem_json(String assetID, String assetName, String assetCode, String assetDetails, String workerName, String workerID, String category, String additionDate, String maintenanceDate) {
        //Add worker id
        /* File file1 = new File(".//json//AllItem.json"); 
     
     if(file1.delete()) 
        { */
        System.out.println("File deleted successfully");
        JSONObject id_info = new JSONObject();
        id_info.put("assetID", assetID);
        id_info.put("assetName", assetName);
        id_info.put("assetCode", assetCode);
        id_info.put("assetDetails", assetDetails);
        id_info.put("workerName", workerName);
        id_info.put("workerID", workerID);
        id_info.put("category", category);
        id_info.put("additionDate", additionDate);
        id_info.put("maintenanceDate", maintenanceDate);

        //Write JSON file
        try (FileWriter file = new FileWriter(new File(".//json//maintenance.json"))) {

            file.write(id_info.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        /* } 
     else
     {
         System.out.println("Failed to delete the file"); 
     }*/

    }

}
