package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Review {
	private String text;
	private String date;
	private String review_id;


	public Review(String s) {
		
		JSONParser parser = new JSONParser();
		
		Scanner Sc= new Scanner(s);
	while(Sc.hasNextLine()) {
		
			Object obj;
			try {
				obj = parser.parse(Sc.nextLine());
				JSONObject jsonObject = (JSONObject) obj;
				review_id= (String) jsonObject.get("review_id");
				text= (String) jsonObject.get("text");
				date= (String) jsonObject.get("date");
				
				
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	Sc.close();
		
		
		
		
	}
	public Review(String v, String s) {
JSONParser parser = new JSONParser();
		
		Scanner Sc= new Scanner(s);
	while(Sc.hasNextLine()) {
		
			Object obj;
			try {
				obj = parser.parse(Sc.nextLine());
				JSONObject jsonObject = (JSONObject) obj;
				text= (String) jsonObject.get("text");
				date= (String) jsonObject.get("date");
				
				
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	Sc.close();
	
/////GENERATING A UNIQUE REVIEWID
	StringBuilder Reviewid = new StringBuilder();
	for(int i=0; i<12; i++) {
		Random r = new Random();
		String Reviewid1 = UUID.randomUUID().toString();
		Reviewid.append(Reviewid1.charAt(r.nextInt(Reviewid1.length()-1)));
	}
	review_id = Reviewid.toString();
	
	}
	
	public String getText() {
		return new String(text);
	}
	
	
	public void setText(String s) {
		this.text=s;
	}
	
	public String getReview_id() {
		return new String(review_id);
	}
	
	public void setReview_id(String s) {
		review_id=s;
	}
	
	public String getDate() {
		return new String(date);
	}
	
	public void setDate(String s) {
		this.date=s;
	}
	
	
}