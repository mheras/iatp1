package edu.itba.ia.tp1.problem.binary2bcd.circuit.test;

import edu.itba.ia.tp1.problem.binary2bcd.circuit.Circuit;

public class CircuitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Circuit circuitA = Circuit.generateRandomCircuit(4, 8, 5, 5);
		circuitA.printCircuit();
		System.out.println(circuitA.operate(new Integer(4)));

	}

}
