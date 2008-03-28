/**
 * 
 */
package edu.itba.ia.tp1.engine.population.selection;

import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.Population;

/**
 * Implementation of EliteUniversal Algorithm; in which Elite is applied first,
 * and regarding the amount of Individuals retrieved, the rest of the population
 * is calculated by Universal.
 * 
 * @author Pablo F. Siviero
 */
public class EliteUniversalImpl implements I_SelectionAlgorithm {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.engine.population.manager.IPopulationAlgorithm#execute(edu.itba.ia.tp1.engine.population.Population)
	 */
	@Override
	public Population execute(Population population, I_Aptitude aptitude,Integer nIndividuals) {
		return null;
	}

}