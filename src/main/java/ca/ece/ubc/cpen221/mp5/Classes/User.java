package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
			