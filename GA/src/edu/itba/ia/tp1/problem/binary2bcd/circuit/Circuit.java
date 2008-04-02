package edu.itba.ia.tp1.problem.binary2bcd.circuit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.BinaryGate;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitTree;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Gate;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Input;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Output;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.UnaryGate;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicOff;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicOn;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicState;

/**
 * This class implementates a circuit. The main concept behind of this implementation
 * is a graph that all of its nodes must be correctly connected, based on the concepts
 * of a circuit.
 * 
 * @author Jorge Goldman & Martín A. Heras
 * 
 */
public class Circuit extends A_Individual {

	/* Collection of inputs. */
	private List<CircuitComponent> inputs;
	/* Collection of outputs. */
	private List<CircuitComponent> outputs;
	/* Collection of gates. */
	private CircuitTree [] circuits;

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
	private Circuit(Integer inputBits, Integer outputBits, Integer minGates,
			Integer maxGates) {

		
		this.inputBits = inputBits;
		this.outputBits = outputBits;
		this.minGates = minGates;
		this.maxGates = maxGates;

		/* Initialize collections. */
		this.inputs = new ArrayList<CircuitComponent>();
		this.circuits = new CircuitTree [outputBits];
		this.outputs = new ArrayList<CircuitComponent>();
	}
	
	/**
	 * Returns a new circuit instance, fully conected. In which the connections and the 
	 * gates between the inputs and the outputs of the circuit are generated randomly.
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
	public static Circuit generateRandomCircuit(Integer inputBits,
			Integer outputBits, Integer minGates, Integer maxGates) {

		Circuit circuit = new Circuit(inputBits, outputBits, minGates, maxGates);

		/* Generate Inputs. */
		Long n = 0L;
		for (Integer i = 0; i < circuit.getInputBits(); i++) {
			CircuitComponent input = new Input();
			input.setId(n++);
			circuit.addInput(input);
		}

		/* Generate the number of gates that will be part of the circuit. */
		CircuitTree [] circuitTrees = new CircuitTree[outputBits];
		for(Integer i = 0; i < circuit.getOutputBits() ; i++){
			CircuitTree newRandomCircuit = CircuitTree.generateRandomCircuitTree(maxGates);
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
	 * Adds an input to the circuit. The CircuitComponent must be and Input class instance.
	 */
	private void addInput(CircuitComponent in) {
		inputs.add(in);
	}

	/**
	 * Adds an output to the circuit. The CircuitComponent must be and Output class instance.
	 */
	private void addOutput(CircuitComponent output) {
		outputs.add(output);
	}
	

	/**
	 * Prints a circuit recursively. The output of this method could be a bit confusing in 
	 * the way that prints the circuit. It might print the same gate twice.
	 */
	public void printCircuit() {

		for(CircuitTree tree: this.getCircuits()){
			System.out.println(tree.toString());
		}
		
	}

	/**
	 * Prints all the components attached to a certain component. 
	 */
	private void printNextComponents(List<CircuitComponent> list,
			String separator) {

		for (CircuitComponent comp : list) {
			System.out.println(separator + comp);
			separator = separator + separator;
			/* Prints the components attached to this component */
			printNextComponents(comp.getNextComponents(), separator);
		}
	}

	/**
	 * This method receives an input for a certain Circuit instance and evaluates
	 * the circuit depending on the given input. 
	 * 
	 * @param input
	 * 		An Integer instance containing a number to be processed by
	 * 		the circuit.
	 * @return
	 */
	@Override
	public Object operate(Object input) {

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
			/* Put the inputs in the evaluation queue */
		}
		Collections.reverse(inputs);
		
		List<CircuitComponent> outputs = this.getOutputs();
		
		for(CircuitComponent currentInput: inputs){
			currentInput.operate();
		}
		
		for(CircuitTree currentTree : this.circuits){
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
			}
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
		CircuitTree [] circuits = this.getCircuits();

		/* Resets the inputs. */
		for (CircuitComponent input : inputs) {
			input.resetComponent();
		}
		/* Resets the outputs. */
		for (CircuitComponent output : outputs) {
			output.resetComponent();
		}
		/* Resets the gates. */
		for (CircuitTree tree : circuits) {
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

	
	public CircuitTree[] getCircuits() {
		return circuits;
	}

	/**
	 * This method returns a new instance of Circuit that is identical to the given circuit.
	 *   
	 */
	public Circuit clone() {
		return null;
	}

	/**
	 * This method is intended to get an already cloned component from the list of components 
	 * that were cloned.
	 * Returns the cloned component or <code>null</code> otherwise.
	 */
	private CircuitComponent getCloned(List<CircuitComponent> alreadyCloned,
			CircuitComponent compN) {
		boolean found = false;
		CircuitComponent aux = null;
		/* Iterates over the list of already cloned components looking for one
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

	public void setCircuits(CircuitTree[] circuits) {
		this.circuits = circuits;
	}
}
