package edu.itba.ia.tp1.problem.binary2bcd.circuittree.component;

import java.util.List;

import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.exception.InputNotReadyException;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicOff;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicOn;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicState;

/**
 * This class represents the input of a given circuit
 * @author Martín A. Heras
 * 
 */
public class Input extends Wire {

	/**
	 * Gets input logic state value.
	 * @return Input logic state value.
	 */
	public LogicState getInputValue(){
		return input;
	}	
	
	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#operate()
	 */
	public void operate() {
		
		List<CircuitComponent> nexts = this.getNextComponents();
		LogicState output;
		
		if (this.input.isNotReady()) {
			throw new InputNotReadyException();
		}
		
		if (this.input.isOn()) {
			output = LogicOn.getInstance();
		} else {
			output = LogicOff.getInstance();
		}

		for (CircuitComponent component : nexts) {
			component.setInput(output);
		}	
	}
}
