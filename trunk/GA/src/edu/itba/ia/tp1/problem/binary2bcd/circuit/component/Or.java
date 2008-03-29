package edu.itba.ia.tp1.problem.binary2bcd.circuit.component;

import java.util.List;

import edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicOff;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicOn;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Exceptions.InputNotReadyException;

/**
 * TODO: Comment
 * 
 * @author Martín A. Heras
 * 
 */
public class Or extends BinaryGate {

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#operate()
	 */
	public void operate() {
		
		List<CircuitComponent> nexts = this.getNextComponents();
		LogicState output;
		
		if (this.inputs[0].isNotReady() || this.inputs[1].isNotReady()) {
			throw new InputNotReadyException();
		}
		
		if (this.inputs[0].isOn() || this.inputs[1].isOn()) {
			output = new LogicOn();
		} else {
			output = new LogicOff();
		}

		for (CircuitComponent component : nexts) {
			component.setInput(output);
		}		
	}
}
