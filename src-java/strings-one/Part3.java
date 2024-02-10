
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOcurrences(String stringa, String stringb){
        int aLength = stringa.length();
        int startIndex1 = stringb.indexOf(stringa);
        if(startIndex1 != -1){
            int startIndex2 = stringb.indexOf(stringa,startIndex1+aLength);
            if(startIndex2 != -1){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void testing(){
        System.out.println(twoOcurrences("by","A story by Abby."));
        System.out.println(twoOcurrences("a","banana"));
        System.out.println(twoOcurrences("atg","catgtcgga"));
        System.out.println(lastPart("an","banana"));
        System.out.println(lastPart("zoo","forest"));
    }
    
    public String lastPart(String stringa, String stringb){
        String output = "";
        int startIndex = stringb.indexOf(stringa);
        if(startIndex != -1){
            int blength = stringb.length();
            int alength = stringa.length();
            int startIndex2 = stringb.indexOf(startIndex+alength);
            output = stringb.substring(startIndex2,blength-1);
            return output;
        } else {
            return stringb;
        }
    }
    
    public static void main(String[] args){
        Part3 test = new Part3();
        test.testing();
    }
}
