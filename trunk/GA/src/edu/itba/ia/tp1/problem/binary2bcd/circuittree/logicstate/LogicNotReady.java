package edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate;

/**
 * Logic Not Ready, implementation of LogicState.
 * 
 * @author Martín A. Heras
 * 
 */
public class LogicNotReady extends LogicState {

	protected static LogicNotReady instance = null;
	
	public static LogicNotReady getInstance() {
		if (instance == null) {
			instance = new LogicNotReady();
		}
		
		return instance;
	}
	
	private LogicNotReady() {
		// Do nothing.
	}
	
	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState#isNotReady()
	 */
	public boolean isNotReady() {
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState#isOff()
	 */
	public boolean isOff() {
		return false;
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
		return (obj instanceof LogicNotReady);		
	}
}
