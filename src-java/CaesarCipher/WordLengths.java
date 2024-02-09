
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public int[] countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            System.out.println(word);
            int length = word.length();
            StringBuilder word2 = new StringBuilder(word);
            char first = word2.charAt(0);
            char last = word2.charAt(length-1);
            if(!Character.isLetter(first)){
                length--;
            }
            if(!Character.isLetter(last)){
                length--;
            }
            if(length > counts.length-1){
                counts[counts.length-1] +=1;
            } else if (length != -1){
                counts[length] += 1;
            }
        }
        for(int i=0;i<counts.length;i++){
            System.out.println("Word length "+i+": "+counts[i]);
        }
        return counts;
    }
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] serp = new int[31];
        System.out.println("Most common word length: "+indexOfMax(countWordLengths(fr,serp)));
    }
    public int indexOfMax(int[] values){
        int largestIndex = 0;
        int largestCount = 0;
        for(int i=0;i<values.length;i++){
            if(values[i] > largestCount){
                largestCount = values[i];
                largestIndex = i;
            }
        }
        return largestIndex;
    }
    public static void main(String[] args){
        WordLengths test = new WordLengths();
        test.testCountWordLengths();
    }
}
