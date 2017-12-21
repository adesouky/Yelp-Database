package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.*;

import ca.ece.ubc.cpen221.mp5.Classes.MP5Db;


/**
 * YelpMP5DB, an interface roughly specified to fit the functions of yelpdb
 * @author anasdesouky
 *
 */
public interface YelpMP5DB extends MP5Db {

	public List<YelpUser> lookupReviews( Long UserId);
	
	public List<YelpUser> getUsers();
	
	public List<Restaurant> getRestaurants();
	
	
	public void addUser(YelpUser c);
	
	public void addRestaurant(Restaurant c);
	
	public void addReview(YelpReview c);
	
	public void removeUser(YelpUser c);
	
	public void removeRestaurant(Restaurant c);
	
	public void removeReview(YelpReview c);
	
}
