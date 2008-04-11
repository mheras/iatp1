package edu.itba.ia.tp1.problem.binary2bcd;

import java.util.HashMap;
import java.util.Map;

import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.A_Individual;

/**
 * Abstract functionality of all aptitude functions.
 * 
 * @author Martín A. Heras
 *
 */
public abstract class AbstractAptitude implements I_Aptitude {

	/* Input-Output map. */
	protected Map<Integer, Integer> inputOutputMap;

	/**
	 * Constructor.
	 */
	public AbstractAptitude() {
		this.initInputOutputMap();
	}
	
	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.engine.I_Aptitude#evaluate(edu.itba.ia.tp1.engine.population.A_Individual)
	 */
	public abstract Double evaluate(A_Individual x);

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
