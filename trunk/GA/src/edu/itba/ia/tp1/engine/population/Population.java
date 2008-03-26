/**
 * 
 */
package edu.itba.ia.tp1.engine.population;

import java.util.Set;

/**
 * Represents a Population over which selection, reproduction and replacement
 * occurs.
 * 
 * @author Pablo F. Siviero
 */
public class Population {

	/* Set of individuals */
	private Set<Individual> individuals;

	/**
	 * Returns the size of the population.
	 * @return
	 */
	public int getSize() {
		return this.individuals.size();
	}
	
	/* Getters and Setters. */
	public Set<Individual> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(Set<Individual> individuals) {
		this.individuals = individuals;
	}
}
