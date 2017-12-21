package ca.ece.ubc.cpen221.mp5.QueryClasses;

import java.util.Arrays;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.TreeViewer;

import ca.ece.ubc.cpen221.mp5.Classes.Database;
import ca.ece.ubc.cpen221.mp5.Classes.Restaurant;
import ca.ece.ubc.cpen221.mp5.Query5.YelpQueryLexer;
import ca.ece.ubc.cpen221.mp5.Query5.YelpQueryParser;


/**
 * This class represents a query 
 * with a few multiple logical operators
 *
 */

@SuppressWarnings("deprecation")

public class Query {
	String Query;
	Database YelpDB;
	String Result;
	Set<Restaurant> answer;
	
	
	/**
	 * initializes the specific query using a string and the database that will be operated on
	 * @param Query
	 * @param YelpDB
	 */
	public Query(String Query, Database YelpDB) {
		this.Query= Query;
		this.YelpDB= YelpDB;
	}
	
	/**
	 * uses the query to find matches in the database and returns them as a set of restaurants.
	 * @return a set of restaurants satisfying a query
	 */
	
	public Set<Restaurant>getRestaurants()  {
		
		//initializes an input stream
		CharStream Charstream = new ANTLRInputStream(this.Query);
		
		//initilializes the lexer
		YelpQueryLexer lexer = new YelpQueryLexer(Charstream);
		lexer.addErrorListener(new QueryErrorHelper());
		//initializes the token stream
		TokenStream tokenstream = new CommonTokenStream(lexer);
		//initializes the parser
		YelpQueryParser parser = new YelpQueryParser(tokenstream);
		parser.addErrorListener(new QueryErrorHelper());

		//parsing to the most non-terminal operation -or
		ParseTree tree = parser.orExpr();
		
		//walks over the tree
		ParseTreeWalker walker = new ParseTreeWalker();
		
		//initializes the listener
		QueryListener listener = new QueryListener();
		
		walker.walk(listener, tree);
		
		//returns the value last operation on the stack = the biggest and most cumulative, recursive expression
		 answer = listener.getOp().eval(YelpDB);
		this.Result = listener.getOp().toString();
	
		
		
		
	
		//returns a string representation of the value of tha operation
		return answer;
	
	
		
	}
	
	
}
