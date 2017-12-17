package ca.ece.ubc.cpen221.mp5.Operations;

import java.util.*;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;

public class InOp implements Operation{
	private String Location;
	
	public InOp(String Location) {
		this.Location=Location.trim();
		System.out.println(Location);
	}
	
	
	
	@Override
	public Set<Restaurant> eval(Database YelpDB) {
		List<Restaurant> RestaurantList= YelpDB.getRestaurants();
		Restaurant[] rest= RestaurantList.stream().filter(a -> a.getFull_address().contains(Location)).toArray(Restaurant[] :: new);
		Set<Restaurant> RestaurantSet= new HashSet<>();
		RestaurantSet.addAll(Arrays.asList(rest));
		return new HashSet<>(RestaurantSet);
	}

}
