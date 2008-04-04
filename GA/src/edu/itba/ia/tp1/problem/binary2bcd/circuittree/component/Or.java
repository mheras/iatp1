package edu.itba.ia.tp1.problem.binary2bcd.circuittree.component;

import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.exception.InputNotReadyException;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicOff;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicOn;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicState;

/**
 * This class represents a Not Gate.
 * 
 * @author Martín A. Heras
 * 
 */
public class Or extends BinaryGate {

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#operate()
	 */
	public void operate() {
		
		LogicState output;
		
		if (this.inputs[0].isNotReady() || this.inputs[1].isNotReady()) {
			throw new InputNotReadyException();
		}
		
		if (this.inputs[0].isOn() || this.inputs[1].isOn()) {
			output = new LogicOn();
		} else {
			output = new LogicOff();
		}

		CircuitComponent component = this.setParent();
		component.setInput(output);
	}
	public String getOperationString(){
		return "|";
	}
}
