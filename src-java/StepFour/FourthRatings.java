
/**
 * Calculates similarity ratings and returns an ArrayList of those results.
 * 
 * @Santiago Domínguez 
 * @V1.1, fixed line 9 code in getSimilarRatings which caused an exception.
 */
import java.util.*;

public class FourthRatings {
    //private ArrayList<EfficientRater> myRaters;
    
    private double getAverageByID(String id,int minimalRaters){
        int numRaters = 0;
        double sum = 0.0;
        ArrayList<Double> calcAvg = new ArrayList<Double>();
        for(Rater r : RaterDatabase.getRaters()){
            if(r.hasRating(id)){
                numRaters++;
            }
        }
        //System.out.println("++"+MovieDatabase.getTitle(id)+numRaters);
        if(numRaters < minimalRaters){
            return 0.0;
        } else {
            for(Rater r : RaterDatabase.getRaters()){
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
    
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        double result = 0.0;
        for(String movie : movies){
            if(me.hasRating(movie) && r.hasRating(movie)){
                double scaledMe = me.getRating(movie) - 5.0;
                double scaledR = r.getRating(movie) - 5.0;
                result += scaledMe*scaledR;
            }
        }
        return result;
    }
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        
        for(Rater r : raters){
            double dProduct = 0.0;
            if(!r.getID().equals(id)){
                dProduct = dotProduct(me,r);
            }
            if(dProduct>0.0){
                Rating sRating = new Rating(r.getID(),dProduct);
                result.add(sRating);
            }
        }
        Collections.sort(result);
        Collections.reverse(result);
        return result;
    }
    public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String movie : movies){
            int raterCount = 0;
            double sum = 0.0;
            for(int i=0;i<numSimilarRaters&&i<similarities.size();i++){
                Rating rat = similarities.get(i);
                String rID = rat.getItem();
                Rater r = RaterDatabase.getRater(rID);
                if(r.hasRating(movie)){
                    raterCount++;
                    double rating = r.getRating(movie);
                    double temp = rat.getValue() * rating;
                    sum += temp;
                }
            }
            if(raterCount>=minimalRaters){
                double wAverage = sum/raterCount;
                result.add(new Rating(movie,wAverage));
            }
        }
        Collections.sort(result);
        Collections.reverse(result);
        return result;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter f){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(f);
        
        for(String movie : movies){
            int raterCount = 0;
            double sum = 0.0;
            for(int i=0;i<numSimilarRaters;i++){
                Rating rat = similarities.get(i);
                String rID = rat.getItem();
                Rater r = RaterDatabase.getRater(rID);
                if(r.hasRating(movie)){
                    raterCount++;
                    double rating = r.getRating(movie);
                    double temp = rat.getValue() * rating;
                    sum += temp;
                }
            }
            if(raterCount>=minimalRaters){
                double wAverage = sum/raterCount;
                result.add(new Rating(movie,wAverage));
            }
        }
        Collections.sort(result);
        Collections.reverse(result);
        return result;
    }
}
