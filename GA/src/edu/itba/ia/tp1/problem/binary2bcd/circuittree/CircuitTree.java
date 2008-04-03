package edu.itba.ia.tp1.problem.binary2bcd.circuittree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.CircuitComponent;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.Input;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.component.Output;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicOff;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicOn;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.logicstate.LogicState;

/**
 * 
 * @author Jorge Goldman & Martín A. Heras
 * 
 */
public class CircuitTree extends A_Individual {

	/* Collection of inputs. */
	private List<CircuitComponent> inputs;
	/* Collection of outputs. */
	private List<CircuitComponent> outputs;
	/* Collection of gates. */
	private CircuitOutputTree[] circuits;

	/* Number of input bits. */
	private Integer inputBits;
	/* Number of output bits. */
	private Integer outputBits;
	/* Minimum number of gates. */
	private Integer minGates;
	/* Maximum number of gates. */
	private Integer maxGates;

	/**
	 * Returns a new circuit instance, not constructed. Private constructor, to
	 * be used in factory method.
	 * 
	 * @param inputBits
	 *            Input bits.
	 * @param outputBits
	 *            Output bits.
	 * @param minGates
	 *            Minumum gates.
	 * @param maxGates
	 *            Maximum gates.
	 */
	private CircuitTree(Integer inputBits, Integer outputBits,
			Integer minGates, Integer maxGates) {

		this.inputBits = inputBits;
		this.outputBits = outputBits;
		this.minGates = minGates;
		this.maxGates = maxGates;

		/* Initialize collections. */
		this.inputs = new ArrayList<CircuitComponent>();
		this.circuits = new CircuitOutputTree[outputBits];
		this.outputs = new ArrayList<CircuitComponent>();
	}

	/**
	 * Returns a new circuit instance, fully conected. In which the connections
	 * and the gates between the inputs and the outputs of the circuit are
	 * generated randomly.
	 * 
	 * @param inputBits
	 *            Input bits.
	 * @param outputBits
	 *            Output bits.
	 * @param minGates
	 *            Minumum gates.
	 * @param maxGates
	 *            Maximum gates.
	 */
	public static CircuitTree generateRandomCircuit(Integer inputBits,
			Integer outputBits, Integer minGates, Integer maxGates) {

		CircuitTree circuit = new CircuitTree(inputBits, outputBits, minGates,
				maxGates);

		/* Generate Inputs. */
		Long n = 0L;
		for (Integer i = 0; i < circuit.getInputBits(); i++) {
			CircuitComponent input = new Input();
			input.setId(n++);
			circuit.addInput(input);
		}

		/* Generate the number of gates that will be part of the circuit. */
		CircuitOutputTree[] circuitTrees = new CircuitOutputTree[outputBits];
		for (Integer i = 0; i < circuit.getOutputBits(); i++) {
			CircuitOutputTree newRandomCircuit = CircuitOutputTree
					.generateRandomCircuitTree(maxGates, minGates);
			newRandomCircuit.connectInputs(circuit.getInputs());
			circuitTrees[i] = newRandomCircuit;
		}

		/* Generate outputs and connect the outputs randomly. */
		for (Integer i = 0; i < circuit.getOutputBits(); i++) {
			CircuitComponent output = new Output();
			output.setId(new Long(i.longValue()));
			circuit.addOutput(output);
			circuitTrees[i].connectOutput(output);
		}
		circuit.setCircuits(circuitTrees);
		return circuit;
	}

	/**
	 * Adds an input to the circuit. The CircuitComponent must be and Input
	 * class instance.
	 */
	private void addInput(CircuitComponent in) {
		inputs.add(in);
	}

	/**
	 * Adds an output to the circuit. The CircuitComponent must be and Output
	 * class instance.
	 */
	private void addOutput(CircuitComponent output) {
		outputs.add(output);
	}

	/**
	 * Prints a circuit recursively. The output of this method could be a bit
	 * confusing in the way that prints the circuit. It might print the same
	 * gate twice.
	 */
	public void printCircuit() {

		for (CircuitOutputTree tree : this.getCircuits()) {
			System.out.println(tree.toString());
		}

	}

	/**
	 * This method receives an input for a certain Circuit instance and
	 * evaluates the circuit depending on the given input.
	 * 
	 * @param input
	 *            An Integer instance containing a number to be processed by the
	 *            circuit.
	 * @return
	 */
	@Override
	public Object operate(Object input) {

		this.resetCircuit();

		if (!(input instanceof Integer)) {
			return null;
		}

		int inputValue = ((Integer) input).intValue();
		/* Put the input in the inputs of the circuit. */
		List<CircuitComponent> inputs = (ArrayList<CircuitComponent>) this
				.getInputs();

		Collections.reverse(inputs);
		for (CircuitComponent currentInput : inputs) {
			if ((inputValue % 2) == 1) {
				currentInput.setInput(new LogicOn());
			} else {
				currentInput.setInput(new LogicOff());
			}
			inputValue /= 2;
		}
		Collections.reverse(inputs);

		List<CircuitComponent> outputs = this.getOutputs();

		for (CircuitComponent currentInput : inputs) {
			currentInput.operate();
		}

		for (CircuitOutputTree currentTree : this.circuits) {
			currentTree.operate();
		}

		/* Generates the output. */
		int out = 0;

		Collections.reverse(outputs);
		int power = 1;
		for (CircuitComponent currentOutput : outputs) {

			LogicState state = ((Output) currentOutput).getOutputValue();
			if (state.isOn()) {
				out += 1 * power;
			} else
				power *= 2;
		}
		Collections.reverse(outputs);

		this.resetCircuit();

		return new Integer(out);
	}

	/**
	 * This method has the behavior of reseting all the gates on the circuit.
	 * 
	 */
	private void resetCircuit() {

		List<CircuitComponent> inputs = this.getInputs();
		List<CircuitComponent> outputs = this.getOutputs();
		CircuitOutputTree[] circuits = this.getCircuits();

		/* Resets the inputs. */
		for (CircuitComponent input : inputs) {
			input.resetComponent();
		}
		/* Resets the outputs. */
		for (CircuitComponent output : outputs) {
			output.resetComponent();
		}
		/* Resets the gates. */
		for (CircuitOutputTree tree : circuits) {
			tree.resetTree();
		}
	}

	/**
	 * Returns a List of the inputs of the circuit.
	 */
	private List<CircuitComponent> getInputs() {
		return this.inputs;
	}

	/**
	 * Sets a List of the inputs of the circuit.
	 */
	private void setInputs(List<CircuitComponent> inputs) {
		this.inputs = inputs;
	}

	/**
	 * Returns a List of the outputs of the circuit.
	 */
	private List<CircuitComponent> getOutputs() {
		return outputs;
	}

	/**
	 * Sets a List of the outputs of the circuit.
	 */
	private void setOutputs(List<CircuitComponent> outputs) {
		this.outputs = outputs;
	}

	/**
	 * Gets circuit output trees.
	 * 
	 * @return Array of circuit output trees.
	 */
	public CircuitOutputTree[] getCircuits() {
		return circuits;
	}

	/**
	 * This method returns a new instance of Circuit that is identical to the
	 * given circuit.
	 * 
	 */
	public CircuitTree clone() {

		this.resetCircuit();

		CircuitTree newCircuitTree = new CircuitTree(this.inputBits,
				this.outputBits, this.minGates, this.maxGates);

		List<CircuitComponent> clonedInputs = new ArrayList<CircuitComponent>();

		List<CircuitComponent> clonedOutputs = new ArrayList<CircuitComponent>();

		CircuitOutputTree[] clonedTrees = new CircuitOutputTree[this.outputBits];

		for (CircuitComponent currentInput : this.getInputs()) {
			CircuitComponent cloned = currentInput.clone();
			clonedInputs.add(cloned);
		}

		for (CircuitComponent currentOutput : this.getOutputs()) {
			CircuitComponent cloned = currentOutput.clone();
			clonedOutputs.add(cloned);
		}

		for (int i = 0; i < this.getOutputBits(); i++) {
			CircuitOutputTree realTree = this.getCircuits()[i];
			clonedTrees[i] = realTree.clone(clonedInputs, clonedOutputs);
		}

		newCircuitTree.setInputs(clonedInputs);
		newCircuitTree.setOutputs(clonedOutputs);
		newCircuitTree.setCircuits(clonedTrees);
		newCircuitTree.resetCircuit();
		return newCircuitTree;
	}

	/**
	 * Mutates the current circuit tree.
	 * 
	 * @param mutationProbability Mutation probability.
	 */
	public void performMutation(double mutationProbability) {

		for (CircuitOutputTree tree : this.getCircuits()) {
			tree.performMutation(mutationProbability, this.getInputs());
		}
	}

	/**
	 * Returns the number of inputs of the given circuit.
	 */
	public Integer getInputBits() {
		return inputBits;
	}

	/**
	 * Returns the number of outputs of the given circuit.
	 */
	public Integer getOutputBits() {
		return outputBits;
	}

	/**
	 * Returns the number of minimun possible gates of the given circuit.
	 */
	public Integer getMinGates() {
		return minGates;
	}

	/**
	 * Returns the number of maximun possible gates of the given circuit.
	 */
	public Integer getMaxGates() {
		return maxGates;
	}

	public void setCircuits(CircuitOutputTree[] circuits) {
		this.circuits = circuits;
	}
}
