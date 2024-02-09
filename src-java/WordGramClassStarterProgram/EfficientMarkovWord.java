
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<WordGram,ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText,index,myOrder);
        sb.append(kGram);
        sb.append(" ");     
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);            
            if ( follows == null || follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            String st = kGram.toString()+" "+next;
            st = st.trim();
            String[] temp = st.split("\\s+");
            kGram = new WordGram(temp,1,myOrder);            
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        int index = -1;
        for(int i=start; i <= words.length-target.length(); i++){
            WordGram temp = new WordGram(words,i,target.length());
            if(temp.equals(target)){
                index = i;
                break;
            }
        }
        return index;
    }
    
    public void buildMap(){
        for(int i=0; i <= myText.length-myOrder; i++){
            WordGram wg = new WordGram(myText,i,myOrder);
            ArrayList<String> follows  = new ArrayList<String>();
            int pos = i;
            while(true){
                int k = indexOf(myText,wg,pos);
                if(k == -1 || k == myText.length-myOrder){
                    break;
                }
                follows.add(myText[k+myOrder]);
                pos = k+myOrder;
            }
            int temp = 0;
            for(WordGram g: map.keySet()){
                if(g.equals(wg)){
                    temp = 1;
                    break;
                }
            }
            if(temp == 0){
                map.put(wg,follows);
            }            
        }
    }
    
    public ArrayList<String> getFollows(WordGram kGram){
        ArrayList<String> follows =  new ArrayList<String>();
        for(WordGram wg: map.keySet()){
            if(kGram.equals(wg)){
                follows =  map.get(wg);
                break;
            }
        }
        return follows;
    }
    
    private void printHashMapInfo(){
        //System.out.println(map);
        System.out.println("# of keys: "+map.size());
        int largest = 0;
        for(WordGram wg : map.keySet()){
            ArrayList<String> temp = map.get(wg);
            if(temp.size() > largest){
                largest = temp.size();
            }
        }
        System.out.println("Largest key: "+largest);
        for(WordGram wg : map.keySet()){
            ArrayList<String> temp = map.get(wg);
            if(temp.size() == largest){
                System.out.println("With largest: "+wg+" with "+temp);
            }
        }
    }
}
