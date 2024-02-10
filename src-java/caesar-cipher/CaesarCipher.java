
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private String lowAlphabet;
    private String shiftedLowAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowAlphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        shiftedLowAlphabet = lowAlphabet.substring(key) + lowAlphabet.substring(0,key);
        mainKey = key;
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i=0;i<input.length();i++){
            char currChar = input.charAt(i);
            int index = alphabet.indexOf(currChar);
            int lowIndex = lowAlphabet.indexOf(currChar);
            if(index != -1 || lowIndex != -1){
                if(Character.isUpperCase(currChar)){
                    char shiftChar = shiftedAlphabet.charAt(index);
                    encrypted.setCharAt(i,shiftChar);
                } else {
                    char shiftChar = shiftedLowAlphabet.charAt(lowIndex);
                    char newShiftChar = Character.toLowerCase(shiftChar);
                    encrypted.setCharAt(i,newShiftChar);
                }
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedLowAlphabet1 = lowAlphabet.substring(key1) + lowAlphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        String shiftedLowAlphabet2 = lowAlphabet.substring(key2) + lowAlphabet.substring(0,key2);
        for(int i=0;i<input.length();i++){
            char currChar = encrypted.charAt(i);
            int indexChar = input.indexOf(currChar,i);
            if(indexChar%2==0){
                int index = alphabet.indexOf(currChar);
                int lowIndex = lowAlphabet.indexOf(currChar);
                if(index != -1 || lowIndex != -1){
                    if(Character.isUpperCase(currChar)){
                        char shiftChar = shiftedAlphabet1.charAt(index);
                        encrypted.setCharAt(i,shiftChar);
                    } else {
                        char shiftChar = shiftedLowAlphabet1.charAt(lowIndex);
                        char newShiftChar = Character.toLowerCase(shiftChar);
                        encrypted.setCharAt(i,newShiftChar);
                    }
                }
            } else {
                int index = alphabet.indexOf(currChar);
                int lowIndex = lowAlphabet.indexOf(currChar);
                if(index != -1 || lowIndex != -1){
                    if(Character.isUpperCase(currChar)){
                        char shiftChar = shiftedAlphabet2.charAt(index);
                        encrypted.setCharAt(i,shiftChar);
                    } else {
                        char shiftChar = shiftedLowAlphabet2.charAt(lowIndex);
                        char newShiftChar = Character.toLowerCase(shiftChar);
                        encrypted.setCharAt(i,newShiftChar);
                    }
                }
            }
        }
        return encrypted.toString();
    }
}
