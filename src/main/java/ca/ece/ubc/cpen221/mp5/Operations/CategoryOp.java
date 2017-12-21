package ca.ece.ubc.cpen221.mp5.Operations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;


/**
 * Represents a category Expression in a query 
 *
 */
public class CategoryOp implements Operation{

	private String Category;
	
	public CategoryOp(String Category) {
		this.Category= Category.trim();
	}
	
	
	@Override
	public Set<Restaurant> eval(Database YelpDB) {
		List<Restaurant> RestaurantList= YelpDB.getRestaurants();
		Restaurant[] rest= RestaurantList.stream().filter(a -> a.getCategories().contains(Category)).toArray(Restaurant[] :: new);
		Set<Restaurant> RestaurantSet= new HashSet<>();
		RestaurantSet.addAll(Arrays.asList(rest));
		return new HashSet<>(RestaurantSet);
	}

}
