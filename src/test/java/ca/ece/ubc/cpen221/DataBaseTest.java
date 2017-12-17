package ca.ece.ubc.cpen221;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;
import ca.ece.ubc.cpen221.mp5.Classes.User;
import ca.ece.ubc.cpen221.mp5.Classes.YelpReview;
import ca.ece.ubc.cpen221.mp5.Classes.YelpUser;

public class DataBaseTest {

	@Test
	public void test01() throws FileNotFoundException { //tests addRestaurant
		
		List<YelpUser> UserList = new ArrayList<>();
		List<Restaurant> restaurants = new ArrayList<>();
		List<YelpReview> ReviewList = new ArrayList<>();
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		
		Restaurant rest1 = new Restaurant("Starbucks");
		Restaurant rest2 = new Restaurant("GreenLeaf");
		Restaurant rest3 = new Restaurant("CactusClub");
		Restaurant rest4 = new Restaurant("Earls");
		
		restaurants.add(rest1);
		restaurants.add(rest2);
		restaurants.add(rest3);
		restaurants.add(rest4);
		
		db.addRestaurant(rest1);
		
		assertEquals(true, db.getRestaurants().contains(rest1));
	}
	@Test
	public void test02() throws FileNotFoundException { //tests removeRestaurant
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");

		
		
		assertEquals(false, db.getRestaurants().contains(rest1));
	}
	@Test
	public void test03() throws FileNotFoundException { //tests add user
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		YelpUser user1 = new YelpUser("megan");
		
		db.addUser(user1);
		
		assertEquals(true, db.getUsers().contains(user1));
	}
	@Test
	public void test04() throws FileNotFoundException { //tests remove user
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		YelpUser user1 = new YelpUser("megan");
		
		db.addUser(user1);
		db.removeUser(user1);
		
		assertEquals(false, db.getUsers().contains(user1));
	}
	@Test
	public void test05() throws FileNotFoundException { //tests add Review
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		YelpReview review1 = new YelpReview("this is a review"); 
		
		db.addReview(review1);
		
		assertEquals(true, db.getReviews().contains(review1));
	}
	@Test
	public void test06() throws FileNotFoundException { //tests remove review
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		YelpReview review1 = new YelpReview("this is a review"); 
		
		db.addReview(review1);
		db.removeReview(review1);
		
		assertEquals(false, db.getReviews().contains(review1));
	}
	@Test
	public void test07() throws FileNotFoundException { //tests remove review
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");
		rest1.setBusinessid("1*2*3*");
		
		db.addRestaurant(rest1);
		
		assertEquals(rest1, db.getRestaurant("1*2*3*"));
	}
	@Test
	public void test08() throws FileNotFoundException { //tests remove review
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		YelpUser user1 = new YelpUser("megan");
		YelpReview review1 = new YelpReview("this is review1");
		YelpReview review2 = new YelpReview("this is review2");
		List<YelpReview> reviewList = new ArrayList();
		reviewList.add(review1);
		reviewList.add(review2);
		
		review1.setUserid("megan");
		review2.setUserid("megan");
		
		db.addUser(user1);
		db.addReview(review1);
		db.addReview(review2);
		
		assertEquals(reviewList, db.getReviews("megan"));
	}

}