
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
    private String myGenre;
    
    public GenreFilter(String genre){
        myGenre = genre;
    }
    
    public boolean satisfies(String id){
        String thisGenre = MovieDatabase.getGenres(id);
        int gIdx = thisGenre.indexOf(myGenre);
        if(gIdx != -1){
            return true;
        }
        return false;
    }
}
