
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender {
    private int numMovies;
    private Random moviePicker;
    private int numSimilarRaters;
    private int minimalRaters;
    
    public RecommendationRunner(){
        numMovies = 20;
        moviePicker = new Random();
        numSimilarRaters = 20;
        minimalRaters = 5;
    }
    
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> toRate = new ArrayList<String>();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(int i=0;i<numMovies;i++){
            toRate.add(movies.get(moviePicker.nextInt(MovieDatabase.size())));
        }
        System.out.println(toRate);
        return toRate;
    }
    
    public void printRecommendationsFor (String webRaterID){
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID,numSimilarRaters,minimalRaters);
        System.out.println("<h1 id='header'>Your Movie Recommendations</h1>");
        if(ratings.size()==0){
            System.out.println("No recommendations available for your preferences. Sorry!");
        } else {
            int cap = ratings.size();
            if(cap > numMovies){
                cap = numMovies;
            }
            System.out.println("<table>");
            System.out.println("<tr><th>Movie Title</th><th>Year Released</th><th>Genre(s)</th><th>Length</th><th>Country</th><th>Director(s)</th><th>Similarity Rating</th></tr>");
            for(int i=0;i<cap;i++){
                Rating r = ratings.get(i);
                String itemID = r.getItem();
                System.out.println("<tr><td>"+(i+1)+". "+MovieDatabase.getTitle(itemID)+"</td>");
                System.out.println("<td>"+MovieDatabase.getYear(itemID)+"</td>");
                System.out.println("<td>"+MovieDatabase.getGenres(itemID)+"</td>");
                System.out.println("<td>"+MovieDatabase.getMinutes(itemID)+"</td>");
                System.out.println("<td>"+MovieDatabase.getCountry(itemID)+"</td>");
                System.out.println("<td>"+MovieDatabase.getDirector(itemID)+"</td>");
                System.out.println("<td>"+r.getValue()+"</td></tr>");
            }
            System.out.println("</table>");
            System.out.println("<style>table{width: 100%; text-align: center; background-color: beige;}</style>");
            System.out.println("<style>tr,td{font-family: georgia; font-size: 16px; padding: 10px 10px 10px 10px;}</style>");
            System.out.println("<style>th{font-weight: bold; font-size: 24px}</style>");
            System.out.println("<style>#header{text-align: center; background-color: lightblue; padding: 40px; color: black;}</style>");
        }
    }
}
