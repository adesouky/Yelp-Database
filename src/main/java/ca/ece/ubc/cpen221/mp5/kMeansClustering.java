package ca.ece.ubc.cpen221.mp5;

import java.util.*;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import ca.ece.ubc.cpen221.mp5.Classes.*;


public class kMeansClustering  {
	
	private List<Centroid> centroidList;
	private Database dataBase;
	
	public kMeansClustering (Database db) {
		this.dataBase = db;
	}
	
	
	/**
	 * Returns a List of Sets: each Set represents a cluster of restaurants. 
	 * @param k
	 * @return a List of Sets: each Set represents a cluster of restaurants. 
	 */
	public List<Set> getClustersOfResturants(int k){
		List<Set> clusters = new ArrayList<Set>();
		Map<Centroid, Set<Restaurant>> groupMap = new HashMap<>();
		Double maxRestaurantLong = 1.0; //change to actual value
		Double maxResturantLat = 1.0; //change to actual value
		//need to get max value of range of restaurant coordinates 
		
		//create # of new centroids corresponding to #k by initially assigning each centroid to the location of a random resturant
		//by doing this, it protects against the case where a centroid could have no restaunts assigned to it
		for(int i=0; i<k; i++) {
			centroidList.add(Centroid.setInitialLocation(dataBase.getRestaurants()));
		}
	
		//Initialize all restaurants to a group
		groupMap = mapResturants(dataBase.getRestaurants());
		
		List<Centroid> prevCentroidList = centroidList;
		//keep looping until list of previos centroids is equal to the list of current centroids
		do { 
			for(int j=0; j < k; j++) {

				prevCentroidList = centroidList; 
				
				//get new average location for each centroid in centroidList
				Set restaurantSet = groupMap.get(centroidList.get(j));
				centroidList.set(j, Centroid.setAvgLocation(restaurantSet)); //sets the centroid location to the new average of all the restaurants around it
			}
			//after setting the centroids to the new average location, re-map restaurants to their closest centroid
			groupMap = mapResturants(dataBase.getRestaurants()); 
		
		} while(!prevCentroidList.equals(centroidList));
		
		
		//add all sets of restaurants from groupMap to clusters list of sets
		for(int m=0; m < k; m++) {
			clusters.add(groupMap.get(centroidList.get(m)));
		}
		return clusters;
	}

	
	/**
	 * 
	 * @param Restaurants
	 * @return
	 */
	public Map<Centroid, Set<Restaurant>> mapResturants (List<Restaurant> Restaurants){
		Map<Centroid, Set<Restaurant>> restaurantMap = new HashMap<>();
		Map<Restaurant, Centroid> centroidMap = new HashMap<>();
		Set<Restaurant> restSet = new HashSet<>();
		Centroid currentCent = centroidList.get(0);
		
		for(Restaurant rest : Restaurants) {
			double minDistance = Double.MAX_VALUE;
			//looks through all centroids and finds the one that is the shortest distance from the restaurant
			for(Centroid centroid : centroidList) {
				double distance = centroid.findDistance(rest.getLatitude(), rest.getLongitude());
				//minDistance = Math.min(distance, minDistance);
				if(distance < minDistance) {
					minDistance = distance;
					currentCent = centroid; 
				}
			}
			
			//maps the centroid that has the shortest distance to the restaurant
			centroidMap.put(rest, currentCent);
		}
		//loop through centroidList and if centroid == the current centroid add the restaurant to the set 
		//then add that set to the restuarntMap
		for (Centroid cent : centroidList) {
			Set<Restaurant> restaurantSet = new HashSet<Restaurant>();
			for(Restaurant rester : centroidMap.keySet()) {
				//if it matches the current key, add to set
				if(centroidMap.get(rester).equals(cent)) {
					restaurantSet.add(rester);
				}
			}
			//map the set of restaurants to it's centroid
			restaurantMap.put(cent, restaurantSet);
		}
		return restaurantMap;
	}
	
	
	/**
	 * Converts a List of sets to JSON format 
	 */
	public void convertToJSON() {
		
	}

}
