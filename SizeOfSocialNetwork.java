package SocialNetwork;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class SizeOfSocialNetwork {
    
    // Check if the edit distance between 2 words is one
    public static boolean isEditDistanceOne(String s1, String s2) {
        
        int len1=s1.length();
        int len2=s2.length();
        int size = 0,i=0,j=0;
        // If the difference between the lengths of the 2 words is more than 1
        if(abs(len1-len2)>1)                     
             return false;                   
        //Comparing the characters of the two strings
        while(i<len1 && j<len2)
        {  
            // The characters are not same
            if(s1.charAt(i)!=s2.charAt(j))      
            {   
                if(size == 1)                  
                    return false;              
                if(len1>len2)                    
                    i++;
                else if(len1<len2)               
                    j++;
                else{                              
                    i++;
                    j++;
                }      
                size++;           
            }
            // The characters are same
            else{                                                 
                i++;
                j++;
            } 
        }
        //To check if there is an extra character
        if(i<len1 || j<len2)                    
            size++;
       
         return size==1;
    }
     
    //Calculate the number of friends for the input word
    static int calculateSize(LinkedList<String> list,String input){
        
        HashMap<Integer,LinkedList<String>> finalList = new HashMap<>();
        Integer max = Integer.MIN_VALUE;
        int inputLength=input.length();
        //Find the length of longest word.
        for(String word : list) {                   
            int len = word.length();
            if(max < len) {
                max = len;
            }
        }
        //If the input word is longer than the longest word in dictionary
        if(max < inputLength)                       
            max= inputLength;                       
        
        //Store list of words from the dictionary according to the word length in HashMap
        for(int i = 0;i<=max+1;i++) {                
            finalList.put(i,new LinkedList<>());        
        }        
        list.forEach((word) -> {                    
            int len = word.length();                
            finalList.get(len).add(word);
        });
       
        //Countize of social network
        int size=0;                     
        //List to keep track of all the generated friends       
        LinkedList<String> friends = new LinkedList<>();   
        friends.add(input);      
        finalList.get(inputLength).remove(input);          
        // Until the friends list is empty, traverse recursively
            while(!friends.isEmpty()) {                  
                int length=friends.get(0).length();
                LinkedList<String> wordList = new LinkedList<>();
                //A word with length i can have its friend in with i-1,i or i+1 word length list
                for(int len = length-1;len<=length+1;len++){
                    // Traverse the list to find the friends
                    for(String cWord : finalList.get(len))          
                    {
                        // To check if edit distance is One
                        if(isEditDistanceOne(friends.get(0),cWord))      
                        {
                            size++; 
                            // Add to the friends list
                            friends.add(cWord);                  
                            wordList.add(cWord);
                        }
                    }
                    //Remove all the generated friends from the list
                    finalList.get(len).removeAll(wordList);     
                    wordList.clear();
                }
                // Remove the processed word from friends list
                friends.remove();                                                                           
            }
        
        return size;
    }
    
    public static void main(String[] args) {
                                    
        LinkedList<String> dictionary= new LinkedList<>();
        BufferedReader br;
        String strLine;
        int size;
        
        System.out.println("Enter the word to calculate the size of its social network:");                
        Scanner s= new Scanner(System.in);                                       
        String [] input = s.nextLine().trim().split(" ");                      
        try {
            if(input.length==1)
            {
                //The dictionary 
                br = new BufferedReader( new FileReader("/dictionary.txt"));     
                while( (strLine = br.readLine()) != null){
                dictionary.add(strLine.trim());                               
                }
              
                if(!input[0].equals("")){
                    //Dictionary should not be empty
                    if(!dictionary.isEmpty()) {        
                        //The input word is present in its own social network
                        if(dictionary.contains(input[0])){
                        size = calculateSize(dictionary,input[0]) + 1; 
                        }
                        //The input word is not present in its own social network
                        else{
                        size = calculateSize(dictionary,input[0]);
                        } 
                        //The total size of the social network for input word.
                        System.out.println("The size of social network for "+input[0] +" is : "+size);
                    }else
                    System.out.println("The dictionary is empty");
                }else
                    System.out.println("The input is invalid");            
            }else
                System.out.println("The input is invalid");  
            
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find the file: fileName");
        } catch (IOException e) {
            System.err.println("Unable to read the file: fileName");
        }    
    }
}

