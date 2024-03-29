package edu.itba.ia.tp1.problem.binary2bcd.circuittree.component;

import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicNotReady;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicState;

/**
 * This class represents a wire, used to generate the 
 * inputs and outputs of a circuit.
 * 
 * @author Jorge Goldman
 *
 */
public abstract class Wire extends CircuitComponent {

	/* Input. */
	protected LogicState input = LogicNotReady.getInstance();

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#isReady()
	 */
	@Override
	public boolean isReady() {
		return !input.isNotReady();
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#resetComponent()
	 */
	@Override
	public void resetComponent() {
		input = LogicNotReady.getInstance();
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#setInput(int)
	 */
	@Override
	public void setInput(LogicState state) {
		if (input.isNotReady()) {
			input = state;
		}
	}
}
