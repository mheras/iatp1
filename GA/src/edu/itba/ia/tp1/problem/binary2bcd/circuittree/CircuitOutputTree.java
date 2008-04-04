package edu.itba.ia.tp1.problem.binary2bcd.circuittree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import sun.security.jca.GetInstance;

import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.BinaryGate;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.CircuitComponent;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.Gate;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.Input;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.UnaryGate;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.Wire;

/**
 * This class represents an output circuit components tree. The tree's root is
 * the output. Every node within this tree is a <code>CircuitComponent</code>,
 * specifically a <code>Gate</code>. Leafs are supposed to be inputs.
 * 
 * @author Jorge Goldman
 * 
 */
public class CircuitOutputTree {

	/* Collection of gates. */
	private List<CircuitComponent> gates;

	/**
	 * Private constructor.
	 */
	private CircuitOutputTree() {
		gates = new ArrayList<CircuitComponent>();
	}

	/**
	 * Adds a gate.
	 * 
	 * @param gate
	 *            The gate.
	 */
	public void addGate(CircuitComponent gate) {
		gates.add(gate);
	}

	/**
	 * Generates a random <code>CircuitOutputTree</code>, specifying min and
	 * max gates.
	 * 
	 * @param maxGates
	 *            Maximum gates.
	 * @param minGates
	 *            Minimum gates.
	 * @return The new randomly generated <code>CircuitOutputTree</code>.
	 */
	public static CircuitOutputTree generateRandomCircuitTree(Integer maxGates,
			Integer minGates) {
		Random rand = new Random();
		int nGates = rand.nextInt(maxGates - minGates) + minGates;
		CircuitOutputTree cTree = new CircuitOutputTree();
		CircuitComponent component = Gate.randomGate();
		cTree.addGate(component);
		generateChildren(cTree, component, nGates - 1);
		return cTree;
	}

	/**
	 * Clones this <code>CircuitOutputTree</code>.
	 * 
	 * @param clonedInputs
	 *            Cloned inputs.
	 * @param clonedOutputs
	 *            Cloned outputs.
	 * @return Cloned <code>CircuitOutputTree</code>.
	 */
	public CircuitOutputTree clone(List<CircuitComponent> clonedInputs,
			List<CircuitComponent> clonedOutputs) {

		CircuitOutputTree newCircuitOutputTree = new CircuitOutputTree();

		List<CircuitComponent> realComponentList = this.getGates();
		List<CircuitComponent> clonedComponentList = new ArrayList<CircuitComponent>();

		CircuitComponent realComponent = realComponentList.get(0);
		CircuitComponent output = getClonedComponent(((Gate) realComponent)
				.getParent(), clonedOutputs);
		CircuitComponent clonedComponent = realComponent.clone();
		((Gate) clonedComponent).setParent(output);
		clonedComponentList.add(clonedComponent);

		cloneChildren(realComponent, clonedComponent, clonedComponentList,
				clonedInputs);

		newCircuitOutputTree.setGates(clonedComponentList);
		newCircuitOutputTree.resetTree();
		return newCircuitOutputTree;

	}

	private void cloneChildren(CircuitComponent realComponent,
			CircuitComponent clonedComponent,
			List<CircuitComponent> clonedComponentList,
			List<CircuitComponent> clonedInputs) {

		if (realComponent instanceof BinaryGate) {
			if (((BinaryGate) realComponent).getLeftSon() instanceof Input) {
				CircuitComponent clonedInput = getClonedComponent(
						((BinaryGate) realComponent).getLeftSon(), clonedInputs);
				((BinaryGate) clonedComponent).setLeftSon(clonedInput);
				clonedInput.addNextComponent(clonedComponent);
			}
			if (((BinaryGate) realComponent).getRightSon() instanceof Input) {
				CircuitComponent clonedInput = getClonedComponent(
						((BinaryGate) realComponent).getRightSon(),
						clonedInputs);
				((BinaryGate) clonedComponent).setRightSon(clonedInput);
				clonedInput.addNextComponent(clonedComponent);
			}
			if (((BinaryGate) realComponent).getLeftSon() instanceof Gate) {
				CircuitComponent newCloned = ((BinaryGate) realComponent)
						.getLeftSon().clone();
				clonedComponentList.add(newCloned);
				((BinaryGate) clonedComponent).setLeftSon(newCloned);
				((Gate) newCloned).setParent(clonedComponent);
				cloneChildren(((BinaryGate) realComponent).getLeftSon(),
						newCloned, clonedComponentList, clonedInputs);
			}
			if (((BinaryGate) realComponent).getRightSon() instanceof Gate) {
				CircuitComponent newCloned = ((BinaryGate) realComponent)
						.getRightSon().clone();
				clonedComponentList.add(newCloned);
				((BinaryGate) clonedComponent).setRightSon(newCloned);
				((Gate) newCloned).setParent(clonedComponent);
				cloneChildren(((BinaryGate) realComponent).getRightSon(),
						newCloned, clonedComponentList, clonedInputs);
			}
		} else {
			if (((UnaryGate) realComponent).getSon() instanceof Input) {
				CircuitComponent clonedInput = getClonedComponent(
						((UnaryGate) realComponent).getSon(), clonedInputs);
				((UnaryGate) clonedComponent).setSon(clonedInput);
				clonedInput.addNextComponent(clonedComponent);
			}
			if (((UnaryGate) realComponent).getSon() instanceof Gate) {
				CircuitComponent newCloned = ((UnaryGate) realComponent)
						.getSon().clone();
				clonedComponentList.add(newCloned);
				((UnaryGate) clonedComponent).setSon(newCloned);
				((Gate) newCloned).setParent(clonedComponent);
				cloneChildren(((UnaryGate) realComponent).getSon(), newCloned,
						clonedComponentList, clonedInputs);
			}
		}

	}

	private CircuitComponent getClonedComponent(CircuitComponent compN,
			List<CircuitComponent> alreadyCloned) {
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

	private static void generateChildren(CircuitOutputTree tree,
			CircuitComponent component, int nGates) {

		if (nGates > 0) {
			if (component instanceof BinaryGate) {
				CircuitComponent firstComponent = Gate.randomGate();
				((Gate) firstComponent).setParent(component);
				CircuitComponent secondComponent = Gate.randomGate();
				((Gate) secondComponent).setParent(component);
				tree.addGate(firstComponent);
				tree.addGate(secondComponent);
				((BinaryGate) component).setRightSon(firstComponent);
				((BinaryGate) component).setLeftSon(secondComponent);
				generateChildren(tree, firstComponent, nGates - 2);
				generateChildren(tree, secondComponent, nGates - 2);
			} else {
				CircuitComponent newComponent = Gate.randomGate();
				((Gate) newComponent).setParent(component);
				tree.addGate(newComponent);
				((UnaryGate) component).setSon(newComponent);
				generateChildren(tree, newComponent, nGates - 1);
			}
		}
	}

	public void operate() {

		List<CircuitComponent> gateList = this.getGates();
		CircuitComponent parent = gateList.get(0);
		if (parent instanceof BinaryGate) {
			operateSon(((BinaryGate) parent).getLeftSon());
			operateSon(((BinaryGate) parent).getRightSon());
		} else {
			operateSon(((UnaryGate) parent).getSon());
		}
		if (parent.isReady()) {
			((Gate) parent).operate();
		}

	}

	private void operateSon(CircuitComponent son) {
		if (son instanceof Input) {
			son.operate();
			return;
		} else {
			if (son instanceof BinaryGate) {
				operateSon(((BinaryGate) son).getLeftSon());
				operateSon(((BinaryGate) son).getRightSon());
			} else {
				operateSon(((UnaryGate) son).getSon());
			}
			if (son.isReady()) {
				((Gate) son).operate();
			}
			return;
		}

	}

	public static void performCrossover(CircuitOutputTree circuitTree1,
			CircuitOutputTree circuitTree2, List<CircuitComponent> inputList,
			List<CircuitComponent> inputList2) {

		List<CircuitComponent> gates1 = circuitTree1.getGates();
		List<CircuitComponent> gates2 = circuitTree2.getGates();

		/* Nodes that are going to be inserted in the crossed tree */
		List<CircuitComponent> crossedNodesTree1List = new ArrayList<CircuitComponent>();
		List<CircuitComponent> crossedNodesTree2List = new ArrayList<CircuitComponent>();

		Random rand = new Random();
		int crossPointTree1 = rand.nextInt(gates1.size());
		int crossPointTree2 = rand.nextInt(gates2.size());

		/* Starting Point for the crossover for Tree1. */
		CircuitComponent root1 = gates1.get(crossPointTree1);

		/* Starting Point for the crossover for Tree2. */
		CircuitComponent root2 = gates2.get(crossPointTree2);

		CircuitComponent parent1 = ((Gate) root1).getParent();
		CircuitComponent parent2 = ((Gate) root2).getParent();

		((Gate) root2).setParent(parent1);
		((Gate) root1).setParent(parent2);

		if (parent1 instanceof BinaryGate) {
			if (((BinaryGate) parent1).getLeftSon() == root1) {
				((BinaryGate) parent1).setLeftSon(root2);
			} else {
				((BinaryGate) parent1).setRightSon(root2);
			}
		} else if (parent1 instanceof UnaryGate) {
			((UnaryGate) parent1).setSon(root2);
		}

		if (parent2 instanceof BinaryGate) {
			if (((BinaryGate) parent2).getLeftSon() == root2) {
				((BinaryGate) parent2).setLeftSon(root1);
			} else {
				((BinaryGate) parent2).setRightSon(root1);
			}
		} else if (parent2 instanceof UnaryGate) {
			((UnaryGate) parent2).setSon(root1);
		}

		getChildren(root1, crossedNodesTree1List);
		getChildren(root2, crossedNodesTree2List);

		List<CircuitComponent> crossedInputsComponent1 = getInputLeaves(crossedNodesTree1List);
		List<CircuitComponent> crossedInputsComponent2 = getInputLeaves(crossedNodesTree2List);

		gates1.removeAll(crossedNodesTree1List);
		gates2.removeAll(crossedNodesTree2List);

		connectCrossedInputs(crossedInputsComponent1, inputList2);
		connectCrossedInputs(crossedInputsComponent2, inputList);

		/* Do not cross this. */
		removeNextComponent(inputList, crossedInputsComponent1);
		removeNextComponent(inputList2, crossedInputsComponent2);

		gates1.addAll(crossedNodesTree2List);
		gates2.addAll(crossedNodesTree1List);

	}

	private static void connectCrossedInputs(
			List<CircuitComponent> crossedInputsComponent,
			List<CircuitComponent> inputList) {

		for (CircuitComponent currentComponent : crossedInputsComponent) {

			if (currentComponent instanceof BinaryGate) {
				BinaryGate binaryComponent = (BinaryGate) currentComponent;
				CircuitComponent leftInput = binaryComponent.getLeftSon();
				CircuitComponent rightInput = binaryComponent.getRightSon();
				CircuitComponent realInput = findRealInput(leftInput, inputList);
				((Input) realInput).addNextComponent(binaryComponent);
				binaryComponent.setLeftSon(realInput);
				realInput = findRealInput(rightInput, inputList);
				((Input) realInput).addNextComponent(binaryComponent);
				binaryComponent.setRightSon(realInput);
			} else {
				UnaryGate unaryComponent = (UnaryGate) currentComponent;
				CircuitComponent input = unaryComponent.getSon();
				CircuitComponent realInput = findRealInput(input, inputList);
				((Input) realInput).addNextComponent(unaryComponent);
				unaryComponent.setSon(realInput);
			}
		}
	}

	private static CircuitComponent findRealInput(CircuitComponent leftInput,
			List<CircuitComponent> inputList) {
		CircuitComponent result = null;
		for (CircuitComponent currentInput : inputList) {
			if (currentInput.getId() == leftInput.getId()) {
				result = currentInput;
			}
		}
		return result;

	}

	private static void removeNextComponent(List<CircuitComponent> inputList,
			List<CircuitComponent> crossedInputsComponent1) {
		for (CircuitComponent currentInput : inputList) {
			currentInput.getNextComponents().removeAll(crossedInputsComponent1);
		}

	}

	private static void getChildren(CircuitComponent root,
			List<CircuitComponent> crossedNodesTree1List) {
		if (root instanceof Input) {
			return;
		}
		crossedNodesTree1List.add(root);
		if (root instanceof BinaryGate) {
			getChildren(((BinaryGate) root).getLeftSon(), crossedNodesTree1List);
			getChildren(((BinaryGate) root).getRightSon(),
					crossedNodesTree1List);
		} else {
			getChildren(((UnaryGate) root).getSon(), crossedNodesTree1List);
		}

	}

	public void performMutation(double mutationProbability,
			List<CircuitComponent> inputsList) {
		List<CircuitComponent> componentList = this.getGates();
		List<CircuitComponent> newList = new ArrayList<CircuitComponent>();
		Random rand = new Random();

		int size = componentList.size();
		for (int i = 0; i < size; i++) {
			double mutateP = rand.nextDouble();
			CircuitComponent chosenComponent = componentList.get(i);
			if (mutateP < mutationProbability) {
				CircuitComponent componentX = mutateComponent(chosenComponent,
						inputsList, componentList);
				newList.add(componentX);
			} else {
				newList.add(chosenComponent);
			}

		}
		this.setGates(newList);
	}

	private CircuitComponent mutateComponent(CircuitComponent component,
			List<CircuitComponent> inputsList,
			List<CircuitComponent> componentList) {

		CircuitComponent componentX = Gate.randomGate();

		((Gate) componentX).setParent(((Gate) component).getParent());
		CircuitComponent parent = ((Gate) component).getParent();

		/* Sets the component's father child. */
		if (parent != null) {
			if (parent instanceof BinaryGate) {
				if (((BinaryGate) parent).getLeftSon() == component) {
					((BinaryGate) parent).setLeftSon(componentX);
				} else {
					((BinaryGate) parent).setRightSon(componentX);
				}
			} else if (!(parent instanceof Wire)
					&& (parent instanceof UnaryGate)) {
				((UnaryGate) parent).setSon(componentX);
			}
		}

		if ((componentX instanceof BinaryGate)
				&& (component instanceof BinaryGate)) {

			/* Sets the mutated component's childs. */

			CircuitComponent leftSon = ((BinaryGate) component).getLeftSon();
			CircuitComponent rightSon = ((BinaryGate) component).getRightSon();

			((BinaryGate) componentX).setLeftSon(leftSon);
			((BinaryGate) componentX).setRightSon(rightSon);

			/* Set the child's parent. */

			if (leftSon instanceof Input) {
				((Input) leftSon).addNextComponent(componentX);
				((Input) leftSon).removeNextComponent(component);
			} else {
				((Gate) leftSon).setParent(componentX);
			}

			if (rightSon instanceof Input) {
				((Input) rightSon).addNextComponent(componentX);
				((Input) rightSon).removeNextComponent(component);
			} else {
				((Gate) rightSon).setParent(componentX);
			}

		} else if ((componentX instanceof UnaryGate)
				&& (component instanceof BinaryGate)) {

			/* Sets the mutated component's childs. */
			Random rand = new Random();
			int chosenBit = rand.nextInt(2);
			CircuitComponent rejected;

			CircuitComponent son;
			if (chosenBit == 0) {
				son = ((BinaryGate) component).getLeftSon();
				rejected = ((BinaryGate) component).getRightSon();
			} else {
				son = ((BinaryGate) component).getRightSon();
				rejected = ((BinaryGate) component).getLeftSon();

			}

			((UnaryGate) componentX).setSon(son);

			if (rejected instanceof Input) {
				((Input) rejected).removeNextComponent(component);
			} else {
				((Gate) rejected).setParent(null);
				// removeFromList(rejected, componentList);
			}

			/* Set the child's parent. */

			if (son instanceof Input) {
				((Input) son).addNextComponent(componentX);
				((Input) son).removeNextComponent(component);
			} else {
				((Gate) son).setParent(componentX);
			}

		} else if ((componentX instanceof BinaryGate)
				&& (component instanceof UnaryGate)) {
			/* Sets the mutated component's child. */

			Random rand = new Random();
			int chosenInput = rand.nextInt(inputsList.size());
			CircuitComponent son = ((UnaryGate) component).getSon();

			((BinaryGate) componentX).setLeftSon(son);
			CircuitComponent input = inputsList.get(chosenInput);
			((BinaryGate) componentX).setRightSon(input);
			input.addNextComponent(componentX);

			/* Set the child's parent. */
			son = ((UnaryGate) component).getSon();
			if (son instanceof Input) {
				((Input) son).addNextComponent(componentX);
				((Input) son).removeNextComponent(component);
			} else {
				((Gate) son).setParent(componentX);
			}
		} else {
			/* Sets the mutated component's childs. */

			CircuitComponent son = ((UnaryGate) component).getSon();

			((UnaryGate) componentX).setSon(son);

			/* Set the child's parent. */

			if (son instanceof Input) {
				((Input) son).addNextComponent(componentX);
				((Input) son).removeNextComponent(component);
			} else {
				((Gate) son).setParent(componentX);
			}

		}
		return componentX;

	}

	public void resetTree() {
		for (CircuitComponent component : this.gates) {
			component.resetComponent();
		}
	}

	public void connectOutput(CircuitComponent output) {
		CircuitComponent root = this.getGates().get(0);
		((Gate) root).setParent(output);
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
		/* Gets the root of the tree. */
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

	private static List<CircuitComponent> getInputLeaves(
			List<CircuitComponent> componentList) {
		List<CircuitComponent> leaves = new ArrayList<CircuitComponent>();

		for (CircuitComponent component : componentList) {
			if (isInputLeaf(component)) {
				leaves.add(component);
			}
		}

		return leaves;
	}

	private static boolean isInputLeaf(CircuitComponent component) {
		if (component instanceof BinaryGate) {
			if (((BinaryGate) component).getLeftSon() instanceof Input
					|| ((BinaryGate) component).getRightSon() instanceof Input) {
				return true;
			} else {
				return false;
			}
		} else {
			if (((UnaryGate) component).getSon() instanceof Input) {
				return true;
			} else {
				return false;
			}
		}
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
