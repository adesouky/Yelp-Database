import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;
import ca.ece.ubc.cpen221.mp5.Classes.YelpReview;

public class YelpReviewTest {

	@Test
	public void test01() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpReview review1 = new YelpReview("This is review1");
			
			review1.setType("Type1");
			assertEquals("Type1", review1.getType());
			
		//	db.addRestaurant(rest1);
		}
	
	@Test
	public void test02() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpReview review1 = new YelpReview("This is review1");
			
			review1.setUserid("megan");
			assertEquals("megan", review1.getUserid());
			
		//	db.addRestaurant(rest1);
		}
	
	@Test
	public void test03() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpReview review1 = new YelpReview("This is review1");
			
			review1.setStars((long) 3.0);
			assertEquals(3.0, review1.getStars(), 0.0001);
			
		//	db.addRestaurant(rest1);
		}
	
	@Test
	public void test04() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpReview review1 = new YelpReview("This is review1");
			
			review1.setBusinessid("*1*2*3");
			assertEquals("*1*2*3", review1.getBusinessid());
			
		//	db.addRestaurant(rest1);
		}
	
	@Test
	public void test05() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpReview review1 = new YelpReview("This is review1");
			//List<String> votes = new ArrayList<>();
			Map<String, Long> votes = new HashMap();
			
			votes.put("Vote1", (long) 1);
			votes.put("Vote2", (long) 1);
			
			review1.addVote("Vote1");
			review1.addVote("Vote2");
			assertEquals(votes, review1.getVotes());
			
			review1.removeVote("Vote2");
			votes.replace("Vote1", (long) 1);
			votes.replace("Vote2", (long) 0);
			assertEquals(votes, review1.getVotes());
			
		//	db.addRestaurant(rest1);
		}

	@Test
	public void test06() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			YelpReview review1 = new YelpReview("This is review1");
			//List<String> votes = new ArrayList<>();
			Map<String, Long> votes = new HashMap();
			
			votes.put("Vote1", (long) 2);
			
			review1.addVote("Vote1");
			review1.addVote("Vote1");
			
			assertEquals(votes, review1.getVotes());
			
			review1.removeVote("Vote3");
			
		//	db.addRestaurant(rest1);
		}
	

}
