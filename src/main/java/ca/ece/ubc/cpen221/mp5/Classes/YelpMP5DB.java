package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.*;

import ca.ece.ubc.cpen221.mp5.MP5Db;

public interface YelpMP5DB extends MP5Db {

	public List<YelpUser> lookupReviews( Long UserId);
	
	public List<YelpUser> getUsers();
	
	public List<Restaurant> getRestaurants();
	
	public void addUser(YelpUser newuser);
	
	public void addRestaurant(Restaurant newrestaurant);
	
	public void addReview(YelpReview newReview);
	
	public void removeUser(YelpUser user);
	
	public void removeRestaurant(Restaurant restaurant);
	
	public void removeReview(YelpReview review);
	
}
