package edu.itba.ia.tp1.main;

import edu.itba.ia.tp1.engine.A_Problem;
import edu.itba.ia.tp1.engine.Engine;
import edu.itba.ia.tp1.engine.population.reproduction.CrossImpl;
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
		Engine engine = new Engine(200, 20);
		A_Problem bin2Bcd = new Problem(new EliteImpl(),
				new EliteImpl(), new CrossImpl(),
				new AptitudeImpl());
		
		engine.run(bin2Bcd);
	}
}
