
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size,int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void testHashMap(){
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        st = st.replace('\n', ' ');
        int size = 50;
        
        EfficientMarkovModel markov = new EfficientMarkovModel(2);
        runModel(markov, st, size,42);
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        
        MarkovModel m2 = new MarkovModel(2);
        runModel(m2, st, size,42);
        
        EfficientMarkovModel markov = new EfficientMarkovModel(2);
        runModel(markov, st, size,42);
    }
    
    public void quiz(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        EfficientMarkovModel ef = new EfficientMarkovModel(6);
        runModel(ef, st, size,792);
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,1);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,1);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,1);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size,1);

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}
