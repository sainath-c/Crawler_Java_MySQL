import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class TagRank {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try
		    {
		        FileInputStream file = new FileInputStream("C:\\Users\\Sainath\\Downloads\\map.txt");
		        DataInputStream dis = new DataInputStream(file);
		        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		        
		        FileInputStream file1 = new FileInputStream("C:\\Users\\Sainath\\Downloads\\keyWords.txt");
		        DataInputStream dis1 = new DataInputStream(file1);
		        BufferedReader br1 = new BufferedReader(new InputStreamReader(dis1));
		        
		        
		        int i=0;
		        String inter="";
		        String[] keyWords=new String[20];
		        Map<String,Integer> wordCounter=new HashMap<String,Integer>();
		        
		        while ((inter = br1.readLine()) != null) {
		            keyWords[i]=inter.toLowerCase();
		            wordCounter.put(inter, 0);
		            i++;
		        }
		        
		           
		        	String Contents="";
			        while ((Contents = br.readLine()) != null) {
			        	for(int x=0;x<keyWords.length;x++)
			        	{
			            while(keyWords[x]!=null)
			            {
			            if(Contents.toLowerCase().contains(keyWords[x].toLowerCase()))
			            {
			            	if(wordCounter.containsKey(keyWords[x])){
				        		wordCounter.put(keyWords[x], wordCounter.get(keyWords[x]+1) );
				        	}
			            }
			            }
			        	}
			        }
		        
		        
		        for(String key:wordCounter.keySet())
			       {
			           System.out.println(key+"  "+wordCounter.get(key));
			       }  
		    }
		 catch(IOException e1){
		        System.out.println(e1);
		    }
		
	}

}
