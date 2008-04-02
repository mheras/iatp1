/**
 * 
 */
package edu.itba.ia.tp1.problem.binary2bcd.circuittree;

import edu.itba.ia.tp1.engine.A_Problem;
import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.reproduction.ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.I_SelectionAlgorithm;

/**
 * Binary2BCD Problem that is solved by the GA Engine.
 * 
 * @author Pablo F. Siviero
 */
public class CircuitTreeProblem extends A_Problem {

	/**
	 * @param selection
	 * @param replacement
	 * @param reproduction
	 * @param aptitude
	 * @param individuals
	 */
	public CircuitTreeProblem(I_SelectionAlgorithm selection,
			I_SelectionAlgorithm replacement,
			ReproductionAlgorithm reproduction, I_Aptitude aptitude,
			Long individuals) {
		super(selection, replacement, reproduction, aptitude, individuals);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.engine.problem.AProblem#initPopulation()
	 */
	@Override
	public Population initPopulation(Long individuals) {
		// TODO: Implement
		return null;
	}

}
