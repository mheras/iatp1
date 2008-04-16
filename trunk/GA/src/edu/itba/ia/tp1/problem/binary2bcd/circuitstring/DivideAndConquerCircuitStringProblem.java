package edu.itba.ia.tp1.problem.binary2bcd.circuitstring;

import edu.itba.ia.tp1.engine.AbstractProblem;
import edu.itba.ia.tp1.engine.IAptitude;
import edu.itba.ia.tp1.engine.population.AbstractIndividual;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.reproduction.ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.ISelectionAlgorithm;

/**
 * Binary2BCD Problem that is solved by the GA Engine (CircuitString
 * implementation).
 * 
 * @author Martín A. Heras
 * 
 */
public class DivideAndConquerCircuitStringProblem extends AbstractProblem {

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
	public DivideAndConquerCircuitStringProblem(ISelectionAlgorithm selection,
			ISelectionAlgorithm replacement,
			ReproductionAlgorithm reproduction, IAptitude aptitude,
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
			AbstractIndividual circuit = CircuitString.generateRandomCircuit(4, 1,
					10, 40);
			/* Sets its own aptitude. */
			circuit.setAptitude(this.getAptitude().evaluate(circuit));

			/* Adds it to the population. */
			population.addIndividual(circuit);
		}

		return population;
	}
}
