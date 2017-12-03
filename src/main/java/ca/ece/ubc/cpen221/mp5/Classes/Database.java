package ca.ece.ubc.cpen221.mp5.Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.ToDoubleBiFunction;


import org.junit.runner.manipulation.Filterable;

import ca.ece.ubc.cpen221.mp5.MP5Db;

import ca.ece.ubc.cpen221.mp5.kMeansClustering;


public class Database implements YelpMP5DB{
	
	List<YelpUser> UserList = new ArrayList<>();
	List<Restaurant> RestaurantList = new ArrayList<>();
	List<YelpReview> ReviewList = new ArrayList<>();
	
	/**
	 * Creates a new database from the three files given
	 * @param File1
	 * 			a string that represents a file
	 * @param File2
	 * 			a string that represents a file
	 * @param File3
	 * 			a string that represents a file
	 * @throws FileNotFoundException
	 * 			gets thrown if the file is not found
	 */
	public Database(String File1, String File2, String File3) throws FileNotFoundException{
		File File11 = new File(File1);
		File File22 = new File(File2);
		File File33 = new File(File3);
		
		
		Scanner RestaurantSc= new Scanner(File11);
		while(RestaurantSc.hasNextLine()) {
			RestaurantList.add(new Restaurant(RestaurantSc.nextLine()));
		}
		RestaurantSc.close();
		
		Scanner ReviewSc= new Scanner(File22);
		while(ReviewSc.hasNextLine()) {
			
			ReviewList.add(new YelpReview(ReviewSc.nextLine()));
		}
		ReviewSc.close();
		
		Scanner UserSc= new Scanner(File33);
		while(UserSc.hasNextLine()) {
			UserList.add( new YelpUser(UserSc.nextLine()));
		}
		UserSc.close();
	}

	/**
	 * Returns a list of Yelp Reviews that represents all reviews for a userID
	 * @param userID
	 * 			a string representing a user ID
	 * @return a list of Yelp Reviews that represents all reviews for a userID
	 */
	public List<YelpReview> getReviews(String userID) {
		YelpReview[] FilteredReview = ReviewList.stream().filter(  a -> a.getUserid().equals(userID)).toArray(YelpReview[] :: new);
		List<YelpReview> FilteredReviewList =  Arrays.asList(FilteredReview);
		return FilteredReviewList;
	}
	/**
	 * Returns the restaurant corresponding to the businessID 
	 * @param BusinessID 
	 * 				a string that represents the ID of a business
	 * @return the restaurant corresponding to the businessID
	 */
	public Restaurant getRestaurant(String BusinessID) {
		Restaurant[] ab = RestaurantList.stream().filter(x -> x.getBusinessid().equals(BusinessID)).toArray(Restaurant[] :: new);
		Restaurant a= ab[0];
		return a;
	}

	
	/**
	 * Perform a structured query and return the set of objects that matches the
	 * query
	 * 
	 * @param queryString
	 * @return the set of objects that matches the query
	 */
	@Override
	public Set getMatches(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String kMeansClusters_json(int k) {
		kMeansClustering kc = new kMeansClustering(this);
		return kc.getClustersOfResturants(k).toJSONString();
	}
	/**
	 * throws an illegalargumentexception if the user has 1 or less reviews.
	 */
	@Override
	public ToDoubleBiFunction<MP5Db<Restaurant>, String> getPredictorFunction(String user) {
		List<Double> Variables= ComputeCoefficiants(user);
		
		double a = Variables.get(0);
		double b = Variables.get(1);
		double r_squared = Variables.get(2);
		
		ToDoubleBiFunction<MP5Db<Restaurant>, String> i = (x,y) -> ((b*((Database) x).getRestaurant(y).getPrice() +a) < 1) ? 1 : ((b*((Database) x).getRestaurant(y).getPrice() +a) >5) ? 5 : ((b*((Database) x).getRestaurant(y).getPrice() +a));
		
		return i;
		
		//return null;
	}
	

	@Override
	public List<YelpUser> lookupReviews(Long UserId) {
		// TODO Auto-generated method stub
		List<YelpUser> YelpUserList = new ArrayList();
		for(YelpReview review : ReviewList) {
			
		}
		return null;
	}

	@Override
	public List<YelpUser> getUsers() {
		return new ArrayList<>(UserList);
	}

	@Override
	public List<Restaurant> getRestaurants() {
		return new ArrayList<>(RestaurantList);
	}

	@Override
	public void addUser(YelpUser newUser) {
		UserList.add(newUser);
	}

	@Override
	public void addRestaurant(Restaurant newRestaurant) {
		RestaurantList.add(newRestaurant);
	}

	@Override
	public void addReview(YelpReview newReview) {
		ReviewList.add(newReview);
	}

	@Override
	public void removeUser(YelpUser user) {
		UserList.remove(user);
	}

	@Override
	public void removeRestaurant(Restaurant restaurant) {
		RestaurantList.remove(restaurant);
		
	}

	@Override
	public void removeReview(YelpReview review) {
		ReviewList.remove(review);
	}
	
	
	private List<Double> ComputeCoefficiants(String userID){
		List<Double> returnList = new ArrayList<>();
		List<YelpReview> Reviews = getReviews(userID);
		
		List<String> RestaurantsReviewedID =  Arrays.asList(Reviews.stream().map(a -> a.getBusinessid()).toArray(String[]:: new));
		List<Restaurant> RestaurantsReviewed = new ArrayList<>();
		for(String s: RestaurantsReviewedID) {
			RestaurantsReviewed.add(getRestaurant(s));
		}
		if(Reviews.size() <= 1) {
			throw new IllegalArgumentException();
		}
		else {
			Double[] stars = Reviews.stream().map(a-> a.getStars().doubleValue()).toArray(Double[] :: new);
			List<Double> starList = Arrays.asList(stars);
			Double[] price = RestaurantsReviewed.stream().map(a-> a.getPrice().doubleValue()).toArray(Double[] :: new);
			List<Double> priceList = Arrays.asList(price);
			double meanStars = Reviews.stream().mapToDouble(a -> a.getStars().doubleValue()).average().getAsDouble();
			double meanPrice = RestaurantsReviewed.stream().mapToDouble(a -> a.getPrice().doubleValue()).average().getAsDouble();
			List<Double> subSXX= new ArrayList<>();
			List<Double> subSYY= new ArrayList<>();
			List<Double> subSXY= new ArrayList<>();
			
			for(Double s: starList) {
				subSYY.add(s-meanStars);
			}
			for(Double p : priceList ) {
				subSXX.add(p-meanPrice);
			}
			for(int i=0 ; i< subSXX.size(); i++) {
				subSXY.add(subSXX.get(i)*subSYY.get(i));
			}
			Double SXX= subSXX.stream().mapToDouble(a -> Math.pow(a, 2)).sum();
			Double SYY= subSYY.stream().mapToDouble(a -> Math.pow(a, 2)).sum();
			Double SXY= subSXY.stream().mapToDouble(a-> a).sum();
			
			Double b = SXY/ SXX;
			Double a= meanStars - (b * meanPrice);
			Double r_squared = Math.pow(SXY, 2) /  (SXX *SYY);
			
			
			returnList.add(0, a);
			returnList.add(1, b);
			returnList.add(2, r_squared);
		}
		return returnList;
	}
}