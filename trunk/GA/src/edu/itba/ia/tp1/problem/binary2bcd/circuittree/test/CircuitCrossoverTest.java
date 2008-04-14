package edu.itba.ia.tp1.problem.binary2bcd.circuittree.test;

import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.CircuitTree;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.aptitude.MainCircuitTreeAptitudeImpl;

public class CircuitCrossoverTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Integer input = new Integer(4);
		
		I_Aptitude aptitudeAlgorithm = new MainCircuitTreeAptitudeImpl();
		
		System.out.println("Randomly generated circuit A:");
		CircuitTree circuitA = CircuitTree.generateRandomCircuit(4, 8, 4, 5);
		System.out.println(circuitA.toString());
		System.out.println("Input: " + input);
		System.out.println("Output: " + circuitA.operate(input));
		System.out.println("Aptitude: " + aptitudeAlgorithm.evaluate(circuitA));
		System.out.println("");
	
		System.out.println("Randomly generated circuit B:");
		CircuitTree circuitB = CircuitTree.generateRandomCircuit(4, 8, 2, 4);
		System.out.println(circuitB.toString());
		System.out.println("Input: " + input);
		System.out.println("Output: " + circuitB.operate(input));
		System.out.println("Aptitude: " + aptitudeAlgorithm.evaluate(circuitB));
		System.out.println("");
		
		CircuitTree childA = circuitA.clone();
		CircuitTree childB = circuitB.clone();
		CircuitTree.performCrossover(childA, childB);
		
		System.out.println("Child A:");
		System.out.println(childA.toString());
		System.out.println("Input: " + input);
		System.out.println("Output: " + childA.operate(input));
		System.out.println("Aptitude: " + aptitudeAlgorithm.evaluate(childA));
		System.out.println("");
		
		System.out.println("Child B:");
		System.out.println(childB.toString());
		System.out.println("Input: " + input);
		System.out.println("Output: " + childB.operate(input));
		System.out.println("Aptitude: " + aptitudeAlgorithm.evaluate(childB));
		System.out.println("");

	}

}
