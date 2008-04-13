package edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate;

/**
 * Logic On, implementation of LogicState.
 * 
 * @author Martín A. Heras
 * 
 */
public class LogicOn extends LogicState {

	protected static LogicOn instance = null;
	
	public static LogicOn getInstance() {
		if (instance == null) {
			instance = new LogicOn();
		}
		
		return instance;
	}
	
	private LogicOn() {
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
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState#isOn()
	 */
	public boolean isOn() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return (obj instanceof LogicOn);		
	}
}
