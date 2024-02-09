
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile){
        FirstRatings firstR = new FirstRatings();
        myRaters = firstR.loadRaters(ratingsfile);
    }
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id,int minimalRaters){
        int numRaters = 0;
        double sum = 0.0;
        ArrayList<Double> calcAvg = new ArrayList<Double>();
        for(EfficientRater r : myRaters){
            if(r.hasRating(id)){
                numRaters++;
            }
        }
        //System.out.println("++"+MovieDatabase.getTitle(id)+numRaters);
        if(numRaters < minimalRaters){
            return 0.0;
        } else {
            for(EfficientRater r : myRaters){
                Double temp = r.getRating(id);
                if(temp!=-1.0){
                    calcAvg.add(r.getRating(id));
                }
            }
            for(Double num : calcAvg){
                //System.out.println(sum+" "+num);
                sum += num;
            }
        }
        //System.out.println(sum+" "+numRaters);
        //System.out.println("/"+MovieDatabase.getTitle(id));
        return sum/numRaters;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String m : movies){
            String movieID = m;
            Double avgRating = getAverageByID(movieID,minimalRaters);
            if(avgRating != 0.0){
                Rating rat = new Rating(movieID,avgRating);
                avgRatingList.add(rat);
            }
        }
        return avgRatingList;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String m : movies){
            String movieID = m;
            Double avgRating = getAverageByID(movieID,minimalRaters);
            if(avgRating != 0.0){
                //System.out.println("-"+MovieDatabase.getTitle(movieID));
                Rating rat = new Rating(movieID,avgRating);
                result.add(rat);
            }
        }
        //System.out.println(result);
        //System.out.println(result.size());
        return result;
    }
}
