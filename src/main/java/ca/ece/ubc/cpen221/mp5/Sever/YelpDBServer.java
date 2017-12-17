package ca.ece.ubc.cpen221.mp5.Sever;



import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import ca.ece.ubc.cpen221.mp5.Classes.*;

/**
 * FibonacciServerMulti is a server that finds the n^th Fibonacci number given
 * n. It accepts requests of the form: Request ::= Number "\n" Number ::= [0-9]+
 * and for each request, returns a reply of the form: Reply ::= (Number | "err")
 * "\n" where a Number is the request Fibonacci number, or "err" is used to
 * indicate a misformatted request. FinbonacciServerMulti can handle multiple
 * concurrent clients.
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
	 * Make a FibonacciServerMulti that listens for connections on port.
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
	 *  @throws RestaurantNotFoundException
	 */
	public void serve() throws IOException, RestaurantNotFoundException {
		while (true) {
			// block until a client connects
			final Socket socket = serverSocket.accept();
			// create a new thread to handle that client
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
								handle(socket);	
						} catch (RestaurantNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
	
	
	private synchronized String getRestaurant(String businessID) throws RestaurantNotFoundException {
		String a=YelpDB.getRestaurantJsonString(businessID);
		return a;
	}
	
	private synchronized String addUser(String s) {
		YelpUser user = new YelpUser(true, s);
	//	System.out.println(user.getUserJSONString());
		YelpDB.addUser(user);
		return user.getUserJSONString();
	}
	private synchronized String addReview(String s) {
		YelpReview Review = new YelpReview("new", s);
		YelpDB.addReview(Review);
		Restaurant ReviewedRest= YelpDB.getRestaurant(Review.getBusinessid());
		///Updating Review Count
		ReviewedRest.setReviewCount(ReviewedRest.getReviewCount()+1);
		//Updating Stars
		double UpdatedStars = (ReviewedRest.getStars()*(ReviewedRest.getReviewCount()-1) + Review.getStars())/ReviewedRest.getReviewCount();
		//rounding to the nearest .5
		ReviewedRest.setStars(Math.round(UpdatedStars * 2) / 2.0);
		
		YelpUser ReviewingUser= YelpDB.getUser(Review.getUserid());
		///Updating Review Count
		ReviewingUser.setReview_count(ReviewingUser.getReview_count()+1);
		//Updating Stars
		ReviewingUser.setAverage_stars((ReviewingUser.getAverage_stars()*(ReviewingUser.getReview_count()-1) + Review.getStars())/ReviewingUser.getReview_count());
		
		return Review.getJSONString();
	}
	private synchronized String addRestaurant(String s) {
		Restaurant Restaurant = new Restaurant( s);
		YelpDB.addRestaurant(Restaurant);
		return Restaurant.getJSONString();
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
	private void handle(Socket socket) throws IOException, RestaurantNotFoundException {
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
						out.println(getRestaurant(tk.nextToken().trim()));
					}
					else if( nextToken.equals("ADDUSER")) {
						out.println(addUser(tk.nextToken("").trim()));
					}
					else if( nextToken.equals("ADDRESTAURANT")) {
						out.println(addRestaurant(tk.nextToken("").trim()));
					}
					else if(nextToken.equals("ADDREVIEW")) {
						out.println(addReview(tk.nextToken("").trim()));
					}
					else {
						//////ERRROR
					}
					
					if(tk.hasMoreTokens()) {
						////////ERRROR
					}
				}
				// important! our PrintWriter is auto-flushing, but if it were
				// not:
				// out.flush();
			}
		} finally {
			out.close();
			in.close();
		}
	}
	
	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(YELP_PORT, "data/restaurants.json", "data/reviews.json" , "data/users.json");
			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RestaurantNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



