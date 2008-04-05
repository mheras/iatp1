package edu.itba.ia.tp1.engine.population;

import java.util.Comparator;

/**
 * Implementation of Comparator to decide if an Individual is better
 * adapted than another one.
 * 
 * @author Pablo F. Siviero
 */
public class PopulationSortComparatorImpl implements Comparator<A_Individual> {
	
	public int compare(A_Individual individual1, A_Individual individual2) {
		
		if (individual1.getAptitude() > individual2.getAptitude()) {
			return 1;
		} else if (individual1.getAptitude() < individual2.getAptitude()) {
			return -1;
		} else {
			return 0;
		}
	}
}
