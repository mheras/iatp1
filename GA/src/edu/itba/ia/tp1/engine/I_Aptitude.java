package edu.itba.ia.tp1.engine;

import edu.itba.ia.tp1.engine.population.A_Individual;

/**
 * Implementations of IAptitude should provide the way to analyze how adapted is
 * an Individual to the Population.
 * 
 * @author Pablo F. Siviero
 */
public interface I_Aptitude {

	/**
	 * Evaluates an Individual.
	 * 
	 * @param x The individual to analyze
	 * @return a number between 0 and 1 representing how adapted it is.
	 */
	public Double evaluate(A_Individual x);
}
