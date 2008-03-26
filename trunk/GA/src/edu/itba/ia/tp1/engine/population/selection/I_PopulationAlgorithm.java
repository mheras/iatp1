/**
 * 
 */
package edu.itba.ia.tp1.engine.population.selection;

import edu.itba.ia.tp1.engine.population.Population;

/**
 * Implementations of this interface should be able to select or replace
 * Individuals from the Population.
 * 
 * @author Pablo F. Siviero
 */
public interface I_PopulationAlgorithm {

	/**
	 * Executes the algorithm over the population.
	 * 
	 * @param population
	 *            Partial population over which selection or replacement is
	 *            applied.
	 * @param nIndividuals
	 * 			  Amount of individuals to select or replace.
	 * @return The new population after selection or replacement.
	 */
	public Population execute(Population population, Integer nIndividuals);

}
