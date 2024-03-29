package edu.itba.ia.tp1.ui.thread;

import edu.itba.ia.tp1.problem.binary2bcd.AbstractCircuit;


/**
 * Callback used to notify that the execution thread is done with his job.
 * 
 * @author Mart�n A. Heras
 * 
 */
public interface IExecutionThreadDone {

	/**
	 * This method will be called once the active execution thread is done.
	 */
	public void onExecutionThreadDone(AbstractCircuit bestCircuit);
}
