
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String result = "";
        int startIndex = dna.indexOf(startCodon);
        if(startIndex==-1){
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
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
        System.out.println("NEW TEST2");
        String dnaString = "ATCGTTAGGTAAGTAGTGG";
        System.out.println("DNA String is "+dnaString);
        String result = findSimpleGene(dnaString,"ATG","TAA");
        System.out.println("Gene is "+result);
        
        dnaString = "GTATGCTGAATTGG";
        System.out.println("DNA String is "+dnaString);
        result = findSimpleGene(dnaString,"ATG","TAA");
        System.out.println("Gene is "+result);
        
        dnaString = "ATAGGTCGTTTAGGATTGAAT";
        System.out.println("DNA String is "+dnaString);
        result = findSimpleGene(dnaString,"ATG","TAA");
        System.out.println("Gene is "+result);
        
        dnaString = "ATAGGTCATGTTAGGATTGCAATAAGT";
        dnaString = "ataggtcatgttaggattgcaataagt";
        System.out.println("DNA String is "+dnaString);
        result = findSimpleGene(dnaString,"atg","taa");
        System.out.println("Gene is "+result);
        
        dnaString = "ATAGGTCATGTTAGGATTGAATAAGT";
        System.out.println("DNA String is "+dnaString);
        result = findSimpleGene(dnaString,"ATG","TAA");
        System.out.println("Gene is "+result);
    }
    
    public static void main(String [] args){
        Part2 test = new Part2();
        test.testSimpleGene();
    }
}
