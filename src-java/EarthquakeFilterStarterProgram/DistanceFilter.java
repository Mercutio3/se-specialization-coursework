
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private double maxDist;
    private Location from;
    private String name = "Distance";
    public DistanceFilter(Location loc,double max){
        maxDist = max;
        from = loc;
    }
    
    public String getName(){
        return name;
    }
    public boolean satisfies(QuakeEntry qe){
        if(qe.getLocation().distanceTo(from) < maxDist){
            return true;
        }
        return false;
    }
}
