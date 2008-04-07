package edu.itba.ia.tp1.ui;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import edu.itba.ia.tp1.engine.population.selection.EliteImpl;
import edu.itba.ia.tp1.engine.population.selection.EliteRouletteImpl;
import edu.itba.ia.tp1.engine.population.selection.EliteUniversalImpl;
import edu.itba.ia.tp1.engine.population.selection.I_SelectionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.RouletteImpl;
import edu.itba.ia.tp1.engine.population.selection.UniversalImpl;

/**
 * Combo box model for method selection algorithms.
 * 
 * @author Martín A. Heras
 * @since Apr 7, 2008
 * 
 */
public class MethodComboBoxModel extends DefaultComboBoxModel {

	private static final long serialVersionUID = 3876446600843158951L;

	private static Vector<I_SelectionAlgorithm> algorithms = new Vector<I_SelectionAlgorithm>();

	static {
		// Yes... We use a static block...
		algorithms.add(new EliteImpl());
		algorithms.add(new RouletteImpl());
		algorithms.add(new EliteRouletteImpl());
		algorithms.add(new UniversalImpl());
		algorithms.add(new EliteUniversalImpl());
	}

	/**
	 * Creates a new Method      
	 */
	public MethodComboBoxModel() {
		super(algorithms);
	}
}
