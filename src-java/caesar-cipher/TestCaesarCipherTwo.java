
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start){
        StringBuilder half = new StringBuilder("");
        for(int i=start;i<message.length();i+=2){
            char ch = message.charAt(i);
            half.append(ch);
        }
        String result = half.toString();
        return result;
    }
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
        String firstHalf = halfOfString(input, 0);
        String secondHalf = halfOfString(input, 1);
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        StringBuilder poopy = new StringBuilder();
        int big = 0;
        if(firstHalf.length() <= secondHalf.length()){
            big = secondHalf.length();
        } else {
            big = firstHalf.length();
        }
        for(int i=0;i<big;i++){
            if(i<=firstHalf.length()){
                char ch = firstHalf.charAt(i);
                poopy.append(ch);
            }
            if(i<secondHalf.length()){
                char ch = secondHalf.charAt(i);
                poopy.append(ch);
            }
        }
        String encryptable = poopy.toString();
        CaesarCipherTwo cctd = new CaesarCipherTwo(key1,key2);
        return cctd.decrypt(encryptable);
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String toBeEncrypted = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(26,0);
        String encrypted = cc.encrypt(toBeEncrypted);
        System.out.println(encrypted);
        //String decrypted = cc.decrypt(toBeEncrypted);
        //System.out.println(decrypted);
        String out = breakCaesarCipher(toBeEncrypted);
        System.out.println(out);
    }
    public static void main(String[] args){
        TestCaesarCipherTwo test = new TestCaesarCipherTwo();
        test.simpleTests();
    }
}
