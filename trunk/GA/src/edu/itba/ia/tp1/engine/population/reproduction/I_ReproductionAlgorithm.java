/**
 * 
 */
package edu.itba.ia.tp1.engine.population.reproduction;

import edu.itba.ia.tp1.engine.population.Population;

/**
 * Implementations of this interface should provide the reproduction of
 * Invididuals on the Population.
 * 
 * @author Pablo F. Siviero
 */
public interface I_ReproductionAlgorithm {

	/**
	 * Reproduces the population.
	 * 
	 * @param parents
	 *            The actual population.
	 * @return The new population, including parents and children.
	 */
	public Population reproduce(Population parents);

}
