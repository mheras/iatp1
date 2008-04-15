package edu.itba.ia.tp1.ui.thread;

import edu.itba.ia.tp1.ui.divideandconquer.thread.DivideAndConquerExecutionThread;
import edu.itba.ia.tp1.ui.main.thread.MainExecutionThread;

/**
 * @author Martín A. Heras
 *
 */
public class ThreadsBag {
	
	private static ThreadsBag instance;
	
	private MainExecutionThread mainExecutionThread;
	private DivideAndConquerExecutionThread divideAndConquerExecutionThread;
	
	public MainExecutionThread getMainExecutionThread() {
		return mainExecutionThread;
	}

	public DivideAndConquerExecutionThread getDivideAndConquerExecutionThread() {
		return divideAndConquerExecutionThread;
	}

	public void setDivideAndConquerExecutionThread(
			DivideAndConquerExecutionThread divideAndConquerExecutionThread) {
		this.divideAndConquerExecutionThread = divideAndConquerExecutionThread;
	}

	public void setMainExecutionThread(MainExecutionThread executionThread) {
		this.mainExecutionThread = executionThread;
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
