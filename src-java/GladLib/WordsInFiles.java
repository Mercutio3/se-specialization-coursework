
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> myWords;
    
    public WordsInFiles(){
        myWords = new HashMap<String,ArrayList<String>>();
    }
    public void addWordsFromFile(File f){
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        
        for(String word : fr.words()){
            ArrayList<String> thisWord = new ArrayList<String>();
            if(!myWords.containsKey(word)){
                ArrayList<String> temp = thisWord;
                temp.add(fileName);
                myWords.put(word,temp);
            } else if(myWords.containsKey(word)){
                thisWord = myWords.get(word);
                if(thisWord.indexOf(fileName) == -1){
                    thisWord.add(fileName);
                }
            }
        }
    }
    public void buildWordFileMap(){
        myWords.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        int largestCount = 0;
        for(String key : myWords.keySet()){
            ArrayList<String> temp = myWords.get(key);
            for(int i=0;i<temp.size();i++){
                if(i >= largestCount){
                    largestCount++;
                }
            }
        }
        return largestCount;
    }
    public ArrayList<String> wordsInNumFiles(int num){
        ArrayList<String> output = new ArrayList<String>();
        for(String key : myWords.keySet()){
            ArrayList<String> temp = myWords.get(key);
            int tempCount = 0;
            for(int i=0;i<temp.size();i++){
                tempCount++;
            }
            if(tempCount == num){
                output.add(key);
            }
        }
        return output;
    }
    public void printFilesIn(String word){
        for(String key : myWords.keySet()){
            if(key.equals(word)){
                ArrayList<String> temp = myWords.get(key);
                for(int i=0;i<temp.size();i++){
                    System.out.println(temp.get(i));
                }
            }
        }
    }
    public void tester(){
        buildWordFileMap();
        System.out.println("Max number was: "+maxNumber());
        ArrayList<String> wordsNumFiles = wordsInNumFiles(4);
        int count = 0;
        for(int i=0;i<wordsNumFiles.size();i++){
            System.out.println("Word that appears num times: "+wordsNumFiles.get(i));
            count++;
        }
        System.out.println(count);
        printFilesIn("tree");
    }
}
