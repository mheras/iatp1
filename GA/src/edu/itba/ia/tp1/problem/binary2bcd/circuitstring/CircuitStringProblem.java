package edu.itba.ia.tp1.problem.binary2bcd.circuitstring;

import edu.itba.ia.tp1.engine.A_Problem;
import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.reproduction.ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.I_SelectionAlgorithm;

/**
 * Binary2BCD Problem that is solved by the GA Engine (CircuitString
 * implementation).
 * 
 * @author Martín A. Heras
 * 
 */
public class CircuitStringProblem extends A_Problem {

	/**
	 * Creates a new CircuitString implementation problem.
	 * 
	 * @param selection
	 *            Selection algorithm.
	 * @param replacement
	 *            Replacement algorithm.
	 * @param reproduction
	 *            Reproduction algorithm.
	 * @param aptitude
	 *            Aptitude function.
	 * @param populationSize
	 *            Population size.
	 */
	public CircuitStringProblem(I_SelectionAlgorithm selection,
			I_SelectionAlgorithm replacement,
			ReproductionAlgorithm reproduction, I_Aptitude aptitude,
			Long populationSize) {
		super(selection, replacement, reproduction, aptitude, populationSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.engine.A_Problem#initPopulation(java.lang.Long)
	 */
	protected Population initPopulation(Long populationSize) {

		Population population = new Population();

		for (long i = 0; i < populationSize; i++) {
			/* Generates a random circuit. */
			A_Individual circuit = CircuitString.generateRandomCircuit(4, 5,
					10, 40);
			/* Sets its own aptitude. */
			circuit.setAptitude(this.getAptitude().evaluate(circuit));

			/* Adds it to the population. */
			population.addIndividual(circuit);
		}

		return population;
	}
}
