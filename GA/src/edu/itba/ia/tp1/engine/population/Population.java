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
	private Set<A_Individual> individuals;

	/**
	 * Returns the size of the population.
	 * 
	 * @return
	 */
	public int getSize() {
		return this.individuals.size();
	}

	/**
	 * Adds an Individual
	 * 
	 * @param individual
	 */
	public void addIndividual(A_Individual individual) {
		this.individuals.add(individual);
	}

	/**
	 * Removes an Individual
	 * 
	 * @param individual
	 */
	public void removeIndividual(A_Individual individual) {
		this.individuals.remove(individual);
	}

	/* Getters and Setters. */
	public Set<A_Individual> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(Set<A_Individual> individuals) {
		this.individuals = individuals;
	}
}
