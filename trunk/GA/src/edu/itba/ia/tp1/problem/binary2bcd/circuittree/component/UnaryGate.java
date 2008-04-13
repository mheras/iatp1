package edu.itba.ia.tp1.problem.binary2bcd.circuittree.component;

import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicNotReady;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicState;

/**
 * This class represents a Binary Gate
 * 
 * @author Martín A. Heras
 * 
 */
/**
 * @author Martín A. Heras
 *
 */
public abstract class UnaryGate extends Gate {

	/* Son component of this component. */
	private CircuitComponent son = null;
	
	
	/* Input. */
	protected LogicState input = LogicNotReady.getInstance();

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.Gate#dispose()
	 */
	public void dispose() {
		super.dispose();
		this.son = null;
		this.input = null;
	}
	
	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#isReady()
	 */
	public boolean isReady() {
		return !input.isNotReady();
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#resetComponent()
	 */
	public void resetComponent() {
		input = LogicNotReady.getInstance();
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#setInput(int)
	 */
	public void setInput(LogicState state) {
		if (input.isNotReady()) {
			input = state;
		}
	}

	public CircuitComponent getSon() {
		return son;
	}

	public void setSon(CircuitComponent son) {
		this.son = son;
	}
}
