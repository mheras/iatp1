package edu.itba.ia.tp1.problem.binary2bcd.circuit.component;

import java.awt.Component;
import java.util.List;

import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.exceptions.InputNotReadyException;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicOff;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicOn;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicState;

/**
 * This class represents a Not Gate.
 * 
 * @author Martín A. Heras
 * 
 */
public class Not extends UnaryGate {

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#operate()
	 */
	public void operate() {
		
		LogicState output;
		
		if (this.input.isNotReady()) {
			throw new InputNotReadyException();
		}
		
		if (this.input.isOff()) {
			output = new LogicOn();
		} else {
			output = new LogicOff();
		}

		CircuitComponent component = this.getFather();
		component.setInput(output);
		
	}	
	
	public String getOperationString(){
		return "¬";
	}
}
