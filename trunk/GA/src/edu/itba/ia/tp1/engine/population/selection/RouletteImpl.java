package edu.itba.ia.tp1.engine.population.selection;

import java.util.List;
import java.util.Random;

import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.Utils;

/**
 * Implementation of Roulette Algorithm
 * 
 * @author Pablo F. Siviero
 */
public class RouletteImpl implements I_SelectionAlgorithm {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.motor.population.IPopulationAlgorithm#execute()
	 */
	public Population execute(Population population, Long nIndividuals) {
		Random random = new Random();
		Population ret = new Population();
		List<Double> cumulativeFrequencies = Utils.getCumulativeFrequencies(population);
		/* Add 0.0 in the first position to enable the first individual to be picked */
		cumulativeFrequencies.add(0, 0.0);
		
		for (int i = 0; i < nIndividuals; i++) {
			Double randomNumber = random.nextDouble();
			for (int j = 1; j < cumulativeFrequencies.size(); j++) {
				Double cumFreq1 = cumulativeFrequencies.get(j - 1);
				Double cumFreq2 = cumulativeFrequencies.get(j);
				Boolean capable = ((cumFreq1.compareTo(randomNumber)) < 0 &&
								   (randomNumber.compareTo(cumFreq2) < 0));
				if (capable) {
					ret.addIndividual(population.getIndividualByPosition(j - 1));
				}
			} 
		}

		return ret;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Roulette";
	}
}