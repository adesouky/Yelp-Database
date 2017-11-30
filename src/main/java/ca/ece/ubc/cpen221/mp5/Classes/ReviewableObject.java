package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReviewableObject {
	
	private String url;
	private String name;
	private Long review_count;
	private Long price;
	private String photo_url;
	
	public ReviewableObject(String s) {
		
		JSONParser parser = new JSONParser();
		
		Scanner Sc= new Scanner(s);
	while(Sc.hasNextLine()) {
		try {
			Object obj = parser.parse(Sc.nextLine());
			JSONObject jsonObject = (JSONObject) obj;
		url = (String) jsonObject.get("url");
		name = (String) jsonObject.get("name") ;
		review_count = (Long) jsonObject.get("review_count") ;
		price = (Long) jsonObject.get("price") ;
		photo_url = (String) jsonObject.get("photo_url") ;
		}
		 catch ( ParseException e) {
				e.printStackTrace();
			}
	}
	}
	
	
	
	public void setUrl(String url) {
		this.url=url;
	}
	
	public String getUrl() {
		return new String(url);
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return new String(name);
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url=photo_url;
	}
	
	public String getPhoto_url() {
		return new String(photo_url);
	}
	
	public void setReviewCount(Long review_count) {
		this.review_count=review_count;
	}
	
	public Long getReviewCount() {
		return new Long(review_count);
		
	}
	
	public void setPrice(Long price) {
		this.price=price;
	}
	
	public Long getPrice() {
		return new Long(price);
	}
	
	
	
	

}