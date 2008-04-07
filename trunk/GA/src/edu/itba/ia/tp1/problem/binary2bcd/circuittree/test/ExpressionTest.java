package edu.itba.ia.tp1.problem.binary2bcd.circuittree.test;

import jboolexpr.BooleanExpression;
import jboolexpr.MalformedBooleanException;

public class ExpressionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 String strBoolExpr = "!true&&false||true";
		 BooleanExpression boolExpr = null;
		 try {
		     boolExpr = BooleanExpression.readLeftToRight(strBoolExpr);
		     boolean bool = boolExpr.booleanValue();
		     // bool == true
		     System.out.println(boolExpr.toString() + " == " + bool);
		     // (((!true)&&false)||true) == true
		 } catch (MalformedBooleanException e) {
		     e.printStackTrace();
		 }
		
	}

}
