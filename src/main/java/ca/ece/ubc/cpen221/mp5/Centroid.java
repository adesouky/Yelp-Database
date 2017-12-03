package ca.ece.ubc.cpen221.mp5;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;

public class Centroid {
	private double latitude;
	private double longitude;
	//Map<Character, Double> centroid;
	
	/**
	 * Creates a new centroid from  given longitude and latitude values
	 * @param latVal
	 * 			a double which represents the latitude value of the centroid 
	 * @param longVal
	 * 			a double which represents the longitude value of the centroid
	 */
	public Centroid(double latVal, double longVal) {
	//	centroid = new HashMap<Character, Double>();
//		centroid.put(x, xval);
//		centroid.put('y', yval);
		this.latitude = latVal; 
		this.longitude = longVal;
	}
	
	/**
	 * Returns the latitude of this centroid
	 * @return a double, the latitude of this centroid
	 */
	public double getLatitude() {
		return this.latitude;
	}
	
	/**
	 * Returns the longitude of this centroid
	 * @return a double, the longitude of this centroid
	 */
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
	/**
	 * Returns a centroid now at the average location of all the restaurants it was mapped to
	 * @param restaurants 
	 * 			a list of restaurants in the database
	 * @return a centroid now at the average location of all the restaurants it was mapped to
	 */
	public static Centroid setAvgLocation (Set<Restaurant> restaurants) { //change to type resturant instead
		//get new average location for restaurants around it
		//look at all resturants in this cluster, and then 
		double numRest = restaurants.size();
		double totalLong = 0;
		double totalLat = 0;
		for (Restaurant rest : restaurants) {
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
	 * 			the latitude of the restaurant
	 * @param restLong
	 * 			the longitude of the restaurant
	 * @return
	 */
	public double findDistance (double restLat, double restLong) {
		double distance = Math.sqrt(Math.pow(Math.abs(this.latitude - restLat), 2) + Math.pow(Math.abs(this.longitude - restLong), 2) );
		return distance;
	}
	
//	//this method randomLocation is no longer used
//	public static Centroid randomLocation (double maxLat, double maxLong) { 
//		double randomLong = Math.random()*maxLong;
//		double randomLat = Math.random()*maxLat;
//		
//		return new Centroid(randomLat, randomLong);
//	}
	
	/**
	 * Returns a new Centroid at the location of a random restuant from the data base
	 * @param restaurants
	 * 				a list of all the resturants in the database
	 * @return a new Centroid at the location of a random restuant from the data base
	 */
	public static Centroid setInitialLocation (List<Restaurant> restaurants) {
		Random rand = new Random();
		int random = getRandomNumberInRange(0, restaurants.size()-1);
	//	int random = ThreadLocalRandom.current().nextInt(0, resturants.size() - 1);
		
		double randRestLat = restaurants.get(random).getLatitude();
		double randRestLong = restaurants.get(random).getLongitude();
		
		//System.out.println(randRestLat+ " " +  randRestLong);
		return new Centroid(randRestLat, randRestLong);
	}

	private static int getRandomNumberInRange(int min, int max) {
	
	
	Random rand = new Random();
	return rand.nextInt((max - min) + 1) + min;
}
}