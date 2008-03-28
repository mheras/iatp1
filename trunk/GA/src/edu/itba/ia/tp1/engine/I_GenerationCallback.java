/**
 * 
 */
package edu.itba.ia.tp1.engine;

import edu.itba.ia.tp1.engine.population.Population;

/**
 * This interface acts as a callback to report to the view the actual population
 * after replacement, and generate statistics and reports from its output.
 * 
 * @author Pablo F. Siviero
 */
public interface I_GenerationCallback {

	/**
	 * Sets the population to be accesible from the client.
	 * @param population
	 */
	public void onGenerationStep(Population population);

}
