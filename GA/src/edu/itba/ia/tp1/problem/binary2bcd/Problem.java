/**
 * 
 */
package edu.itba.ia.tp1.problem.binary2bcd;

import edu.itba.ia.tp1.engine.AProblem;
import edu.itba.ia.tp1.engine.IAptitude;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.manager.IPopulationAlgorithm;
import edu.itba.ia.tp1.engine.population.reproduction.IReproductionAlgorithm;

/**
 * Problem that is solved by the GA Engine.
 * 
 * @author Pablo F. Siviero
 */
public class Problem extends AProblem {

	public Problem(IPopulationAlgorithm selection,
			IPopulationAlgorithm replacement,
			IReproductionAlgorithm reproduction, IAptitude aptitude) {
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
