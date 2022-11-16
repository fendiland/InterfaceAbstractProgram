
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.util.Random;
public class EfficientMarkovModel extends AbstractMarkovModel {
    private int N;
    private HashMap<String, ArrayList<String>> map;
    //public MarkovModel(int n) {
        //N = n;
    //}
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        N = n;
        map = new HashMap<String, ArrayList<String>>();
    }
    
    public String toString() {
        return "Efficient Markov Model of order " + N;
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
    
    public void buildMap() {
        //int pos = 0;
        //the key is the unique words and value is what follows after that unique word
        //this
        for (int i=0; i<myText.length()-N+1;i++){
            String key = myText.substring(i, i+N);
            ArrayList<String> follows = new ArrayList<>();
            if (i+N>=myText.length()){
                map.put(key, follows);
            }else{

                if (map.containsKey(key)){               
                    map.get(key).add(myText.substring(i+N, i+N+1));
                }
                else {
                    follows.add(myText.substring(i+N, i+N+1));
                    map.put(key, follows);
                }
            }
        }
        
    }
    
    public ArrayList<String> getFollowsEM() {
        
        ArrayList<String>follows = new ArrayList<String>(); 
        for(String s : map.keySet()) {
            follows = map.get(s);
        }
        return follows;
    }
    public void printHashMapInfo() {
        //map.clear();
        int maxValSize = 0;
        String maxKey = "";
        System.out.println("amount of keys in this map are " + map.size());
        for(String s : map.keySet()) {
            //System.out.println("these are the keys and the number of their values " + s + map.get(s).size());
            if(map.get(s).size() > maxValSize) {
                maxValSize = map.get(s).size();
                //maxKey = s;
               
             
            }
            if(map.get(s).size() >= 3) {
                //System.out.println("The keys with the largest value size are " + s);
            }
            
        }
        System.out.println("this is the max value size in the array " + maxValSize);
        //System.out.println("these are the keys with the max values " + maxKey);
    }
}    