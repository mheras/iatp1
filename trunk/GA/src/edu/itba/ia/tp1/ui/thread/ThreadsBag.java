package edu.itba.ia.tp1.ui.thread;

/**
 * @author Martín A. Heras
 *
 */
public class ThreadsBag {
	
	private static ThreadsBag instance;
	
	private ExecutionThread executionThread;
	
	public ExecutionThread getExecutionThread() {
		return executionThread;
	}

	public void setExecutionThread(ExecutionThread executionThread) {
		this.executionThread = executionThread;
	}

	private ThreadsBag() {
		// Do nothing (required by getInstance method).
	}
	
	public static ThreadsBag getInstance() {
		if (instance == null) {
			instance = new ThreadsBag();
		}
		return instance;
	}
}
