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
	private Integer maxParents;
	/* Amount of generations to inspect. */
	private Integer maxGenerations;

	public Engine(Integer maxParents, Integer maxGenerations) {
		this.maxParents = maxParents;
		this.maxGenerations = maxGenerations;
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
			Population offsprings = problem.getReproduction()
					.reproduce(parents);
			population = problem.getReplacement().execute(offsprings, problem.getAptitude(), this.maxParents);
		}
	}

	/* Getters and Setters. */
	public Integer getMaxParents() {
		return maxParents;
	}

	public void setMaxParents(Integer maxParents) {
		this.maxParents = maxParents;
	}

	public Integer getMaxGenerations() {
		return maxGenerations;
	}

	public void setMaxGenerations(Integer maxGenerations) {
		this.maxGenerations = maxGenerations;
	}
}