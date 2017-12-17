package ca.ece.ubc.cpen221.mp5.Operations;

import java.util.*;


import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;

public class OrOp implements Operation{
	private List<Operation> Operations;
	private boolean ContainsOr;
	
	
	public OrOp(List<Operation> Operations, boolean ContainsOr) {
		this.Operations=Operations;
		this.ContainsOr=ContainsOr;
	}
	
	
	@Override
	public Set<Restaurant> eval(Database YelpDB) {
		Set<Restaurant> RestaurantSet = new HashSet<>();
		
		if (this.ContainsOr) {
			for (Operation child : Operations) {
				RestaurantSet.addAll(child.eval(YelpDB));
			}

			return RestaurantSet;
		} else {
			RestaurantSet = Operations.get(0).eval(YelpDB);
			return RestaurantSet;
		}
	
	}

}
