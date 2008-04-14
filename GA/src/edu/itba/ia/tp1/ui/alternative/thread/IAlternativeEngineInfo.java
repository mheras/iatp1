package edu.itba.ia.tp1.ui.alternative.thread;

import edu.itba.ia.tp1.engine.Engine;

/**
 * Callback used to notify engine's events information.
 * 
 * @author Martín A. Heras
 * 
 */
public interface IAlternativeEngineInfo {

	/**
	 * Called on every engine step.
	 * 
	 * @param source The engine.
	 * @param avgAptitude
	 *            Average aptitude.
	 * @param bestAptitude
	 *            Best aptitude.
	 * @param worstAptitude
	 *            Worst aptitude.
	 *          
	 */
	public void onEngineStep(Engine source, double avgAptitude, double bestAptitude,
			double worstAptitude);

	/**
	 * Called when initial population is calculated.
	 * 
	 * @param source Engine with its initial population already calculated.
	 */
	public void onInitPopulationDone(Engine source);
}
