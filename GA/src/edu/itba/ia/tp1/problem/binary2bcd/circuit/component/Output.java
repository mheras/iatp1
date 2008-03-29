package edu.itba.ia.tp1.problem.binary2bcd.circuit.component;

import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicState;

/**
 * This class represents the output of a given circuit
 * @author Jorge Goldman
 * 
 */
public class Output extends Wire {

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#operate()
	 */
	@Override
	public void operate() {
		/* Do nothing :) */
	}
	
	/**
	 * Gets output logic state value.
	 * @return Output logic state value.
	 */
	public LogicState getOutputValue(){
		return input;
	}	
}
