
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Arrays;
public class DirectorsFilter implements Filter {
    private String[] myDirectors;
    
    public DirectorsFilter(String directors){
        myDirectors = directors.split("\\s*,");
        System.out.println(Arrays.toString(myDirectors));
    }
    
    @Override
    public boolean satisfies(String id){
        String[] directorList = MovieDatabase.getDirector(id).split("\\s*,");
        //System.out.println(Arrays.toString(directorQuery));
        for(int i=0;i<directorList.length;i++){
            directorList[i] = directorList[i].trim();
        }
        for(String director : myDirectors){
            for(int i=0;i<directorList.length;i++){
                if(director.equals(directorList[i])){
                    return true;
                }
            }
        }
        return false;
    }
}
