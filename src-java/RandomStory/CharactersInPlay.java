
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterCounts;
    
    public CharactersInPlay(){
        characterNames = new ArrayList<String>();
        characterCounts = new ArrayList<Integer>();
    }
    public void update(String person){
        int index = characterNames.indexOf(person);
        if(index == -1){
            characterNames.add(person);
            characterCounts.add(1);
        } else {
            int count = characterCounts.get(index);
            characterCounts.set(index,count+1);
        }
    }
    public void findAllCharacters(){
        characterNames.clear();
        characterCounts.clear();
        FileResource fr = new FileResource();
        for(String word : fr.lines()){
            int index = word.indexOf('.');
            if(index != -1){
                String name = word.substring(0,index);
                update(name);
            }
        }
    }
    public void charactersWithNumParts(int num1,int num2){
        for(int i=0;i<characterNames.size();i++){
            int numberOf = characterCounts.get(i);
            if(numberOf > num1 && numberOf <= num2){
                System.out.println(characterNames.get(i));
            }
        }
    }
    public void tester(){
        findAllCharacters();
        for(int i=0;i<characterNames.size();i++){
            if(characterCounts.get(i) > 1){
                System.out.println(characterNames.get(i)+" "+characterCounts.get(i));
            }
        }
        System.out.println("----------");
        charactersWithNumParts(9,15);
    }
    public static void main(String[] args){
        CharactersInPlay test = new CharactersInPlay();
        test.tester();
    }
}
