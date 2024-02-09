
/**
 * Basic code that finds the number of occurences of stringa inside stringb.
 * 
 * @author Santiago Domínguez
 * @version August 30, 2020.
 */
public class Part2 {
    public static void main(String[] args){
        Part2 test = new Part2();
        test.testHowMany();
    }
    
    public void testHowMany(){
        System.out.println(howMany("weed","bruhweedbruhweed"));
        System.out.println(howMany("serp","serpiguanococotorti"));
    }
    
    public int howMany(String stringa, String stringb){
        int startSearch = 0;
        int count = 0;
        while(true){
            int startA = stringb.indexOf(stringa, startSearch);
            if(startA == -1){
                break;
            }
            count += 1;
            startSearch += startA+stringa.length();
        }
        return count;
    }
}
