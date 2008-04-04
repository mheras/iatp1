/**
 * 
 */
package edu.itba.ia.tp1.problem.binary2bcd.circuittree.algorithm;

import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.reproduction.I_GeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.CircuitTree;

/**
 * Circuit tree implementation of Mutation algorithm.
 * 
 * @author Jorge Goldman
 */
public class CircuitTreeMutationGeneticOperation implements I_GeneticOperation {

	/* Probability of mutation */
	private Double mutationProbability;

	/**
	 * Creates a circuit tree mutation algorithm specifying the mutation
	 * probability.
	 * 
	 * @param mutationProbability
	 *            Mutation probability.
	 */
	public CircuitTreeMutationGeneticOperation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.motor.reproduction.IReproductionAlgorithm#perform()
	 */
	public Population perform(Population children) {

		// A este metodo le llegan los hijos no mutados aún. Lo que debe
		// hacer es mutar cada uno de los miembros de la poblacion, devolviendo
		// esa poblacion mutada.

		for (A_Individual individual : children.getIndividuals()) {
			CircuitTree circuitChild = (CircuitTree) individual;
			circuitChild.performMutation(mutationProbability);
		}

		return children;
	}

	/**
	 * Gets the mutation probability.
	 * 
	 * @return The mutation probability.
	 */
	public Double getMutationProbability() {
		return mutationProbability;
	}

	/**
	 * Sets the mutation probability.
	 * 
	 * @param mutationProbability
	 *            The mutation probability.
	 */
	public void setMutationProbability(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
}
