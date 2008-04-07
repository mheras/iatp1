package edu.itba.ia.tp1.engine.population.selection;

import java.util.Collections;
import java.util.List;

import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.PopulationSortComparatorImpl;

/**
 * Implementation of Elite Algorithm.
 * 
 * @author Pablo F. Siviero
 */
public class EliteImpl implements I_SelectionAlgorithm {

	/**
	 * Sorts the population by descending order by the aptitude function and
	 * returns a new Population with these Individuals.
	 */
	public Population execute(Population population, Long nIndividuals) {
		Collections.sort(population.getIndividuals(),
				new PopulationSortComparatorImpl());

		List<A_Individual> sorted = population.getIndividuals();
		List<A_Individual> elite = sorted.subList(sorted.size()
				- nIndividuals.intValue(), sorted.size());

		Population newPopulation = new Population();
		newPopulation.addAll(elite);

		return newPopulation;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Elite";
	}
}