package edu.itba.ia.tp1.problem.binary2bcd.circuittree.test;

import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.problem.binary2bcd.AptitudeImpl;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.CircuitTree;

/**
 * Circuit tree test.
 * 
 * @author Jorge Goldman
 *
 */
public class CircuitMutateTest {

	/**
	 * Entry point for the circuit tree test.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		
		Integer input = new Integer(4);
		Double mutationProbability = 0.2;
		I_Aptitude aptitudeAlgorithm = new AptitudeImpl();
		
		System.out.println("Randomly generated circuit:");
		CircuitTree circuitA = CircuitTree.generateRandomCircuit(4, 8, 2, 5);
		circuitA.printCircuit();
		System.out.println("Input: " + input);
		System.out.println("Output: " + circuitA.operate(input));
		System.out.println("Aptitude: " + aptitudeAlgorithm.evaluate(circuitA));
		System.out.println("");
	
		System.out.println("Mutated circuit (p = " + mutationProbability +  "):");
		CircuitTree circuitClone = circuitA.clone();
		circuitClone.performMutation(mutationProbability);
		circuitClone.printCircuit();
		System.out.println("Input: " + input);
		System.out.println("Output: " + circuitClone.operate(input));
		System.out.println("Aptitude: " + aptitudeAlgorithm.evaluate(circuitClone));
		System.out.println("");
	}
}
