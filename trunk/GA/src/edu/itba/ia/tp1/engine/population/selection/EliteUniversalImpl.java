package edu.itba.ia.tp1.engine.population.selection;

import java.util.List;

import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.Utils;

/**
 * Implementation of EliteUniversal Algorithm; in which Elite is applied first,
 * and regarding the amount of Individuals retrieved, the rest of the population
 * is calculated by Universal.
 * 
 * @author Pablo F. Siviero
 */
public class EliteUniversalImpl implements I_SelectionAlgorithm {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.engine.population.manager.IPopulationAlgorithm#execute(edu.itba.ia.tp1.engine.population.Population)
	 */
	public Population execute(Population population, Long nIndividuals) {
		Population ret = new Population();
		UniversalImpl universal = new UniversalImpl();
		List<Double> relativeFrequencies = Utils.getRelativeFrequencies(population);
		
		for (int i = 0; i < relativeFrequencies.size(); i++) {
			int nSelected = (int) Math.floor(relativeFrequencies.get(i) * nIndividuals);
			
			while (nSelected-- != 0) {
				ret.addIndividual(population.getIndividualByPosition(i));
			}
		}
		
		/* Si todavia no se completo la poblacion, se obtiene por ruleta el resto. */
		if (ret.getSize() < nIndividuals.intValue()) {
			Population roulettePopulation = universal.execute(population, new Long(nIndividuals.intValue() - ret.getSize()));
			ret.addAll(roulettePopulation.getIndividuals());
		}
		
		return ret;
	}

}
