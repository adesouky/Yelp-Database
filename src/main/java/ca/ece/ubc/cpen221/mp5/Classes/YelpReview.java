package ca.ece.ubc.cpen221.mp5.Classes;


import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class YelpReview extends Review {
	


	private String type;
	private String business_id;
	private Map<String, Long> votes = new HashMap<>();
	private String user_id;
	private long stars;



	public YelpReview(String s){
		super(s);
		JSONParser parser = new JSONParser();
		Scanner Sc= new Scanner(s);
	while(Sc.hasNextLine()) {
			Object obj;
			try {
				obj = parser.parse(Sc.nextLine());
				JSONObject jsonObject = (JSONObject) obj;
				type= (String) jsonObject.get("type");
				business_id= (String) jsonObject.get("business_id");
				votes=(Map<String, Long>) (jsonObject.get("votes"));
				stars= (Long) jsonObject.get("stars");
				user_id= (String) jsonObject.get("user_id");
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	Sc.close();

}
	
	public String getType() {
		return type;
	}
	
	public String getUserid() {
		return user_id;
	}
	
	public Long getStars() {
		return new Long(this.stars);
	}
	public String getBusinessid() {
		return business_id;
	}
	public Map<String, Long> getVotes(){
		return new HashMap<>(votes);
	}
	public void addVote(String s) {
		if(!votes.containsKey(s)) {
			votes.put(s, (long) 0);
		}
		votes.replace(s, votes.get(s)+1);
	}
	public void removeVote(String s) {
		if(votes.containsKey(s)) {
		votes.replace(s, votes.get(s)-1);
		}
	}
	
	public void setType(String s) {
		this.type=s;
	}
	
	public void setBusinessid(String s) {
		this.business_id=s;
	}
	public void setUserid(String s) {
		this.user_id=s;
	}
	public void setStars(long s) {
		this.stars = s;
	}

}