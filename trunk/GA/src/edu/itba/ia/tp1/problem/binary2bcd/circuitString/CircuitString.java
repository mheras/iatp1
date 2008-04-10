package edu.itba.ia.tp1.problem.binary2bcd.circuitString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.CircuitTree;




public class CircuitString extends A_Individual{
	
	/* Collection of inputs. */
	private List<Boolean> inputs;
	
	/* Collection of gates. */
	private CircuitOutputString[] circuits;
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
	private CircuitString(Integer inputBits, Integer outputBits,
			Integer minGates, Integer maxGates) {

		this.inputBits = inputBits;
		this.outputBits = outputBits;
		this.minGates = minGates;
		this.maxGates = maxGates;

		/* Initialize collections. */
		this.inputs = new ArrayList<Boolean>();
		this.circuits = new CircuitOutputString[outputBits];
		
	}

	public static CircuitString generateRandomCircuit(Integer inputBits,
			Integer outputBits, Integer minGates, Integer maxGates) {
		
		CircuitString circuit = new CircuitString(inputBits, outputBits, minGates,
				maxGates);
		
		CircuitOutputString[] circuitStrings = new CircuitOutputString[outputBits];
		for (Integer i = 0; i < circuit.getOutputBits(); i++) {
			CircuitOutputString newRandomCircuit = CircuitOutputString
					.generateRandomCircuitString(circuit.getInputBits(), minGates, maxGates);
			circuitStrings[i] = newRandomCircuit;
		}
		
		circuit.setCircuits(circuitStrings);
		return circuit;
		
	}
	
	public String toString() {

		StringBuffer buffer = new StringBuffer("");

		for (CircuitOutputString tree : this.getCircuits()) {
			buffer.append(tree.toString() + "\n");
		}

		return buffer.toString();
	}
	
	

	/**
	 * This method returns a new instance of Circuit that is identical to the
	 * given circuit.
	 * 
	 */
	public CircuitString clone() {
		CircuitString result = new CircuitString(this.getInputBits(), this.getOutputBits(),this.getMinGates(),this.getMaxGates());
		CircuitOutputString[] circuitStrings = new CircuitOutputString[outputBits];
		
		for(int i = 0; i< this.getOutputBits(); i++){
			circuitStrings[i] = (CircuitOutputString) this.getCircuits()[i].clone(); 
		}
		result.setCircuits(circuitStrings);
		result.setInputs(this.getInputs());
		return result;
		
	}
	
	/**
	 * Mutates the current circuit tree.
	 * 
	 * @param mutationProbability
	 *            Mutation probability.
	 * @return Itself (mutated).
	 */
	public CircuitString performMutation(double mutationProbability) {
		
		for(CircuitOutputString currentOutputString : this.getCircuits()){
			currentOutputString.performMutation(mutationProbability);
		}
		
		return this;
		
	}
	
	
	public static void performCrossover(CircuitString circuit1,
			CircuitString circuit2) {
		
		CircuitOutputString [] circuits1 = circuit1.getCircuits();
		CircuitOutputString [] circuits2 = circuit2.getCircuits();
		
		for(int i = 0; i < circuits1.length; i++){
			CircuitOutputString.performCrossover(circuits1[i], circuits2[i]);
		}
		
	}
	
	public Object operate(Object input) {

		
		if (!(input instanceof Integer)) {
			return null;
		}

		int inputValue = ((Integer) input).intValue();
		/* Put the input in the inputs of the circuit. */
		List<Boolean> inputs = new ArrayList<Boolean>();

		int nInputs = this.inputBits;
		while(nInputs > 0) {
			if ((inputValue % 2) == 1) {
				 inputs.add(new Boolean(true));
			} else {
				 inputs.add(new Boolean(false));
			}
			nInputs--;
			inputValue /= 2;
		}
		Collections.reverse(inputs);
		this.setInputs(inputs);
		int out = 0;
		int power = 1;
		List <Boolean> outputs = new ArrayList<Boolean>();
		for (CircuitOutputString currentTree : this.circuits) {
		
			Boolean operateResult = currentTree.operate(this.inputs);
			outputs.add(operateResult);

		}
		Collections.reverse(outputs);
		for(Boolean bool : outputs){
			if (bool.booleanValue()) {
				out += 1 * power;
			}
			power *= 2;
		}

		/* Generates the output. */
		return new Integer(out);
	}

	public CircuitOutputString[] getCircuits() {
		return circuits;
	}

	public void setCircuits(CircuitOutputString[] circuits) {
		this.circuits = circuits;
	}

	public void setInputs(List<Boolean> inputs) {
		this.inputs = inputs;
	}

	public Integer getInputBits() {
		return inputBits;
	}

	public void setInputBits(Integer inputBits) {
		this.inputBits = inputBits;
	}

	public Integer getMaxGates() {
		return maxGates;
	}

	public void setMaxGates(Integer maxGates) {
		this.maxGates = maxGates;
	}

	public Integer getMinGates() {
		return minGates;
	}

	public void setMinGates(Integer minGates) {
		this.minGates = minGates;
	}

	public Integer getOutputBits() {
		return outputBits;
	}

	public void setOutputBits(Integer outputBits) {
		this.outputBits = outputBits;
	}

	private List<Boolean> getInputs() {
		return inputs;
	}





}
