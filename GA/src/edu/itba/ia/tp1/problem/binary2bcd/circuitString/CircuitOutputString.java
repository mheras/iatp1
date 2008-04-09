package edu.itba.ia.tp1.problem.binary2bcd.circuitString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jboolexpr.BooleanExpression;
import jboolexpr.MalformedBooleanException;

public class CircuitOutputString {

	private static final int N_OPERATIONS = 3;

	private String ouputString;

	public String getOuputString() {
		return ouputString;
	}

	public void setOuputString(String ouputString) {
		this.ouputString = ouputString;
	}
	
	public String toString(){
		return this.ouputString;
	}

	public static CircuitOutputString generateRandomCircuitString(
			Integer nInputs, Integer minGates, Integer maxGates) {
		CircuitOutputString result = new CircuitOutputString();
		List<String> posibleOutputString = new ArrayList<String>();
		StringBuilder initialBuilder;
		
		char initialChar = 'A';
		String equation = null;
		for (int i = 0; i < nInputs; i++) {
			initialBuilder = new StringBuilder();
			posibleOutputString.add(initialBuilder.append(
					(char) (initialChar + i)).toString());
		}

		Random rand = new Random();
		int nGates = rand.nextInt(maxGates - minGates) + minGates;
		for (int i = 0; i < nGates; i++) {
			String op = generateRandomOp(N_OPERATIONS);
			
			if( i == 0){
				if (op.equals("&&") || op.equals("||")) {
				String operator1 = posibleOutputString.get(rand
						.nextInt(posibleOutputString.size()));
				String operator2 = posibleOutputString.get(rand
						.nextInt(posibleOutputString.size()));
				equation = "(" + operator1 + op + operator2 + ")";
				}else{
					String operator = posibleOutputString.get(rand
							.nextInt(posibleOutputString.size()));
					equation =  "("+  op +  operator + ")";
				}
			}else{
				if (op.equals("&&") || op.equals("||")) {
					String operator2 = posibleOutputString.get(rand
							.nextInt(posibleOutputString.size()));
					equation = "(" + equation + op + operator2 + ")";
				} else {
					equation =  "("+  op +  equation + ")";
				}	
			}
			
		}

		result.setOuputString(equation);
		return result;

	}

	private static String generateRandomOp(int n_operations) {

		Random rand = new Random();
		int randomGate = rand.nextInt(n_operations);
		switch (randomGate) {
		/* & */
		case 0: {
			return "&&";
		}
			/* | */
		case 1: {
			return "||";
		}
			/* ! */
		case 2: {
			return "!";
		}

		}
		return null;

	}

	public Boolean operate(List<Boolean> input) {
		
		 char letterInput = 'A';
		 BooleanExpression boolExpr = null;
		 String evaluationString = this.getOuputString();
		 for(Boolean currentInput : input){
			 StringBuilder strAux = new StringBuilder();
			 strAux.append(letterInput);
			 if(currentInput.booleanValue()){
				 evaluationString = evaluationString.replaceAll(strAux.toString(), "true");
			 }else{
				 evaluationString = evaluationString.replaceAll(strAux.toString(), "false");
			 }
			 letterInput += 1;
		 }
		 System.out.println(evaluationString);
		 try {
		     boolExpr = BooleanExpression.readLeftToRight(evaluationString);
		     return boolExpr.booleanValue();
		     
		 } catch (MalformedBooleanException e) {
		     e.printStackTrace();
		 }
		
		return null;
		
		
	}
	
	
}
