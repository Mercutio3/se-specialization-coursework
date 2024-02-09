
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        
        ArrayList<Rating> ratings = tr.getAverageRatings(35);
        Collections.sort(ratings);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByYear(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20,new YearAfterFilter(2000));
        Collections.sort(ratings);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByGenre(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20,new GenreFilter("Comedy"));
        Collections.sort(ratings);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(r.getItem()));
        }
    }
    public void printAverageRatingsByMinutes(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(5,new MinutesFilter(105,135));
        Collections.sort(ratings);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByDirector(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(4,new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        Collections.sort(ratings);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t"+MovieDatabase.getDirector(r.getItem()));
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
    public void printAverageRatingsByDirectorsAndMinutes(){
        String moviefile = "ratedmoviesfull.csv";
        String raterfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        
        AllFilters filterList = new AllFilters();
        filterList.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        filterList.addFilter(new MinutesFilter(90,180));
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(3,filterList);
        Collections.sort(ratings);
        
        System.out.println("Total movies: "+MovieDatabase.size());
        System.out.println("Total raters: "+tr.getRaterSize());
        System.out.println("Found "+ratings.size()+" movies.");
        
        for(Rating r : ratings){
            System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t"+MovieDatabase.getDirector(r.getItem()));
        }
    }
}
