
import app.dao.EmployeeDAO;
import app.domain.Employee;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class TestJsonification {
    
   
    
    public static void main(String[] args) throws IOException {
        JSONObject jsonObj = null;
        List<JSONObject> jsonList = new ArrayList<>();  
        Employee emp1 = new Employee(1);
        Employee emp2 = new Employee(2);
        emp1.setAge(40);
        emp1.setFirstname("Mika");
        emp1.setLastname("Mikic");
        emp1.setPersonalNumber("665413");
        emp1.setPosition("QS");
        
        emp2.setFirstname("Zika");
        emp2.setAge(30);
        emp2.setLastname("Zikic");
        emp2.setPersonalNumber("978611");
        emp2.setPosition("Team Manager");
        
        List<Employee> employees = new ArrayList<>();
        employees.add(emp2);
        employees.add(emp1);
                
        System.out.println("the resut is the following: ****");
        for (Employee emp : employees) {
            jsonObj = new JSONObject(emp);
            jsonList.add(jsonObj);
        }
        System.out.println("JSONObject list is " + jsonList);

    }

    
    
}
