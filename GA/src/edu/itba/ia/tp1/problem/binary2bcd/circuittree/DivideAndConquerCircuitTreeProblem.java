/**
 * 
 */
package edu.itba.ia.tp1.problem.binary2bcd.circuittree;

import edu.itba.ia.tp1.engine.A_Problem;
import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.reproduction.ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.I_SelectionAlgorithm;

/**
 * Binary2BCD Problem that is solved by the GA Engine (CircuitTree implementation).
 * 
 * @author Pablo F. Siviero
 */
public class DivideAndConquerCircuitTreeProblem extends A_Problem {

	/**
	 * Creates a new CircuitTree implementation problem.
	 * 
	 * @param selection Selection algorithm.
	 * @param replacement Replacement algorithm.
	 * @param reproduction Reproduction algorithm.
	 * @param aptitude Aptitude function.
	 * @param populationSize Population size.
	 */
	public DivideAndConquerCircuitTreeProblem(I_SelectionAlgorithm selection,
			I_SelectionAlgorithm replacement,
			ReproductionAlgorithm reproduction, I_Aptitude aptitude,
			Long populationSize) {
		super(selection, replacement, reproduction, aptitude, populationSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.engine.problem.AProblem#initPopulation()
	 */
	public Population initPopulation(Long populationSize) {
		
		Population population = new Population();
		
		for (long i = 0; i < populationSize; i++) {
			/* Generates a random circuit. */
			A_Individual circuit =	CircuitTree.generateRandomCircuit(4, 1, 10, 40);
			/* Sets its own aptitude. */
			circuit.setAptitude(this.getAptitude().evaluate(circuit));
			
			/* Adds it to the population. */
			population.addIndividual(circuit);
		}
		
		return population;
	}
}