
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int markovNum;
    
    public MarkovModel(int num) {
        myRandom = new Random();
        markovNum = num;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-markovNum);
        String key = myText.substring(index,index+markovNum);
        sb.append(key);
        for(int k=0; k < numChars-markovNum; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
               break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()){
            int index = myText.indexOf(key,pos);
            pos = index+1;
            if(index == -1){                
                break;            
            }            
            if(index+key.length() >= myText.length()){
                break;            
            } 
            String add = Character.toString(myText.charAt(index+markovNum));
            follows.add(add);
        }
        return follows;
    }
}
