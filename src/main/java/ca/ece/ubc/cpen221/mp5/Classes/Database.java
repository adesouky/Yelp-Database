package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class Database implements YelpMP5DB{
	
	List<YelpUser> UserList;
	List<Restaurant> RestaurantList;
	List<YelpReview> ReviewList;
	
	public Database(String File1, String File2, String File3){
		Scanner RestaurantSc= new Scanner(File1);
		while(RestaurantSc.hasNextLine()) {
			RestaurantList.add(new Restaurant(RestaurantSc.nextLine()));
		}
		RestaurantSc.close();
		
		Scanner ReviewSc= new Scanner(File2);
		while(ReviewSc.hasNextLine()) {
			
			ReviewList.add(new YelpReview(ReviewSc.nextLine()));
		}
		ReviewSc.close();
		
		Scanner UserSc= new Scanner(File3);
		while(UserSc.hasNextLine()) {
			UserList.add( new YelpUser(UserSc.nextLine()));
		}
		UserSc.close();
	}

	@Override
	public Set getMatches(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String kMeansClusters_json(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ToDoubleBiFunction getPredictorFunction(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YelpUser> lookupReviews(Long UserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YelpUser> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> getRestaurants() {
		return new ArrayList<>(RestaurantList);
		
	}

	@Override
	public void addUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRestaurant() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReview() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRestaurant() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeReview() {
		// TODO Auto-generated method stub
		
	}

}