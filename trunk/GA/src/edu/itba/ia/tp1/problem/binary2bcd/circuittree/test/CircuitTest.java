package edu.itba.ia.tp1.problem.binary2bcd.circuittree.test;

import edu.itba.ia.tp1.problem.binary2bcd.circuittree.CircuitTree;

public class CircuitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CircuitTree circuitA = CircuitTree.generateRandomCircuit(4, 8, 1, 16);
		circuitA.printCircuit();
		System.out.println(circuitA.operate(new Integer(4)));
		
		CircuitTree circuitClone = circuitA.clone();
		circuitClone.printCircuit();
		System.out.println(circuitClone.operate(new Integer(4)));
		
		
//		circuitA.performMutation(0.5);
//		
//		System.out.println();
//		circuitA.printCircuit();
		
		
	}
}
