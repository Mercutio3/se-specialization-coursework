
/**
 * Part of a Coursera course. Experimenting and testing with methods meant
 * to find genes in a DNA strand. This is the least complex implementation.
 * 
 * @author Santiago Domínguez
 * @version August 30, 2020.
 */
public class Part1 {
    public void printAllGenes(String dna){
        int findIndex = 0;
        String actualDNA = dna.toUpperCase();
        while(true){
            String foundGene = findGene(actualDNA, findIndex);
            if(foundGene.isEmpty()){
                break;
            }
            System.out.println("Found gene: "+foundGene);
            findIndex += foundGene.length();
        }
    }
    
    public void testFindGene(){
        System.out.println("New Test");
        System.out.println("Gene 1: "+findGene("CCGTCTTCGCTTGCTCG",0));
        System.out.println("Gene 2: "+findGene("CATGCCCTTTTAAGGG",0));
        System.out.println("Gene 3: "+findGene("CATGCCCTAGTAAG",0));
        System.out.println("Gene 4: "+findGene("CATGGGGGGGGGCCC",0));
        System.out.println("Gene 5: "+findGene("CATGCCCCTGA",0));
        System.out.println("Gene 1 should be:");
        System.out.println("Gene 2 should be: ATGCCCTTTTAA");
        System.out.println("Gene 3 should be: ATGCCCTAG");
        System.out.println("Gene 4 should be:");
        System.out.println("Gene 5 should be:");
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
    
    public void testStopCodon(){
        int bruh = findStopCodon("CATGCCCTAAC",1,"TAA");
        int bruh2 = findStopCodon("CATGCCCGTAAC",1,"TAA");
        int bruh3 = findStopCodon("CATGCCCTAGC",1,"TAG");
        int bruh4 = findStopCodon("CATGCCCGTAGC",1,"TAG");
        int bruh5 = findStopCodon("CATGCCCTGAC",1,"TGA");
        int bruh6 = findStopCodon("CATGCCCGTGAC",1,"TGA");
        System.out.println("Gene is: "+bruh);
        System.out.println("Gene is: "+bruh2);
        System.out.println("Gene is: "+bruh3);
        System.out.println("Gene is: "+bruh4);
        System.out.println("Gene is: "+bruh5);
        System.out.println("Gene is: "+bruh6);
    }
    
    public static void main(String[] args){
        Part1 test = new Part1();
        test.printAllGenes("cagggagacaatgaacggcgattcagtccacagtgtcgatctaataacccgtggaacaagttagacctcgcgagctgcccaaggatccagatcgtcgaggacgggcagggctatagtgaagccagagtttggactaagctctgcatggctgtattccgaggattgctagatgcttcggggacttaggatggttgccgggc");
    }
}