package edu.itba.ia.tp1.problem.binary2bcd.circuittree;

import java.util.HashMap;
import java.util.Map;
import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.A_Individual;

/**
 * This is the aptitude function for a Binary to BCD Converter.
 * 
 * @author Martín A. Heras
 */
public class CircuitTreeAptitudeImpl implements I_Aptitude {

	private Map<Integer, Integer> inputOutputMap;

	/**
	 * Creates a new instance of <code>AptitudeImpl</code>.
	 */
	public CircuitTreeAptitudeImpl() {
		this.initInputOutputMap();
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

	/**
	 * Initializes the input-output map.
	 */
	private void initInputOutputMap() {

		this.inputOutputMap = new HashMap<Integer, Integer>();

		this.inputOutputMap.put(new Integer(0), new Integer(0));
		this.inputOutputMap.put(new Integer(1), new Integer(1));
		this.inputOutputMap.put(new Integer(2), new Integer(2));
		this.inputOutputMap.put(new Integer(3), new Integer(3));
		this.inputOutputMap.put(new Integer(4), new Integer(4));
		this.inputOutputMap.put(new Integer(5), new Integer(5));
		this.inputOutputMap.put(new Integer(6), new Integer(6));
		this.inputOutputMap.put(new Integer(7), new Integer(7));
		this.inputOutputMap.put(new Integer(8), new Integer(8));
		this.inputOutputMap.put(new Integer(9), new Integer(9));
		this.inputOutputMap.put(new Integer(10), new Integer(16));
		this.inputOutputMap.put(new Integer(11), new Integer(17));
		this.inputOutputMap.put(new Integer(12), new Integer(18));
		this.inputOutputMap.put(new Integer(13), new Integer(19));
		this.inputOutputMap.put(new Integer(14), new Integer(20));
		this.inputOutputMap.put(new Integer(15), new Integer(21));
	}

}
