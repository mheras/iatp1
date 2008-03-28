package edu.itba.ia.tp1.main;

import edu.itba.ia.tp1.engine.A_Problem;
import edu.itba.ia.tp1.engine.Engine;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.reproduction.ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.EliteImpl;
import edu.itba.ia.tp1.problem.binary2bcd.AptitudeImpl;
import edu.itba.ia.tp1.problem.binary2bcd.Problem;

/**
 * Entry point to the application.
 * 
 * @author Pablo F. Siviero
 */
public class Main {

	public static void main(String args[]) {

		A_Problem bin2Bcd = new Problem(new EliteImpl(), new EliteImpl(),
				new ReproductionAlgorithm(), new AptitudeImpl());
		Engine engine = new Engine(bin2Bcd, 200L, 20L);

		/* Temporal annotation. */
		@SuppressWarnings("unused")
		Population currentPopulation;

		while (engine.step()) {
			currentPopulation = engine.getPopulation();
			/*
			 * Do something with this population (i.e. make statistics to show
			 * them in a chart).
			 */
		}
	}
}
