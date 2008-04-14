package edu.itba.ia.tp1.engine.population;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilities for population.
 * 
 * @author Mart�n A. Heras
 * 
 */
public class Utils {

	/**
	 * Gets the best individual.
	 * 
	 * @param population The population.
	 * @return The best individual.
	 */
	public static A_Individual getBestIndividual(Population population) {
		A_Individual best = null;

		for (A_Individual individual : population.getIndividuals()) {
			if (best == null) {
				best = individual;
			} else {
				if (individual.getAptitude().compareTo(best.getAptitude()) >= 0) {
					best = individual;
				}
			}
		}
		
		return best;
	}

	/**
	 * Gets the aptidude average over the population.
	 * 
	 * @param population
	 *            The population.
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
	 * 
	 * @param population
	 *            The population.
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
	 * 
	 * @param population
	 *            The population.
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

	/**
	 * Returns the cumulative frequency for a whole population.
	 * 
	 * @param population
	 *            The population.
	 * @return A list containing the cumulative frequency for every individual,
	 *         in the same order as they have in the population.
	 */
	public static List<Double> getCumulativeFrequencies(Population population) {
		List<Double> relativeFrequencies = getRelativeFrequencies(population);
		List<Double> cumulativeFrequencies = new ArrayList<Double>();
		Double step = 0.0;

		for (int i = 0; i < population.getSize(); i++) {
			step += relativeFrequencies.get(i);
			cumulativeFrequencies.add(new Double(step));
		}

		return cumulativeFrequencies;
	}

	/**
	 * Returns the relative frequency for a whole population.
	 * 
	 * @param population
	 *            The population.
	 * @return A list containing the relative frequency for every individual, in
	 *         the same order as they have in the population.
	 */
	public static List<Double> getRelativeFrequencies(Population population) {
		List<Double> relativeFrequencies = new ArrayList<Double>();
		Double accumulatedAptitude = getAccumulatedAptitude(population);

		for (int i = 0; i < population.getSize(); i++) {
			relativeFrequencies.add(new Double(population
					.getIndividualByPosition(i).getAptitude()
					/ accumulatedAptitude));
		}

		return relativeFrequencies;
	}

	/**
	 * Returns the accumulated aptitude of a whole population.
	 * 
	 * @param population
	 *            The population.
	 * @return The accumulated aptitude.
	 */
	public static Double getAccumulatedAptitude(Population population) {
		Double ret = 0.0;

		for (int i = 0; i < population.getSize(); i++) {
			ret += population.getIndividualByPosition(i).getAptitude();
		}

		return ret;
	}
}
