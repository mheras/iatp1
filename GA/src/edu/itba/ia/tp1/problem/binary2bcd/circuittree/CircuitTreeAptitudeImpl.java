package edu.itba.ia.tp1.problem.binary2bcd.circuittree;

import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.problem.binary2bcd.AbstractAptitude;

/**
 * This is the aptitude function for a Binary to BCD Converter (CircuitTree
 * implementation).
 * 
 * @author Mart�n A. Heras
 */
public class CircuitTreeAptitudeImpl extends AbstractAptitude {

	/**
	 * Creates a new instance of <code>AptitudeImpl</code>.
	 */
	public CircuitTreeAptitudeImpl() {
		super();
	}

	/**
	 * Naif approach to get individual aptitude.
	 * 
	 * @deprecated
	 * @param individual
	 *            The individual.
	 * @return The individual aptitude.
	 */
	public Double evaluate2(A_Individual individual) {

		int size = this.inputOutputMap.size();
		Double aptitude = new Double(0);

		Double matches = 0.0;

		for (Integer input : this.inputOutputMap.keySet()) {

			if (individual.operate(input)
					.equals(this.inputOutputMap.get(input))) {

				matches++;
			}
		}

		aptitude = new Double(matches / size);

		return aptitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.engine.IAptitude#evaluate(edu.itba.ia.tp1.engine.population.Individual)
	 */
	public Double evaluate(A_Individual individual) {

		// Aptitude to return.
		Double aptitude = new Double(0.0);
		// IO map size.
		int size = this.inputOutputMap.size();
		// Number of output bits.
		int nOutputs = ((CircuitTree) individual).getOutputBits();
		// Holds the matches amount. If all output bits matches are solution for
		// the input given, this variable will be incremented by 1. Otherwise,
		// it will be incremented by a proportional amount, depending on how
		// many outputs matches the real solution.
		double totalMatchesAmount = 0.0;
		// Amount to be incremented when a single output bit matches.
		double bitMatchAmount = (1.0 / (nOutputs * size)) / 2;
		int realOutput;
		int output;

		for (Integer input : this.inputOutputMap.keySet()) {

			output = (Integer) individual.operate(input);
			realOutput = this.inputOutputMap.get(input).intValue();

			if (output == realOutput) {
				totalMatchesAmount++;
			} else {

				// Watches every bit in order to increment the aptitude.
				for (int i = 0; i < nOutputs; i++) {
					if ((output % 2) == 1 && (realOutput % 2) == 1) {
						totalMatchesAmount += bitMatchAmount;
					} else if ((output % 2) == 0 && (realOutput % 2) == 0) {
						totalMatchesAmount += bitMatchAmount;
					}
					output /= 2;
					realOutput /= 2;
				}
			}
		}

		aptitude = new Double(totalMatchesAmount / size);

		return aptitude;
	}
}
