package ca.ece.ubc.cpen221.mp5.Classes;


import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * This is a YelpReview Representation
 * @author anasdesouky
 *
 */
public class YelpReview extends Review {
	


	private String type;
	private String business_id;
	private Map<String, Long> votes = new HashMap<>();
	private String user_id;
	private Long stars;



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

	
	
	public YelpReview(String f , String s) throws MissingInputException {
		super(f, s);
		JSONParser parser = new JSONParser();
		Scanner Sc= new Scanner(s);
		while(Sc.hasNextLine()) {
			Object obj;
			try {
				obj = parser.parse(Sc.nextLine());
				JSONObject jsonObject = (JSONObject) obj;
				business_id= (String) jsonObject.get("business_id");
				if(jsonObject.containsKey("votes")) {
					votes=(Map<String, Long>) (jsonObject.get("votes"));
				}
				else {
					votes.put("useful", (long) 0);
					votes.put("funny", (long) 0);
					votes.put("cool", (long) 0);
				}
				
				if(jsonObject.containsKey("stars")) {
				stars= (Long) jsonObject.get("stars");
				}
				else {
					stars=(long) 5;
				}
				if(!(jsonObject.containsKey("user_id") &&
					jsonObject.containsKey("business_id") &&
					jsonObject.containsKey("text") &&
					jsonObject.containsKey("date") &&
					jsonObject.containsKey("stars"))) {
					throw new MissingInputException();
				}
				user_id= (String) jsonObject.get("user_id");
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			type= "review";
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
		votes.replace(s, votes.get(s)+1);
	}
	public void removeVote(String s) {
		votes.replace(s, votes.get(s)-1);
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
	public void setStars(Long s) {
		this.stars = s;
	}
	
	public String getJSONString() {
		JSONObject obj = new JSONObject();
	
		 obj.put("user_id", this.getUserid());
		 obj.put("votes", this.getVotes());
		 obj.put("type", this.getType());
		 obj.put("stars", this.getStars());
		 obj.put("business_id", this.getBusinessid());
		 obj.put("review_id", this.getReview_id());
		 obj.put("text", this.getText());
		 obj.put("date", this.getDate());
		 
		 return obj.toJSONString();
	}

}