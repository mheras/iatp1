/**
 * 
 */
package edu.itba.ia.tp1.engine;

import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.reproduction.I_ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.IPopulationAlgorithm;

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
public abstract class A_Problem {

	/* Population over which the GA performs selection and replacement. */
	private Population population;
	/* Selection algorithm. */
	private IPopulationAlgorithm selection;
	/* Replacement algorithm. */
	private IPopulationAlgorithm replacement;
	/* Reproduction algorithm. */
	private I_ReproductionAlgorithm reproduction;
	/* The aptitude function to evaluate an individual. */
	private I_Aptitude aptitude;

	public A_Problem(IPopulationAlgorithm selection,
			IPopulationAlgorithm replacement,
			I_ReproductionAlgorithm reproduction, I_Aptitude aptitude) {
		this.selection = selection;
		this.replacement = replacement;
		this.reproduction = reproduction;
		this.aptitude = aptitude;
		this.population = this.initPopulation();
	}

	/**
	 * Inheritance over AProblem should implement the way the population gets
	 * initially loaded.
	 * 
	 * @return an initialized Population
	 */
	protected abstract Population initPopulation();

	/* Getters and Setters. */
	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}

	public IPopulationAlgorithm getSelection() {
		return selection;
	}

	public void setSelection(IPopulationAlgorithm selection) {
		this.selection = selection;
	}

	public IPopulationAlgorithm getReplacement() {
		return replacement;
	}

	public void setReplacement(IPopulationAlgorithm replacement) {
		this.replacement = replacement;
	}

	public I_ReproductionAlgorithm getReproduction() {
		return reproduction;
	}

	public void setReproduction(I_ReproductionAlgorithm reproduction) {
		this.reproduction = reproduction;
	}

	public I_Aptitude getAptitude() {
		return aptitude;
	}

	public void setAptitude(I_Aptitude aptitude) {
		this.aptitude = aptitude;
	}
}
