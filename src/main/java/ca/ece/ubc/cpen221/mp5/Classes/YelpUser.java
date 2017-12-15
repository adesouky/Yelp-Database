package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class YelpUser extends User{
	private Map<String, Long> votes= new HashMap<>();
	private Long review_count;
	private String type;
	private double average_stars;
	
	public YelpUser(String s) {
		super(s);
		JSONParser parser = new JSONParser();
		
		Scanner Sc= new Scanner(s);
	while(Sc.hasNextLine()) {
		
			Object obj;
			try {
				obj = parser.parse(Sc.nextLine());
				JSONObject jsonObject = (JSONObject) obj;
				
				votes = (Map<String, Long>) jsonObject.get("votes");
				review_count = (Long) jsonObject.get(review_count);
				type = (String) jsonObject.get("type");
				average_stars = (double) jsonObject.get("average_stars");
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	Sc.close();
}
	
	public YelpUser(String New, String s) {
		super(New, s);
		review_count=(long) 0;
		type= "user";
		average_stars=0;
	}
	
	
	public Map<String, Long> getVotes(){
		return new HashMap<String, Long>( votes);
	}
	
	public void addVote(String s) {
		votes.replace(s, votes.get(s)+1);
	}
	public void removeVote(String s) {
		votes.replace(s, votes.get(s)-1);
	}
	
	
	public Long getReview_count() {
		return new Long(review_count);
	}
	public void setReview_count(Long n) {
		review_count=n;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type=type;
	}
	public double getAverage_stars() {
		return new Double(average_stars);
	}
	public void setAverage_stars(double n) {
		average_stars=n;
	}
	public String getUserJSONString() {
		 JSONObject obj = new JSONObject();
		 obj.put("name", this.getName());
		 obj.put("url", this.getUrl());
		 obj.put("user_id", this.getUser_id());
		 obj.put("review_count", this.getReview_count());
		 obj.put("votes", this.getVotes());
		 obj.put("type", this.getType());
		 obj.put("average_stars", this.getAverage_stars());
		 
		 return obj.toJSONString();
	}
	
}