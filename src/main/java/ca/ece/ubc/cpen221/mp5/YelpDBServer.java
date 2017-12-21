package ca.ece.ubc.cpen221.mp5;



import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.json.JsonException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import ca.ece.ubc.cpen221.mp5.Classes.*;
import ca.ece.ubc.cpen221.mp5.Sever.IllegalQueryException;
import ca.ece.ubc.cpen221.mp5.Sever.NoMatchException;

/**
 * YelpDB is a server that recieves requests or queries and responds in json format to those queries.
 */
public class YelpDBServer {
	/** Default port number where the server listens for connections. */
	public static final int YELP_PORT = 4949;

	private ServerSocket serverSocket;
	private Database YelpDB;

	// Rep invariant: serverSocket != null
	//
	// Thread safety argument:
	// TODO FIBONACCI_PORT
	// TODO serverSocket
	// TODO socket objects
	// TODO readers and writers in handle()
	// TODO data in handle()

	/**
	 * Make a Yelp that listens for connections on port.
	 * 
	 * @param port
	 *            port number, requires 0 <= port <= 65535
	 */
	public YelpDBServer(int port, String RestaurantFile, String ReviewFile, String UserFile) throws IOException {
		serverSocket = new ServerSocket(port);
		YelpDB = new Database( RestaurantFile, ReviewFile, UserFile);
	}

	/**
	 * Run the server, listening for connections and handling them.
	 * 
	 * @throws IOException
	 *             if the main server socket is broken
	 * 
	 */
	public void serve() throws IOException {
		while (true) {
			// block until a client connects
			final Socket socket = serverSocket.accept();
			// create a new thread to handle that client
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
								handle(socket);	
						} finally {
							socket.close();
						}
					} catch (IOException ioe) {
						// this exception wouldn't terminate serve(),
						// since we're now on a different thread, but
						// we still need to handle it
						ioe.printStackTrace();
					}
				}
			});
			// start the thread
			handler.start();
		}
	}
	
	/**
	 * private method that takes care of the getRestaurant Query
	 
	 * @param businessID
	 * @return the restaurant in JSON Format if the input is valid, "ERR: INVALID_BUSINESSID_STRING "if its not,
	 * and "ERR: NO_SUCH_RESTAURANT" if the restaurant could not be found in the database.
	 */
	private synchronized String getRestaurant(String businessID) {
		String a ="";
		if(businessID.contains((" "))) {
			return( "ERR: INVALID_BUSINESSID_STRING ");
		}
		try{
			 a=YelpDB.getRestaurantJsonString(businessID);
		}
		catch(Exception ex) {
			return ("ERR: NO_SUCH_RESTAURANT");
		}
		return a;
	}
	
	
	/**
	 * private method that takes care of the addUser Query
	 * requires that the string contains the name of the user for the string to be valid
	 * @param Json String of a user
	 * @return the User in JSON Format if the input is valid, "ERR: INVALID_User_STRING "if its not,
	 * 
	 */
	private synchronized String addUser(String s) {
		
		if(!isJSONValid(s)) {
			return ("ERR: INVALID_USER_STRING");
		}
		try{
			YelpUser user = new YelpUser(true, s);
		
	//	System.out.println(user.getUserJSONString());
		YelpDB.addUser(user);
		return user.getUserJSONString();
		} 
		catch(Exception ex) {
			return ("ERR: INVALID_USER_STRING");
		}
	}
	
	
	/**
	 * private method that takes care of the addReview Query
	 * requires that the json String contains the text, the stars, the date for string to be valid.
	 * @param Json String of a review
	 * @return the review added in JSON Format if the input is valid, "ERR: INVALID_Review_STRING "if its not,
	 * , "ERR: NO_SUCH_RESTAURANT" if the restaurant could not be found in the database and "ERR: NO_SUCH_USER" 
	 * if the user could not be found in the database.
	 */
	private synchronized String addReview(String s) {
		if(!isJSONValid(s)) {
			return ("ERR: INVALID_REVIEW_STRING");
		}
	
		YelpReview Review;
		Restaurant ReviewedRest;
		try {
			Review = new YelpReview("new", s);
			
			YelpUser ReviewingUser;
			try {
			ReviewingUser= YelpDB.getUser(Review.getUserid());
			}
			catch(Exception ex) {
				return("ERR: NO_SUCH_USER");
			}
		
		try {
			 ReviewedRest= YelpDB.getRestaurant(Review.getBusinessid());
			///Updating Review Count
			
			ReviewedRest.setReviewCount(ReviewedRest.getReviewCount()+1);
			
			//Updating Stars
			double UpdatedStars = (ReviewedRest.getStars()*(ReviewedRest.getReviewCount()-1) + Review.getStars())/ReviewedRest.getReviewCount();
			//rounding to the nearest .5
			ReviewedRest.setStars(Math.round(UpdatedStars * 2) / 2.0);
		}
		catch(Exception ex) {
			return("ERR: NO_SUCH_RESTAURANT");
		}
		
		///Updating Review Count
		
		ReviewingUser.setReview_count(ReviewingUser.getReview_count()+1);
		//Updating Stars
		ReviewingUser.setAverage_stars((ReviewingUser.getAverage_stars()*(ReviewingUser.getReview_count()-1) + Review.getStars())/ReviewingUser.getReview_count());

		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		return("ERR: INVALID_REVIEW_STRING");
	}
		
		YelpDB.addReview(Review);
		return Review.getJSONString();
	}
	

	
	
	/**
	 * private method that takes care of the addRestaurant Query
	 *  requires that the json String contains the name of the restaurant, the location, the city, the state, neighborhoods, 
	 * categories, schools, full_address for the string to be valid.
	 * @param Json String of a restaurant
	 * @return the restaurant added in JSON Format if the input is valid, "ERR: INVALID_RESTAURANT_STRING" if its not.
	 * 
	 */
	
	private synchronized String addRestaurant(String s) {
		Restaurant Restaurant;
	try {	if(!isJSONValid(s)) {
			return ("ERR: INVALID_RESTAURANT_STRING");
		}
		Restaurant = new Restaurant( s);
		try{
			if (!Restaurant.Validate(s)) {
				return ("ERR: INVALID_RESTAURANT_STRING");
			}
		}
		catch(Exception ex) {
			return ("ERR: INVALID_RESTAURANT_STRING");
		}
		YelpDB.addRestaurant(Restaurant);
	}
	catch(Exception ex) {
		return ("ERR: INVALID_RESTAURANT_STRING");
	}
	
		return Restaurant.getJSONString();
		
	
	}
	

	/**
	 * private method that takes care of the Queries starting with the word Query
	 * @param  a Query
	 * @return the list of all restaurants that satisfy the query in the database in json Format;
	 * 
	 */
	
	
	
	private synchronized List<String>  getResponse(String Query) throws Exception, NoMatchException {
		List<String> JsonList= new ArrayList<>();
		Set<Restaurant> Result;
		try {
			 Result = YelpDB.getMatches(Query);
		}
			catch(Exception ex) {
				throw new IllegalQueryException();
			}
		
		
		for(Restaurant R: Result) {
			JsonList.add(R.getJSONString());
		}
		if(Result.size()==0) {
			throw new NoMatchException();
		}
	
		return JsonList;
	}
	/**
	 * Handle one client connection. Returns when client disconnects.
	 * 
	 * @param socket
	 *            socket where client is connected
	 * @throws IOException
	 *             if connection encounters an error
	 * @throws RestaurantNotFoundException 
	 */
	private void handle(Socket socket) throws IOException {
		System.err.println("client connected");

		// get the socket's input stream, and wrap converters around it
		// that convert it from a byte stream to a character stream,
		// and that buffer it so that we can read a line at a time
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		// similarly, wrap character=>bytestream converter around the
		// socket output stream, and wrap a PrintWriter around that so
		// that we have more convenient ways to write Java primitive
		// types to it.
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()), true);

		try {
			// each request is a single line containing a number
			for (String line = in.readLine(); line != null; line = in
					.readLine()) {
				System.err.println("request: " + line);
				StringTokenizer tk = new StringTokenizer(line, " ");
				if(!tk.hasMoreTokens()) {
					//////ERRRRRROR
				}
				else{
					String nextToken= tk.nextToken();
					if(nextToken.equals("GETRESTAURANT")) {
						out.println(getRestaurant(tk.nextToken("").trim()));
					}
					else if( nextToken.equals("ADDUSER")) {
						out.println(addUser(tk.nextToken("").trim()));
					}
					else if( nextToken.equals("ADDRESTAURANT")) {
					//	out.println(tk.nextToken(""));
					
						out.println(
								addRestaurant(
										tk.nextToken("").
										trim()));//addRestaurant(tk.nextToken("").trim());
					
					}
					else if(nextToken.equals("ADDREVIEW")) {
						out.println(addReview(tk.nextToken("").trim()));
					}
					else if(nextToken.equals("QUERY")){
						out.println(getResponse(tk.nextToken("").trim()));
					}
					
					if(tk.hasMoreTokens()) {
						////////ERRROR
					}
				}
				// important! our PrintWriter is auto-flushing, but if it were
				// not:
				// out.flush();
			}
		}catch(NoMatchException e) {
			out.println("ERR: NO_MATCH");
		}
		catch(IllegalQueryException iex) {
			out.println("ERR: INVALID_QUERY");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			out.println("ERR: INVALID_QUERY");
		} finally {
			out.close();
			in.close();
		}
	}
	
	/**
	 * Starts a YelpServer running on the default port.
	 */
	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(YELP_PORT, "data/restaurants.json", "data/reviews.json" , "data/users.json");
			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * a helper method that validates whether or not a json string is valid
	 */
	private boolean isJSONValid(String test) {
		try {
			
		
	    try {
			JSONParser parser = new JSONParser();
	    		Object obj = parser.parse(test);
	    		JSONObject jsonObject = (JSONObject) obj;
	    
	    } catch (ParseException e) {
            return false;
			
	   
	}
		}
		catch(Exception ex) {
			return false;
		}

	    return true;
	}
}



