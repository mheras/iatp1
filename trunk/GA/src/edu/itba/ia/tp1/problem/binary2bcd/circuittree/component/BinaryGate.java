package edu.itba.ia.tp1.problem.binary2bcd.circuittree.component;

import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicNotReady;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicState;

/**
 * This class represents a Binary Gate
 * 
 * @author Jorge Goldman
 *
 */
public abstract class BinaryGate extends Gate {

	
	/* Sons of this Component. */
	private CircuitComponent [] sons = new CircuitComponent [] {null, null};
	
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
	/* Getters and Setters. */
	
	public CircuitComponent[] getSons() {
		return sons;
	}

	public void setSons(CircuitComponent[] sons) {
		this.sons = sons;
	}
	public void setLeftSon(CircuitComponent component){
		this.sons[0] = component;
	}
	public void setRightSon(CircuitComponent component){
		this.sons[1] = component;
	}
	
	public CircuitComponent getRightSon(){
		return this.sons[1];
	}
	
	public CircuitComponent getLeftSon(){
		return this.sons[0];
	}
}
