package src.edu.itba.ia.tp1.circuit;

import java.util.ArrayList;

import TP1.circuit.Input;
import TP1.circuit.NOT;
import TP1.circuit.OR;
import TP1.circuit.Output;


public abstract class CircuitComponent {
	
	ArrayList <CircuitComponent> nextComp;
	String ID;
	int []input = {-1,-1};
	
	public abstract void operate();
	
	public CircuitComponent clone(){
		CircuitComponent comp;
		if(this instanceof AND){
			comp = new AND();
		}else if(this instanceof OR){
			comp = new OR();
		}else if(this instanceof NOT){
			comp = new NOT();
		}else if(this instanceof Input){
			comp = new Input();
		}else{
			comp = new Output();
		}
		comp.setID(this.getID());
		return comp;
	}
	
	public boolean isReady(){
		if(this instanceof Input || this instanceof Output || this instanceof NOT){
			return (input[0] != -1);
		}
		return (input[0] != -1) && (input[1]!= -1);
	}
	public void setInput(int in){
		if((this instanceof Input) || (this instanceof Output) || (this instanceof NOT)){
			input[0] = in;
		}else{
			if(input[0] != -1)
				input[1] = in;
			else
				input[0] = in;
		}
		
	}
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public CircuitComponent (){
		nextComp = new ArrayList();
	}
	public boolean hasNextComponent(){
		return nextComp.size() > 0;
	}
	public void addNextComp(CircuitComponent circ){
		this.nextComp.add(circ);
	}

	public ArrayList<CircuitComponent> getNextComp() {
		return nextComp;
	}

	public void setComp(ArrayList<CircuitComponent> comp) {
		nextComp = comp;
	}
	
	public void resetComponent(){
		this.input[0] = -1;
		this.input[1] = -1;
	}
}
