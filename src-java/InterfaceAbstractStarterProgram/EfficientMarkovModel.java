
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int mkNum;
    private HashMap<String,ArrayList<String>> map;
    public EfficientMarkovModel(int num) {
        myRandom = new Random();
        mkNum = num;
        map = new HashMap<String,ArrayList<String>>();
    }
    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
        printHashMapInfo();
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
    public void buildMap(){
        for(int i=0; i<myText.length()-mkNum+1;i++){
            String key = myText.substring(i, i+mkNum);
            ArrayList<String> follows = new ArrayList<>();
            if(i+mkNum>=myText.length()){
                map.put(key, follows);
            } else {
                if(map.containsKey(key)){               
                    map.get(key).add(myText.substring(i+mkNum, i+mkNum+1));
                } else {
                    follows.add(myText.substring(i+mkNum, i+mkNum+1));
                    map.put(key, follows);
                }
            }
        }
    }
    public void printHashMapInfo(){
        //System.out.println(map);
        System.out.println("Map size: "+map.size());
        int largest = 0;
        for(String key : map.keySet()){
            ArrayList<String> tList = map.get(key);
            if(tList.size() > largest){
                largest = tList.size();
            }
        }
        System.out.println("Largest value: "+largest);
        System.out.println("Keys with largest value: ");
        for(String key : map.keySet()){
            ArrayList<String> tList = map.get(key);
            if(tList.size() == largest){
                System.out.println(key);
            }
        }
    }
    protected ArrayList<String> getFollows(String key){
        return map.get(key);
    }
    public String toString(){
        return "EfficientMarkovModel order "+mkNum;
    }
}
