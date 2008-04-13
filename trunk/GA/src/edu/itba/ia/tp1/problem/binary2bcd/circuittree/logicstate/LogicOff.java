package edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate;

/**
 * Logic Off, implementation of LogicState.
 * 
 * @author Martín A. Heras
 * 
 */
public class LogicOff extends LogicState {

	protected static LogicOff instance = null;
	
	public static LogicOff getInstance() {
		if (instance == null) {
			instance = new LogicOff();
		}
		
		return instance;
	}
	
	private LogicOff() {
		// Do nothing.
	}
	
	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState#isNotReady()
	 */
	public boolean isNotReady() {
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState#isOff()
	 */
	public boolean isOff() {
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState#isOn()
	 */
	public boolean isOn() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return (obj instanceof LogicOff);		
	}
}
