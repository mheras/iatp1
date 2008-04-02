package edu.itba.ia.tp1.problem.binary2bcd.circuit.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jgoldman
 *
 */

public class CircuitTree {

	/* Collection of gates. */
	private List<CircuitComponent> gates;
	
	private CircuitTree(){
		gates = new ArrayList<CircuitComponent>();
	}
	
	public void addGate(CircuitComponent gate){
		gates.add(gate);
	}
	public static CircuitTree generateRandomCircuitTree(Integer maxGates){
		Random rand = new Random();
		int nGates = rand.nextInt(maxGates);
		CircuitTree cTree = new CircuitTree();
		CircuitComponent component = Gate.randomGate();
		cTree.addGate(component);
		generateSons(cTree, component, nGates-1);
		return cTree;
	}
	
	private static void generateSons(CircuitTree tree, CircuitComponent component, int nGates) {
		
		if(nGates > 0){
			if(component instanceof BinaryGate){
				CircuitComponent firstComponent = Gate.randomGate();
				((Gate)firstComponent).setFather(component);
				CircuitComponent secondComponent = Gate.randomGate();
				((Gate)secondComponent).setFather(component);
				tree.addGate(firstComponent);
				tree.addGate(secondComponent);
				((BinaryGate)component).setRightSon(firstComponent);
				((BinaryGate)component).setLeftSon(secondComponent);
				generateSons(tree, firstComponent, nGates - 2);
				generateSons(tree, secondComponent, nGates - 2);
			}else{
				CircuitComponent newComponent = Gate.randomGate();
				((Gate)newComponent).setFather(component);
				tree.addGate(newComponent);
				((UnaryGate)component).setSon(newComponent);
				generateSons(tree, newComponent, nGates - 1);
				
			}
							
		}
		
	}

	public void connectOutput(CircuitComponent output){
		CircuitComponent root = this.getGates().get(0);
		((Gate)root).setFather(output);
	}
	
	public void connectInputs(List<CircuitComponent> Inputs)
	{
		Random rand = new Random();
		int nInputs = Inputs.size();
		int chosenInput;
		List<CircuitComponent> leafs = this.getLeafs();
		for(CircuitComponent component: leafs){
			chosenInput = rand.nextInt(nInputs);
			if(component instanceof BinaryGate){
				((BinaryGate)component).setLeftSon(Inputs.get(chosenInput));
				Inputs.get(chosenInput).addNextComponent(component);
				chosenInput = rand.nextInt(nInputs);
				((BinaryGate)component).setRightSon(Inputs.get(chosenInput));
				Inputs.get(chosenInput).addNextComponent(component);
			}else{
				((UnaryGate)component).setSon(Inputs.get(chosenInput));
				Inputs.get(chosenInput).addNextComponent(component);
			}
			
		}
		
	}
	
	public String toString(){
		
		String evaluationString = "";
		String operation = "";
		/* Gets the roor of the tree. */
		CircuitComponent component = this.getGates().get(0);
		 
		operation = getOperationString(component);
		
		if (component instanceof BinaryGate){
		evaluationString = "(" + generateEvaluationString(((BinaryGate)component).getLeftSon())	+ operation + generateEvaluationString(((BinaryGate)component).getRightSon())+")";	
		}else{
			evaluationString = "(" + getOperationString(component) + generateEvaluationString(((UnaryGate)component).getSon())+ ")";
		}
		
		return evaluationString;
		
		
	}
	private String generateEvaluationString(CircuitComponent component) {
	
		
		if(component instanceof Input){
			return component.getId().toString();
		}else if (component instanceof BinaryGate){
			return "(" + generateEvaluationString(((BinaryGate)component).getLeftSon())
			+ getOperationString(component)+ generateEvaluationString(((BinaryGate)component).getRightSon())+")";
		}else{
			return "(" + getOperationString(component) + generateEvaluationString(((UnaryGate)component).getSon())+ ")";
		}
		
	}

	private String getOperationString(CircuitComponent component) {
		if (component instanceof And){
			return " & ";
		}else if (component instanceof Or){
			return " | ";
		}else{
			return " ^";
		}
		
	}

	/**
	 * @return
	 */
	public List<CircuitComponent> getLeafs(){
		List<CircuitComponent> leafs = new ArrayList<CircuitComponent>();
		
		for(CircuitComponent component: this.getGates()){
			if(isLeaf(component)){
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
		if(component instanceof BinaryGate){
			if(((BinaryGate)component).getLeftSon() == null && ((BinaryGate)component).getRightSon() == null){
				return true;
			}
			else{
				return false;
			}
		}else{
			if(((UnaryGate)component).getSon() == null){
				return true;
			}
			else{
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
