package ca.ece.ubc.cpen221.mp5;

import java.util.*;

public class Centroid {
	private double latitude;
	private double longitude;
	//Map<Character, Double> centroid;
	
	public Centroid(double latVal, double longVal) {
	//	centroid = new HashMap<Character, Double>();
//		centroid.put(x, xval);
//		centroid.put('y', yval);
		this.latitude = latVal; 
		this.longitude = longVal;
	}
	
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	/*public void setX(double val) {
		centroid.replace(x, val);
	}
	
	public void setY(double val) {
		centroid.replace(y, val);
	}
*/
	public static Centroid setAvgLocation (Set<Centroid> restaurants) { //change to type resturant instead
		//get new average location for restaurants around it
		//look at all resturants in this cluster, and then 
		double numRest = restaurants.size();
		double totalLong = 0;
		double totalLat = 0;
		for (Centroid rest : restaurants) {
			totalLat += rest.getLatitude();
			totalLong += rest.getLongitude();
		}
		double averageLat = totalLat / numRest;
		double averageLong = totalLong / numRest;
		
		return new Centroid(averageLat, averageLong);
		
	}
	/**
	 * Returns the distance from a centroid to a restaurant
	 * @param restLat
	 * @param restLong
	 * @return
	 */
	public double findDistance (double restLat, double restLong) {
		double distance = Math.sqrt(Math.pow(Math.abs(this.longitude - restLat), 2) + Math.pow(Math.abs(this.longitude - restLong), 2) );
		return distance;
	}
	
	public static Centroid randomLocation (double maxLat, double maxLong) {
		double randomLong = Math.random()*maxLong;
		double randomLat = Math.random()*maxLat;
		
		return new Centroid(randomLat, randomLong);
	}
}
