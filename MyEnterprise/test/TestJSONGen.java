
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David
 */
public class TestJSONGen {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person();
        objectMapper.writeValue(new File("C:\\Users\\David\\Desktop\\JSONi.txt"), person);
        System.out.println(person);
        
        
    }

}
