
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int count = myFreqs.get(index);
                myFreqs.set(index,count+1);
            }
        }
    }
    public int findIndexOfMax(){
        int largest = 0;
        for(int i=0;i<myFreqs.size();i++){
            if(myFreqs.get(i) > largest){
                largest = myFreqs.get(i);
                System.out.println("increased");
                System.out.println(myWords.get(i));
            }
        }
        return largest;
    }
    public void tester(){
        findUnique();
        System.out.println("Number of unique words: "+myWords.size());
        for(int i=0;i<myWords.size();i++){
            System.out.println(myWords.get(i)+" appeared "+myFreqs.get(i)+" times.");
        }
        System.out.println("Most frequent word: "+myWords.get(findIndexOfMax()-1)+" with "+findIndexOfMax());
    }
    public static void main(String[] args){
        WordFrequencies test = new WordFrequencies();
        test.tester();
    }
}
