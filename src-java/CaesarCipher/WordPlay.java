
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        if(ch == 'a' || ch == 'A'){
            return true;
        } else if (ch == 'e' || ch == 'E'){
            return true;
        } else if (ch == 'i' || ch == 'I'){
            return true;
        } else if (ch == 'o' || ch == 'O'){
            return true;
        } else if (ch == 'u' || ch == 'U'){
            return true;
        } else {
            return false;
        }
    }
    public String replaceVowels(String phrase, char ch){
        StringBuilder replaced = new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++){
            char currChar = replaced.charAt(i);
            if(isVowel(currChar)){
                replaced.setCharAt(i,ch);
            }
        }
        return replaced.toString();
    }
    public String emphazise(String phrase, char ch){
        StringBuilder replaced = new StringBuilder(phrase);
        char test = ch;
        if(Character.isUpperCase(ch)){
            test = Character.toLowerCase(ch);
        } else {
            test = Character.toUpperCase(ch);
        }
        for(int i=0;i<phrase.length();i++){
            char currChar = replaced.charAt(i);
            if(currChar == ch || currChar == test){
                int charIndex = phrase.indexOf(ch,i);
                if(charIndex%2==0){
                    replaced.setCharAt(i,'*');
                } else {
                    replaced.setCharAt(i,'+');
                }
            }
        }
        return replaced.toString();
    }
    public static void main(String[] args){
        WordPlay test = new WordPlay();
        System.out.println("Is F a vowel? "+test.isVowel('F'));
        System.out.println("Is e a vowel? "+test.isVowel('e'));
        System.out.println("Hello world without vowels: "+test.replaceVowels("Hello world",'*'));
        System.out.println("Mary Bella Abracadabra --> "+test.emphazise("Mary Bella abracadabra",'a'));
    }
}
