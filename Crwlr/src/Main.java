import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 












import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 
public class Main {
	public static DB db = new DB();
	
	public static StringBuffer fn=new StringBuffer(67);
	
	public static void main(String[] args) throws SQLException, IOException {
		db.runSql2("TRUNCATE Record;");
		String web="http://forums.androidcentral.com/";
		URL url=new URL(web);
		processPage(web);
		//getUrlContnt(url);
		
		
		
		PrintWriter writer = new PrintWriter("C:\\Users\\Sainath\\Downloads\\outpt.txt", "UTF-8");
		writer.println(fn.toString());
		writer.close();
		
	}
 
	public static void processPage(String URL) throws SQLException, IOException{
		//check if the given URL is already in database
		String sql = "select * from Record where URL = '"+URL+"'";
		ResultSet rs = db.runSql(sql);
		
		if(rs.next()){
 
		}else{
			//store the URL to database to avoid parsing again
			sql = "INSERT INTO  `Crawler`.`Record` " + "(`URL`) VALUES " + "(?);";
			PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, URL);
			stmt.execute();
			System.out.println(URL);
			//getUrlContnt(new URL(URL));
			//get useful information
			Document doc = Jsoup.connect(URL).timeout(10*1000).get();
 

			fn.append(doc.body().text());
			
			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Sainath\\Downloads\\outpt.txt", true)));
			    out.println(doc.body().text());
			    out.close();
			} catch (IOException e) {
			    //oh noes!
			}
			
			if(doc.text().contains("cookies")){
				System.out.println(URL);
			}
 
			//get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			for(Element link: questions){
				if(link.attr("href").contains("forums.androidcentral.com"))
					if(!link.attr("href").contains("http://forums.androidcentral.com/register.php"))
					    processPage(link.attr("abs:href"));
			}
		}
	}
	
	
	
	/*public static String getUrlContnt(URL url) throws SQLException, IOException {
		//db.runSql2("TRUNCATE Record;");
		
		String ret = "";
		
		try {
		URLConnection conn = url.openConnection();

		// open the stream and put it into BufferedReader
		BufferedReader br = new BufferedReader(
                           new InputStreamReader(conn.getInputStream()));

		String inputLine;
		ret = org.apache.commons.io.IOUtils.toString(br);
		
		//save to this filename
		String fileName = "/users/mkyong/test.html";
		File file = new File(fileName);

		if (!file.exists()) {
			file.createNewFile();
		}

		//use FileWriter to write file
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		while ((inputLine = br.readLine()) != null) {
			bw.write(inputLine);
		}

		bw.close();
		br.close();

		System.out.println("Done");

	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		System.out.println(ret);
		return ret;

}*/
}