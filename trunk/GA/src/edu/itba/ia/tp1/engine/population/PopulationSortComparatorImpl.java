/**
 * 
 */
package edu.itba.ia.tp1.engine.population;

import java.util.Comparator;

import edu.itba.ia.tp1.engine.I_Aptitude;

/**
 * Implementation of Comparator to decide if an Individual is better
 * adapted than another one.
 * 
 * @author Pablo F. Siviero
 */
public class PopulationSortComparatorImpl implements Comparator<A_Individual> {
	
	/* The aptitude function */
	private I_Aptitude aptitude;
	
	public PopulationSortComparatorImpl(I_Aptitude aptitude) {
		this.aptitude = aptitude;
	}

	@Override
	public int compare(A_Individual individual1, A_Individual individual2) {
		Double ap1 = aptitude.evaluate(individual1);
		Double ap2 = aptitude.evaluate(individual2);
		
		if (ap1 > ap2) {
			return 1;
		} else if (ap1 < ap2) {
			return -1;
		} else {
			return 0;
		}
	}
}
