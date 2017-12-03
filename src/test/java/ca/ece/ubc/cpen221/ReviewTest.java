package ca.ece.ubc.cpen221;
import static org.junit.Assert.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Classes.Review;
import ca.ece.ubc.cpen221.mp5.Classes.YelpReview;

public class ReviewTest {
	
	@Test
	public void test01() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			Review review1 = new Review("This is review1");
			
			review1.setText("Text1");
			assertEquals("Text1", review1.getText());
			
		//	db.addRestaurant(rest1);
		}

	@Test
	public void test02() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			Review review1 = new Review("This is review1");
			
			review1.setReview_id("123");
			assertEquals("123", review1.getReview_id());
			
		//	db.addRestaurant(rest1);
		}
	@Test
	public void test03() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			Review review1 = new Review("This is review1");
			
			review1.setDate("01/01/17");
			assertEquals("01/01/17", review1.getDate());
			
		//	db.addRestaurant(rest1);
		}
}