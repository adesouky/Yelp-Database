package ca.ece.ubc.cpen221.mp5.Classes;

import java.util.Set;
import java.util.function.ToDoubleBiFunction;;

// This interface represents a database of objects of type T. 
// It supports querying for objects from the database as well
// as two basic statistical learning operations on the database.

public interface MP5Db<T> {

	/**
	 * Perform a structured query and return the set of objects that matches the
	 * query
	 * 
	 * @param queryString
	 * @return the set of objects that matches the query
	 */
	Set<T> getMatches(String queryString);

	/**
	 * Cluster objects into k clusters using k-means clustering
	 * 
	 * @param k
	 *            number of clusters to create (0 < k <= number of objects)
	 * @return a String, in JSON format, that represents the clusters
	 */
	String kMeansClusters_json(int k);

	/**
	 * 
	 * @param user
	 *            represents a user_id in the database
	 * @return a function that predicts the user's ratings for objects (of type
	 *         T) in the database of type MP5Db<T>. The function that is
	 *         returned takes two arguments: one is the database and other other
	 *         is a String that represents the id of an object of type T.
	 */
	ToDoubleBiFunction<MP5Db<T>, String> getPredictorFunction(String user);

}
