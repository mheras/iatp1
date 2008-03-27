/**
 * 
 */
package edu.itba.ia.tp1.problem.binary2bcd;

import edu.itba.ia.tp1.engine.A_Problem;
import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.reproduction.I_ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.I_SelectionAlgorithm;

/**
 * Binary2BCD Problem that is solved by the GA Engine.
 * 
 * @author Pablo F. Siviero
 */
public class Problem extends A_Problem {

	public Problem(I_SelectionAlgorithm selection,
			I_SelectionAlgorithm replacement,
			I_ReproductionAlgorithm reproduction, I_Aptitude aptitude) {
		super(selection, replacement, reproduction, aptitude);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.engine.problem.AProblem#initPopulation()
	 */
	@Override
	public Population initPopulation() {
		// TODO: Implement
		return null;
	}

}
