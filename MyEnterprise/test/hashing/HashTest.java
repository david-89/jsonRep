/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashing;

import java.security.SecureRandom;
import java.util.UUID;
import org.mindrot.BCrypt;

/**
 *
 * @author David
 */
public class HashTest {

    public static void main(String[] args) {
        String password1 = "david123";
        String hash1 = BCrypt.hashpw(password1, BCrypt.gensalt());
        System.out.println(hash1);
        boolean same = BCrypt.checkpw("david1234", hash1);
        
        System.out.println(same);
        
        String password2 = "Milan#123";
        String hash2 = BCrypt.hashpw(password2, BCrypt.gensalt(10));
        System.out.println(hash2);
        boolean same2 = BCrypt.checkpw("dfgsdfgdf", hash2);
        System.out.println(same2);
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        System.out.println(s);
        
        
      

    }
}
