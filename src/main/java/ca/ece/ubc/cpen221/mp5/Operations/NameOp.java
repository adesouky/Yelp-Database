package ca.ece.ubc.cpen221.mp5.Operations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;

public class NameOp implements Operation{

	private String Name;
	
	public NameOp(String Name) {
		this.Name= Name.trim();
	}
	
	@Override
	public Set<Restaurant> eval(Database YelpDB) {
		List<Restaurant> RestaurantList= YelpDB.getRestaurants();
		Restaurant[] rest= RestaurantList.stream().filter(a -> a.getName().equals(Name)).toArray(Restaurant[] :: new);
		Set<Restaurant> RestaurantSet= new HashSet<>();
		RestaurantSet.addAll(Arrays.asList(rest));
		return new HashSet<>(RestaurantSet);
	}

}
