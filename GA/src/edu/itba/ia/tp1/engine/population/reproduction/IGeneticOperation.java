/**
 * 
 */
package edu.itba.ia.tp1.engine.population.reproduction;

import edu.itba.ia.tp1.engine.population.Population;

/**
 * Any implementation of this interface should take a Population, perform some
 * changes over it and return it again. In this way, it may be thought as steps
 * for reproduction.
 * 
 * @author Pablo F. Siviero
 * 
 */
public interface IGeneticOperation {

	/**
	 * Performs a change over a Population and returns it back again.
	 * 
	 * @param population
	 * @return The changed Population
	 */
	public Population perform(Population population);

}
