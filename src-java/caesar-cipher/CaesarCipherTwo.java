
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private String lowAlphabet;
    private String shiftedLowAlphabet1;
    private String shiftedLowAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowAlphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedLowAlphabet1 = lowAlphabet.substring(key1) + lowAlphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        shiftedLowAlphabet2 = lowAlphabet.substring(key2) + lowAlphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
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
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        return cc.encrypt(input);
    }
}
