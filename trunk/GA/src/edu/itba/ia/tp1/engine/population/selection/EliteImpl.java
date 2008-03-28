/**
 * 
 */
package edu.itba.ia.tp1.engine.population.selection;

import java.util.Collections;
import java.util.List;

import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.PopulationSortComparatorImpl;

/**
 * Implementation of Elite Algorithm.
 * 
 * @author Pablo F. Siviero
 */
public class EliteImpl implements I_SelectionAlgorithm {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.motor.population.IPopulationAlgorithm#execute()
	 */
	/**
	 * Sorts the population by descending order by the aptitude function and
	 * returns a new Population with these Individuals.
	 */
	@Override
	public Population execute(Population population, I_Aptitude aptitude,
			Long nIndividuals) {
		Collections.sort(population.getIndividuals(), new PopulationSortComparatorImpl(aptitude));
		
		List<A_Individual> sorted = population.getIndividuals();
		List<A_Individual> elite = sorted.subList(0, nIndividuals.intValue());
		
		Population newPopulation = new Population();
		newPopulation.addAll(elite);

		return newPopulation;
	}
}