package edu.itba.ia.tp1.problem.binary2bcd.circuit.component;

import java.util.ArrayList;
import java.util.List;

import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicState;

/**
 * This class represents a Circuit componet.
 * 
 * @author Jorge Goldman & Martín A. Heras
 * 
 */
public abstract class CircuitComponent implements Cloneable {

	/*
	 * List of circuit components which are attached to this circuit component
	 * output.
	 */
	private List<CircuitComponent> nextComponents;
	/* Component ID. */
	private Long id;

	/**
	 * Constructs a new instance of <code>CircuitComponent</code>.
	 */
	public CircuitComponent() {
		nextComponents = new ArrayList<CircuitComponent>();
	}

	/**
	 * Evaluates the circuit component based on its inputs and propagates its
	 * output to the components attached to it.
	 */
	public abstract void operate();

	/**
	 * Indicates whether this component is ready or not.
	 * 
	 * @return <code>true</code> if this component is ready; otherwise
	 *         <code>false</code>.
	 */
	public abstract boolean isReady();

	public abstract void setInput(LogicState in);

	public abstract void resetComponent();

	public boolean hasNextComponent() {
		return !nextComponents.isEmpty();
	}

	public void addNextComponent(CircuitComponent component) {
		this.nextComponents.add(component);
	}

	/* Getters and setters. */

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CircuitComponent> getNextComponents() {
		return nextComponents;
	}

	public void setNextComponents(List<CircuitComponent> nextComponents) {
		this.nextComponents = nextComponents;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String className = this.getClass().getName();
		return className.substring(className.lastIndexOf(".")) + "["
				+ this.getId() + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public CircuitComponent clone() {

		CircuitComponent newComponent = null;
		
		try {
			newComponent = (CircuitComponent) this.getClass()
					.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		newComponent.setId(this.getId());
		newComponent.resetComponent();
		newComponent.setNextComponents(new ArrayList<CircuitComponent>());
		
		return newComponent;
	}
}
