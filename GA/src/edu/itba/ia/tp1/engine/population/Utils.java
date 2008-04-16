package edu.itba.ia.tp1.engine.population;

import java.util.ArrayList;
import java.util.List;

import edu.itba.ia.tp1.problem.binary2bcd.AbstractCircuit;

/**
 * Utilities for population.
 * 
 * @author Martín A. Heras
 * 
 */
public class Utils {

	/**
	 * Gets the best circuit, depending on its aptitude and gates length.
	 * 
	 * @param population
	 *            The population.
	 * @return The best circuit.
	 */
	public static AbstractCircuit getBestCircuit(Population population) {
		AbstractCircuit best = null;

		for (AbstractIndividual individual : population.getIndividuals()) {
			AbstractCircuit current = (AbstractCircuit) individual;
			if (best == null) {
				best = current;
			} else {
				if (current.getAptitude().compareTo(best.getAptitude()) > 0) {
					// If it has better aptitude, it is the best.
					best = current;
				} else if (current.getAptitude().compareTo(best.getAptitude()) == 0) {
					// If it has the best one aptitude, checks if it has less
					// gates.
					if (current.getGatesLength() < best.getGatesLength()) {
						best = current;
					}
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

		for (AbstractIndividual individual : population.getIndividuals()) {
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

		for (AbstractIndividual individual : population.getIndividuals()) {
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

		for (AbstractIndividual individual : population.getIndividuals()) {
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
