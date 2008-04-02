package edu.itba.ia.tp1.problem.binary2bcd.circuittree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.BinaryGate;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.CircuitComponent;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.Gate;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.Input;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.UnaryGate;

/**
 * TODO: Comment
 * 
 * @author Jorge Goldman
 * 
 */

public class CircuitOutputTree {

	/* Collection of gates. */
	private List<CircuitComponent> gates;

	private CircuitOutputTree() {
		gates = new ArrayList<CircuitComponent>();
	}

	public void addGate(CircuitComponent gate) {
		gates.add(gate);
	}

	public static CircuitOutputTree generateRandomCircuitTree(Integer maxGates) {
		Random rand = new Random();
		int nGates = rand.nextInt(maxGates);
		CircuitOutputTree cTree = new CircuitOutputTree();
		CircuitComponent component = Gate.randomGate();
		cTree.addGate(component);
		generateSons(cTree, component, nGates - 1);
		return cTree;
	}

	public CircuitOutputTree clone(List<CircuitComponent> clonedInputs, List<CircuitComponent>clonedOutputs){
		
		CircuitOutputTree newCircuitOutputTree = new CircuitOutputTree();
		
		List<CircuitComponent> realComponentList = this.getGates();
		List<CircuitComponent> clonedComponentList = new ArrayList<CircuitComponent>();
		
		CircuitComponent realComponent = realComponentList.get(0);
		CircuitComponent output = getClonedComponent(((Gate)realComponent).getFather(), clonedOutputs);
		CircuitComponent clonedComponent = realComponent.clone();
		((Gate)clonedComponent).setFather(output);
		clonedComponentList.add(clonedComponent);
		
		cloneSons(realComponent, clonedComponent, clonedComponentList, clonedInputs);
		
		
		
		newCircuitOutputTree.setGates(clonedComponentList);
		return newCircuitOutputTree;
		
	}
	
	private void cloneSons(CircuitComponent realComponent, CircuitComponent clonedComponent, List<CircuitComponent> clonedComponentList, List<CircuitComponent> clonedInputs) {
		
		if(realComponent instanceof BinaryGate){
			if(((BinaryGate)realComponent).getLeftSon() instanceof Input){
				CircuitComponent clonedInput = getClonedComponent(((BinaryGate)realComponent).getLeftSon(), clonedInputs);
				((BinaryGate)clonedComponent).setLeftSon(clonedInput);
				clonedInput.addNextComponent(clonedComponent);
			} 
			if (((BinaryGate)realComponent).getRightSon() instanceof Input){
				CircuitComponent clonedInput = getClonedComponent(((BinaryGate)realComponent).getRightSon(), clonedInputs);
				((BinaryGate)clonedComponent).setRightSon(clonedInput);
				clonedInput.addNextComponent(clonedComponent);
			}
			if(((BinaryGate)realComponent).getLeftSon() instanceof Gate){
				CircuitComponent newCloned = ((BinaryGate)realComponent).getLeftSon().clone();
				clonedComponentList.add(newCloned);
				((BinaryGate)clonedComponent).setLeftSon(newCloned);
				((Gate)newCloned).setFather(clonedComponent);
				cloneSons(((BinaryGate)realComponent).getLeftSon(), newCloned, clonedComponentList, clonedInputs);
			}
			if(((BinaryGate)realComponent).getRightSon() instanceof Gate){
				CircuitComponent newCloned = ((BinaryGate)realComponent).getRightSon().clone();
				clonedComponentList.add(newCloned);
				((BinaryGate)clonedComponent).setRightSon(newCloned);
				((Gate)newCloned).setFather(clonedComponent);
				cloneSons(((BinaryGate)realComponent).getRightSon(), newCloned, clonedComponentList, clonedInputs);
			}
		}else{
			if (((UnaryGate)realComponent).getSon() instanceof Input){
				CircuitComponent clonedInput = getClonedComponent(((UnaryGate)realComponent).getSon(), clonedInputs);
				((UnaryGate)clonedComponent).setSon(clonedInput);
				clonedInput.addNextComponent(clonedComponent);
			}
			if(((UnaryGate)realComponent).getSon() instanceof Gate){
				CircuitComponent newCloned = ((UnaryGate)realComponent).getSon().clone();
				clonedComponentList.add(newCloned);
				((UnaryGate)clonedComponent).setSon(newCloned);
				((Gate)newCloned).setFather(clonedComponent);
				cloneSons(((UnaryGate)realComponent).getSon(), newCloned, clonedComponentList, clonedInputs);
			}
		}
		
	}

	private CircuitComponent getClonedComponent(CircuitComponent compN, List<CircuitComponent> alreadyCloned) {
		boolean found = false;
		CircuitComponent aux = null;
		/*
		 * Iterates over the list of already cloned components looking for one
		 * with the same id of the requested component.
		 */
		Iterator<CircuitComponent> iter = alreadyCloned.iterator();
		while (iter.hasNext() && !found) {
			CircuitComponent c = (CircuitComponent) iter.next();
			if (c.getId().equals(compN.getId())) {
				if ((c.getClass().equals(compN.getClass()))) {
					aux = c;
					found = true;
				}
			}
		}

		return aux;

	}

	private static void generateSons(CircuitOutputTree tree,
			CircuitComponent component, int nGates) {

		if (nGates > 0) {
			if (component instanceof BinaryGate) {
				CircuitComponent firstComponent = Gate.randomGate();
				((Gate) firstComponent).setFather(component);
				CircuitComponent secondComponent = Gate.randomGate();
				((Gate) secondComponent).setFather(component);
				tree.addGate(firstComponent);
				tree.addGate(secondComponent);
				((BinaryGate) component).setRightSon(firstComponent);
				((BinaryGate) component).setLeftSon(secondComponent);
				generateSons(tree, firstComponent, nGates -2);
				generateSons(tree, secondComponent, nGates -2);
			} else {
				CircuitComponent newComponent = Gate.randomGate();
				((Gate) newComponent).setFather(component);
				tree.addGate(newComponent);
				((UnaryGate) component).setSon(newComponent);
				generateSons(tree, newComponent, nGates - 1);
			}
		}
	}

	public void operate() {
		List<CircuitComponent> queueList = getInputLeafs();

		while (!queueList.isEmpty()) {
			CircuitComponent component = queueList.get(0);
			queueList.remove(0);
			if (component.isReady()) {
				component.operate();
				if (component instanceof Gate) {
					queueList.add(((Gate) component).getFather());
				}
			} else {
				queueList.add(component);
			}
		}
	}

	private List<CircuitComponent> getInputLeafs() {
		List<CircuitComponent> inputLeafs = new ArrayList<CircuitComponent>();

		for (CircuitComponent component : this.getGates()) {

			if (component instanceof BinaryGate) {
				if ((((BinaryGate) component).getLeftSon() instanceof Input)
						|| (((BinaryGate) component).getRightSon() instanceof Input)) {
					inputLeafs.add(component);
				}
			} else {
				if ((((UnaryGate) component).getSon() instanceof Input)) {
					inputLeafs.add(component);
				}
			}
		}

		return inputLeafs;

	}

	public void resetTree() {
		for (CircuitComponent component : this.gates) {
			component.resetComponent();
		}
	}

	public void connectOutput(CircuitComponent output) {
		CircuitComponent root = this.getGates().get(0);
		((Gate) root).setFather(output);
	}

	public void connectInputs(List<CircuitComponent> Inputs) {
		Random rand = new Random();
		int nInputs = Inputs.size();
		int chosenInput;
		List<CircuitComponent> leafs = this.getLeafs();
		for (CircuitComponent component : leafs) {
			chosenInput = rand.nextInt(nInputs);
			if (component instanceof BinaryGate) {
				((BinaryGate) component).setLeftSon(Inputs.get(chosenInput));
				Inputs.get(chosenInput).addNextComponent(component);
				chosenInput = rand.nextInt(nInputs);
				((BinaryGate) component).setRightSon(Inputs.get(chosenInput));
				Inputs.get(chosenInput).addNextComponent(component);
			} else {
				((UnaryGate) component).setSon(Inputs.get(chosenInput));
				Inputs.get(chosenInput).addNextComponent(component);
			}
		}
	}

	public String toString() {

		String evaluationString = "";
		String operation = "";
		/* Gets the roor of the tree. */
		CircuitComponent component = this.getGates().get(0);

		operation = getOperationString(component);

		if (component instanceof BinaryGate) {
			evaluationString = "("
					+ generateEvaluationString(((BinaryGate) component)
							.getLeftSon())
					+ operation
					+ generateEvaluationString(((BinaryGate) component)
							.getRightSon()) + ")";
		} else {
			evaluationString = "("
					+ getOperationString(component)
					+ generateEvaluationString(((UnaryGate) component).getSon())
					+ ")";
		}

		return evaluationString;

	}

	private String generateEvaluationString(CircuitComponent component) {

		if (component instanceof Input) {
			return component.getId().toString();
		} else if (component instanceof BinaryGate) {
			return "("
					+ generateEvaluationString(((BinaryGate) component)
							.getLeftSon())
					+ getOperationString(component)
					+ generateEvaluationString(((BinaryGate) component)
							.getRightSon()) + ")";
		} else {
			return "("
					+ getOperationString(component)
					+ generateEvaluationString(((UnaryGate) component).getSon())
					+ ")";
		}
	}

	private String getOperationString(CircuitComponent component) {
		return ((Gate) component).getOperationString();
	}

	/**
	 * @return
	 */
	public List<CircuitComponent> getLeafs() {
		List<CircuitComponent> leafs = new ArrayList<CircuitComponent>();

		for (CircuitComponent component : this.getGates()) {
			if (isLeaf(component)) {
				leafs.add(component);
			}
		}

		return leafs;
	}

	/**
	 * @param component
	 * @return
	 */
	private boolean isLeaf(CircuitComponent component) {
		if (component instanceof BinaryGate) {
			if (((BinaryGate) component).getLeftSon() == null
					&& ((BinaryGate) component).getRightSon() == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (((UnaryGate) component).getSon() == null) {
				return true;
			} else {
				return false;
			}
		}
	}

	public List<CircuitComponent> getGates() {
		return gates;
	}

	public void setGates(List<CircuitComponent> gates) {
		this.gates = gates;
	}
}
