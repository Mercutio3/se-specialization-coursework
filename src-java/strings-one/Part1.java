
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if(startIndex==-1){
            return "";
        }
        int stopIndex = dna.indexOf("TAA",startIndex+3);
        if(stopIndex==-1){
            return "";
        }
        int diffIndex = stopIndex - startIndex;
        if(diffIndex%3==0){
            result = dna.substring(startIndex,stopIndex+3);
            return result;
        } else {
            return "";
        }
    }
    
    public void testSimpleGene(){
        String dnaString = "ATCGTTAGGTAAGTAGTGG";
        System.out.println("DNA String is "+dnaString);
        String result = findSimpleGene(dnaString);
        System.out.println("Gene is "+result);
        
        dnaString = "GTATGCTGAATTGG";
        System.out.println("DNA String is "+dnaString);
        result = findSimpleGene(dnaString);
        System.out.println("Gene is "+result);
        
        dnaString = "ATAGGTCGTTTAGGATTGAAT";
        System.out.println("DNA String is "+dnaString);
        result = findSimpleGene(dnaString);
        System.out.println("Gene is "+result);
        
        dnaString = "ATAGGTCATGTTAGGATTGCAATAAGT";
        System.out.println("DNA String is "+dnaString);
        result = findSimpleGene(dnaString);
        System.out.println("Gene is "+result);
        
        dnaString = "ATAGGTCATGTTAGGATTGAATAAGT";
        System.out.println("DNA String is "+dnaString);
        result = findSimpleGene(dnaString);
        System.out.println("Gene is "+result);
    }
    
    public static void main(String [] args){
        Part1 test = new Part1();
        test.testSimpleGene();
    }
}
