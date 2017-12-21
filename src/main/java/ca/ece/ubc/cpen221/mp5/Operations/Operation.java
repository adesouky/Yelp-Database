package ca.ece.ubc.cpen221.mp5.Operations;

import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Classes.*;

/**
 * 
 * @author anasdesouky
 *an interface for all Query Operations to extend
 */

public interface Operation {

	
	/**
	 * this method evaluates the expression 
	 * @param YelpDB the database to operate on
	 * @return set of restaurants that satisfy the given logical expression
	 */
	public Set<Restaurant> eval(Database YelpDB);
}
