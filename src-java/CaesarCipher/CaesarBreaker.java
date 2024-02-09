
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarBreaker {
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
        return dKey;
    }
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int dKey = getKey(encrypted);
        String message = cc.encrypt(encrypted,26-dKey);
        return message;
    }
    public void testDecrypt(){
        CaesarCipher cc1 = new CaesarCipher();
        FileResource fr = new FileResource();
        String toBeEncrypted = fr.asString();
        String ogEncrypt = cc1.encrypt(toBeEncrypted,15);
        System.out.println(ogEncrypt);
        System.out.println(decrypt(ogEncrypt));
    }
    public String halfOfString(String message, int start){
        StringBuilder half = new StringBuilder("");
        for(int i=start;i<message.length();i+=2){
            char ch = message.charAt(i);
            half.append(ch);
        }
        String result = half.toString();
        return result;
    }
    public String decryptTwoKeys(String encrypted){
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        String firstDecrypt = decrypt(firstHalf);
        String secondDecrypt = decrypt(secondHalf);
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
        CaesarCipher ccTwo = new CaesarCipher();
        String toDecryptTwo = poopy.toString();
        System.out.println("Keys used: "+key1+", "+key2);
        return ccTwo.encryptTwoKeys(toDecryptTwo,26-key1,26-key2);
    }
    public void testDecryptTwoKeys(){
        CaesarCipher cc2 = new CaesarCipher();
        FileResource fr = new FileResource();
        String toBeEncrypted = fr.asString();
        String ogTwoEncrypt = cc2.encryptTwoKeys(toBeEncrypted, 15, 13);
        System.out.println(ogTwoEncrypt);
        System.out.println(decryptTwoKeys(ogTwoEncrypt));
        System.out.println(cc2.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx",24,6));
    }
    public static void main(String[] args){
        CaesarBreaker test = new CaesarBreaker();
        //test.testDecrypt();
        //System.out.println(test.halfOfString("Huevo zen tu", 0));
        //System.out.println(test.halfOfString("Huevo zen tu", 1));
        test.testDecryptTwoKeys();
    }
}
