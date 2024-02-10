
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> result = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genres = record.get("genre");
            String director = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            Movie m = new Movie(id,title,year,genres,director,country,poster,minutes);
            result.add(m);
        }
        return result;
    }
    public void testLoadMovies(){
        ArrayList<Movie> movieList = loadMovies("data/ratedmoviesfull.csv");
        int comedyCount = 0;
        int minuteCount = 0;
        int bigDirector = 0;
        String director = "";
        for(Movie m : movieList){
            String g = m.getGenres();
            int contains = g.indexOf("Comedy");
            if(contains != -1){
                comedyCount++;
            }
            int min = m.getMinutes();
            if(min > 150){
                minuteCount++;
            }
        }
        System.out.println("Comedy movie count: "+comedyCount);
        System.out.println("150+ min movie count: "+ minuteCount);
        HashMap<String,Integer> dMovies = new HashMap<String,Integer>();
        for(Movie m : movieList){
            String d = m.getDirector();
            if(!dMovies.containsKey(d)){
                dMovies.put(d,1);
            } else {
                dMovies.put(d,dMovies.get(d)+1);
            }
        }
        for(String d : dMovies.keySet()){
            int num = dMovies.get(d);
            if(num > bigDirector){
                bigDirector = num;
                director = d;
            }
        }
        System.out.println("Biggest director: "+director+" with "+bigDirector);
    }
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> result = new ArrayList<Rater>();
        int read = 0;
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            String id = record.get("rater_id");
            String sread = String.valueOf(read);
            if(id.equals(sread)){
                Rater r = result.get(read-1);
                r.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                result.set(read-1,r);
            } else {
                Rater r = new Rater(id);
                r.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                read++;
                result.add(r);
            }
        }
        return result;
    }
    public void testLoadRaters(){
        ArrayList<Rater> raterList = loadRaters("data/ratings.csv");
        System.out.println("Number of raters: "+raterList.size());
        //for(Rater r : raterList){
            //System.out.println("Rater id: "+r.getID()+" - Number of ratings: "+r.numRatings());
        //}
        String readID = "193";
        int max = 0;
        for(Rater r : raterList){
            String cherp = r.getID();
            if(cherp.equals(readID)){
                System.out.println("Num of ratings for rater "+readID+": "+r.numRatings());
            }
            if(r.numRatings() > max){
                max = r.numRatings();
            }
        }
        System.out.println("Max: "+max);
        int numMax = 0;
        for(Rater r : raterList){
            if(r.numRatings() == max){
                numMax++;
                System.out.println("Rater "+r.getID()+" has max.");
            }
        }
        System.out.println("Total "+numMax+" raters with max.");
        String readMovie = "1798709";
        int rateCount = 0;
        for(Rater r : raterList){
            if(r.hasRating(readMovie)){
                rateCount++;
            }
        }
        System.out.println("Ratings for movie "+readMovie+": "+rateCount);
        ArrayList<String> diffMovies = new ArrayList<String>();
        for(Rater r : raterList){
            ArrayList<String> temp = r.getItemsRated();
            for(String rating : temp){
                if(!diffMovies.contains(rating)){
                    diffMovies.add(rating);
                }
            }
        }
        System.out.println("Different movies: "+diffMovies.size());
    }
}
