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
	/* Current generation. */
	private Long currentGeneration;
	/* Specific problem. */
	private AbstractProblem problem;

	/**
	 * Creates a new instance of the engine.
	 * 
	 * @param problem
	 *            The problem that the engine is going to solve.
	 * @param maxParents
	 *            Maximum parents going to be selected in order to get their
	 *            children.
	 * @param maxGenerations
	 *            Maximum generations going to be performed by the engine.
	 */
	public Engine(AbstractProblem problem, Long maxParents, Long maxGenerations) {
		this.maxParents = maxParents;
		this.maxGenerations = maxGenerations;
		this.currentGeneration = 0L;
		// problem specified has its population initialized.
		this.problem = problem;
	}

	/**
	 * Resets the engine. Usefull to step the engine from the beginning without
	 * having to instantiate another engine.
	 */
	public void reset() {
		/* Reinitialize the population. */
		this.problem.setPopulation(this.problem.initPopulation(this.problem
				.getPopulationSize()));
		/* Reset current generation counter. */
		this.currentGeneration = 0L;
	}

	/**
	 * Steps the engine in order to get a new generation.
	 * 
	 * @return <code>true</code> if there is another generation available;
	 *         otherwise <code>false</code>.
	 */
	public boolean step() {

		/* Return false if execution is done. */
		if (this.currentGeneration.equals(this.maxGenerations)) {
			return false;
		}

		Long populationSize = problem.getPopulationSize();

		/* Select parents based on the selection method attached to the problem. */
		Population parents = problem.getSelection().execute(
				problem.getPopulation(), this.maxParents);

		/*
		 * Obtain offsprings using reproduction method over their parents (i.e.
		 * Crossing and mutation).
		 */
		Population offsprings = problem.getReproduction().reproduce(parents);
		problem.getPopulation().addAll(offsprings.getIndividuals());

		Population replacement = problem.getReplacement().execute(
				problem.getPopulation(), populationSize);

//		/* We dispose every killed individual in order to free resources. */
//		Population killedPopulation = new Population();
//		killedPopulation.addAll(problem.getPopulation().getIndividuals());
//		killedPopulation.getIndividuals().removeAll(
//				replacement.getIndividuals());
//		disposeKilledPopulation(killedPopulation);

		problem.setPopulation(replacement);
		/* Increment current generation counter. */
		this.currentGeneration++;

		return true;
	}

//	/**
//	 * Disposes killed population.
//	 * 
//	 * @param killedPopulation
//	 *            Killed population.
//	 */
//	private void disposeKilledPopulation(Population killedPopulation) {
//		for (A_Individual individual : killedPopulation.getIndividuals()) {
//			individual.dispose();
//		}
//	}

	/* Getters and Setters. */

	/**
	 * Gets maximum parents allowed.
	 * 
	 * @return Maximum parents allowed.
	 */
	public Long getMaxParents() {
		return maxParents;
	}

	/**
	 * Sets maximum parents allowed.
	 * 
	 * @param maxParents
	 *            Maximum parents allowed.
	 */
	public void setMaxParents(Long maxParents) {
		this.maxParents = maxParents;
	}

	/**
	 * Gets maximum generations.
	 * 
	 * @return Maximum generations.
	 */
	public Long getMaxGenerations() {
		return maxGenerations;
	}

	/**
	 * Sets maximum generations.
	 * 
	 * @param maxGenerations
	 */
	public void setMaxGenerations(Long maxGenerations) {
		this.maxGenerations = maxGenerations;
	}

	/**
	 * Gets the problem attached to the engine.
	 * 
	 * @return The problem attached to the engine.
	 */
	public AbstractProblem getProblem() {
		return problem;
	}

	/**
	 * Gets the current step population. This method must be called after
	 * calling step method.
	 * 
	 * @return The current step population.
	 */
	public Population getPopulation() {
		return problem.getPopulation();
	}

	/**
	 * Gets current generation.
	 * 
	 * @return Current generation.
	 */
	public Long getCurrentGeneration() {
		return currentGeneration;
	}
}