package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.*;

import ca.ece.ubc.cpen221.mp5.MP5Db;

public interface YelpMP5DB extends MP5Db {

	public List<YelpUser> lookupReviews( Long UserId);
	
	public List<YelpUser> getUsers();
	
	public List<Restaurant> getRestaurants();
	
	public void addUser();
	
	public void addRestaurant();
	
	public void addReview();
	
	public void removeUser();
	
	public void removeRestaurant();
	
	public void removeReview();
	
}
