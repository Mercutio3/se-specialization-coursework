
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double minDep = 0;
    private double maxDep = 0;
    private String name = "Depth";
    public DepthFilter(double min,double max){
        minDep = min;
        maxDep = max;
    }
    
    public String getName(){
        return name;
    }
    public boolean satisfies(QuakeEntry qe){
        if(qe.getDepth() >= minDep && qe.getDepth() <= maxDep){
            return true;
        }
        return false;
    }
}
