package edu.itba.ia.tp1.problem.binary2bcd.circuit.component;

import edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState;

/**
 * TODO: Comment 
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
