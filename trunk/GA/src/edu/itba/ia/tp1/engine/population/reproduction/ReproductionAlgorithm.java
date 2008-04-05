package edu.itba.ia.tp1.engine.population.reproduction;

import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.engine.population.Population;

/**
 * Reproduction takes place by setting the steps added by the operations list.
 * Using this approach, any reproduction algorithm can be implemented, following
 * different orders or algorithms, as far as they implement I_GeneticOperation.
 * 
 * @author Martín A. Heras
 */
public class ReproductionAlgorithm {

	/* Crossover algorithm. */
	private I_GeneticOperation crossover;
	/* Mutation algorithm. */
	private I_GeneticOperation mutation;
	/* Aptitude function. */
	private I_Aptitude aptitude;

	/**
	 * Creates a new reproduction algorithm, based on crossover and mutation
	 * algorithms specified.
	 * 
	 * @param crossover
	 *            Crossover algorithm.
	 * @param mutation
	 *            Mutation algorithm.
	 */
	public ReproductionAlgorithm(I_GeneticOperation crossover,
			I_GeneticOperation mutation, I_Aptitude aptitude) {
		this.crossover = crossover;
		this.mutation = mutation;
		this.aptitude = aptitude;
	}

	/**
	 * Applies all the genetic operators included in the list, following the
	 * insertion order.
	 * 
	 * @param parents
	 *            The actual population.
	 * @return The new population, including parents and children.
	 */
	public Population reproduce(Population parents) {

		Population children = null;

		// Performs crossover.
		if (this.crossover != null) {
			children = this.crossover.perform(parents);
		}
		// Performs mutation.
		if (this.mutation != null) {
			children = this.mutation.perform(children);
		}
		// Calculates each child's aptitude.		
		for (A_Individual individual : children.getIndividuals()) {
			individual.setAptitude(this.aptitude.evaluate(individual));
		}

		return children;
	}

	/**
	 * Gets the crossover algorithm.
	 * 
	 * @return The crossover algorithm.
	 */
	public I_GeneticOperation getCrossover() {
		return crossover;
	}

	/**
	 * Sets the crossover algorithm.
	 * 
	 * @param crossover
	 *            The crossover algorithm.
	 */
	public void setCrossover(I_GeneticOperation crossover) {
		this.crossover = crossover;
	}

	/**
	 * Gets the mutation algorithm.
	 * 
	 * @return The mutation algorithm.
	 */
	public I_GeneticOperation getMutation() {
		return mutation;
	}

	/**
	 * Sets the mutation algorithm.
	 * 
	 * @param mutation
	 *            The mutation algorithm.
	 */
	public void setMutation(I_GeneticOperation mutation) {
		this.mutation = mutation;
	}

	/**
	 * Gets the aptitude function.
	 * 
	 * @return The aptitude function.
	 */
	public I_Aptitude getAptitude() {
		return aptitude;
	}

	/**
	 * Sets the aptitude function.
	 * 
	 * @param aptitude
	 *            The aptitude function.
	 */
	public void setAptitude(I_Aptitude aptitude) {
		this.aptitude = aptitude;
	}
}