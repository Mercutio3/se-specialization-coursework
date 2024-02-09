import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Location loc = new Location(39.7392, -104.9903);

        Filter f = new DistanceFilter(loc,1000000.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        Filter f2 = new PhraseFilter("a","end");
        ArrayList<QuakeEntry> m8 = filter(m7,f2);
        for(QuakeEntry qe : m8){
            System.out.println(qe);
        }
        System.out.println("Eq number: "+m8.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchFilter maf = new MatchFilter();
        maf.addFilter(new MagnitudeFilter(0.0,2.0));
        maf.addFilter(new DepthFilter(-100000.0,-10000.0));
        maf.addFilter(new PhraseFilter("a","any"));
        ArrayList<QuakeEntry> result = filter(list,maf);
        for(QuakeEntry qe : result){
            System.out.println(qe);
        }
        System.out.println("Filters used: "+maf.getName());
        System.out.println("# of earthquakes: "+result.size());
    }
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchFilter maf = new MatchFilter();
        maf.addFilter(new MagnitudeFilter(0.0,5.0));
        Location loc = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(loc,3000000.0));
        maf.addFilter(new PhraseFilter("e","any"));
        ArrayList<QuakeEntry> result = filter(list,maf);
        for(QuakeEntry qe : result){
            System.out.println(qe);
        }
        System.out.println("# of earthquakes: "+result.size());
    }
}
