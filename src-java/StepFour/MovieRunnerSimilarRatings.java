
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        
        ArrayList<Rating> ratings = fr.getAverageRatings(35);
        Collections.sort(ratings);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        
        AllFilters filterList = new AllFilters();
        filterList.addFilter(new YearAfterFilter(1990));
        filterList.addFilter(new GenreFilter("Drama"));
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(8,filterList);
        Collections.sort(ratings);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatings(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        
        ArrayList<Rating> ratings = fr.getSimilarRatings("71",20,5);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printSimilarRatingsByGenre(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("964",20,5,new GenreFilter("Mystery"));
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printSimilarRatingsByDirector(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("120",10,2,new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printSimilarRatingsByGenreAndMinutes(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        
        AllFilters filterList = new AllFilters();
        filterList.addFilter(new MinutesFilter(80,160));
        filterList.addFilter(new GenreFilter("Drama"));
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("168",10,3,filterList);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printSimilarRatingsByYearAndMinutes(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        
        AllFilters filterList = new AllFilters();
        filterList.addFilter(new MinutesFilter(70,200));
        filterList.addFilter(new YearAfterFilter(1975));
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("314",10,5,filterList);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+RaterDatabase.size());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
}
