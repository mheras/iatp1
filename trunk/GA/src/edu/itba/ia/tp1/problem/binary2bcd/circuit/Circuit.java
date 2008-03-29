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
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Gate;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Input;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Output;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.UnaryGate;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicOff;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicOn;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.logicstate.LogicState;

/**
 * 
 * @author Jorge Goldman & Martín A. Heras
 * 
 * This class implementates a circuit. The main concept behind of this implementation
 * is a graph that all of its nodes must be correctly connected, based on the concepts
 * of a circuit.
 * 
 */


public class Circuit extends A_Individual {

	/* Collection of inputs. */
	private List<CircuitComponent> inputs;
	/* Collection of outputs. */
	private List<CircuitComponent> outputs;
	/* Collection of gates. */
	private List<CircuitComponent> gates;

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
		this.gates = new ArrayList<CircuitComponent>();
		this.outputs = new ArrayList<CircuitComponent>();
	}
	/**
	 * Returns a new circuit instance, fully conected. In which the connections and the 
	 * gates between the inputs and the outputs of the circuit are generated randomly
	 * 
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
		Random rand = new Random();
		rand.setSeed(Calendar.MILLISECOND);
		Integer nGates = rand.nextInt(circuit.getMaxGates()
				- circuit.getMinGates());
		nGates += circuit.getMinGates();

		/* Generates the circuit. */
		List<CircuitComponent> componentBag = new ArrayList<CircuitComponent>();
		componentBag.addAll(circuit.getInputs());

		for (int i = 0; i < nGates; i++) {

			/* Generate random gate. */
			CircuitComponent component = Gate.randomGate();
			component.setId(n++);

			if (component instanceof BinaryGate) {
				/*
				 * Attach two component outputs (inputs or another gate output)
				 * to the BinaryGate inputs (if applicable).
				 */
				CircuitComponent input1 = (CircuitComponent) componentBag
						.get(rand.nextInt(componentBag.size()));
				CircuitComponent input2 = (CircuitComponent) componentBag
						.get(rand.nextInt(componentBag.size()));
				input1.addNextComponent(component);
				input2.addNextComponent(component);
			} else if (component instanceof UnaryGate) {
				/*
				 * Attach a component output (input or another gate output) to
				 * the UnaryGate input (if applicable).
				 */
				CircuitComponent input1 = (CircuitComponent) componentBag
						.get(rand.nextInt(componentBag.size()));
				input1.addNextComponent(component);

			}
			componentBag.add(component);
		}

		/* Check if there is no input without a connection. */
		for (Integer i = 0; i < circuit.getInputBits(); i++) {

			if (!circuit.getInputs().get(i).hasNextComponent()) {

				/* Generate a new random gate. */
				CircuitComponent component = Gate.randomGate();
				component.setId(new Long(i.longValue()));

				/* Get the unconnected input. */
				CircuitComponent unconnected = circuit.getInputs().get(i);
				if (component instanceof BinaryGate) {
					CircuitComponent input1 = (CircuitComponent) componentBag
							.get(rand.nextInt(componentBag.size()));
					unconnected.addNextComponent(component);
					input1.addNextComponent(component);
				} else if (component instanceof UnaryGate) {
					circuit.getInputs().get(i).addNextComponent(component);
				}
				componentBag.add(component);
			}

		}

		/* Generate outputs and connect the outputs randomly. */
		for (Integer i = 0; i < circuit.getOutputBits(); i++) {
			CircuitComponent output = new Output();
			output.setId(new Long(i.longValue()));
			circuit.addOutput(output);
			CircuitComponent out = (CircuitComponent) componentBag.get(rand
					.nextInt(componentBag.size()));
			out.addNextComponent(output);
		}

		/* Put the parts in the circuit. */
		for (int i = 0; i < componentBag.size(); i++) {
			CircuitComponent gate = (CircuitComponent) componentBag.get(i);
			if (gate instanceof Gate) {
				circuit.addGate(gate);
			}
		}

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
	 * Adds an gate to the circuit. The CircuitComponent must be and Gate class instance.
	 */
	private void addGate(CircuitComponent gate) {
		this.gates.add(gate);
	}

	/**
	 * Prints a circuit recursively. The output of this method could be a bit confusing in 
	 * the way that prints the circuit. It might print the same gate twice.
	 */
	public void printCircuit() {

		String separator = "	";
		List<CircuitComponent> inputs = this.getInputs();
		/* Prints the inputs of the circuit. */
		for (CircuitComponent input : inputs) {
			System.out.println(input);
			/* Prints the components attached to this input*/
			this.printNextComponents(input.getNextComponents(), separator);
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
		List<CircuitComponent> evaluationQueue = new ArrayList<CircuitComponent>();

		Collections.reverse(inputs);
		for (CircuitComponent currentInput : inputs) {
			if ((inputValue % 2) == 1) {
				currentInput.setInput(new LogicOn());
			} else {
				currentInput.setInput(new LogicOff());
			}
			inputValue /= 2;
			/* Put the inputs in the evaluation queue */
			evaluationQueue.add(currentInput);
		}
		Collections.reverse(inputs);
		
		/* Operates on every Circuit Component in the operation queue, until the queue is empty. */
		while (!evaluationQueue.isEmpty()) {
			CircuitComponent element = (CircuitComponent) evaluationQueue
					.get(0);
			
			/* Takes the first element of the queue. */
			evaluationQueue.remove(0);
			
			/* Checks if the element is ready, if it is, operates on the component,
			 * if it's not, it goes back to the queue.
			 */
			if (element.isReady()) {
				element.operate();
				
				/* If the component is not an output, enqueue the components attached to
				 * to the componene we operated over before.
				 */
				if (!(element instanceof Output)) {
					List<CircuitComponent> nextComponents = element
							.getNextComponents();
					for (CircuitComponent currentComponent : nextComponents) {
						if (!evaluationQueue.contains(currentComponent))
							evaluationQueue.add(currentComponent);
					}
				}
			} else {
				evaluationQueue.add(element);
			}
		}

		/* Generates the output. */
		int out = 0;
		List<CircuitComponent> outputs = this.getOutputs();
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
		List<CircuitComponent> gates = this.getGates();

		/* Resets the inputs. */
		for (CircuitComponent input : inputs) {
			input.resetComponent();
		}
		/* Resets the outputs. */
		for (CircuitComponent output : outputs) {
			output.resetComponent();
		}
		/* Resets the gates. */
		for (CircuitComponent gate : gates) {
			gate.resetComponent();
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
	 * Returns a List of the gates of the circuit.
	 */
	private List<CircuitComponent> getGates() {
		return this.gates;
	}
	/**
	 * Sets a List of the gates of the circuit.
	 */
	private void setGates(List<CircuitComponent> gates) {
		this.gates = gates;
	}


	/**
	 * This method returns a new instance of Circuit that is identical to the given circuit.
	 *   
	 */
	public Circuit clone() {

		/* List of the components that has already been cloned. */
		List<CircuitComponent> alreadyCloned = new ArrayList<CircuitComponent>();
		/* List of the inputs of the circuit that were cloned. */
		List<CircuitComponent> insCloned = new ArrayList<CircuitComponent>();
		/* List of the gates of the circuit that were cloned. */
		List<CircuitComponent> partsCloned = new ArrayList<CircuitComponent>();
		/* List of the outputs of the circuit that were cloned. */
		List<CircuitComponent> outsCloned = new ArrayList<CircuitComponent>();

		/* Clone the inputs of the circuit first. */
		for (CircuitComponent input : this.getInputs()) {
			CircuitComponent cloned = input.clone();
			alreadyCloned.add(cloned);
		}
		/* Clone the gates of the circuit. */
		for (CircuitComponent gate : this.getGates()) {
			CircuitComponent cloned = gate.clone();
			alreadyCloned.add(cloned);
		}

		/* Clone the outputs of the circuit. */
		for (CircuitComponent output : this.getOutputs()) {
			CircuitComponent cloned = output.clone();
			alreadyCloned.add(cloned);
		}

		/* Now, let´s connect the cloned components in the same way that the real
		 * circuit was connected.
		 */
		List<CircuitComponent> total = new ArrayList<CircuitComponent>();
		total.addAll(this.getInputs());
		total.addAll(this.getGates());

		for (CircuitComponent component : total) {
			if (!(component instanceof Output)) {

				List<CircuitComponent> nextComps = component
						.getNextComponents();

				CircuitComponent clonedFather = getCloned(alreadyCloned,
						component);
				for (CircuitComponent nextComp : nextComps) {
					CircuitComponent clonedNextComp = getCloned(alreadyCloned,
							nextComp);
					clonedFather.addNextComponent(clonedNextComp);
				}
			}
		}

		/* Build up the connected components in the new circuit instance*/
		for (CircuitComponent clonedComponent : alreadyCloned) {
			if (clonedComponent instanceof Gate) {
				partsCloned.add(clonedComponent);
			} else if (clonedComponent instanceof Output) {
				outsCloned.add(clonedComponent);
			} else {
				insCloned.add(clonedComponent);
			}
		}

		Circuit circuit = new Circuit(this.getInputBits(),
				this.getOutputBits(), this.getMinGates(), this.getMaxGates());
		circuit.setInputs(insCloned);
		circuit.setOutputs(outsCloned);
		circuit.setGates(partsCloned);
		
		return circuit;
	}

	/**
	 * This method is intended to get an already cloned component from the list of components 
	 * that were cloned.
	 * Returns the cloned component or <code>null</code> otherwise
	 */
	private CircuitComponent getCloned(List<CircuitComponent> alreadyCloned,
			CircuitComponent compN) {
		boolean found = false;
		CircuitComponent aux = null;
		/* Iterates over the list of already cloned components lloking for one
		 * with the same id of the requested component.
		 * 
		 * */
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
}
