
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private HashMap<String,ArrayList<String>> wordsConsidered;
    
    private ArrayList<String> wordsUsed;
    private int wordsReplaced = 0;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {;
        wordsUsed = new ArrayList<String>();
        myMap = new HashMap<String,ArrayList<String>>();
        wordsConsidered = new HashMap<String,ArrayList<String>>();
        String[] labels = {"country","noun","animal","adjective","name","color","timeframe","verb","fruit"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(label.equals("number")){
            if(!wordsConsidered.containsKey(label)){
                ArrayList<String> list = readIt("/Users/santiagodominguezham/Desktop/Java/Coursera/GladLib/data/"+label+".txt");
                wordsConsidered.put(label,list);
            }
            return ""+myRandom.nextInt(50)+5;
        }
        if(!wordsConsidered.containsKey(label)){
            ArrayList<String> list = readIt("/Users/santiagodominguezham/Desktop/Java/Coursera/GladLib/data/"+label+".txt");
            wordsConsidered.put(label,list);
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        String returnWord = prefix+sub+suffix;
        for(int i=0;i<wordsUsed.size();i++){
            String current = wordsUsed.get(i);
            if(sub.equals(current)){
                sub = getSubstitute(w.substring(first+1,last));
                wordsReplaced++;
            }
        }
        wordsUsed.add(sub);
        return returnWord;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("Words replaced: "+wordsReplaced);
        System.out.println("Total words in map: "+totalWordsInMap());
        totalWordsConsidered();
    }
    
    public void totalWordsConsidered(){
        int count = 0;
        for(String key : wordsConsidered.keySet()){
            ArrayList<String> temp = wordsConsidered.get(key);
            for(int i=0;i<temp.size();i++){
                count++;
            }
        }
        System.out.println("Words considered: "+count);
    }
    
    public int totalWordsInMap(){
        int count = 0;
        for(String key : myMap.keySet()){
            ArrayList<String> temp = myMap.get(key);
            for(int i=0;i<temp.size();i++){
                count++;
            }
        }
        return count;
    }
}
