/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.dao.EmployeeDAO;
import app.domain.Employee;
import app.domain.Faculty;
import app.domain.Student;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

/**
 *
 * @author David
 */
@Controller
public class MainController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @RequestMapping(value = {"/index", "/", ""})
    public String indexPage() {
        return "index";
    }

    @RequestMapping(value = {"/displayEmp"})
    public String displayEmp() {
        return "displayEmp";
    }

    @RequestMapping(value = "/create")
    public String getCreateEmp(Model model) {
        model.addAttribute("employeeModel", new Employee());
        return "createEmp";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreateEmp(@ModelAttribute("employeeModel") Employee emp) {
        System.out.println("Conteroller reached " + emp.getFirstname());
        getEmployeeDAO().registerEmployee(emp);
        return "createEmp";
    }

    @RequestMapping(value = "/getAllEmps")
    @ResponseBody
    public String getAllEmps() {
        System.out.println("Get all emps method reached");
        JSONObject jsonObj = null;
        List<JSONObject> jsonList = new ArrayList<>();
        List<Employee> employees = getEmployeeDAO().getAllEmployees();
        System.out.println("the resut is the following: ****");
        for (Employee emp : employees) {
            jsonObj = new JSONObject(emp);
            jsonList.add(jsonObj);
        }
        System.out.println("JSONObject list is " + jsonList);
        return jsonList.toString();

    }

    @RequestMapping(value = "getEmpById")  //returns json string
    @ResponseBody
    public String getEmpById(Integer id) {
        Employee emp = getEmployeeDAO().getEmployeeById(id);
        JSONObject jSONObject = new JSONObject(emp);        
        System.out.println("JSON object by id is " + jSONObject);
        return jSONObject.toString();
    }
    
    @RequestMapping(value = "getEmpAsJson") //returns a jsonified object pulled from the DB
    @ResponseBody
    public String getEmpAsJson(Integer id) throws IOException {
        Employee emp = getEmployeeDAO().getEmployeeById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
        StringWriter sw = new StringWriter();
        objectMapper.writeValue(sw, emp);
        System.out.println("JSONified emp object is " + sw);
        return sw.toString();
    }

    @RequestMapping(value = "jsonParser") //turns a json string into a jsonObject without a mapping class
    public void jsonParser(HttpServletRequest request) throws JSONException {
        System.out.println("method reached");
        String jsonString = request.getParameter("jsonString");
        JSONObject jSONObject = new JSONObject(jsonString);
        String type = jSONObject.getString("type");
        JSONObject batter = jSONObject.getJSONObject("batters");
        JSONArray batterArray = batter.getJSONArray("batter");
        System.out.println("array is " + batterArray);        
        System.out.println("first element is " + batterArray.get(0));
        JSONObject firstElem = (JSONObject) batterArray.get(0);
        System.out.println("Type of the first element is " + firstElem.getString("type")); 
        System.out.println("type is " + type);
        System.out.println("Batter is " + batter);
        JSONArray topping = jSONObject.getJSONArray("topping");
        JSONObject secondTopping = (JSONObject) topping.get(1);
        System.out.println("Second topping type is " + secondTopping.getString("type"));
        
    }
    
    @RequestMapping(value = "jacksonParser") //turns json to an object via ObjectMapper which means we need a matching class
    public void jacksonParser(HttpServletRequest request) throws IOException {
        String stringForJackson = request.getParameter("stringForJackson");
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(stringForJackson, Student.class);
        System.out.println("Student: " + student);                
    }
    
    @RequestMapping(value = "jacksonParser2") 
    public void jacksonParser2(HttpServletRequest request) throws IOException, JSONException {
        String stringForJackson2 = request.getParameter("stringForJackson2");
        ObjectMapper objectMapper = new ObjectMapper();
        Faculty faculty = objectMapper.readValue(stringForJackson2, Faculty.class);
        System.out.println("Faculty " + faculty);
        //Student student = faculty.getStudent();
      // System.out.println("Student is " + student);
    }


    @RequestMapping(value = "jsonify") //we build json object without a corresponding class
    public void jsonify(HttpServletRequest request) throws JSONException { 
        System.out.println("method jsonify reached");
        String firstname = request.getParameter("firstname"); 
        String lastname = request.getParameter("lastname"); 
        int age = Integer.valueOf(request.getParameter("age")); 
        String facultyName = request.getParameter("facultyName"); 
        String highSchoolName = request.getParameter("highSchoolName"); 
        int highSchoolRate= Integer.valueOf(request.getParameter("highSchoolRate")); 
        int rate = Integer.valueOf(request.getParameter("rate")); 
        JSONObject jSONObject = new JSONObject(); 
        jSONObject.accumulate("firstname", firstname); 
        jSONObject.accumulate("lastname", lastname);  
        jSONObject.accumulate("age", age); 
        jSONObject.accumulate("education", new JSONObject()); 
        JSONObject educationObj = jSONObject.getJSONObject("education"); 
        JSONArray array = new JSONArray(); 
        JSONObject faculty = new JSONObject();      
        JSONObject highSchool = new JSONObject(); 
        faculty.accumulate("facultyName", facultyName); 
        faculty.accumulate("rate", rate); 
        highSchool.accumulate("highSchoolName", highSchoolName); 
        highSchool.accumulate("highSchoolRate", highSchoolRate); 
        educationObj.accumulate("faculty", faculty);  
        educationObj.accumulate("highSchool", highSchool); 
        jSONObject.accumulate("education", array); 
        System.out.println("JSON object is " + jSONObject); 
    }

    /**
     * @return the employeeDAO
     */
    public EmployeeDAO getEmployeeDAO() { 
        return employeeDAO;
    } 

    /**
     * @param employeeDAO the employeeDAO to set
     */
    public void setEmployeeDAO(EmployeeDAO employeeDAO) { 
        this.employeeDAO = employeeDAO;
    } 
}
