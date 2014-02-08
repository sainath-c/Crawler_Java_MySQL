import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class Mapr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			    try
			    {
			        FileInputStream file = new FileInputStream("C:\\Users\\Sainath\\Downloads\\outpt.txt");
			        DataInputStream dis = new DataInputStream(file);
			        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			        String Contents="";
			        String str="";

			        while ((Contents = br.readLine()) != null) {
			            str+=Contents;
			        }
			        
			        System.out.println("here");
			        
			        String strArr=str.toString().trim();
			        Map<String,Integer> wordCounter=new HashMap<String,Integer>();
			        String[] wordArray=new String[4225019];
			        wordArray=strArr.split(" ");
			        System.out.println(wordArray.length);
			        
			        int count = 0;
			        String ch = wordArray[count];
			        //Map<Character,Integer> charCounter=new HashMap<Character,Integer>();
			        for(int i=0;i<wordArray.length;i++)
			        {
			        	ch = wordArray[i]; 
			        	if(wordCounter.containsKey(ch)){
			        		wordCounter.put(ch, wordCounter.get(ch)+1);
			        	}
			        	else
			        	{
			        		wordCounter.put(ch, 1);
			        	}
			       }

			        PrintWriter writer = new PrintWriter("C:\\Users\\Sainath\\Downloads\\outpt.txt", "UTF-8");
			       for(String key:wordCounter.keySet())
			       {
			   		   writer.println(key+""+wordCounter.get(key));
			           //System.out.println(key+""+wordCounter.get(key));
			       }
			       writer.close();
			    } 
			    catch(IOException e1){
			        System.out.println(e1);
			    }
			    
		}


}
