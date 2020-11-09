/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.usedAlot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author User
 */
public class special_char {
    public static int containsSpecialChar(){
         String password="12345";
       Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[//!@#$%&*()_+=|<>?{}\\[\\]~-]");
        //Pattern eight = Pattern.compile (".{8}");


           Matcher hasLetter = letter.matcher(password);
           Matcher hasDigit = digit.matcher(password);
           Matcher hasSpecial = special.matcher(password);

          // return hasLetter.find() && hasDigit.find() && hasSpecial.find();
           System.out.println("hasLetter"+hasLetter.find());
           System.out.println("hasDigit"+hasDigit.find());
           System.out.println("hasSpecial"+hasSpecial.find());
           //if (hasLetter )

        return 0;
    }
    ///FOR TEXT VALIDATION E.G. NAME, DEPARTMENT AND LOCATION
    public static boolean hasSpecialAndDigits(String characters ){
         
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[//!@#$%&*()_+=|<>?{}\\[\\]~-]");
       
           Matcher hasDigit = digit.matcher(characters);
           Matcher hasSpecial = special.matcher(characters);
                   
           
               return (hasDigit.find()==true || hasSpecial.find()==true);                 
        
    }
    
    ///FOR DIGITS VALIDATION E.G. PHONE NUMBER AND ID
    public static boolean hasSpecialAndLetters(String characters ){
        
        Pattern letter = Pattern.compile("[a-zA-z]");        
        Pattern special = Pattern.compile ("[//!@#$%&*()_+=|<>?{}\\[\\]~-]");
       
           Matcher hasLetter = letter.matcher(characters);          
           Matcher hasSpecial = special.matcher(characters);

          // return hasLetter.find() && hasDigit.find() && hasSpecial.find();
          return (hasSpecial.find()==true || hasLetter.find()==true); 
        
    }
    
    ///FOR ASSET CODE NO SPECIAL CHAR NEEDED
    public static boolean hasSpecial(String characters ){
        
       // Pattern letter = Pattern.compile("[a-zA-z]");        
        Pattern special = Pattern.compile ("[//!@#$%&*()_+=|<>?{}\\[\\]~-]");
       
          // Matcher hasLetter = letter.matcher(characters);          
           Matcher hasSpecial = special.matcher(characters);

          // return hasLetter.find() && hasDigit.find() && hasSpecial.find();
          return (hasSpecial.find()); 
        
    }
    
     public static boolean DateHasChar(String characters ){
        
       Pattern letter = Pattern.compile("[a-zA-z]");        
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
       
          Matcher hasLetter = letter.matcher(characters);          
           Matcher hasSpecial = special.matcher(characters);

          return hasLetter.find() && hasSpecial.find();
          //return (hasLetter.find()); 
        
    }
    
   
    
    
}
