 package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Restaurant extends ReviewableObject {
	
	private boolean open;
	//private String url;
	private double longitude;
	private List<String> neighborhoods = new ArrayList<>();
	private String business_id;
	//private String name;
	private List<String> categories = new ArrayList<>();
	private String state;
	private String type;
	private double stars;
	private String city;
	private String full_address;
	//private Long review_count;
	//private String photo_url;
	private List<String> schools= new ArrayList<>();
	private double latitude;
	//private Long price;
	
	
	public Restaurant(String s){
		super(s);
		JSONParser parser = new JSONParser();
		
			Scanner Sc= new Scanner(s);
		while(Sc.hasNextLine()) {
			try {
			Object obj = parser.parse(Sc.nextLine());
			JSONObject jsonObject = (JSONObject) obj;
			open = (boolean) jsonObject.get("open");
			  		
			
					
			longitude = (double) jsonObject.get("longitude");
					
			JSONArray neighborhoodsArray= (JSONArray) jsonObject.get("neighborhoods");
			Iterator it = neighborhoodsArray.iterator();
			while(it.hasNext()) {
			neighborhoods.add(it.next().toString());
			}
					
			business_id = (String) jsonObject.get("business_id") ;
					
			
					
			JSONArray categoriesArray= (JSONArray) jsonObject.get("categories");
			Iterator itc = categoriesArray.iterator();
			while(itc.hasNext()) {
			categories.add(itc.next().toString());
			}
					
			state = (String) jsonObject.get("state") ;
					
			type = (String) jsonObject.get("type") ;
					
			stars = (double) jsonObject.get("stars") ;
					
			city = (String) jsonObject.get("city") ;
					
			full_address = (String) jsonObject.get("full_address") ;
					
			
					
			JSONArray schoolsArray= (JSONArray) jsonObject.get("schools");
			Iterator its = schoolsArray.iterator();
			while(its.hasNext()) {
			schools.add(its.next().toString());
			}
					
			latitude = (double) jsonObject.get("latitude");
					
			}
			
			 catch ( ParseException e) {
				e.printStackTrace();
			 }
			
		}
		Sc.close();	
	}
	
	public boolean isOpen() {
		boolean result = open;
		return result;
	}
	
	public void setClosed() {
		open=false;
	}
	
	public void setOpen() {
		open=true;
	}
	
	public double getLongitude() {
		return new Double(longitude);
	}
	
	public void setLongitude(double longitude) {
		this.longitude= longitude;
	}
	
	public double getLatitude() {
		return new Double (latitude);
	}
	
	public void setLatitude( double latitude) {
		this.latitude=latitude;
	}
	
	public List<String> getNeighborhoods(){
		List<String> result = new ArrayList<>(neighborhoods);
		return result;
	}
	
	public void addNeighborhood( String n) {
		neighborhoods.add(n);	
	}
	public void removeNeighborhood(String n) {
		neighborhoods.remove(n);
	}
	public boolean isInNeighborhood( String n) {
		return neighborhoods.contains(n);
	}
	public String getBusinessid() {
		return new String(business_id);
	}
	public void setBusinessid(String business_id) {
		this.business_id=business_id;
	}
	public List<String> getCategories(){
		List<String> result = new ArrayList<>(categories);
		return result;
	}
	
	public void addCategory( String n) {
		categories.add(n);	
	}
	public void removeCategory(String n) {
		categories.remove(n);
	}
	public boolean isInCategory( String n) {
		return categories.contains(n);
	}
	public String getState() {
		return new String(state);
	}
	public void setState(String state) {
		this.state=state;
	}
	public String getCity() {
		return new String(city);
	}
	public void setCity(String city) {
		this.city=city;
	}
	public String getType() {
		return new String(type);
	}
	public void setType(String type) {
		this.type=type;
	}
	public String getFull_address() {
		return new String(full_address);
	}
	public void setFull_address(String full_address) {
		this.full_address=full_address;
	}
	public double getStars() {
		return new Double(stars);
	}
	public void setStars(double stars) {
		this.stars=stars;
	}
	public List<String> getSchools(){
		return new ArrayList<String>(schools);
	}
	public void addSchool(String s) {
		schools.add(s);
	}
	public void removeSchool(String s) {
		schools.remove(s);
	}
	public boolean isNearSchool(String s) {
		return schools.contains(s);
	}
}