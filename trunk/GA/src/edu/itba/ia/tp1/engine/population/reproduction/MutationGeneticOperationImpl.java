/**
 * 
 */
package edu.itba.ia.tp1.engine.population.reproduction;

import edu.itba.ia.tp1.engine.population.Population;

/**
 * Implementation of Mutation algorithm.
 * 
 * @author Pablo F. Siviero
 */
public class MutationGeneticOperationImpl implements I_GeneticOperation {
	
	/* Probability of mutation */
	private Double mutationProbability;
	
	public MutationGeneticOperationImpl(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.motor.reproduction.IReproductionAlgorithm#perform()
	 */
	public Population perform(Population parents) {
		
		return null;
	}

	public Double getMutationProbability() {
		return mutationProbability;
	}

	public void setMutationProbability(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
}
