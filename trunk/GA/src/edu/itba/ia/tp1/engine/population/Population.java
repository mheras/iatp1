/**
 * 
 */
package edu.itba.ia.tp1.engine.population;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a Population over which selection, reproduction and replacement
 * occurs.
 * 
 * @author Pablo F. Siviero
 */
public class Population {

	/* Set of individuals */
	private List<A_Individual> individuals;

	/**
	 * Creates a new population.
	 */
	public Population() {
		this.individuals = new ArrayList<A_Individual>();
	}
	
	/**
	 * Returns the size of the population.
	 * 
	 * @return The size of the population.
	 */
	public int getSize() {
		return this.individuals.size();
	}

	/**
	 * Adds an Individual.
	 * 
	 * @param individual An individual.
	 */
	public void addIndividual(A_Individual individual) {
		this.individuals.add(individual);
	}

	/**
	 * Removes an Individual.
	 * 
	 * @param individual An individual.
	 */
	public void removeIndividual(A_Individual individual) {
		this.individuals.remove(individual);
	}
	
	/**
	 * Removes an individual by its position.
	 * @param position Individual position.
	 */
	public void removeByPosition(int position) {
		this.individuals.remove(position);
	}
	
	/**
	 * Adds an entire Collection.
	 * 
	 * @param population A population to be added.
	 */
	public void addAll(Collection<? extends A_Individual> population) {
		this.individuals.addAll(population);
	}
	
	/**
	 * Gets an individual by its position in the population.
	 * 
	 * @param position Individual position.
	 * @return The individual.
	 */
	public A_Individual getIndividualByPosition(int position) {
		return this.individuals.get(position);
	}

	/* Getters and Setters. */
	
	/**
	 * Gets the individuals.
	 * @return The individuals.
	 */
	public List<A_Individual> getIndividuals() {
		return individuals;
	}

	/**
	 * Sets the individuals.
	 * @param individuals The individuals.
	 */
	public void setIndividuals(List<A_Individual> individuals) {
		this.individuals = individuals;
	}
	
	
	public boolean contains(A_Individual individualByPosition) {
		return this.individuals.contains(individualByPosition);
		
	}
}
