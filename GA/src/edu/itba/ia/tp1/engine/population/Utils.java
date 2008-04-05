package edu.itba.ia.tp1.engine.population;

/**
 * Utilities for population.
 * 
 * @author Martín A. Heras
 * 
 */
public class Utils {

	/**
	 * Gets the aptidude average over the population.
	 * 
	 * @param population The population.
	 * @return The aptidude average over the population.
	 */
	public static Double getAptitudeAvg(Population population) {

		Double sum = 0.0;

		for (A_Individual individual : population.getIndividuals()) {
			sum += individual.getAptitude();
		}

		return sum / population.getSize();
	}
	
	/**
	 * Gets the best aptitude over the population.
	 * @param population The population.
	 * @return The best aptitude over the population.
	 */
	public static Double getBestAptitude(Population population) {

		Double best = 0.0;
		Double currentApt;
		
		for (A_Individual individual : population.getIndividuals()) {
			currentApt = individual.getAptitude();
			if (currentApt.compareTo(best) > 0) {
				best = currentApt;
			}
		}
		
		return best;
	}
	
	/**
	 * Gets the worst aptitude over the population.
	 * @param population The population.
	 * @return The worst aptitude over the population.
	 */
	public static Double getWorstAptitude(Population population) {

		Double worst = 1.0;
		Double currentApt;
		
		for (A_Individual individual : population.getIndividuals()) {
			currentApt = individual.getAptitude();
			if (currentApt.compareTo(worst) < 0) {
				worst = currentApt;
			}
		}
		
		return worst;
	}
}
