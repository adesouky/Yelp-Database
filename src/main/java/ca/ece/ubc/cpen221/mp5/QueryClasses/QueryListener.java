package ca.ece.ubc.cpen221.mp5.QueryClasses;

import java.util.*;

import org.antlr.v4.runtime.tree.*;

import ca.ece.ubc.cpen221.mp5.Operations.*;
import ca.ece.ubc.cpen221.mp5.Operations.Operation;
import ca.ece.ubc.cpen221.mp5.Query5.YelpQueryBaseListener;
import ca.ece.ubc.cpen221.mp5.Query5.YelpQueryParser.*;
import ca.ece.ubc.cpen221.mp5.Query5.YelpQueryParser.OrExprContext;

public class QueryListener extends YelpQueryBaseListener {
	private Stack<Operation> stack;

	
	public QueryListener() {
		this.stack=new Stack<>();
	}
	@Override
	public void exitIn(InContext ctx) {
		List<ParseTree> Children = ctx.children;
		String Bracketed= ctx.getText();

	//String Bracketed= "" ;
		int i;
		
		i = ctx.children.indexOf("(");
		int j= ctx.children.indexOf(")");
		for(int f=i+1 ; f<j; f++) {
			Bracketed+=ctx.children.get(f);
			Bracketed+= " ";
	}
//		System.out.println(Bracketed);
//		
////		for(i=0 ; i< ctx.getText().length(); i++) {
////			if(Character.toString(ctx.getText().charAt(i)).equals("(")) {
////				i++;
////				break;
//			}
//			else {
//				i++;
//			}
//		}
//		while(!Character.toString(ctx.getText().charAt(i)).equals(")")){
//			
//		}
		InOp In = new InOp(getEnclosed(Bracketed));
		stack.push(In);
	}
	@Override
	public void exitCategory (CategoryContext ctx) {
		List<ParseTree> Children = ctx.children;
		String Bracketed= ctx.getText();

		CategoryOp Category = new CategoryOp(getEnclosed(Bracketed));
		stack.push(Category);
	}
	@Override
	public void exitName (NameContext ctx) {
		List<ParseTree> Children = ctx.children;
		String Bracketed= ctx.getText();

		NameOp Name = new NameOp(getEnclosed(Bracketed));
		stack.push(Name);
	}
	
	@Override
	public void exitRating(RatingContext ctx) {
		String Exp = ctx.getText();
		String ineq = ctx.ineq().getText().trim();
		String[] split = Exp.split(ineq);
		int number = Integer.parseInt(split[1].toString());

		RatingOp Rating = new RatingOp(ineq, number);
		stack.push(Rating);
	}
	@Override
	public void exitPrice(PriceContext ctx) {
		String Exp = ctx.getText();
		String ineq = ctx.ineq().getText().trim();
		String[] split = Exp.split(ineq);
		int number = Integer.parseInt(split[1].toString());

		PriceOp Price = new PriceOp(ineq, number);
		stack.push(Price);
	}
	
	
		@Override
		public void exitOrExpr(OrExprContext ctx) {
			List<Operation> Operations = new ArrayList<>();
			boolean ContainsOr = false;

			for (@SuppressWarnings("unused")
			AndExprContext context : ctx.andExpr()) {
				Operations.add(stack.pop());
			}
			// Checking if it indeed contains ||
			for (ParseTree p : ctx.children) {
				if (p.getText().equals("||")) {
					ContainsOr = true;
				}
			}
			OrOp Or = new OrOp(Operations, ContainsOr);
			stack.add(Or);
		}
		
		@Override
		public void exitAndExpr(AndExprContext ctx) {
			List<Operation> Operations = new ArrayList<>();

			boolean ContainsAnd = false;

			for (@SuppressWarnings("unused")
			AtomContext context : ctx.atom()) {
				Operations.add(stack.pop());
			}
			// Checking if it indeed contains &&
			for (ParseTree p : ctx.children) {
				if (p.getText().equals("&&")) {
					ContainsAnd = true;
				}
			}
			AndOp And = new AndOp(Operations, ContainsAnd);
			stack.add(And);

		}

	
//	public void exitOrExpr(OrExprContext ctx) {
//		
//	}
	

	public  String getEnclosed(String Terminal) {
		StringBuilder str = new StringBuilder();
		System.out.println("Terminal" +Terminal);
		int i=0;
		char[] TerminalArray = Terminal.toCharArray();
		for(char c : TerminalArray) {
			if (Character.toString(c).equals("(")) {
				i++;
				System.out.println("a");

				break;
			}
			else {
				i++;
			}
			
		}
		
		while(!(Character.toString(TerminalArray[i]).equals(")"))) {
			str.append(TerminalArray[i]);
			i++;
		}
		
		return str.toString();

	}
	
	public Operation getOp() {
		return stack.peek();
	}
}
