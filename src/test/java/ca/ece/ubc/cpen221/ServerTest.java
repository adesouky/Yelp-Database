package ca.ece.ubc.cpen221;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.StringTokenizer;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;
import ca.ece.ubc.cpen221.mp5.QueryClasses.Query;
import ca.ece.ubc.cpen221.mp5.QueryClasses.QueryListener;
import ca.ece.ubc.cpen221.mp5.Sever.FibonacciClient;
import ca.ece.ubc.cpen221.mp5.YelpDBServer;

public class ServerTest {
	private Database YELPDB = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");

	
	
	@Test
	public void test1() {
		String s = "ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/peppermint-grill-berkeley\", \"longitude\": -122.2598181, \"neighborhoods\": [\"UC Campus Area\"], \"business_id\": \"FWadSZw0G7HsgKXq7gHTnw\", \"name\": \"Peppermint Grill\", \"categories\": [\"American (Traditional)\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 2.5, \"city\": \"Berkeley\", \"full_address\": \"2505 Hearst Ave\\nSte B\\nUC Campus Area\\nBerkeley, CA 94709\", \"review_count\": 16, \"photo_url\": \"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/924a6444ca6c/gfx/blank_biz_medium.gif\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8751965, \"price\": 2}\n";
				StringTokenizer st = new StringTokenizer(s, " ");
				System.out.println(st.nextToken());
				System.out.println(st.nextToken("").trim());
//				try {
					//Database YELPDB = new Database("data/restaurants.json", "data/reviews.json" , "data/users.json");
					//String v = YELPDB.getUser("3417473bc874").getUserJSONString();
				//	System.out.println(v);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				//System.out.println(st.nextToken());
	
	}
	
	@Test 
	public void test2() throws Exception {
		
		Query A = new Query("in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2", YELPDB);
		
		Set<Restaurant> SetR = A.getRestaurants();
		System.out.println(SetR.size());
		for(Restaurant R : SetR) {
			System.out.println(R.getJSONString());
		}

		
	}
	
	@Test 
	public void test4() throws Exception {
		
		Query A = new Query("in(Telegraph Ave) && (category(Chinese) || category(Italian)) && rating >= 2", YELPDB);
		
		Set<Restaurant> SetR = A.getRestaurants();
		System.out.println(SetR.size());
		for(Restaurant R : SetR) {
			System.out.println(R.getJSONString());
		}

		
	}
	
	@Test 
	public void test5() throws Exception {
		
		Query A = new Query("in(Telegraph Ave) && name(Fondue Fred)", YELPDB);
		
		Set<Restaurant> SetR = A.getRestaurants();
		System.out.println(SetR.size());
		for(Restaurant R : SetR) {
			System.out.println(R.getJSONString());
		}

		
	}
	
	@Test
	public void test6() {
		System.out.println(YELPDB.getUser("f_1bRw8RuK-zZAKuUMTqNw"));
		Thread testServer = new Thread (new Runnable() {
			public void run() {
				
					YelpDBServer.main(null);
			
			}
				
		});
		System.out.println("before" + YELPDB.getUser("f_1bRw8RuK-zZAKuUMTqNw").getReview_count());
		System.out.println("before" + YELPDB.getUser("f_1bRw8RuK-zZAKuUMTqNw").getAverage_stars());
		System.out.println("before Restaurant review" +YELPDB.getRestaurant("1CBs84C-a-cuA3vncXVSAw").getReviewCount());
		System.out.println("before" + YELPDB.getUser("f_1bRw8RuK-zZAKuUMTqNw").getUserJSONString());
		testServer.start();
		String[] args = { "QUERY in(Telegraph Ave)", 
				"QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price < 2", 
				"QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && rating < 4",
				"QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price > 2", 
				"QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && rating > 1",
				"QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price >= 2", 
				"QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && rating <= 4",
				"QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && rating >= 1",
				"QUERY in(Telegraph Ave) && rating >= 1",
				"QUERY in(Telegraph Ave) && rating > 1",
				"QUERY in(Telegraph Ave) && rating > 4",
				"QUERY indfdd(Telegraph Ave) && rating > 4",
				"QUERY in+-(Telegraph Ave) && rating > 4",
				"QUERY in(Telegraph Ave) + rating > 4",

				"QUERY in(Telegraph Ave) && rating >= 4",
				"QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price = 2",
				"QUERY in(Telegraph Ave) && (category(Chinese) || category(Italian)) && rating = 2",
				"GETRESTAURANT QQIjsdcokFermi2ugoD6ow" , 
				"GETRESTAURANT QQIjsdcokF    ermi2ugoD6ow" , 
				"GETRESTAURANT QQIjs dcokFermi2ugoD6ow" , 

				"GETRESTAURANT XVEYYER" , 
				"ADDUSER {\"name\": \"Sathish G.\"}", 
				"ADDUSER {\"nae\": \"Sathish G.\"}", 
				"ADDUSER \"name\": \"Sathish G.\"}" , 
				"ADDUSER \"Sathish G.\"" ,
				"ADDUSER {\"type\": \"Sathish G.\"}",
				"ADDREVIEW {\"business_id\": \"1CBs84C-a-cuA3vncXVSAw\",  \"text\": \"If you're on the north side of campus and want a quick lunch and don't want La Burrita, this is the place to go! This place is also good for group dinners too. They sell pizzas by the slice, and have lots of variety. I really like the Athenian slice! The pizzas can take a while to come out though\", \"stars\": 4, \"user_id\": \"f_1bRw8RuK-zZAKuUMTqNw\", \"date\": \"2011-02-19\"}\n" ,
				"ADDREVIEW {\"text\": \"If you're on the north side of campus and want a quick lunch and don't want La Burrita, this is the place to go! This place is also good for group dinners too. They sell pizzas by the slice, and have lots of variety. I really like the Athenian slice! The pizzas can take a while to come out though\", \"stars\": 4, \"user_id\": \"f_1bRw8RuK-zZAKuUMTqNw\", \"date\": \"2011-02-19\"}\n" ,
				"ADDREVIEW business_id\": \"1CBs84C-a-cuA3vncXVSAw\",  \"text\": \"If you're on the north side of campus and want a quick lunch and don't want La Burrita, this is the place to go! This place is also good for group dinners too. They sell pizzas by the slice, and have lots of variety. I really like the Athenian slice! The pizzas can take a while to come out though\", \"stars\": 4, \"user_id\": \"f_1bRw8RuK-zZAKuUMTqNw\", \"date\": \"2011-02-19\"}\n" ,
				"ADDREVIEW {\"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"stars\": 4, \"user_id\": \"f_1bRw8RuK-zZAKuUMTqNw\", \"date\": \"2011-02-19\"}\n" ,
				"ADDREVIEW {\"business_id\": \"1CBs84C-a-csns jsuA3vncXVSAw\",  \"text\": \"If you're on the north side of campus and want a quick lunch and don't want La Burrita, this is the place to go! This place is also good for group dinners too. They sell pizzas by the slice, and have lots of variety. I really like the Athenian slice! The pizzas can take a while to come out though\", \"stars\": 4, \"user_id\": \"f_1bRw8RuK-zZAKuUMTqNw\", \"date\": \"2011-02-19\"}\n" ,
				"ADDREVIEW {\"business_id\": \"1CBs84C-a-cuA3vncXVSAw\",  \"text\": \"If you're on the north side of campus and want a quick lunch and don't want La Burrita, this is the place to go! This place is also good for group dinners too. They sell pizzas by the slice, and have lots of variety. I really like the Athenian slice! The pizzas can take a while to come out though\", \"stars\": 4, \"user_id\": \"f_1bRw8RuK-zZAsjsjndjKuUMTqNw\", \"date\": \"2011-02-19\"}\n" ,
	//			"ADDRESTAURANT {\"longitude\": -122.2598181, \"neighborhoods\": [\"UC Campus Area\"], \"name\": \"Momo Masala\", \"categories\": [\"Indian\", \"Asian Fusion\", \"Restaurants\"], \"state\": \"CA\", \"city\": \"Berkeley\", \"full_address\": \"2505 Hearst Ave\\nSte B\\nUC Campus Area\\nBerkeley, CA 94709\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8751965}\n" ,
				"ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/la-cascada-taqueria-berkeley\", \"longitude\": -122.2663047, \"neighborhoods\": [\"Downtown Berkeley\", \"UC Campus Area\"], \"business_id\": \"WXKx2I2SEzBpeUGtDMCS8A\", \"name\": \"La Cascada Taqueria\", \"categories\": [\"Mexican\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.0, \"city\": \"Berkeley\", \"full_address\": \"2164 Center St\\nDowntown Berkeley\\nBerkeley, CA 94704\", \"review_count\": 101, \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/-Nwor_L01Lsm6Mcf36-QCA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8705337, \"price\": 2}\n",
				"ADDRESTAURANT {\"longitude\": -122.2663047, \"neighborhoods\": [\"Downtown Berkeley\", \"UC Campus Area\"], \"business_id\": \"WXKx2I2SEzBpeUGtDMCS8A\", \"name\": \"La Cascada Taqueria\", \"categories\": [\"Mexican\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.0, \"city\": \"Berkeley\", \"full_address\": \"2164 Center St\\nDowntown Berkeley\\nBerkeley, CA 94704\", \"review_count\": 101, \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/-Nwor_L01Lsm6Mcf36-QCA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8705337, \"price\": 2}\n",
				"ADDRESTAURANT {\"neighborhoods\": [\"Downtown Berkeley\", \"UC Campus Area\"], \"business_id\": \"WXKx2I2SEzBpeUGtDMCS8A\", \"name\": \"La Cascada Taqueria\", \"categories\": [\"Mexican\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.0, \"city\": \"Berkeley\", \"full_address\": \"2164 Center St\\nDowntown Berkeley\\nBerkeley, CA 94704\", \"review_count\": 101, \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/-Nwor_L01Lsm6Mcf36-QCA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8705337, \"price\": 2}\n",
				"ADDRESTAURANT \"longitude\": -122.2663047, \"neighborhoods\": [\"Downtown Berkeley\", \"UC Campus Area\"], \"business_id\": \"WXKx2I2SEzBpeUGtDMCS8A\", \"name\": \"La Cascada Taqueria\", \"categories\": [\"Mexican\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.0, \"city\": \"Berkeley\", \"full_address\": \"2164 Center St\\nDowntown Berkeley\\nBerkeley, CA 94704\", \"review_count\": 101, \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/-Nwor_L01Lsm6Mcf36-QCA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8705337, \"price\": 2}\n",
				"ADDRESTAURANT {\"longitude\": -122.2663047, \"neighborhoods\": [\"Downtown Berkeley\", \"UC Campus Area\"], \"name\": \"La Cascada Taqueria\", \"categories\": [\"Mexican\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.0, \"city\": \"Berkeley\", \"full_address\": \"2164 Center St\\nDowntown Berkeley\\nBerkeley, CA 94704\", \"review_count\": 101, \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/-Nwor_L01Lsm6Mcf36-QCA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8705337, \"price\": 2}\n" ,
				"ADDREVIEW {\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"2xF1VFsuxRdtQxqn-NbNAw\", \"text\": \"If you're on the north side of campus and want a quick lunch and don't want La Burrita, this is the place to go! This place is also good for group dinners too. They sell pizzas by the slice, and have lots of variety. I really like the Athenian slice! The pizzas can take a while to come out though\", \"stars\": 4, \"user_id\": \"f_1bRw8RuK-zZAKuUMTqNw\", \"date\": \"2011-02-19\"}\n" ,
				"ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/la-cascada-taqueria-berkeley\", \"neighborhoods\": [\"Downtown Berkeley\", \"UC Campus Area\"], \"business_id\": \"WXKx2I2SEzBpeUGtDMCS8A\", \"name\": \"La Cascada Taqueria\", \"categories\": [\"Mexican\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.0, \"city\": \"Berkeley\", \"full_address\": \"2164 Center St\\nDowntown Berkeley\\nBerkeley, CA 94704\", \"review_count\": 101, \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/-Nwor_L01Lsm6Mcf36-QCA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8705337, \"price\": 2}\n",

				
			
		};
			
		FibonacciClient.main(args);
		System.out.println("after" + YELPDB.getUser("f_1bRw8RuK-zZAKuUMTqNw").getReview_count());
		System.out.println("after" + YELPDB.getUser("f_1bRw8RuK-zZAKuUMTqNw").getAverage_stars());
		System.out.println("AFTER Restaurant review" +YELPDB.getRestaurant("1CBs84C-a-cuA3vncXVSAw").getReviewCount());
		System.out.println("after" + YELPDB.getUser("f_1bRw8RuK-zZAKuUMTqNw").getUserJSONString());
		
	}
	
	
	@Test
	public void test7() {
		System.out.println("AFTER Restaurant review" +YELPDB.getRestaurant("QQIjsdcokFermi2ugoD6ow").getReviewCount());

	}
	
	@Test
	public void test8() {
		System.out.println("Before Review" + YELPDB.getUser("f_1bRw8RuK-zZAKuUMTqNw").getAverage_stars());
		System.out.println("AFTER Restaurant review" +YELPDB.getRestaurant("QQIjsdcokFermi2ugoD6ow").getReviewCount());
		
		
		Thread testServer = new Thread (new Runnable() {
			public void run() {
				
					YelpDBServer.main(null);
					
			
			}
				
		});
		testServer.start();
		Thread testClient = new Thread(new Runnable() {
			
			public void run() {
				String[] args = {
						
						"GETRESTAURANT QQIjsdcokFermi2ugoD6ow" , 
						"ADDREVIEW {\"type\": \"review\", \"business_id\": \"QQIjsdcokFermi2ugoD6ow\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"review_id\": \"2xF1VFsuxRdtQxqn-NbNAw\", \"text\": \"If you're on the north side of campus and want a quick lunch and don't want La Burrita, this is the place to go! This place is also good for group dinners too. They sell pizzas by the slice, and have lots of variety. I really like the Athenian slice! The pizzas can take a while to come out though\", \"stars\": 4, \"user_id\": \"f_1bRw8RuK-zZAKuUMTqNw\", \"date\": \"2011-02-19\"}\n" ,
						"GETRESTAURANT QQIjsdcokFermi2ugoD6ow" ,
						"ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/la-cascada-taqueria-berkeley\", \"longitude\": -122.2663047, \"neighborhoods\": [\"Downtown Berkeley\", \"UC Campus Area\"], \"business_id\": \"WXKx2I2SEzBpeUGtDMCS8A\", \"name\": \"La Cascada Taqueria\", \"categories\": [\"Mexican\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.0, \"city\": \"Berkeley\", \"full_address\": \"2164 Center St\\nDowntown Berkeley\\nBerkeley, CA 94704\", \"review_count\": 101, \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/-Nwor_L01Lsm6Mcf36-QCA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8705337, \"price\": 2}\n",

						
				};
				FibonacciClient.main(args);
			}
		});
		
		testClient.start();
		
		for(int i=0; i<100; i++) {
		while(testClient.isAlive()) {
			
		}
		}
		
		System.out.println("After Review" + YELPDB.getUser("f_1bRw8RuK-zZAKuUMTqNw").getAverage_stars());
		System.out.println("AFTER Restaurant review" +YELPDB.getRestaurant("QQIjsdcokFermi2ugoD6ow").getReviewCount());
		//System.out.println("lalala");

	
	}	
	
	
	
//	@Test
//	public void test9() {
//		this.test8();
//		System.out.println("AFTER Restaurant review" +YELPDB.getRestaurant("QQIjsdcokFermi2ugoD6ow").getReviewCount());
//	}
	
}
