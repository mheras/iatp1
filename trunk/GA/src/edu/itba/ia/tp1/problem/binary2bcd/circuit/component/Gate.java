package edu.itba.ia.tp1.problem.binary2bcd.circuit.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a gate. A gate is a Circuit component that has a logic operation that
 * could be performed to obtain an output of a given input.
 *
 * @author Jorge Goldman
 * 
 */
public abstract class Gate extends CircuitComponent{
		
	/**
	 * This method generates a random gate and returns its instance.
	 * 
	 * @return Gate
	 * 			An instance of a randomly generated gate.
	 */		
	public static Gate randomGate() {
		
		Gate gate;
		
		List<String> subclasses = new ArrayList<String>();
		/* The following is hardcoded. */
		subclasses.add("edu.itba.ia.tp1.problem.binary2bcd.circuit.component.And");
		subclasses.add("edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Or");
		subclasses.add("edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Not");

		Random rand = new Random();
		int chosenIndexGate = rand.nextInt(subclasses.size());

		Class<?> clazz;
		try {
			clazz = Class.forName(subclasses.get(chosenIndexGate));
			gate = (Gate) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return gate;
	}
}