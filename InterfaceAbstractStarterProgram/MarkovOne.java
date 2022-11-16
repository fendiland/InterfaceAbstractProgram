
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.util.Random;
public class MarkovOne extends AbstractMarkovModel {

    
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public String toString() {
        return "Markov One";
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
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
       
        for(int k=0; k < numChars-1; k++){
            ArrayList<String>follows = getFollows(key);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        
        return sb.toString();
    }
}

