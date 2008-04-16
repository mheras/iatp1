/**
 * 
 */
package edu.itba.ia.tp1.problem.binary2bcd.circuittree.algorithm;

import java.util.Random;

import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.reproduction.IGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.CircuitTree;

/**
 * Circuit tree implementation of Cross algorithm.
 * 
 * @author Jorge Goldman
 */
public class CircuitTreeCrossGeneticOperation implements IGeneticOperation {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.motor.reproduction.IReproductionAlgorithm#perform()
	 */
	public Population perform(Population parents) {

		// Por parametro llegan la poblacion de CircuitTree
		// correspondiente a los padres que deben ser cruzados. Este metodo debe
		// crear una nueva poblacion, producto de cruzar la poblacion de
		// CircuitTree padres que llegan por parametro.

		Population children = new Population();
		Random rand = new Random();
		int position;

		while (parents.getSize() > 0) {
			if (parents.getSize() >= 2) {
				// Selects parents randomly.
				position = rand.nextInt(parents.getSize());
				CircuitTree parent1 = (CircuitTree) parents
						.getIndividualByPosition(position);
				parents.removeByPosition(position);
				position = rand.nextInt(parents.getSize());
				CircuitTree parent2 = (CircuitTree) parents
						.getIndividualByPosition(position);
				parents.removeByPosition(position);
				// Let's them have sex and get children.
				CircuitTree child1 = parent1.clone();
				CircuitTree child2 = parent2.clone();
				CircuitTree.performCrossover(child1, child2);
				children.addIndividual(child1);
				children.addIndividual(child2);
			} else if (parents.getSize() == 1) {
				// Rare case...
				CircuitTree hermaphroditeParent = (CircuitTree) parents
						.getIndividualByPosition(0);
				parents.removeByPosition(0);
				CircuitTree rareChild = hermaphroditeParent.clone();
				children.addIndividual(rareChild);
			}
		}

		return children;
	}

}
