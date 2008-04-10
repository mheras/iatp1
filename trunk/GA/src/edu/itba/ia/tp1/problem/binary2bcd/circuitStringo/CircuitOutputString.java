package edu.itba.ia.tp1.problem.binary2bcd.circuitStringo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jboolexpr.BooleanExpression;
import jboolexpr.MalformedBooleanException;

public class CircuitOutputString {

	private static final int N_OPERATIONS = 3;

	private String ouputString;

	private Integer cantGates;

	public void setCantGates(Integer cantGates) {
		this.cantGates = cantGates;
	}

	public Integer getCantGates() {
		return cantGates;
	}

	public CircuitOutputString clone() {
		CircuitOutputString result = new CircuitOutputString();
		result.setOuputString(this.getOuputString());
		result.setCantGates(this.getCantGates());
		return result;

	}

	public String getOuputString() {
		return ouputString;
	}

	public void setOuputString(String ouputString) {
		this.ouputString = ouputString;
	}

	public String toString() {
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
		result.setCantGates(nGates);
		for (int i = 0; i < nGates; i++) {
			String op = generateRandomOp(N_OPERATIONS);

			if (i == 0) {
				if (op.equals("&&") || op.equals("||")) {
					String operator1 = posibleOutputString.get(rand
							.nextInt(posibleOutputString.size()));
					String operator2 = posibleOutputString.get(rand
							.nextInt(posibleOutputString.size()));
					equation = "(" + operator1 + op + operator2 + ")";
				} else {
					String operator = posibleOutputString.get(rand
							.nextInt(posibleOutputString.size()));
					equation = "(" + op + operator + ")";
				}
			} else {
				if (op.equals("&&") || op.equals("||")) {
					String operator2 = posibleOutputString.get(rand
							.nextInt(posibleOutputString.size()));
					equation = "(" + equation + op + operator2 + ")";
				} else {
					equation = "(" + op + equation + ")";
				}
			}

		}

		result.setOuputString(equation);
		return result;

	}

	public static void performCrossover(CircuitOutputString circuitString1,
			CircuitOutputString circuitString2) {

		Random rand = new Random();

		String circuit1 = circuitString1.getOuputString();
		String circuit2 = circuitString2.getOuputString();
		int crossPoint1 = rand.nextInt(circuit1.length());
		int crossPoint2 = rand.nextInt(circuit2.length());

		String crossBranch1 = getCrossBranch(circuit1, crossPoint1);
		String crossBranch2 = getCrossBranch(circuit2, crossPoint2);
		
		circuit1 = circuit1.replace(crossBranch1, crossBranch2);
		circuit2 = circuit2.replace(crossBranch2, crossBranch1);

		circuitString1.setOuputString(circuit1);
		circuitString2.setOuputString(circuit2);
	}

	private static String getCrossBranch(String outputString, int crossPoint) {

		char[] charArray = outputString.toCharArray();
		int closingParen = 1;
		int beginIndex = crossPoint;
		int finalIndex = crossPoint;
		char init = charArray[crossPoint];
		/* Look Backward for the beggining. */
		if (init == '(') {
			beginIndex = crossPoint;
		} else {
			if(beginIndex > 0){
				beginIndex--;
			}
			
			while (closingParen > 0 && beginIndex > 0) {
				if (charArray[beginIndex] == ')') {
					closingParen++;
				}
				if (charArray[beginIndex] == '(') {
					closingParen--;
				}
				if(closingParen != 0 )
					beginIndex--;
			}
			
		}

		/* Look Foward for an end. */
		int openingParen = 1;
		if (charArray[finalIndex] == ')') {
			finalIndex = crossPoint;
		}
		else{
			if(finalIndex < outputString.length())
				finalIndex++;
			
			while (openingParen > 0 && finalIndex > 0) {
				if (charArray[finalIndex] == ')') {
					openingParen--;
				}
				if (charArray[finalIndex] == '(') {
					openingParen++;
				}
				if(openingParen != 0)
					finalIndex++;
			}
		}

		return outputString.substring(beginIndex, finalIndex+1);
	}

	public void performMutation(double mutationProbability) {

		String originalString = this.getOuputString();

		char[] charArray = originalString.toCharArray();
		Random rand = new Random();
		StringBuilder mutatedString = new StringBuilder();
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == '!' || charArray[i] == '&'
					|| charArray[i] == '|') {
				if (mutationProbability > rand.nextDouble()) {
					if (charArray[i] == '&') {
						mutatedString.append("||");
						i++;
					}
					if (charArray[i] == '|') {
						mutatedString.append("&&");
						i++;
					}
				} else {
					if (charArray[i] == '&' || charArray[i] == '|') {
						mutatedString.append(charArray[i]);
						mutatedString.append(charArray[i]);
						i++;
					} else {
						mutatedString.append(charArray[i]);
					}

				}

			} else {
				mutatedString.append(charArray[i]);
			}
		}

		this.setOuputString(mutatedString.toString());
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
		for (Boolean currentInput : input) {
			StringBuilder strAux = new StringBuilder();
			strAux.append(letterInput);
			if (currentInput.booleanValue()) {
				evaluationString = evaluationString.replaceAll(strAux
						.toString(), "true");
			} else {
				evaluationString = evaluationString.replaceAll(strAux
						.toString(), "false");
			}
			letterInput += 1;
		}
//		System.out.println(evaluationString);
		try {
			boolExpr = BooleanExpression.readLeftToRight(evaluationString);
			return boolExpr.booleanValue();

		} catch (MalformedBooleanException e) {
			e.printStackTrace();
		}

		return null;

	}

}
