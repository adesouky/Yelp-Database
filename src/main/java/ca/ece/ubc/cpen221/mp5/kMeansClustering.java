package ca.ece.ubc.cpen221.mp5;

import java.util.*;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;


public class kMeansClustering implements MP5Db {

	@Override
	public Set getMatches(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String kMeansClusters_json(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ToDoubleBiFunction getPredictorFunction(String user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Returns a List of Sets: each Set represents a cluster of restaurants. 
	 * @param k
	 * @return a List of Sets: each Set represents a cluster of restaurants. 
	 */
	public List<Set> getClustersOfResturants(int k){
		List<Set> clusters = new ArrayList<Set>();
		Map<Centroid, Set<Centroid>> groupMap = new HashMap<Centroid, Set<Centroid>>();  //NOTE: change to set of Restaurants
		Centroid[] centroidArray = new Centroid[k];
		Double maxRestaurantLong = 1.0; //change to actual value
		Double maxResturantLat = 1.0; //change to actual value
		//need to get max value of range of restaurant coordinates 
		
		//create # of new clusters corresponding to #k with random x and y values
		for(int i=0; i<k; i++) {
			centroidArray[i] = Centroid.randomLocation(maxRestaurantLong, maxResturantLat); //Math.random()*maxvalue of range of restaurant locations
		}
		
		//could put first thing at a resturant location to prevent issue where a centriod woud have no resturant
		//ranomly place all centriods - do this in previous loop?
		
		//loop - stop when location of all previous centroids are the same as the current location of centriod
		// how would I do this because the array would just be a reference???
		//
		
		Centroid[] prevCentroidArray = centroidArray;
		while(!prevCentroidArray.equals(centroidArray)) {
			for(int j=0; j < k; j++) {
				//will have to get a new value first and then compare
				//get new centroid location and update the x and y based on the average of location value
				
				//method - compute new centriod location based on restaurants and update the map with those restaurants
				centroidArray[j] = Centroid.setAvgLocation(groupMap.get(centroidArray[j])); //sets the centroid location to the new average of all the restaurants around it
				//once we set the new average location, now we need to change all the resturants to the new groups
				
				
				Set<Centroid> restSet = groupMap.get(centroidArray[j]);
				for(Centroid rest : restSet) {
					if()
				}
				
				
				
				
				/*if(prevCentroidArray[j].getX() != centroidArray[j].getY() || prevCentroidArray[j].getX() != centroidArray[j].getY()) {
					continue;
				} else if(prevCentroidArray[0].getX() != centroidArray[0].getY() || prevCentroidArray[0].getX() != centroidArray[0].getY()) {
					continue;
				} else if(prevCentroidArray[0].getX() != centroidArray[0].getY() || prevCentroidArray[0].getX() != centroidArray[0].getY()) {
					continue;
				} else {
					break; //or return thing
				}*/
				
			}
		
		}
		//add all sets of resturants from groupMap to clusters list of sets
		for(int m=0; m < k; m++) {
			clusters.add(groupMap.get(centroidArray[m]));
		}
		return clusters;
	}
	
	
	public Map<Centroid, Set<Centroid>> giveResturantsGroup(Map<Centroid, Set<Centroid>> currRestMap, Centroid[] centArray) {
		Map<Centroid, Set<Centroid>> restMap = new HashMap<Centroid, Set<Centroid>>(); //change to set of resturant
		
			for (int i = 0; i < centArray.length -1; i++) {
				Set<Centroid> restSetPrev = restMap.get(centArray[i]); //changhe to set of resturant
				for (int j = i + 1; j < centArray.length; j++) {
					Set<Centroid> restSetCur = restMap.get(centArray[j]); //changhe to set of resturant
					
					//if the distance for one centroid is less than another move it to the other group
					//make a method called move resturant?
					for(Centroid rest : restSetPrev) {
						if(centArray[i].findDistance(rest.getLatitude(), rest.getLongitude()) >
						centArray[j].findDistance(rest.getLatitude(), rest.getLongitude()) ){
							//if curr rest is greater distance than next centroid distnace, then that rest needs to be put into curr set
							restSetCur.add(rest); //add it to the current set
							restSetPrev.remove(rest);
							//remove it from the previous set
						}
						
					}
					
				}
				restMap.put(centArray[i], restSetPrev); //add curr sets to the new map
				//restSetPrev = restSet; //set prev to curr
			}
			
			
			
		//Set<Centroid> restSetPrev = restMap.get(centArray[0]); //changhe to set of resturant
			
		for(int i=0; i < centArray.length -1; i++ ) {
		//	Set<Centroid> restSet = restMap.get(centArray[i+1]); //changhe to set of resturant
		
			
			
			
			//need to compare all sets to eachother - this is not right
			for(Centroid rest : restSetPrev) {
				if(centArray[i].findDistance(rest.getLatitude(), rest.getLongitude()) >
					centArray[i+1].findDistance(rest.getLatitude(), rest.getLongitude()) ){
					//if curr rest is greater distance than next centroid distnace, then that rest needs to be put into curr set
					restSet.add(rest); //add it to the current set
					restSetPrev.remove(rest);
					//remove it from the previous set
					
				}
			}
			restMap.put(centArray[i], restSet); //add curr sets to the new map
			restSetPrev = restSet; //set prev to curr
						
		}
		return restMap;
		
	}
	
	/**
	 * Converts a List of sets to JSON format 
	 */
	public void convertToJSON() {
		
	}

}
