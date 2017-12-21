package ca.ece.ubc.cpen221;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;

public class RestaurantTest {

	@Test
	public void test01() throws FileNotFoundException { //tests open
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");

		rest1.setOpen();
		assertTrue(rest1.isOpen());
		
		rest1.setClosed();
		assertFalse(rest1.isOpen());
	
		db.addRestaurant(rest1);
	}
	
	@Test
	public void test02() throws FileNotFoundException { //tests Longitude, latitude
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");
		
		double latitude = 5.2;
		double longitude = 4.3;
		rest1.setLatitude( latitude);
		rest1.setLongitude(longitude);
		
		
		assertEquals(latitude, rest1.getLatitude(), 0.001);
		assertEquals(longitude, rest1.getLongitude(), 0.0001);

		db.addRestaurant(rest1);
	}
	
	
	@Test
	public void test03() throws FileNotFoundException { //tests neighborhoods
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");
		List<String> neighborhoods = new ArrayList();
		neighborhoods.add("Neigh1");
		neighborhoods.add("Neigh2");
		
		rest1.addNeighborhood("Neigh1");
		assertEquals(true, rest1.isInNeighborhood("Neigh1"));
		
		rest1.addNeighborhood("Neigh2");
		assertEquals(neighborhoods, rest1.getNeighborhoods());
		assertEquals(true, rest1.isInNeighborhood("Neigh2"));
		
		rest1.removeNeighborhood("Neigh2");
		assertEquals(false, rest1.isInNeighborhood("Neigh2"));

		db.addRestaurant(rest1);
	}
	@Test
	public void test04() throws FileNotFoundException { 
	//	Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");
		rest1.setBusinessid("1*2*3*");
		
	//	db.addRestaurant(rest1);
		
		assertEquals("1*2*3*", rest1.getBusinessid());
	}
	
	@Test
	public void test05() throws FileNotFoundException {
	//	Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");
		List<String> categories = new ArrayList();
		
		categories.add("Category1");
		categories.add("Category2");
		
		rest1.addCategory("Category1");
		assertEquals(true, rest1.isInCategory("Category1"));
		
		rest1.addCategory("Category2");
		assertEquals(true, rest1.isInCategory("Category2"));
		assertEquals(categories, rest1.getCategories());
		
		rest1.removeCategory("Category2");
		assertEquals(false, rest1.isInCategory("Category2"));
		
	//	db.addRestaurant(rest1);
	}
	@Test
	public void test06() throws FileNotFoundException {
	//	Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");
		
		rest1.setState("State1");
		assertEquals("State1", rest1.getState());
		
		rest1.setCity("City1");
		assertEquals("City1", rest1.getCity());
		
	//	db.addRestaurant(rest1);
	}
	@Test
	public void test07() throws FileNotFoundException {
	//	Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");
		
		rest1.setType("Type1");
		assertEquals("Type1", rest1.getType());
		
	//	db.addRestaurant(rest1);
	}
	@Test
	public void test08() throws FileNotFoundException {
	//	Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");
		
		rest1.setFull_address("FullAddress1");
		assertEquals("FullAddress1", rest1.getFull_address());
		
	//	db.addRestaurant(rest1);
	}
	@Test
	public void test09() throws FileNotFoundException {
	//	Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");
		
		rest1.setStars(3.5);
		rest1.setReviewCount((long) 467);
		assertEquals(3.5, rest1.getStars(), 0.001);
		assertEquals(467, rest1.getReviewCount(), 0.001);
		
	//	db.addRestaurant(rest1);
	}
	@Test
	public void test10() throws FileNotFoundException {
	//	Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		Restaurant rest1 = new Restaurant("Starbucks");
		List<String> schools = new ArrayList();
		schools.add("School1");
		schools.add("School2");
		
		rest1.addSchool("School1");
		assertEquals(true, rest1.isNearSchool("School1"));
		
		rest1.addSchool("School2");
		assertEquals(true, rest1.isNearSchool("School2"));
		assertEquals(schools, rest1.getSchools());
		
		rest1.removeSchool("School2");
		assertEquals(false, rest1.isNearSchool("School2"));
		
		
		
	//	db.addRestaurant(rest1);
	}
	

}