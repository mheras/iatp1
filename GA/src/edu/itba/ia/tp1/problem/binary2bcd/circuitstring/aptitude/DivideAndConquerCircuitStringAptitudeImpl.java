package edu.itba.ia.tp1.problem.binary2bcd.circuitstring.aptitude;

import java.util.HashMap;

import edu.itba.ia.tp1.engine.population.AbstractIndividual;
import edu.itba.ia.tp1.problem.binary2bcd.AbstractAptitude;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.CircuitString;

/**
 * This is the aptitude function for a Binary to BCD Converter (CircuitString
 * implementation).
 * 
 * @author Martín A. Heras
 */
public class DivideAndConquerCircuitStringAptitudeImpl extends AbstractAptitude {
	
	/**
	 * Creates a new instance of <code>DivideAndConquerCircuitStringAptitudeImpl</code>.
	 */
	public DivideAndConquerCircuitStringAptitudeImpl(int bit) {
		this.initInputOutputMap(bit);
	}
	
	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.engine.I_Aptitude#evaluate(edu.itba.ia.tp1.engine.population.A_Individual)
	 */
	public Double evaluate(AbstractIndividual individual) {

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

		Long nGates = ((CircuitString) individual).getGatesLength();
		aptitude -= penalize(aptitude, nGates);

		return aptitude;
	}

	/**
	 * Penalize aptitude, depending on the number of gates.
	 * 
	 * @param aptitude
	 *            Aptitude to be penalized.
	 * @param nGates
	 *            Number of gates.
	 * @return Penalization.
	 */
	private Double penalize(Double aptitude, Long nGates) {

		Double penalization = 0.0;

		if (nGates > 1000) {
			penalization = aptitude * 0.5;
		} else if (nGates > 750) {
			penalization = aptitude * 0.4;
		} else if (nGates > 500) {
			penalization = aptitude * 0.3;
		} else if (nGates > 400) {
			penalization = aptitude * 0.25;
		} else if (nGates > 300) {
			penalization = aptitude * 0.2;
		} else if (nGates > 250) {
			penalization = aptitude * 0.15;
		} else if (nGates > 200) {
			penalization = aptitude * 0.1;
		} else if (nGates > 150) {
			penalization = aptitude * 0.05;
		} else if (nGates > 100) {
			penalization = aptitude * 0.025;
		} else if (nGates > 80) {
			penalization = aptitude * 0.02;
		} else if (nGates > 50) {
			penalization = aptitude * 0.015;
		} else if (nGates > 25) {
			penalization = aptitude * 0.007;
		}

		return penalization;
	}
	
	/**
	 * Initializes the input-output map.
	 */
	private void initInputOutputMap(int bit) {

		this.inputOutputMap = new HashMap<Integer, Integer>();

		if (bit == 0) {
			this.inputOutputMap.put(new Integer(0), new Integer(0));
			this.inputOutputMap.put(new Integer(1), new Integer(1));
			this.inputOutputMap.put(new Integer(2), new Integer(0));
			this.inputOutputMap.put(new Integer(3), new Integer(1));
			this.inputOutputMap.put(new Integer(4), new Integer(0));
			this.inputOutputMap.put(new Integer(5), new Integer(1));
			this.inputOutputMap.put(new Integer(6), new Integer(0));
			this.inputOutputMap.put(new Integer(7), new Integer(1));
			this.inputOutputMap.put(new Integer(8), new Integer(0));
			this.inputOutputMap.put(new Integer(9), new Integer(1));
			this.inputOutputMap.put(new Integer(10), new Integer(0));
			this.inputOutputMap.put(new Integer(11), new Integer(1));
			this.inputOutputMap.put(new Integer(12), new Integer(0));
			this.inputOutputMap.put(new Integer(13), new Integer(1));
			this.inputOutputMap.put(new Integer(14), new Integer(0));
			this.inputOutputMap.put(new Integer(15), new Integer(1));
		} else if (bit == 1) {
			this.inputOutputMap.put(new Integer(0), new Integer(0));
			this.inputOutputMap.put(new Integer(1), new Integer(0));
			this.inputOutputMap.put(new Integer(2), new Integer(1));
			this.inputOutputMap.put(new Integer(3), new Integer(1));
			this.inputOutputMap.put(new Integer(4), new Integer(0));
			this.inputOutputMap.put(new Integer(5), new Integer(0));
			this.inputOutputMap.put(new Integer(6), new Integer(1));
			this.inputOutputMap.put(new Integer(7), new Integer(1));
			this.inputOutputMap.put(new Integer(8), new Integer(0));
			this.inputOutputMap.put(new Integer(9), new Integer(0));
			this.inputOutputMap.put(new Integer(10), new Integer(0));
			this.inputOutputMap.put(new Integer(11), new Integer(0));
			this.inputOutputMap.put(new Integer(12), new Integer(1));
			this.inputOutputMap.put(new Integer(13), new Integer(1));
			this.inputOutputMap.put(new Integer(14), new Integer(0));
			this.inputOutputMap.put(new Integer(15), new Integer(0));
		} else if (bit == 2) {
			this.inputOutputMap.put(new Integer(0), new Integer(0));
			this.inputOutputMap.put(new Integer(1), new Integer(0));
			this.inputOutputMap.put(new Integer(2), new Integer(0));
			this.inputOutputMap.put(new Integer(3), new Integer(0));
			this.inputOutputMap.put(new Integer(4), new Integer(1));
			this.inputOutputMap.put(new Integer(5), new Integer(1));
			this.inputOutputMap.put(new Integer(6), new Integer(1));
			this.inputOutputMap.put(new Integer(7), new Integer(1));
			this.inputOutputMap.put(new Integer(8), new Integer(0));
			this.inputOutputMap.put(new Integer(9), new Integer(0));
			this.inputOutputMap.put(new Integer(10), new Integer(0));
			this.inputOutputMap.put(new Integer(11), new Integer(0));
			this.inputOutputMap.put(new Integer(12), new Integer(0));
			this.inputOutputMap.put(new Integer(13), new Integer(0));
			this.inputOutputMap.put(new Integer(14), new Integer(1));
			this.inputOutputMap.put(new Integer(15), new Integer(1));
		} else if (bit == 3) {
			this.inputOutputMap.put(new Integer(0), new Integer(0));
			this.inputOutputMap.put(new Integer(1), new Integer(0));
			this.inputOutputMap.put(new Integer(2), new Integer(0));
			this.inputOutputMap.put(new Integer(3), new Integer(0));
			this.inputOutputMap.put(new Integer(4), new Integer(0));
			this.inputOutputMap.put(new Integer(5), new Integer(0));
			this.inputOutputMap.put(new Integer(6), new Integer(0));
			this.inputOutputMap.put(new Integer(7), new Integer(0));
			this.inputOutputMap.put(new Integer(8), new Integer(1));
			this.inputOutputMap.put(new Integer(9), new Integer(1));
			this.inputOutputMap.put(new Integer(10), new Integer(0));
			this.inputOutputMap.put(new Integer(11), new Integer(0));
			this.inputOutputMap.put(new Integer(12), new Integer(0));
			this.inputOutputMap.put(new Integer(13), new Integer(0));
			this.inputOutputMap.put(new Integer(14), new Integer(0));
			this.inputOutputMap.put(new Integer(15), new Integer(0));
		} else {
			this.inputOutputMap.put(new Integer(0), new Integer(0));
			this.inputOutputMap.put(new Integer(1), new Integer(0));
			this.inputOutputMap.put(new Integer(2), new Integer(0));
			this.inputOutputMap.put(new Integer(3), new Integer(0));
			this.inputOutputMap.put(new Integer(4), new Integer(0));
			this.inputOutputMap.put(new Integer(5), new Integer(0));
			this.inputOutputMap.put(new Integer(6), new Integer(0));
			this.inputOutputMap.put(new Integer(7), new Integer(0));
			this.inputOutputMap.put(new Integer(8), new Integer(0));
			this.inputOutputMap.put(new Integer(9), new Integer(0));
			this.inputOutputMap.put(new Integer(10), new Integer(1));
			this.inputOutputMap.put(new Integer(11), new Integer(1));
			this.inputOutputMap.put(new Integer(12), new Integer(1));
			this.inputOutputMap.put(new Integer(13), new Integer(1));
			this.inputOutputMap.put(new Integer(14), new Integer(1));
			this.inputOutputMap.put(new Integer(15), new Integer(1));
		}
	}
}
