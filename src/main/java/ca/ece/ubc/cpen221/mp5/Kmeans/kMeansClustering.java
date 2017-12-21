
package ca.ece.ubc.cpen221.mp5.Kmeans;

import java.util.*;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ca.ece.ubc.cpen221.mp5.Classes.*;
/**
 * acts as a represnation of an algorithm that forms k clusters 
 * @author anasdesouky
 *
 */

public class kMeansClustering  {
	
	private List<Centroid> centroidList = new ArrayList<>();
	private Database dataBase;
	private Set<Restaurant> restaurantSet = new HashSet<>();
	private Map<Centroid, Set<Restaurant>> restaurantMap = new HashMap<>();
	private Map<Centroid, Set<Restaurant>> groupMap = new HashMap<>();
	////private Map<Centroid, Set<Restaurant>> groupMap = new HashMap<>();
	
	public kMeansClustering (Database db) {
		this.dataBase = db;
	}
	public kMeansClustering (Set<Restaurant> Restaurants) {
		this.restaurantSet = Restaurants;
	}
	
	/**
	 * Returns a List of Sets: each Set represents a cluster of restaurants. 
	 * @param k
	 * @return a List of Sets: each Set represents a cluster of restaurants. 
	 */
	//public Map<Centroid, Set<Restaurant>> getClustersOfResturants(int k){
	public JSONArray getClustersOfResturants(int k){
		List<Set<Restaurant>> clusters = new ArrayList<>();
		//Map<Centroid, Set<Restaurant>> groupMap = new HashMap<>();
		//private Map<Centroid, Set<Restaurant>> groupMap = new HashMap<>();
		
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
			int maxCentVal = 0; // groupMap.get(centroidList.get(0)).size();
			Centroid maxCent = centroidList.get(0);
			for(int j=0; j < k; j++) {

				prevCentroidList = centroidList; 
				//get new average location for each centroid in centroidList
				Set restaurantSet = groupMap.get(centroidList.get(j));
				centroidList.set(j, Centroid.setAvgLocation(restaurantSet));//sets the centroid location to the new average of all the restaurants around it
			}
				
			groupMap = mapResturants(dataBase.getRestaurants());
			//this gets the largest centroid
			for(int j=0; j < k; j++) {
			if(maxCentVal < groupMap.get(centroidList.get(j)).size()) {
				maxCentVal = groupMap.get(centroidList.get(j)).size();
			maxCent = centroidList.get(j);
					
		}
			}
			//after setting the centroids to the new average location, re-map restaurants to their closest centroid
			groupMap = mapResturants(dataBase.getRestaurants()); 
			//System.out.println("vvv" + groupMap.get(maxCent).size());
			
			//loop through all the centroids to check if any centroids have no restaurants
			int EmptyCheck=1;
			System.out.println("maxCent" + groupMap.get(maxCent).size());

			
			
			while(EmptyCheck==1){
			for(Centroid cent : centroidList) {
				int f= centroidList.indexOf(cent);
				if(groupMap.get(centroidList.get(f)).isEmpty()){
					System.out.println("xxxxx");
					Random r = new Random();
				    
					Centroid cents = new Centroid( maxCent.getLatitude()*.000002*r.nextInt(10), maxCent.getLongitude()*.000002*r.nextInt(10));
					centroidList.set(f, cents);
					groupMap = mapResturants(dataBase.getRestaurants()); 
					
					if(groupMap.get(centroidList.get(f)).isEmpty()){
						Centroid cents2 = new Centroid( maxCent.getLatitude()*-.000002*r.nextInt(10), maxCent.getLongitude()*-.000002*r.nextInt(10));
						centroidList.set(f, cents2);
						groupMap = mapResturants(dataBase.getRestaurants()); 
					}
					
					if(groupMap.get(centroidList.get(f)).isEmpty()){
						Centroid cents2 = new Centroid( maxCent.getLatitude()*-.000002*r.nextInt(10), maxCent.getLongitude()*-.000002*r.nextInt(10));
						centroidList.set(f, cents2);
						groupMap = mapResturants(dataBase.getRestaurants()); 
					}
					if(groupMap.get(centroidList.get(f)).isEmpty()){
						Centroid cents3 = new Centroid( maxCent.getLatitude()*-.000002*r.nextInt(10), maxCent.getLongitude()*+.000002*r.nextInt(10));
						centroidList.set(f, cents3);
						groupMap = mapResturants(dataBase.getRestaurants()); 
					}
					
					if(groupMap.get(centroidList.get(f)).isEmpty()){
						Centroid cents2 = new Centroid( maxCent.getLatitude()*+.000002*r.nextInt(10), maxCent.getLongitude()*-.000002*r.nextInt(10));
						centroidList.set(f, cents2);
						groupMap = mapResturants(dataBase.getRestaurants()); 
					}
					
					if(groupMap.get(centroidList.get(f)).isEmpty()){
						Centroid cents2 = new Centroid( maxCent.getLatitude()*-.000001, maxCent.getLongitude()*-.000001);
						centroidList.set(f, cents2);
						groupMap = mapResturants(dataBase.getRestaurants()); 
					}
					
					if(groupMap.get(centroidList.get(f)).isEmpty()){
						Centroid cents2 = new Centroid( maxCent.getLatitude()*-.000001, maxCent.getLongitude()*-.000001);
						centroidList.set(f, cents2);
						groupMap = mapResturants(dataBase.getRestaurants()); 
					}
					if(groupMap.get(centroidList.get(f)).isEmpty()){
						Centroid cents3 = new Centroid( maxCent.getLatitude()*-.000001, maxCent.getLongitude()*+.000001);
						centroidList.set(f, cents3);
						groupMap = mapResturants(dataBase.getRestaurants()); 
					}
					
					if(groupMap.get(centroidList.get(f)).isEmpty()){
						Centroid cents2 = new Centroid( maxCent.getLatitude()*+.000001, maxCent.getLongitude()*-.000001);
						centroidList.set(f, cents2);
						groupMap = mapResturants(dataBase.getRestaurants()); 
					}
					
					
					
					
				}
				
				}
			EmptyCheck=0;
				
			}
		
			EmptyCheck=0;
			
			
			
			
		}
			while(!prevCentroidList.equals(centroidList));
		
		
		
		//add all sets of restaurants from groupMap to clusters list of sets
		for(int m=0; m < k; m++) {
			clusters.add(groupMap.get(centroidList.get(m)));
		}
		
		
	
		return convertToJSON(clusters);
		
		
	}
	
		
		
		public Map MapReturner(int k) {
		this.getClustersOfResturants(k);
		Map<Centroid, Set<Restaurant>> groupMapcopy = new HashMap<>(groupMap);
		//System.out.println(groupMap);
		return groupMapcopy;
	}
	
	
	
	
	/**
	 * 
	 * @param Restaurants
	 * @return
	 */
	public Map<Centroid, Set<Restaurant>> mapResturants (List<Restaurant> Restaurants){
		restaurantMap.clear();
		Centroid currentCent = centroidList.get(0);
		
		
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
		System.out.println("xxx" + Clusters.size());
		for(Set x: Clusters) {
			System.out.println(x.size());
		}
		//List< Centroid> CentroidMapList = new ArrayList<>(restaurantMap.keySet());
		for(Set<Restaurant> s : Clusters) {
		//for(Centroid cent : restaurantMap.keySet()) {
			for( Restaurant rest : s) {
				 JSONObject obj = new JSONObject();
				 obj.put("y", rest.getLongitude());
				 obj.put("x", rest.getLatitude());
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
