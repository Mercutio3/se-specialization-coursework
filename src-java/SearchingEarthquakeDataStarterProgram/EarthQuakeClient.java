import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);

            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getLocation().distanceTo(from) < distMax){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,double minDepth,double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> depthList = filterByDepth(list,-4000.0,-2000.0);
        int count = 0;
        for(QuakeEntry qe : depthList){
            System.out.println(qe);
            count++;
        }
        System.out.println("Read "+count+" quakes for that reading.");
    }
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,String where,String phrase){
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        if(where.equals("start")){
            for(QuakeEntry qe : quakeData){
                String quakeInfo = qe.getInfo();
                if(quakeInfo.startsWith(phrase)){
                    result.add(qe);
                }
            }
        } else if(where.equals("end")){
            for(QuakeEntry qe : quakeData){
                String quakeInfo = qe.getInfo();
                if(quakeInfo.endsWith(phrase)){
                    result.add(qe);
                }
            }
        } else {
            for(QuakeEntry qe : quakeData){
                String quakeInfo = qe.getInfo();
                int tempIndex = quakeInfo.indexOf(phrase);
                if(tempIndex != -1){
                    result.add(qe);
                }
            }
        }
        return result;
    }
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> phraseList = filterByPhrase(list,"any","Can");
        int count = 0;
        for(QuakeEntry qe : phraseList){
            System.out.println(qe);
            count++;
        }
        System.out.println("Read "+count+" quakes for that phrase.");
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> bigList = filterByMagnitude(list,5.0);
        int count = 0;
        for(QuakeEntry qe : bigList){
            System.out.println(qe);
            count++;
        }
        System.out.println("Read "+count+" quakes for that reading.");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom"; 
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        // TODO
        int count = 0;
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list,1000000.0,city);
        for(QuakeEntry qe : close){
            System.out.println(qe.getLocation().distanceTo(city)+"—"+qe.getInfo());
            count++;
        }
        System.out.println("Found "+count+" quakes for that criteria.");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
