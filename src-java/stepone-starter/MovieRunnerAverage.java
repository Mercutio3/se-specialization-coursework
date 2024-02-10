
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        String moviefile = "data/ratedmoviesfull.csv";
        String raterfile = "data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile,raterfile);
        
        ArrayList<Rating> ratings = sr.getAverageRatings(12);
        Collections.sort(ratings);
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+sr.getTitle(r.getItem()));
        }
        
        System.out.println("Total movies: "+sr.getMovieSize());
        System.out.println("Total raters: "+sr.getRaterSize());
    }
    public void getAverageRatingOneMovie(){
        String moviefile = "data/ratedmoviesfull.csv";
        String raterfile = "data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile,raterfile);
        
        String whichMovie = "Vacation";
        ArrayList<Rating> ratings = sr.getAverageRatings(3);
        String id = sr.getID(whichMovie);
        for(Rating r : ratings){
            String ratedMovieID = r.getItem();
            if(ratedMovieID.equals(id)){
                System.out.println(r.getValue());
            }
        }
    }
}
