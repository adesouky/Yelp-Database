
package ca.ece.ubc.cpen221.mp5;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ca.ece.ubc.cpen221.mp5.Classes.*;


public class kMeansClustering  {
	
	private List<Centroid> centroidList = new ArrayList<>();
	private Database dataBase;
	private Set<Restaurant> restaurantSet = new HashSet<>();
	Map<Centroid, Set<Restaurant>> restaurantMap = new HashMap<>();
	
	public kMeansClustering (Database db) {
		this.dataBase = db;
	}
	public kMeansClustering (Set<Restaurant> Restaurants) {
		this.restaurantSet = Restaurants;
	}
	
	//need to make a main method in order to test it
	public static void main(String[] args) throws FileNotFoundException {
		//Database db;
		Database db = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
		kMeansClustering kmeans = new kMeansClustering(db);
		kmeans.getClustersOfResturants(5);
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
	public JSONArray getClustersOfResturants(int k){
		List<Set<Restaurant>> clusters = new ArrayList<>();
		Map<Centroid, Set<Restaurant>> groupMap = new HashMap<>();
		Double maxRestaurantLong = 1.0; //change to actual value
		Double maxResturantLat = 1.0; //change to actual value
		//need to get max value of range of restaurant coordinates 
		
		//create # of new centroids corresponding to #k by initially assigning each centroid to the location of a random resturant
		//by doing this, it protects against the case where a centroid could have no restaunts assigned to it
		//System.out.println("lalalalal");
		//System.out.println(dataBase.getRestaurants());
		for(int i=0; i<k; i++) {
			centroidList.add(Centroid.setInitialLocation(dataBase.getRestaurants()));
			System.out.println("Latitdude: " + centroidList.get(i).getLatitude() +" " +"Longitude: " + centroidList.get(i).getLongitude());
			//System.out.println(centroidList);
		}
		//System.out.println("lalalalal");
		//Initialize all restaurants to a group
		//System.out.println("lalalalal");
		groupMap = mapResturants(dataBase.getRestaurants());
		
		//System.out.println("lalalalal");
		
		 KMeansVisualizer v = new KMeansVisualizer(); //part of testing needs to be removed after
		 v.setDelay(500); //part of testing needs to be removed after
		
		List<Centroid> prevCentroidList = centroidList;
		//keep looping until list of previos centroids is equal to the list of current centroids
		do { 
			int maxCentVal = 0; // groupMap.get(centroidList.get(0)).size();
			Centroid maxCent = centroidList.get(0);
			for(int j=0; j < k; j++) {

				prevCentroidList = centroidList; 
				
				//get new average location for each centroid in centroidList
				Set restaurantSet = groupMap.get(centroidList.get(j));
				System.out.println("Rest Set size: " + restaurantSet.size());
				centroidList.set(j, Centroid.setAvgLocation(restaurantSet, dataBase.getRestaurants())); //sets the centroid location to the new average of all the restaurants around it
				System.out.println("LatitdudeAvg: " + centroidList.get(j).getLatitude() +" " +"LongitudeAvg: " + centroidList.get(j).getLongitude());
				
				groupMap = mapResturants(dataBase.getRestaurants());
				System.out.println("hihi");
				//System.out.println("groupMap" + groupMap);
				//System.out.println(j); 
				//System.out.println(centroidList);
				System.out.println("hihihi"); 
				
				//find centroid with largest amount of restaurants to use when fixing if a centroid is empty
				if(maxCentVal < groupMap.get(centroidList.get(j)).size()) {
					maxCentVal = groupMap.get(centroidList.get(j)).size();
					maxCent = centroidList.get(j);
				}
			
			}
			//after setting the centroids to the new average location, re-map restaurants to their closest centroid
			groupMap = mapResturants(dataBase.getRestaurants()); 
			//System.out.println("lalalalal");
			
			//loop through all the centroids to check if any centroids have no restaurants
			for(Centroid cent : centroidList) {
					if(groupMap.get(cent) == null || groupMap.get(cent).isEmpty()) { //CHECK: would the restaurant set be null or just empty
						System.out.println("found an empty centroid");
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
							index++;
						}
						//replace entry in map for the largest centroid we took restaurants from and the centroid that had no restaurants
						groupMap.replace(cent, emptyHalf);
						groupMap.replace(maxCent, maxHalf);
					}
			}
		
//			
//		code for testing purposes, needs to be removed after
			for(Centroid cent : centroidList) {
				v.beginCluster(cent.getLatitude(), cent.getLongitude());
				for (Restaurant point : groupMap.get(cent)){
								v.addPoint(point.getLatitude(), point.getLongitude());
					  		}
			}
			v.show();
			v.setDelay(500);
//			//v.setDelay(1);
//			
			//end of testing section
			
			List<Set<Restaurant>> clustersTest = new ArrayList<>();
			for(int m=0; m < k; m++) {
				clustersTest.add(groupMap.get(centroidList.get(m)));
			}
		//	System.out.println("\n convet to J: " +convertToJSON(clustersTest));
			
		} while(!prevCentroidList.equals(centroidList));
		v.close();
		
		/*for(Centroid cent : restaurantMap.keySet()) {
			for(Restaurant rest : restaurantMap.get(cent)) {
				System.out.println(cent + " -> " + rest) ;
			}
		}*/
		
		//add all sets of restaurants from groupMap to clusters list of sets
		for(int m=0; m < k; m++) {
			clusters.add(groupMap.get(centroidList.get(m)));
		}
		
	/*	for(Centroid cent : groupMap.keySet()) {
			clusters.add(groupMap.get(cent));
		}*/
		return convertToJSON(clusters);
	}

	
	/**
	 *  Returns a map that maps a centroid to a set of restaurants
	 *  	the restaurants in the set the centroid is mapped represents all restaurants which are closer to 
	 * 		that centroid than any other
	 * @param Restaurants
	 * @return a map that maps a centroid to a set of restaurants
	 * 			
	 */
	public Map<Centroid, Set<Restaurant>> mapResturants (List<Restaurant> Restaurants){
		
		Centroid currentCent = centroidList.get(0);
		//System.out.println(centroidList);
		//System.out.println("Mapp5ara List  " + centroidList);
		
		for(Restaurant rest : Restaurants) {
			double minDistance = Double.MAX_VALUE;
			//Set<Restaurant> restaurantSet = new HashSet<Restaurant>();
			
			//looks through all centroids and finds the one that is the shortest distance from the restaurant
			for(Centroid centroid : centroidList) {
				//System.out.println("Centroid" + centroid);
				if(!restaurantMap.containsKey(centroid)) {
					restaurantMap.put(centroid, new HashSet<Restaurant>());
				}
				double distance = centroid.findDistance(rest.getLatitude(), rest.getLongitude());
				
				//minDistance = Math.min(distance, minDistance);
				if(distance < minDistance) {
					minDistance = distance;
					currentCent = centroid; 
				}
				
			}
			
			restaurantMap.get(currentCent).add(rest);
			
			//maps the centroid that has the shortest distance to the restaurant
			//centroidMap.put(rest, currentCent);
		}
		//System.out.println("dnsudsadnn   " + restaurantMap);
	
		System.out.println(restaurantMap);

		return restaurantMap;

	}
		//loop through centroidList and if centroid == the current centroid add the restaurant to the set 
//		//then add that set to the restuarntMap
//		for (Centroid cent : cen troidList) {
//			Set<Restaurant> restaurantSet = new HashSet<Restaurant>();
//			for(Restaurant rester : centroidMap.keySet()) {
//				//if it matches the current key, add to set
//				if(centroidMap.get(rester).equals(cent)) {
//					restaurantSet.add(rester);
//				}
//			}
//			//map the set of restaurants to it's centroid
//			restaurantMap.put(cent, restaurantSet);
//		}
	
	
	
//	public void checkForEmpty (Map<Centroid, Set<Restaurant>> groupMap ) {
//		//deal with if any centroids have no restaurants
//		//	int maxCentVal = groupMap.get(centroidList.get(0)).size();
//					for(Centroid cent : centroidList) {
//					//	maxCentVal = Math.max(maxCentVal, groupMap.get(cent).size());
//						if(groupMap.get(cent) == null) {
//							//we need to fix this by finding largest set of restaurants and setting half then remap it 
//							int maxCentVal = groupMap.get(centroidList.get(0)).size();
//							for(Centroid centroid : centroidList) {
//								maxCentVal = Math.max(maxCentVal, groupMap.get(centroid).size());
//							}
//							
//							//now break the cluster in two and set it 
//							//maxentroid will now have to be two new centroids
//							//find average location 
//							//create a
//							//break into two and assign half the resturants to the 
//							
//						}
//					}
//					return;
//	}
	
	/**
	 * Converts a List of sets to JSON format 
	 */
	public JSONArray convertToJSON(List<Set<Restaurant>> Clusters) {
		//List<Object> RestaurantList = new ArrayList<>();
		
		JSONArray list = new JSONArray();
		//System.out.println("xxx" + Clusters);
		//List< Centroid> CentroidMapList = new ArrayList<>(restaurantMap.keySet());
		for(Set<Restaurant> s : Clusters) {
		//for(Centroid cent : restaurantMap.keySet()) {
			for( Restaurant rest : s) {
				 JSONObject obj = new JSONObject();
				 obj.put("x", rest.getLongitude());
				 obj.put("y", rest.getLatitude());
				 obj.put("name", rest.getName());
				 obj.put("cluster", Clusters.indexOf(s));
				 obj.put("weight", 1);
				// System.out.println(obj);
				 list.add(obj);
			}
		}
		return list;
	}

}
