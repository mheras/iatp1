package edu.itba.ia.tp1.ui.thread;

import edu.itba.ia.tp1.ui.alternative.thread.AlternativeExecutionThread;
import edu.itba.ia.tp1.ui.main.thread.MainExecutionThread;

/**
 * @author Mart�n A. Heras
 *
 */
public class ThreadsBag {
	
	private static ThreadsBag instance;
	
	private MainExecutionThread mainExecutionThread;
	private AlternativeExecutionThread alternativeExecutionThread;
	
	public MainExecutionThread getMainExecutionThread() {
		return mainExecutionThread;
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

	public AlternativeExecutionThread getAlternativeExecutionThread() {
		return alternativeExecutionThread;
	}

	public void setAlternativeExecutionThread(
			AlternativeExecutionThread alternativeExecutionThread) {
		this.alternativeExecutionThread = alternativeExecutionThread;
	}
}
