package ca.ece.ubc.cpen221.mp5.Operations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;

public class PriceOp implements Operation {
	private int Number;
	private String ineq;
	
	public PriceOp(String ineq, int Number) {
		this.ineq=ineq.trim();
		this.Number=Number;
	}
	
	
	
	
	@Override
	public Set<Restaurant> eval(Database YelpDB) {
		Set<Restaurant> RestaurantSet= new HashSet<>();

		if(ineq.equals("<")) {
			List<Restaurant> RestaurantList= YelpDB.getRestaurants();
			Restaurant[] rest= RestaurantList.stream().filter(a -> a.getPrice()<Number ).toArray(Restaurant[] :: new);
			//Set<Restaurant> RestaurantSet= new HashSet<>();
			RestaurantSet.addAll(Arrays.asList(rest));
		}
		else if(ineq.equals(">")) {
			List<Restaurant> RestaurantList= YelpDB.getRestaurants();
			Restaurant[] rest= RestaurantList.stream().filter(a -> a.getPrice()>Number ).toArray(Restaurant[] :: new);
			//Set<Restaurant> RestaurantSet= new HashSet<>();
			RestaurantSet.addAll(Arrays.asList(rest));
		}
		else if(ineq.equals("=")) {
			List<Restaurant> RestaurantList= YelpDB.getRestaurants();
			Restaurant[] rest= RestaurantList.stream().filter(a -> a.getPrice()==Number ).toArray(Restaurant[] :: new);
			//Set<Restaurant> RestaurantSet= new HashSet<>();
			RestaurantSet.addAll(Arrays.asList(rest));
			
		}
		else if(ineq.equals("<=")) {
			List<Restaurant> RestaurantList= YelpDB.getRestaurants();
			Restaurant[] rest= RestaurantList.stream().filter(a -> a.getPrice()<=Number ).toArray(Restaurant[] :: new);
			//Set<Restaurant> RestaurantSet= new HashSet<>();
			RestaurantSet.addAll(Arrays.asList(rest));
			return new HashSet<>(RestaurantSet);
		}
		else if(ineq.equals(">=")) {
			List<Restaurant> RestaurantList= YelpDB.getRestaurants();
			Restaurant[] rest= RestaurantList.stream().filter(a -> a.getPrice()>=Number ).toArray(Restaurant[] :: new);
			//Set<Restaurant> RestaurantSet= new HashSet<>();
			RestaurantSet.addAll(Arrays.asList(rest));
		}
		
		return new HashSet<>(RestaurantSet);
	
	}

}
