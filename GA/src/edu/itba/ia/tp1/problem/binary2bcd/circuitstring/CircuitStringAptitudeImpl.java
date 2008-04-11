package edu.itba.ia.tp1.problem.binary2bcd.circuitstring;

import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.problem.binary2bcd.AbstractAptitude;

/**
 * This is the aptitude function for a Binary to BCD Converter (CircuitString
 * implementation).
 * 
 * @author Martín A. Heras
 */
public class CircuitStringAptitudeImpl extends AbstractAptitude {
	
	/**
	 * Creates a new instance of <code>CircuitStringAptitudeImpl</code>.
	 */
	public CircuitStringAptitudeImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.engine.I_Aptitude#evaluate(edu.itba.ia.tp1.engine.population.A_Individual)
	 */
	public Double evaluate(A_Individual individual) {

		// Aptitude to return.
		Double aptitude = new Double(0.0);
		// IO map size.
		int size = this.inputOutputMap.size();
		// Number of output bits.
		int nOutputs = ((CircuitString) individual).getOutputBits();
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
