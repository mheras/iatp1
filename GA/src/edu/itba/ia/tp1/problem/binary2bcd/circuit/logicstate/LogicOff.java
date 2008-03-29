package edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate;

/**
 * Logic Off, implementation of LogicState.
 * 
 * @author Martín A. Heras
 * 
 */
public class LogicOff extends LogicState {

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState#isNotReady()
	 */
	@Override
	public boolean isNotReady() {
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState#isOff()
	 */
	@Override
	public boolean isOff() {
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState#isOn()
	 */
	@Override
	public boolean isOn() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof LogicOff);		
	}
}
