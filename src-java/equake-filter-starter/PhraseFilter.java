
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String phrase;
    private String where;
    private String name = "Phrase";
    public PhraseFilter(String query,String place){
        phrase = query;
        where = place;
    }
    
    public String getName(){
        return name;
    }
    public boolean satisfies(QuakeEntry qe){
        if(where.equals("start")){
            String quakeInfo = qe.getInfo();
            if(quakeInfo.startsWith(phrase)){
                return true;
            }
        } else if(where.equals("end")){
            String quakeInfo = qe.getInfo();
            if(quakeInfo.endsWith(phrase)){
                return true;
            }
        } else {
            String quakeInfo = qe.getInfo();
            int tempIndex = quakeInfo.indexOf(phrase);
            if(tempIndex != -1){
                return true;
            }
        }
        return false;
    }
}
