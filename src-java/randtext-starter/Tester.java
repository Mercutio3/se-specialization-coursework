
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> result = markov.getFollows("e");
        System.out.println(result);
        System.out.println("ArrayL size: "+result.size());
    }
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> result = markov.getFollows("he");
        System.out.println(result);
        System.out.println("ArrayL size: "+result.size());
    }
}
