package edu.itba.ia.tp1.problem.binary2bcd.circuitStringo.test;


import edu.itba.ia.tp1.problem.binary2bcd.circuitStringo.CircuitString;

public class CircuitOuputStringTest {

	public static void main(String[] args) {
		CircuitString circuit1 = CircuitString.generateRandomCircuit(4, 5, 4, 8);
		System.out.println(circuit1);
		CircuitString circuit2 =  CircuitString.generateRandomCircuit(4, 5, 4, 8);
		System.out.println(circuit2);
		
		CircuitString child1 = circuit1.clone();
		CircuitString child2 = circuit2.clone();
		
		CircuitString.performCrossover(child1, child2);
		
		System.out.println(child1.toString());
		System.out.println(child1.operate(new Integer(4)));
		System.out.println();
		System.out.println(child2.toString());
		System.out.println(child2.operate(new Integer(4)));		
		
	}
}