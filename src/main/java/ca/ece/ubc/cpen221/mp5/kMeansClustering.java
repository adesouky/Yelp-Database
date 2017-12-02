package ca.ece.ubc.cpen221.mp5;

import java.util.*;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import ca.ece.ubc.cpen221.mp5.Classes.*;


public class kMeansClustering  {
	
	private List<Centroid> centroidList;
	private Database dataBase;
	private Set<Restaurant> restaurantSet;
	
	public kMeansClustering (Database db) {
		this.dataBase = db;
	}
	public kMeansClustering (Set<Restaurant> Restaurants) {
		this.restaurantSet = Restaurants;
	}
	
	
	//need to make a main method in order to test it
	public static void main(String[] args) {
		//Database db;
	//	Database db = new Database(); //create new database
	//	kMeansClustering kmeans = new kMeansClustering(dataBase);
	//	kmeans.getClustersOfResturants(5);
	}
	
	/*public static void main(String[] args) {
		//Database db;
		Set
		kMeansClustering kmeans = new kMeansClustering(dataBase);
		kmeans.getClustersOfResturants(5);
	}*/
	
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
		
		 KMeansVisualizer v = new KMeansVisualizer(); //part of testing needs to be removed after
		 v.setDelay(500); //part of testing needs to be removed after
		
		List<Centroid> prevCentroidList = centroidList;
		//keep looping until list of previos centroids is equal to the list of current centroids
		do { 
			int maxCentVal = groupMap.get(centroidList.get(0)).size();
			Centroid maxCent = centroidList.get(0);
			for(int j=0; j < k; j++) {

				prevCentroidList = centroidList; 
				
				//get new average location for each centroid in centroidList
				Set restaurantSet = groupMap.get(centroidList.get(j));
				centroidList.set(j, Centroid.setAvgLocation(restaurantSet)); //sets the centroid location to the new average of all the restaurants around it
				
				//this gets the largest centroid
				if(maxCentVal < groupMap.get(centroidList.get(j)).size()) {
					maxCentVal = groupMap.get(centroidList.get(j)).size();
					maxCent = centroidList.get(j);
				}
			
			}
			//after setting the centroids to the new average location, re-map restaurants to their closest centroid
			groupMap = mapResturants(dataBase.getRestaurants()); 
			
			//loop through all the centroids to check if any centroids have no restaurants
			for(Centroid cent : centroidList) {
					if(groupMap.get(cent) == null || groupMap.get(cent).isEmpty()) { //CHECK: would the restaurant set be null or just empty
						//we need to fix this by finding largest set of restaurants and setting half then remap it 
						
						//loop through and add first half of restaurants in the max set to the current centroid annd other half to the empty centroid
						Set<Restaurant> maxHalf = new HashSet<Restaurant>();
						Set<Restaurant> emptyHalf = new HashSet<Restaurant>();
						int index =0;
						for(Restaurant rest : groupMap.get(maxCent) ) {
							if(index < groupMap.get(maxCent).size()/2) {
								maxHalf.add(rest);
							} else if(index >= groupMap.get(maxCent).size()/2) {
								emptyHalf.add(rest);
							}
						}
						//replace entry in map for the largest centroid we took restaurants from and the centroid that had no restaurants
						groupMap.replace(cent, emptyHalf);
						groupMap.replace(maxCent, maxHalf);
					}
			}
			
			
			//code for testing purposes, needs to be removed after
			for(Centroid cent : centroidList) {
				v.beginCluster(cent.getLatitude(), cent.getLongitude());
				for (Restaurant point : groupMap.get(cent)){
								v.addPoint(point.getLatitude(), point.getLongitude());
					  		}
			}
			v.show();
			v.setDelay(100000);
			//v.setDelay(1);
			
			//end of testing section
			
			
			
		} while(!prevCentroidList.equals(centroidList));
		v.close();
		
		
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
	
	
	public void checkForEmpty (Map<Centroid, Set<Restaurant>> groupMap ) {
		//deal with if any centroids have no restaurants
		//	int maxCentVal = groupMap.get(centroidList.get(0)).size();
					for(Centroid cent : centroidList) {
					//	maxCentVal = Math.max(maxCentVal, groupMap.get(cent).size());
						if(groupMap.get(cent) == null) {
							//we need to fix this by finding largest set of restaurants and setting half then remap it 
							int maxCentVal = groupMap.get(centroidList.get(0)).size();
							for(Centroid centroid : centroidList) {
								maxCentVal = Math.max(maxCentVal, groupMap.get(centroid).size());
							}
							
							//now break the cluster in two and set it 
							//maxentroid will now have to be two new centroids
							//find average location 
							//create a
							//break into two and assign half the resturants to the 
							
						}
					}
					return;
	}
	
	/**
	 * Converts a List of sets to JSON format 
	 */
	public void convertToJSON() {
		//use a library
		//saving it to a file 
	}

}
