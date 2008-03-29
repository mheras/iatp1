package edu.itba.ia.tp1.problem.binary2bcd.circuit.component;

import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicNotReady;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicState;

/**
 * TODO: Comment
 * 
 * @author Martín A. Heras
 * 
 */
public abstract class UnaryGate extends Gate {

	/* Input. */
	protected LogicState input = new LogicNotReady();

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
		input = new LogicNotReady();
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
