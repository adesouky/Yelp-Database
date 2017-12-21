package ca.ece.ubc.cpen221.mp5.Operations;

import java.util.*;

import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;

/**
 * Represents an and Expression in a query (e.g "&&" )
 *
 */
public class AndOp implements Operation {
	private List<Operation> Operations;
	private boolean ContainsAnd;
	
	public AndOp(List<Operation> Operations, boolean ContainsAnd) {
		this.Operations=Operations;
		this.ContainsAnd=ContainsAnd;
	}

	@Override
	public Set<Restaurant> eval(Database YelpDB) {
		Set<Restaurant> RestaurantSet = Operations.get(0).eval(YelpDB);


		if(this.ContainsAnd) {
			for(Operation Op: Operations) {
				RestaurantSet.retainAll(Op.eval(YelpDB));
			}
		}

		
		return RestaurantSet;
	}
}
