
/**
 * Program that counts all genes inside a dna strand.
 * 
 * @author Santiago Domínguez
 * @version August 30, 2020.
 */
public class Part3 {
    public static void main(String[] args){
        Part1 finalTest = new Part1();
        Part3 finalTest2 = new Part3();
        String dna = "cagggagacaatgaacggcgattcagtccacagtgtcgatctaataacccgtggaacaagttagacctcgcgagctgcccaaggatccagatcgtcgaggacgggcagggctatagtgaagccagagtttggactaagctctgcatggctgtattccgaggattgctagatgcttcggggacttaggatggttgccgggc";
        finalTest.printAllGenes(dna);
        finalTest2.countAllGenes(dna);
    }
    
    public void countAllGenes(String dna){
        int findIndex = 0;
        String actualDNA = dna.toUpperCase();
        Part1 support = new Part1();
        int count = 0;
        while(true){
            String foundGene = support.findGene(actualDNA, findIndex);
            if(foundGene.isEmpty()){
                break;
            }
            count += 1;
            findIndex += foundGene.length();
        }
        System.out.println("Genes found: "+count);
    }
}
