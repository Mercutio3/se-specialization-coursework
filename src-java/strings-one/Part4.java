import edu.duke.*;

/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    void findLinks(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for(String link : ur.words()){
            String linkLowerCase = link.toLowerCase();
            int index = linkLowerCase.indexOf("youtube.com");
            if(index != -1){
                int start = link.lastIndexOf("\"", index);
                int end = link.lastIndexOf("\"", index+1);
                System.out.println(link.substring(start+1, end));
            }
        }
    }
    
    public static void main(String[] args){
        Part4 test = new Part4();
        test.findLinks();
    }
}