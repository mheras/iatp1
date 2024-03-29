package edu.itba.ia.tp1.engine.population;

import java.util.Comparator;

/**
 * Implementation of Comparator to decide if an Individual is better adapted
 * than another one.
 * 
 * @author Pablo F. Siviero
 */
public class PopulationSortComparatorImpl implements Comparator<AbstractIndividual> {

	public int compare(AbstractIndividual individual1, AbstractIndividual individual2) {

		return individual1.getAptitude().compareTo(individual2.getAptitude());

		// if (individual1.getAptitude() > individual2.getAptitude()) {
		// return 1;
		// } else if (individual1.getAptitude() < individual2.getAptitude()) {
		// return -1;
		// } else {
		// return 0;
		// }
	}
}
