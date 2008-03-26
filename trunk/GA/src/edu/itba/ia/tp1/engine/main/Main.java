package edu.itba.ia.tp1.engine.main;

import edu.itba.ia.tp1.engine.AProblem;
import edu.itba.ia.tp1.engine.Engine;
import edu.itba.ia.tp1.engine.population.manager.EliteAlgorithmImpl;
import edu.itba.ia.tp1.engine.population.reproduction.CrossAlgorithmImpl;
import edu.itba.ia.tp1.problem.binary2bcd.Binary2BCDAptitudeImpl;
import edu.itba.ia.tp1.problem.binary2bcd.Binary2BCDProblem;

/**
 * Entry point to the application.
 * 
 * @author Pablo F. Siviero
 */
public class Main {

	public static void main(String args[]) {
		Engine engine = new Engine(200, 20);
		AProblem bin2Bcd = new Binary2BCDProblem(new EliteAlgorithmImpl(),
				new EliteAlgorithmImpl(), new CrossAlgorithmImpl(),
				new Binary2BCDAptitudeImpl());
		
		engine.run(bin2Bcd);
	}
}
