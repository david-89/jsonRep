
import org.json.JSONException;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class JSONObjectCLass {
    
    public static void main(String[] args) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.accumulate("firstname", "Sima");
        jSONObject.accumulate("lastname", "Simic");
        jSONObject.accumulate("position", "CEO");
        jSONObject.remove("position");
        
        System.out.println(jSONObject);
    }
    
}
