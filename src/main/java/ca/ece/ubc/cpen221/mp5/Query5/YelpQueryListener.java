// Generated from YelpQuery.g4 by ANTLR 4.7.1

package ca.ece.ubc.cpen221.mp5.Query5;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link YelpQueryParser}.
 */
public interface YelpQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link YelpQueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(YelpQueryParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpQueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(YelpQueryParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpQueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(YelpQueryParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpQueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(YelpQueryParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpQueryParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(YelpQueryParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpQueryParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(YelpQueryParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpQueryParser#ineq}.
	 * @param ctx the parse tree
	 */
	void enterIneq(YelpQueryParser.IneqContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpQueryParser#ineq}.
	 * @param ctx the parse tree
	 */
	void exitIneq(YelpQueryParser.IneqContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpQueryParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(YelpQueryParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpQueryParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(YelpQueryParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpQueryParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(YelpQueryParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpQueryParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(YelpQueryParser.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpQueryParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(YelpQueryParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpQueryParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(YelpQueryParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpQueryParser#rating}.
	 * @param ctx the parse tree
	 */
	void enterRating(YelpQueryParser.RatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpQueryParser#rating}.
	 * @param ctx the parse tree
	 */
	void exitRating(YelpQueryParser.RatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link YelpQueryParser#price}.
	 * @param ctx the parse tree
	 */
	void enterPrice(YelpQueryParser.PriceContext ctx);
	/**
	 * Exit a parse tree produced by {@link YelpQueryParser#price}.
	 * @param ctx the parse tree
	 */
	void exitPrice(YelpQueryParser.PriceContext ctx);
}