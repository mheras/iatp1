/**
 * 
 */
package edu.itba.ia.tp1.engine.population.reproduction;

import java.util.ArrayList;
import java.util.List;

import edu.itba.ia.tp1.engine.population.Population;

/**
 * Reproduction takes place by setting the steps added by the operations list.
 * Using this approach, any reproduction algorithm can be implemented, following
 * different orders or algorithms, as far as they implement I_GeneticOperation.
 * 
 * @author Pablo F. Siviero
 */
public class ReproductionAlgorithm {

	private List<I_GeneticOperation> operations;

	public ReproductionAlgorithm() {
		this.operations = new ArrayList<I_GeneticOperation>();
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
		for (I_GeneticOperation operation : this.operations) {
			parents = operation.perform(parents);
		}

		return parents;
	}

	public void addGeneticOperator(I_GeneticOperation operation) {
		this.operations.add(operation);
	}

	/* Getters and Setters. */
	public List<I_GeneticOperation> getOperations() {
		return this.operations;
	}

	public void setOperations(List<I_GeneticOperation> operations) {
		this.operations = operations;
	}
}