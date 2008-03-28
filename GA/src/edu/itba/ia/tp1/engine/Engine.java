/**
 * 
 */
package edu.itba.ia.tp1.engine;

import edu.itba.ia.tp1.engine.population.Population;

/**
 * This is the GA Engine. Performs the Genetic Algorithm over a Problem.
 * 
 * @author Pablo F. Siviero
 */
public class Engine {

	/* Amount of selected parents to begin. */
	private Long maxParents;
	/* Amount of generations to inspect. */
	private Long maxGenerations;
	/* Generation callback */
	private I_GenerationCallback generationCallback;

	public Engine(Long maxParents, Long maxGenerations) {
		this(maxParents, maxGenerations, null);
	}
	
	public Engine(Long maxParents, Long maxGenerations, I_GenerationCallback generationCallback) {
		this.maxParents = maxParents;
		this.maxGenerations = maxGenerations;
		this.generationCallback = generationCallback;
	}

	/**
	 * Performs the Genetic Algorithm.
	 * 
	 * @param problem. An instance of AProblem.
	 */
	public void run(A_Problem problem) {
		Population population = problem.getPopulation();

		for (int i = 0; i < this.maxGenerations; i++) {
			Population parents = problem.getSelection().execute(population, problem.getAptitude(), this.maxParents);
			Population offsprings = problem.getReproduction().reproduce(parents);
			population = problem.getReplacement().execute(offsprings, problem.getAptitude(), this.maxParents);
			generationCallback.onGenerationStep(population);
		}
	}

	/* Getters and Setters. */
	public Long getMaxParents() {
		return maxParents;
	}

	public void setMaxParents(Long maxParents) {
		this.maxParents = maxParents;
	}

	public Long getMaxGenerations() {
		return maxGenerations;
	}

	public void setMaxGenerations(Long maxGenerations) {
		this.maxGenerations = maxGenerations;
	}

	public I_GenerationCallback getGenerationCallback() {
		return generationCallback;
	}

	public void setGenerationCallback(I_GenerationCallback generationCallback) {
		this.generationCallback = generationCallback;
	}
}