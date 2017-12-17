package ca.ece.ubc.cpen221;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Kmeans.Centroid;
import ca.ece.ubc.cpen221.mp5.Classes.MP5Db;
import ca.ece.ubc.cpen221.mp5.Classes.*;

public class PredictorTest {

	
	@Test
	public void Test1() throws FileNotFoundException {
		Database x= new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		ToDoubleBiFunction<MP5Db<Restaurant>, String> y =x.getPredictorFunction("Y5j_2YR42UHvWZOtALgjCw");
		Double z= y.applyAsDouble((Database) x, "6QZR4ToHKlse0yhqpU5ijg");
		System.out.println(z);
	}
	
	@Test
	public void Test2() throws FileNotFoundException {
		Database x= new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		int k=15;
		System.out.println(x.kMeansClusters_json(k));
		Map<Centroid, Set<Restaurant>> restaurantMap = x.kMeansClusters_Map(k);
		//System.out.println(restaurantMap.size());
		List<Centroid> CentroidList = new ArrayList<>(restaurantMap.keySet());
		

		
		for(int i=0; i<k ; i++) {
		Set<Restaurant> current = restaurantMap.get(CentroidList.get(i));
		for(Restaurant rest : current) {
			Centroid currentCent = CentroidList.get(0);
			double minDistance = Double.MAX_VALUE;
			for(Centroid cent : CentroidList) {
				double distance = cent.findDistance(rest.getLatitude(), rest.getLongitude());
				
				//minDistance = Math.min(distance, minDistance);
				if(distance < minDistance) {
					minDistance = distance;
					currentCent = cent; 
				}
			}
			if(CentroidList.indexOf(currentCent) != i) {
				System.out.println(CentroidList.indexOf(currentCent));
				System.out.println(CentroidList.get(11).findDistance(rest.getLatitude(), rest.getLongitude()));
				System.out.println(CentroidList.get(4).findDistance(rest.getLatitude(), rest.getLongitude()));

				fail("Should Equal" + i + "but was" + CentroidList.indexOf(currentCent));
			}
		}
		}
		
	}
	
	
	
}
