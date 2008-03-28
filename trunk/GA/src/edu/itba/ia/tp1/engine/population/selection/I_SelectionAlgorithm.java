/**
 * 
 */
package edu.itba.ia.tp1.engine.population.selection;

import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.Population;

/**
 * Implementations of this interface should be able to select or replace
 * Individuals from the Population.
 * 
 * @author Pablo F. Siviero
 */
public interface I_SelectionAlgorithm {

	/**
	 * Executes the algorithm over the population.
	 * 
	 * @param population
	 *            Partial population over which selection or replacement is
	 *            applied.
	 * @param aptitude
	 * 			  The aptitude function to apply.
	 * @param nIndividuals
	 * 			  Amount of individuals to select or replace.
	 * @return The new population after selection or replacement.
	 */
	public Population execute(Population population, I_Aptitude aptitude, Long nIndividuals);

}
