
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> resultList = getLargest(list,50);
        int count = 0;
        for(QuakeEntry qe : resultList){
            System.out.println(qe);
            count++;
        }
        System.out.println("Read "+count+" quakes for that count.");
    }
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int largestIndex = 0;
        double largestMagnitude = 0.0;
        for(QuakeEntry qe : data){
            if(qe.getMagnitude() > largestMagnitude){
                largestMagnitude = qe.getMagnitude();
                largestIndex = data.indexOf(qe);
            }
        }
        //System.out.println("Largest index: "+largestIndex);
        //System.out.println("Largest magnitude: "+largestMagnitude);
        return largestIndex;
    }
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data,int howMany){
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(data);
        for(int i=0;i<howMany;i++){
            int idxLargest = indexOfLargest(copy);
            QuakeEntry top = copy.get(idxLargest);
            result.add(top);
            copy.remove(top);
        }
        return result;
    }
}
