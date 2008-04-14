package edu.itba.ia.tp1.ui.main.thread;

/**
 * Callback used to notify engine's events information.
 * 
 * @author Martín A. Heras
 * 
 */
public interface IMainEngineInfo {

	/**
	 * Called on every engine step.
	 * 
	 * @param avgAptitude
	 *            Average aptitude.
	 * @param bestAptitude
	 *            Best aptitude.
	 * @param worstAptitude
	 *            Worst aptitude.
	 */
	public void onEngineStep(double avgAptitude, double bestAptitude,
			double worstAptitude);

	/**
	 * Called when initial population is calculated.
	 */
	public void onInitPopulationDone();
}
