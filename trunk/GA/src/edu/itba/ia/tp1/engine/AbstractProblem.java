/**
 * 
 */
package edu.itba.ia.tp1.engine;

import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.reproduction.ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.ISelectionAlgorithm;

/**
 * The GA Engine solves a Problem, a child of AProblem. The problem is applied
 * over a population, after selection, reproduction and replacement methods;
 * under the aptitude function that determines how adapted is the Individual to
 * the Population. 
 * 
 * The only thing AProblem doesn't know, is how to provide an
 * initial Population over which the GA is run.
 * 
 * @author Pablo F. Siviero
 */
public abstract class AbstractProblem {

	/* Population over which the GA performs selection and replacement. */
	private Population population;
	/* Selection algorithm. */
	private ISelectionAlgorithm selection;
	/* Replacement algorithm. */
	private ISelectionAlgorithm replacement;
	/* Reproduction algorithm. */
	private ReproductionAlgorithm reproduction;
	/* The aptitude function to evaluate an individual. */
	private IAptitude aptitude;
	/* Population size. */
	private Long populationSize;

	/**
	 * Creates a new problem.
	 * 
	 * @param selection Selection algorithm.
	 * @param replacement Replacemente algorithm.
	 * @param reproduction Reproduction algorithm.
	 * @param aptitude Aptitude function.
	 * @param populationSize Fixed population size.
	 */
	public AbstractProblem(ISelectionAlgorithm selection,
			ISelectionAlgorithm replacement,
			ReproductionAlgorithm reproduction, IAptitude aptitude, Long populationSize) {
		this.selection = selection;
		this.replacement = replacement;
		this.reproduction = reproduction;
		this.aptitude = aptitude;
		this.populationSize = populationSize;
		this.population = this.initPopulation(populationSize);
	}

	/**
	 * Inheritance over AProblem should implement the way the population gets
	 * initially loaded.
	 * 
	 * @param populationSize Population size.
	 * @return an initialized Population
	 */
	protected abstract Population initPopulation(Long populationSize);

	/* Getters and Setters. */

	/**
	 * Gets the population.
	 * @return The population.
	 */
	public Population getPopulation() {
		return population;
	}

	/**
	 * Sets the population.
	 * @param population The population.
	 */
	public void setPopulation(Population population) {
		this.population = population;
	}

	/**
	 * Gets the selection algorithm.
	 * @return The selection algorithm.
	 */
	public ISelectionAlgorithm getSelection() {
		return selection;
	}

	/**
	 * Sets the selection algorithm.
	 * @param selection The selection algorithm.
	 */
	public void setSelection(ISelectionAlgorithm selection) {
		this.selection = selection;
	}

	/**
	 * Gets the replacement algorithm.
	 * @return The replacement algorithm.
	 */
	public ISelectionAlgorithm getReplacement() {
		return replacement;
	}

	/**
	 * Sets the replacement algorithm.
	 * @param replacement The replacement algorithm.
	 */
	public void setReplacement(ISelectionAlgorithm replacement) {
		this.replacement = replacement;
	}

	/**
	 * Gets the reproduction algorithm.
	 * @return The reproduction algorithm.
	 */
	public ReproductionAlgorithm getReproduction() {
		return reproduction;
	}

	/**
	 * Sets the reproduction algorithm.
	 * @param reproduction The reproduction algorithm.
	 */
	public void setReproduction(ReproductionAlgorithm reproduction) {
		this.reproduction = reproduction;
	}

	/**
	 * Gets the aptitude function.
	 * @return The aptitude function.
	 */
	public IAptitude getAptitude() {
		return aptitude;
	}

	/**
	 * Sets the aptitude function.
	 * @param aptitude The aptitude function.
	 */
	public void setAptitude(IAptitude aptitude) {
		this.aptitude = aptitude;
	}

	/**
	 * Gets the population size.
	 * @return The population size.
	 */
	public Long getPopulationSize() {
		return populationSize;
	}

	/**
	 * Sets the population size.
	 * @param populationSize The population size.
	 */
	public void setPopulationSize(Long populationSize) {
		this.populationSize = populationSize;
	}
}
