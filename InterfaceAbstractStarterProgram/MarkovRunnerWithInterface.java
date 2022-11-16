
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;
import edu.duke.*;
import java.util.Random; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov.toString());
        for(int k=0; k < 3; k++){ 
            String st= markov.getRandomText(size);
            printOut(st);
        } 
    }
    
    public void compareMethods() {
       FileResource fr = new FileResource();
       String st = fr.asString();
       st = st.replace('\n', ' ');
       EfficientMarkovModel m2 = new EfficientMarkovModel(2);
       MarkovModel mm = new MarkovModel(2);
       int size = 1000;
       int seed = 42;
       runModel(mm, st, size, seed);
       
    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "yes-this-is-a-thin-pretty-pink-thistle";
        EfficientMarkovModel m2 = new EfficientMarkovModel(5);
        int size = 50;
        int seed = 531;
        runModel(m2, st, size, seed);
        m2.buildMap();
        m2.printHashMapInfo();
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 273;
        MarkovZero mz = new MarkovZero(); 
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
