
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key){
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
            String add = Character.toString(myText.charAt(index+key.length()));
            follows.add(add);
        }
        return follows;
    }
}
