import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String result = "";
        StringBuilder sb = new StringBuilder(message);
        StringBuilder sb2 = new StringBuilder("");
        for(int i=whichSlice;i<sb.length();i+=totalSlices){
            sb2.append(sb.charAt(i));
        }
        result = sb2.toString();
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cCrack = new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++){
            String temp = sliceString(encrypted,i,klength);
            int tempKey = cCrack.getKey(temp);
            //System.out.println(tempKey);
            key[i] = tempKey;
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String[] langs = new String[]{"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        HashMap<String,HashSet<String>> langMap = new HashMap<String,HashSet<String>>();
        for(int i=0;i<langs.length;i++){
            String language = langs[i];
            FileResource fr2 = new FileResource("/Users/santiagodominguezham/Desktop/Java/Coursera/VigenereProgram/dictionaries/"+language);
            HashSet<String> dict = readDictionary(fr2);
            langMap.put(language,dict);
            System.out.println("Succesfully read: "+language);
        }
        String decrypted = breakForAllLangs(encrypted,langMap);
        System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for(String oneWord : fr.lines()){
            String lowWord = oneWord.toLowerCase();
            words.add(lowWord);
        }
        return words;
    }
    public int countWords(String message,HashSet<String> dictionary){
        int count = 0;
        for(String messWord : message.split("\\W+")){
            String messWord2 = messWord.toLowerCase();
            if(dictionary.contains(messWord2)){
                count++;
            }
        }
        return count;
    }
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        String result = "";
        int biggest = 0;
        char ch = mostCommonCharIn(dictionary);
        System.out.println("Character in language: "+ch);
        for(int i=1;i<101;i++){
            int[] temp = tryKeyLength(encrypted,i,ch);
            VigenereCipher torti = new VigenereCipher(temp);
            String dc = torti.decrypt(encrypted);
            int tempCount = countWords(dc,dictionary);
            if(tempCount > biggest){
                biggest = tempCount;
                result = dc;
                System.out.println(i);
            }
        }
        System.out.println("Total count: "+biggest);
        return result;
    }
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> charCounts = new HashMap<Character,Integer>();
        for(String word : dictionary){
            String realWord = word.toLowerCase();
            for(char c : realWord.toCharArray()){
                if(!charCounts.containsKey(c)){
                    charCounts.put(c,1);
                } else {
                    int tempCount = charCounts.get(c);
                    charCounts.put(c,tempCount+1);
                }
            }
        }
        char mostCommon = '\0';
        int largestCount = 0;
        for(char ch : charCounts.keySet()){
            int currCount = charCounts.get(ch);
            if(currCount > largestCount){
                mostCommon = ch;
                largestCount = currCount;
            }
        }
        return mostCommon;
    }
    public String breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        int largestCount = 0;
        String result = "";
        for(String language : languages.keySet()){
            HashSet<String> tryLang = languages.get(language);
            System.out.println("Trying on: "+language);
            String tryDecrypt = breakForLanguage(encrypted,tryLang);
            int tryCount = countWords(tryDecrypt,tryLang);
            if(tryCount > largestCount){
                largestCount = tryCount;
                result = tryDecrypt;
            }
        }
        return result;
    }
}
