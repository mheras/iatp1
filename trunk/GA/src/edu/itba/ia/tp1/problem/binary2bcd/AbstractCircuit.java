package edu.itba.ia.tp1.problem.binary2bcd;

import edu.itba.ia.tp1.engine.population.AbstractIndividual;

/**
 * Abstract circuit. This is the superclass of all circuit implementations.
 * 
 * @author Martín A. Heras
 *
 */
public abstract class AbstractCircuit extends AbstractIndividual {

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.engine.population.A_Individual#dispose()
	 */
	public abstract void dispose();
	
	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.engine.population.A_Individual#operate(java.lang.Object)
	 */
	public abstract Object operate(Object input);
	
	/**
	 * Gets the gates length.
	 * 
	 * @return The gates length.
	 */
	public abstract Long getGatesLength();
}
