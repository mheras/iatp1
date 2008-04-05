package edu.itba.ia.tp1.problem.binary2bcd.circuittree.test;

import edu.itba.ia.tp1.problem.binary2bcd.circuittree.CircuitTree;

/**
 * @author Martín A. Heras
 * @since Apr 4, 2008
 * 
 */
public class PerformanceCircuitTest {

	/**
	 * Entry point for the circuit tree test.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		
		Integer input = new Integer(4);
		
		System.out.println("Randomly generated circuit:");
		CircuitTree circuitA = CircuitTree.generateRandomCircuit(4, 8, 400, 500);
		circuitA.printCircuit();
		System.out.println("Input: " + input);
		System.out.println("Output: " + circuitA.operate(input));
	}
}
