package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * User Representation For YelpDB
 * @author anasdesouky
 *
 */
public class User {
	private String url;
	private String user_id;
	private String name;
	
	
	public User(String s) {
		JSONParser parser = new JSONParser();
		
		Scanner Sc= new Scanner(s);
	while(Sc.hasNextLine()) {
		
			Object obj;
			try {
				obj = parser.parse(Sc.nextLine());
				JSONObject jsonObject = (JSONObject) obj;
				url= (String) jsonObject.get("url");
				user_id= (String) jsonObject.get("user_id");
				name = (String) jsonObject.get("name");
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	Sc.close();
	}
	
	public User(boolean new1, String s) throws Exception {
		JSONParser parser = new JSONParser();
		
		Scanner Sc= new Scanner(s);
		while(Sc.hasNextLine()) {
		
			Object obj;
			try {
				obj = parser.parse(Sc.nextLine());
				JSONObject jsonObject = (JSONObject) obj;
				name = (String) jsonObject.get("name");
				if(!jsonObject.containsKey("name")) {
					throw new Exception();
				}
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	Sc.close();
		
			
			
			StringBuilder Userid = new StringBuilder();
			for(int i=0; i<12; i++) {
				Random r = new Random();
				String Userid1 = UUID.randomUUID().toString();
				Userid.append(Userid1.charAt(r.nextInt(Userid1.length()-1)));
			}
			user_id = Userid.toString();
			url = "http://www.yelp.com/user_details?userid=" + user_id ;
	
	
	}
	
	
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String s) {
		this.name=s;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String s) {
		user_id=s;
	}

	}
			