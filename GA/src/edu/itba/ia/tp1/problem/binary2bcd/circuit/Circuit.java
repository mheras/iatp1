package edu.itba.ia.tp1.problem.binary2bcd.circuit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicOff;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicOn;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.LogicState.LogicState;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.BinaryGate;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.CircuitComponent;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Gate;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Input;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.Output;
import edu.itba.ia.tp1.problem.binary2bcd.circuit.component.UnaryGate;

/**
 * TODO: Comment
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

	private void addInput(CircuitComponent in) {
		inputs.add(in);
	}

	private void addOutput(CircuitComponent output) {
		outputs.add(output);
	}

	private void addGate(CircuitComponent gate) {
		this.gates.add(gate);
	}

	/**
	 * TODO: Comment
	 */
	public void printCircuit() {

		String separator = "\t";
		List<CircuitComponent> inputs = this.getInputs();

		for (CircuitComponent input : inputs) {
			System.out.println(input);
			this.printNextComponents(input.getNextComponents(), separator);
		}
	}

	private void printNextComponents(List<CircuitComponent> list,
			String separator) {

		for (CircuitComponent comp : list) {
			System.out.println(comp);
			this.printNextComponents(comp.getNextComponents(), separator);
		}
	}

	/**
	 * Assume that the input is binary.
	 * 
	 * @param input
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
			evaluationQueue.add(currentInput);
		}
		Collections.reverse(inputs);

		while (!evaluationQueue.isEmpty()) {
			CircuitComponent element = (CircuitComponent) evaluationQueue
					.get(0);
			evaluationQueue.remove(0);
			if (element.isReady()) {
				element.operate();
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

	private void resetCircuit() {

		List<CircuitComponent> inputs = this.getInputs();
		List<CircuitComponent> outputs = this.getOutputs();
		List<CircuitComponent> gates = this.getGates();

		for (CircuitComponent input : inputs) {
			input.resetComponent();
		}

		for (CircuitComponent output : outputs) {
			output.resetComponent();
		}

		for (CircuitComponent gate : gates) {
			gate.resetComponent();
		}
	}

	private List<CircuitComponent> getInputs() {
		return this.inputs;
	}

	private void setInputs(List<CircuitComponent> inputs) {
		this.inputs = inputs;
	}

	private List<CircuitComponent> getOutputs() {
		return outputs;
	}

	private void setOutputs(List<CircuitComponent> outputs) {
		this.outputs = outputs;
	}

	private List<CircuitComponent> getGates() {
		return this.gates;
	}

	private void setGates(List<CircuitComponent> gates) {
		this.gates = gates;
	}

	public Circuit clone() {

		List<CircuitComponent> alreadyCloned = new ArrayList<CircuitComponent>();
		List<CircuitComponent> insCloned = new ArrayList<CircuitComponent>();
		List<CircuitComponent> partsCloned = new ArrayList<CircuitComponent>();
		List<CircuitComponent> outsCloned = new ArrayList<CircuitComponent>();

		for (CircuitComponent input : this.getInputs()) {
			CircuitComponent cloned = input.clone();
			alreadyCloned.add(cloned);
		}

		for (CircuitComponent gate : this.getGates()) {
			CircuitComponent cloned = gate.clone();
			alreadyCloned.add(cloned);
		}

		for (CircuitComponent output : this.getOutputs()) {
			CircuitComponent cloned = output.clone();
			alreadyCloned.add(cloned);
		}

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

	private CircuitComponent getCloned(List<CircuitComponent> alreadyCloned,
			CircuitComponent compN) {
		boolean found = false;
		CircuitComponent aux = null;
		
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

	public Integer getInputBits() {
		return inputBits;
	}

	public Integer getOutputBits() {
		return outputBits;
	}

	public Integer getMinGates() {
		return minGates;
	}

	public Integer getMaxGates() {
		return maxGates;
	}
}
