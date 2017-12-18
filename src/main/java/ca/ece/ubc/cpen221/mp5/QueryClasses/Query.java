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


@SuppressWarnings("deprecation")
public class Query {
	String Query;
	Database YelpDB;
	String Result;
	Set<Restaurant> answer;
	public Query(String Query, Database YelpDB) {
		this.Query= Query;
		this.YelpDB= YelpDB;
	}
	
	
	public Set<Restaurant>getRestaurants()  {
		
		CharStream Charstream = new ANTLRInputStream(this.Query);
		
		YelpQueryLexer lexer = new YelpQueryLexer(Charstream);
		lexer.addErrorListener(new QueryErrorHelper());
		TokenStream tokenstream = new CommonTokenStream(lexer);
		YelpQueryParser parser = new YelpQueryParser(tokenstream);
		parser.addErrorListener(new QueryErrorHelper());

		ParseTree tree = parser.orExpr();
		System.err.println(tree.toStringTree(parser));
		org.antlr.v4.gui.Trees.inspect(tree, parser);
		ParseTreeWalker walker = new ParseTreeWalker();
		QueryListener listener = new QueryListener();
		walker.walk(listener, tree);
		 answer = listener.getOp().eval(YelpDB);
		this.Result = listener.getOp().toString();
	
		
		
		
	
		// Evaluating the last element in the stack which corresponds to the biggest
		// orExpr formed , i.e the full Query String passed
		
		return answer;
		//show AST in GUI
		
	
		
	}
	public String getFinalExpr() {
		
		return this.Result;
	}
	
	
}
