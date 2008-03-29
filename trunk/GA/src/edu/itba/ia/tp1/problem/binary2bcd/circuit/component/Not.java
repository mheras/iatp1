package edu.itba.ia.tp1.problem.binary2bcd.circuit.component;

import java.util.List;

import edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicOff;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicOn;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Exceptions.InputNotReadyException;

/**
 * TODO: Comment
 * 
 * @author Mart�n A. Heras
 * 
 */
public class Not extends UnaryGate {

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#operate()
	 */
	public void operate() {
		
		List<CircuitComponent> nexts = this.getNextComponents();
		LogicState output;
		
		if (this.input.isNotReady()) {
			throw new InputNotReadyException();
		}
		
		if (this.input.isOff()) {
			output = new LogicOn();
		} else {
			output = new LogicOff();
		}

		for (CircuitComponent component : nexts) {
			component.setInput(output);
		}		
	}	
}
