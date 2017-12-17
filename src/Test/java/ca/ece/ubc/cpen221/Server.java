package ca.ece.ubc.cpen221;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.StringTokenizer;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;
import ca.ece.ubc.cpen221.mp5.Query5.YelpQueryParser.OrExprContext;
import ca.ece.ubc.cpen221.mp5.QueryClasses.Query;
import ca.ece.ubc.cpen221.mp5.QueryClasses.QueryListener;

public class Server {
	private Database YELPDB = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");

	
	
	@Test
	public void test1() {
		String s = "ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/peppermint-grill-berkeley\", \"longitude\": -122.2598181, \"neighborhoods\": [\"UC Campus Area\"], \"business_id\": \"FWadSZw0G7HsgKXq7gHTnw\", \"name\": \"Peppermint Grill\", \"categories\": [\"American (Traditional)\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 2.5, \"city\": \"Berkeley\", \"full_address\": \"2505 Hearst Ave\\nSte B\\nUC Campus Area\\nBerkeley, CA 94709\", \"review_count\": 16, \"photo_url\": \"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/924a6444ca6c/gfx/blank_biz_medium.gif\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8751965, \"price\": 2}\n";
				StringTokenizer st = new StringTokenizer(s, " ");
				System.out.println(st.nextToken());
				System.out.println(st.nextToken("").trim());
//				try {
					//Database YELPDB = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
					String v = YELPDB.getUser("3417473bc874").getUserJSONString();
					System.out.println(v);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				//System.out.println(st.nextToken());
	
	}
	
	@Test 
	public void test2() {
		
		Query A = new Query("in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2", YELPDB);
		
		Set<Restaurant> SetR = A.getRestaurants();
		System.out.println(SetR.size());
		for(Restaurant R : SetR) {
			System.out.println(R.getJSONString());
		}

		for(int i=0 ; i ==i ; i++) {
			i++;
		}
	}
	
	
	@Test
	public void test3() {
	//	String a= QueryListener.getEnclosed("in(Terminal Ave)");
		//System.out.println(a);
	}
}
