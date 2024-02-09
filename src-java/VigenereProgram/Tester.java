
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class Tester {
    public void tester(){
        int[] werp = {17,14,12,4};
        VigenereCipher test = new VigenereCipher(werp);
        FileResource fr = new FileResource();
        String serp = fr.asString();
        String encrypt = test.encrypt(serp);
        System.out.println(encrypt);
        String decrypt = test.decrypt(encrypt);
        System.out.println(decrypt);
    }
}
