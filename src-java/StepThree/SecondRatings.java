
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile,String ratingsfile){
        FirstRatings firstR = new FirstRatings();
        myMovies = firstR.loadMovies(moviefile);
        myRaters = firstR.loadRaters(ratingsfile);
    }
    public int getMovieSize(){
        return myMovies.size();
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
        return sum/numRaters;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
        for(Movie m : myMovies){
            String movieID = m.getID();
            Double avgRating = getAverageByID(movieID,minimalRaters);
            if(avgRating != 0.0){
                Rating rat = new Rating(movieID,avgRating);
                avgRatingList.add(rat);
            }
        }
        return avgRatingList;
    }
    public String getTitle(String id){
        for(Movie m : myMovies){
            String mID = m.getID();
            if(mID.equals(id)){
                return m.getTitle();
            }
        }
        return "ID not found";
    }
    public String getID(String title){
        for(Movie m : myMovies){
            String tempTitle = m.getTitle();
            if(tempTitle.equals(title)){
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}
