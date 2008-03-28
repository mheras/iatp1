package edu.itba.ia.tp1.main;

import edu.itba.ia.tp1.engine.A_Problem;
import edu.itba.ia.tp1.engine.Engine;
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
		Engine engine = new Engine(200L, 20L);
		A_Problem bin2Bcd = new Problem(new EliteImpl(),
				new EliteImpl(), new ReproductionAlgorithm(),
				new AptitudeImpl());
		
		engine.run(bin2Bcd);
	}
}
