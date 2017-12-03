package ca.ece.ubc.cpen221;
import static org.junit.Assert.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Classes.Review;
import ca.ece.ubc.cpen221.mp5.Classes.ReviewableObject;

public class ReviewableobjectTest {

	@Test
	public void test01() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			ReviewableObject reviewable1 = new ReviewableObject("ReviwiableOBject1");
			
			reviewable1.setUrl("url1");
			assertEquals("url1", reviewable1.getUrl());
			
		//	db.addRestaurant(rest1);
		}
	@Test
	public void test02() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			ReviewableObject reviewable1 = new ReviewableObject("ReviwiableOBject1");
			
			reviewable1.setName("name1");
			assertEquals("name1", reviewable1.getName());
			
		//	db.addRestaurant(rest1);
		}
	
	@Test
	public void test03() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			ReviewableObject reviewable1 = new ReviewableObject("ReviwiableOBject1");
			
			reviewable1.setPhoto_url("photoUrl");
			assertEquals("photoUrl", reviewable1.getPhoto_url());
			
		//	db.addRestaurant(rest1);
		}
	@Test
	public void test04() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			ReviewableObject reviewable1 = new ReviewableObject("ReviwiableOBject1");
			
			reviewable1.setReviewCount((long) 100);
			assertEquals((double) 100, (double) reviewable1.getReviewCount(), 0.0001 );
			
		//	db.addRestaurant(rest1);
		}
	@Test
	public void test05() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			ReviewableObject reviewable1 = new ReviewableObject("ReviwiableOBject1");
			
			reviewable1.setPrice((long) 20);
			assertEquals((double) 20, (double) reviewable1.getPrice(), 0.0001 );
			
		//	db.addRestaurant(rest1);
		}

}