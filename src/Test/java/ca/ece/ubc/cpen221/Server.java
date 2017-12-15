package ca.ece.ubc.cpen221;

import java.util.StringTokenizer;

import org.junit.Test;

public class Server {

	
	
	@Test
	public void test1() {
		String s = "ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/peppermint-grill-berkeley\", \"longitude\": -122.2598181, \"neighborhoods\": [\"UC Campus Area\"], \"business_id\": \"FWadSZw0G7HsgKXq7gHTnw\", \"name\": \"Peppermint Grill\", \"categories\": [\"American (Traditional)\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 2.5, \"city\": \"Berkeley\", \"full_address\": \"2505 Hearst Ave\\nSte B\\nUC Campus Area\\nBerkeley, CA 94709\", \"review_count\": 16, \"photo_url\": \"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/924a6444ca6c/gfx/blank_biz_medium.gif\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8751965, \"price\": 2}\n";
				StringTokenizer st = new StringTokenizer(s, " ");
				System.out.println(st.nextToken());
				System.out.println(st.nextToken("").trim());
				//System.out.println(st.nextToken());
	
	}
}
