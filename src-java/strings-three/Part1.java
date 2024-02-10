
/**
 * Part of Coursera course "Java Programming: Solving Problems with Software".
 * This code was used for the final assignment of Week 2. It finds all genes
 * in the "GRch38dnapart.fa" file and stores them. Then it prints out the
 * number of genes, genes with >60 characters, genes with >0.35 ratio of CG
 * to all letters, and length of the longest gene, in that order.
 * 
 * This program helped me understand basic string operations and methods like
 * indexOf(), length(), substring(), etc.
 * 
 * @author Santiago Domínguez.
 * @version Sunday August 30, 2020.
 */
import edu.duke.*;

public class Part1 {
    public StorageResource getAllGenesAlt(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        int currentIndex;
        int count = 0;
        String currentGene = findGene(dna,startIndex);
        while(true){
            if(currentGene.isEmpty()){
                break;
            }
            currentIndex = dna.indexOf(currentGene,startIndex);
            count++;
            geneList.add(currentGene);
            System.out.println(count+" "+currentGene.length()+" "+currentIndex+" Add: "+currentGene);
            startIndex = currentIndex+currentGene.length();
            currentGene = findGene(dna,startIndex);
        }
        return geneList;
    }
    
    public StorageResource getAllGenes(String dna){
        int findIndex = 0;
        StorageResource geneList = new StorageResource();
        String actualDNA = dna.toUpperCase();
        while(true){
            String foundGene = findGene(actualDNA, findIndex);
            if(foundGene.isEmpty()){
                break;
            }
            //System.out.println("Found gene: "+foundGene);
            geneList.add(foundGene);
            findIndex += foundGene.length();
        }
        return geneList;
    }
    
    public String findGene(String dna, int startLooking){
        int startIndex = dna.indexOf("ATG", startLooking);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex;
        if(taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)){
            minIndex = tagIndex;
        } else {
            minIndex = taaIndex;
        }
        if(minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)){
            minIndex = tgaIndex;
        }
        if(minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int sCodon = dna.indexOf(stopCodon, startIndex+3);
        while(sCodon != -1){
            int difference = sCodon - startIndex;
            if(difference%3 == 0){
                return sCodon;
            } else {
                sCodon = dna.indexOf(stopCodon, sCodon+1);
            }
        }
        return -1;
    }
    
    public static void main(String[] args){
        Part1 test = new Part1();
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dnaStrand = fr.asString();
        StorageResource genes = test.getAllGenesAlt(dnaStrand);
        System.out.println("Printing information from StorageResource.");
        test.processGenes(genes);
    }
    
    public double cgRatio(String dna){
        //String actualDNA = dna.toUpperCase();
        int countC = countC(dna);
        int countG = countG(dna);
        //System.out.println("Number of Cs: "+countC);
        //System.out.println("Number of Gs: "+countG);
        int ratioPart = countC + countG;
        return ((double) ratioPart)/dna.length();
    }
    
    public int countC(String dna){
        int countC = 0;
        int start = 0;
        while(true){
            int pos = dna.indexOf("C",start);
            if(pos == -1){
                break;
            }
            countC += 1;
            start = pos+1;
        }
        return countC;
    }
    
    public int countG(String dna){
        int countG = 0;
        int start = 0;
        while(true){
            int pos = dna.indexOf("G",start);
            if(pos == -1){
                break;
            }
            countG += 1;
            start = pos+1;
        }
        return countG;
    }
    
    public void processGenes(StorageResource sr){
        int numberGenes = 0;
        int sixtyCount = 0;
        int cgCount = 0;
        int longString = 0;
        for(String item : sr.data()){
            numberGenes++;
            if(item.length() > 60){
                sixtyCount++;
            }
            if(cgRatio(item) > 0.35){
                cgCount++;
            }
            if(item.length() > longString){
                longString = item.length();
            }
        }
        System.out.println("Number of genes: "+numberGenes);
        System.out.println("Number of genes with >60 chars: "+sixtyCount);
        System.out.println("Number of genes with >0.35 cgratio: "+cgCount);
        System.out.println("Length of longest gene: "+longString);
    }
}
