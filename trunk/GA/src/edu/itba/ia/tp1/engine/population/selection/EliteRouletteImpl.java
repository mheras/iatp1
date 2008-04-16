package edu.itba.ia.tp1.engine.population.selection;

import java.util.Random;

import edu.itba.ia.tp1.engine.population.AbstractIndividual;
import edu.itba.ia.tp1.engine.population.Population;

/**
 * Implementation of EliteRoulette Algorithm; in which Elite is applied first,
 * and regarding the amount of Individuals retrieved, the rest of the population
 * is calculated by Roulette.
 * 
 * @author Pablo F. Siviero
 */
public class EliteRouletteImpl implements ISelectionAlgorithm {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.engine.population.manager.IPopulationAlgorithm#execute(edu.itba.ia.tp1.engine.population.Population)
	 */
	public Population execute(Population population, Long nIndividuals) {
		Population ret = new Population();
		Population shallowCopy = new Population();

		shallowCopy.addAll(population.getIndividuals());
		Random random = new Random();
		Long eliteCount = (long) Math.floor(random.nextDouble() * nIndividuals);

		EliteImpl elite = new EliteImpl();
		Population elitePopulation = elite.execute(shallowCopy, eliteCount);
		ret.addAll(elitePopulation.getIndividuals());
		shallowCopy.getIndividuals()
				.removeAll(elitePopulation.getIndividuals());

		RouletteImpl roulette = new RouletteImpl();

		// List<Double> relativeFrequencies =
		// Utils.getRelativeFrequencies(population);
		//		
		// for (int i = 0; i < relativeFrequencies.size(); i++) {
		// int nSelected = (int) Math.floor(relativeFrequencies.get(i) *
		// nIndividuals);
		//			
		// while (nSelected-- != 0) {
		// ret.addIndividual(population.getIndividualByPosition(i));
		// }
		// }

		/*
		 * Si todavia no se completo la poblacion, se obtiene por ruleta el
		 * resto.
		 */
		if (ret.getSize() < nIndividuals.longValue()) {
			Population roulettePopulation = roulette.execute(shallowCopy,
					new Long(nIndividuals.intValue() - ret.getSize()));
			for (AbstractIndividual individual : roulettePopulation
					.getIndividuals()) {
				if (elitePopulation.getIndividuals().contains(individual)) {
					ret.addIndividual(individual.clone());
				} else {
					ret.addIndividual(individual);
				}
			}

		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Elite Roulette";
	}

}
