import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Classes.YelpReview;
import ca.ece.ubc.cpen221.mp5.Classes.YelpUser;

public class YelpUserTest {

	@Test
	public void test01() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpUser user1 = new YelpUser("megan");
			Map<String, Long> votes = new HashMap();
			
			votes.put("Vote1", (long) 1);
			votes.put("Vote2", (long) 1);
			
			user1.addVote("Vote1");
			user1.addVote("Vote2");
			assertEquals(votes, user1.getVotes());
			
			user1.removeVote("Vote2");
			votes.replace("Vote1", (long) 1);
			votes.replace("Vote2", (long) 0);
			assertEquals(votes, user1.getVotes());
			
		//	db.addRestaurant(rest1);
		}
	@Test
	public void test02() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpUser user1 = new YelpUser("megan");
			Map<String, Long> votes = new HashMap();
			
			votes.put("Vote1", (long) 2);
			
			user1.addVote("Vote1");
			user1.addVote("Vote1");
			
			assertEquals(votes, user1.getVotes());
			
			user1.removeVote("Vote3");
			
		//	db.addRestaurant(rest1);
		}
	
	@Test
	public void test03() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpUser user1 = new YelpUser("megan");
			
			user1.setReview_count((long) 5);
			long count = 5;
			
			assertEquals((double)count, (double)user1.getReview_count(), 0.0001);
			
		//	db.addRestaurant(rest1);
		}
	@Test
	public void test04() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpUser user1 = new YelpUser("megan");
			
			user1.setType("Type1");
			
			assertEquals("Type1", user1.getType());
			
		//	db.addRestaurant(rest1);
		}
	@Test
	public void test05() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpUser user1 = new YelpUser("megan");
			
			user1.setAverage_stars(2.7);
			
			assertEquals(2.7, user1.getAverage_stars(),0.0001);
			
		//	db.addRestaurant(rest1);
		}
	
	
}
