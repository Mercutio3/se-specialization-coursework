
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    public int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i=0;i<message.length();i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alphabet.indexOf(ch);
            if(index != -1){
                counts[index]++;
            }
        }
        return counts;
    }
    public int maxIndex(int[] values){
        int maxDex = 0;
        for(int i=0;i<values.length;i++){
            if(values[i] > values[maxDex]){
                maxDex = i;
            }
        }
        return maxDex;
    }
    public int getKey(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dKey = maxDex - 4;
        if(maxDex < 4){
            dKey = 26 - (4-maxDex);
        }
        System.out.println("Key used: "+dKey);
        return dKey;
    }
    public String breakCaesarCipher(String input){
        int dKey = getKey(input);
        CaesarCipher ccd = new CaesarCipher(dKey);
        String result = ccd.decrypt(input);
        return result;
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String toBeEncrypted = fr.asString();
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(toBeEncrypted);
        System.out.println(encrypted);
        //String decrypted = cc.decrypt(encrypted);
        //System.out.println(decrypted);
        //String out = breakCaesarCipher(encrypted);
        //System.out.println(out);
    }
    public static void main(String[] args){
        TestCaesarCipher test = new TestCaesarCipher();
        test.simpleTests();
    }
}
