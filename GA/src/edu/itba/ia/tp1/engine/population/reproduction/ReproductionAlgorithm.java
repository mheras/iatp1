/**
 * 
 */
package edu.itba.ia.tp1.engine.population.reproduction;

import edu.itba.ia.tp1.engine.population.Population;

/**
 * Reproduction takes place by setting the steps added by the operations list.
 * Using this approach, any reproduction algorithm can be implemented, following
 * different orders or algorithms, as far as they implement I_GeneticOperation.
 * 
 * @author Pablo F. Siviero
 */
public class ReproductionAlgorithm {

	/* Crossover algorithm. */
	private I_GeneticOperation crossover;
	/* Mutation algorithm. */
	private I_GeneticOperation mutation;

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
			I_GeneticOperation mutation) {
		this.crossover = crossover;
		this.mutation = mutation;
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

		Population ret = parents;

		if (this.crossover != null) {
			ret = this.crossover.perform(ret);
		}
		if (this.mutation != null) {
			ret = this.mutation.perform(ret);
		}

		return ret;
	}

	/**
	 * Gets the crossover algorithm.
	 * @return The crossover algorithm.
	 */
	public I_GeneticOperation getCrossover() {
		return crossover;
	}

	/**
	 * Sets the crossover algorithm.
	 * @param crossover The crossover algorithm.
	 */
	public void setCrossover(I_GeneticOperation crossover) {
		this.crossover = crossover;
	}

	/**
	 * Gets the mutation algorithm.
	 * @return The mutation algorithm.
	 */
	public I_GeneticOperation getMutation() {
		return mutation;
	}

	/**
	 * Sets the mutation algorithm.
	 * @param mutation The mutation algorithm.
	 */
	public void setMutation(I_GeneticOperation mutation) {
		this.mutation = mutation;
	}
}