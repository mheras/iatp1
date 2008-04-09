package edu.itba.ia.tp1.problem.binary2bcd.circuitString.test;


import edu.itba.ia.tp1.problem.binary2bcd.circuitString.CircuitString;

public class CircuitOuputStringTest {

	public static void main(String[] args) {
		CircuitString circuit1 = CircuitString.generateRandomCircuit(4, 5, 4, 8);
		System.out.println(circuit1);
		System.out.println(circuit1.operate(new Integer(4)));
	}
}