
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int mkNum;
    public MarkovModel(int num) {
        myRandom = new Random();
        mkNum = num;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-mkNum);
        String key = myText.substring(index,index+mkNum);
        sb.append(key);
        for(int k=0; k < numChars-mkNum; k++){
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
    public String toString(){
        return "MarkovModel order "+mkNum;
    }
}
