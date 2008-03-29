package edu.itba.ia.tp1.problem.binary2bcd.circuit.component;

import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicNotReady;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicState;

public abstract class BinaryGate extends Gate {

	/* Inputs. */
	protected LogicState[] inputs = new LogicState[] { new LogicNotReady(),
			new LogicNotReady() };

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#isReady()
	 */
	@Override
	public boolean isReady() {
		return !(inputs[0].isNotReady()) && !(inputs[1].isNotReady());
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#resetComponent()
	 */
	@Override
	public void resetComponent() {
		inputs = new LogicState[] { new LogicNotReady(),
				new LogicNotReady() };
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent#setInput(int)
	 */
	@Override
	public void setInput(LogicState state) {
		if (inputs[0].isNotReady()) {
			inputs[0] = state;
		} else if (inputs[1].isNotReady()) {
			inputs[1] = state;
		}
	}

}
