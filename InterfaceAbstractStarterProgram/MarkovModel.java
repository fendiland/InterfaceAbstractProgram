
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.util.Random;
public class MarkovModel extends AbstractMarkovModel {
    private int N;
    
    //public MarkovModel(int n) {
        //N = n;
    //}
    public MarkovModel(int n) {
        myRandom = new Random();
        N = n;
    }
    
    public String toString() {
        return "Markov Model of order " + N;
    }
     
   
    
    public void setRandom(int s){
        seed = s;
        myRandom = new Random(s);
    }
    
    public void setTraining(String s){
        myText = s.trim(); 
    }
    
    
     

    
    public String getRandomText(int numChars){
        if (myText == null){ 
    	    return "";
    	}
    	//only need three changes here if you want to use the N value
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
       
        for(int k=0; k < numChars-N; k++){
            ArrayList<String>follows = getFollows(key);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        
        return sb.toString();
    }
}