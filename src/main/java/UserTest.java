import static org.junit.Assert.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Classes.User;


public class UserTest {

	@Test
	public void test01() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			User user1 = new User("megan");
			
			user1.setUrl("url1");
			assertEquals("url1", user1.getUrl());
			
		//	db.addRestaurant(rest1);
		}
	
	@Test
	public void test02() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			User user1 = new User("megan");
			
			user1.setName("Name1");
			assertEquals("Name1", user1.getName());
			
		//	db.addRestaurant(rest1);
		}
	
	@Test
	public void test03() {
//		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
			User user1 = new User("megan");
			
			user1.setUser_id("UserID1");
			assertEquals("UserID1", user1.getUser_id());
			
		//	db.addRestaurant(rest1);
		}


}
