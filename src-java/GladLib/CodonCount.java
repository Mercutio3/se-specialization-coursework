
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap<String,Integer> codonMap;
    public CodonCount(){
        codonMap = new HashMap<String,Integer>();
    }
    public void buildCodonMap(int start,String dna){
        for(int i=start;i<dna.length();i+=3){
            if(i+3 < dna.length()){
                String codon = dna.substring(i,i+3);
                System.out.println(codon);
                if(!codonMap.containsKey(codon)){
                    codonMap.put(codon,1);
                } else {
                    codonMap.put(codon,codonMap.get(codon)+1);
                }
            }
        }
    }
    public String getMostCommonCodon(){
        String result = "";
        int biggest = 0;
        for(String c : codonMap.keySet()){
            int freq = codonMap.get(c);
            if(freq > biggest){
                biggest = freq;
                result = c;
            }
        }
        return result;
    }
    public void printCodonCounts(int start,int end){
        for(String codon : codonMap.keySet()){
            int count = codonMap.get(codon);
            if(count>=start && count<=end){
                System.out.println(codon+"\t"+codonMap.get(codon));
            }
        }
    }
    public void tester(){
        System.out.println("----------");
        FileResource fr = new FileResource();
        String dnaStrand = fr.asString();
        buildCodonMap(0,dnaStrand.trim());
        System.out.println("Most common codon: "+getMostCommonCodon()+" with count "+codonMap.get(getMostCommonCodon()));
        printCodonCounts(5,8);
    }
    public static void main(String[] args){
        CodonCount test = new CodonCount();
        test.tester();
    }
}
