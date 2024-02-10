
/**
 * Write a description of MatchFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchFilter implements Filter{
    private ArrayList<Filter> fList;
    private ArrayList<String> fNames;
    public MatchFilter(){
        fList = new ArrayList<Filter>();
        fNames = new ArrayList<String>();
    }
    
    public void addFilter(Filter f){
        fList.add(f);
    }
    
    public String getName(){
        for(Filter f : fList){
            fNames.add(f.getName());
        }
        return fNames.toString();
    }
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : fList){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
}
