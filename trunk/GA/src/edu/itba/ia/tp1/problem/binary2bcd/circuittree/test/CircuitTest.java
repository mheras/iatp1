package edu.itba.ia.tp1.problem.binary2bcd.circuittree.test;

import edu.itba.ia.tp1.problem.binary2bcd.circuittree.CircuitTree;

/**
 * Circuit tree test.
 * 
 * @author Jorge Goldman
 *
 */
public class CircuitTest {

	/**
	 * Entry point for the circuit tree test.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		CircuitTree circuitA = CircuitTree.generateRandomCircuit(4, 8, 5, 8);
		circuitA.printCircuit();
		System.out.println(circuitA.operate(new Integer(4)));
		
		CircuitTree circuitClone = circuitA.clone();
		circuitClone.printCircuit();
		System.out.println(circuitClone.operate(new Integer(4)));
	}
}
