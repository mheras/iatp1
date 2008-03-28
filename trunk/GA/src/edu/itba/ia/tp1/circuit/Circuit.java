package src.edu.itba.ia.tp1.circuit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;




import com.sun.corba.se.pept.transport.OutboundConnectionCache;

public class Circuit {

	static ArrayList <CircuitComponent> inputs;
	static ArrayList <CircuitComponent> parts;
	static ArrayList <CircuitComponent> outputs;
	
	public static final int BITSINPUT = 8;
	public static final int BITSOUTPUT = 8;
	public static final int MINGATES = 2;
	public static final int MAXGATES = 5;
	public Circuit(){
		inputs = new ArrayList<CircuitComponent>();
		parts = new ArrayList<CircuitComponent>();
		outputs = new ArrayList<CircuitComponent>();
	}
	
	public static Circuit generateRandomCircuit(){
		Circuit circuit = new Circuit();
		//Generate Inputs
		int n = 0;
		for(int x = 0; x < BITSINPUT; x++){
			CircuitComponent in = new Input();
			in.setID(String.valueOf(n++));
			circuit.addInput(in);
		}
		//Generate the number of gates that will be part of the circuit
		Random rand = new Random();
		rand.setSeed(Calendar.MILLISECOND);
		int cantGates = rand.nextInt(MAXGATES-MINGATES);
		cantGates += MINGATES;
		
		//Generates the circuit
		ArrayList componentBag = new ArrayList();
		componentBag.addAll(circuit.getInputs());
		
		for(int i = 0 ; i < cantGates; i++){
			CircuitComponent component = Gate.randomGate();
			component.setID(String.valueOf(n++));
			if((component instanceof OR) || (component instanceof AND) ){
				CircuitComponent input1 = (CircuitComponent) componentBag.get(rand.nextInt(componentBag.size()));
				CircuitComponent input2 = (CircuitComponent) componentBag.get(rand.nextInt(componentBag.size()));
				input1.addNextComp(component);
				input2.addNextComp(component);
				
			}
			else if(component instanceof NOT){
				CircuitComponent input1 = (CircuitComponent) componentBag.get(rand.nextInt(componentBag.size()));
				input1.addNextComp(component);
				
			}
			componentBag.add(component);
			
		}
		//Check if there is no input without a connection
		for(int x = 0; x < BITSINPUT; x++){
			if(!circuit.getInputs().get(x).hasNextComponent()){
				CircuitComponent component = Gate.randomGate();
				component.setID(String.valueOf(x));
				CircuitComponent ins = circuit.getInputs().get(x);
				if((component instanceof OR) || (component instanceof AND) ){
					CircuitComponent input1 = (CircuitComponent) componentBag.get(rand.nextInt(componentBag.size()));
					ins.addNextComp(component);
					input1.addNextComp(component);
				}else if(component instanceof NOT){
					circuit.getInputs().get(x).addNextComp(component);
					
				}
				componentBag.add(component);
				
			}
			
		}
		
		//Generate Outputs
		//Connect the outputs randomly
		for(int x = 0; x < BITSOUTPUT; x++){
			CircuitComponent output = new Output();
			output.setID(String.valueOf(x));
			circuit.addOutput(output);
			CircuitComponent out = (CircuitComponent) componentBag.get(rand.nextInt(componentBag.size()));
			out.addNextComp(output);
		}
		
		//Put the parts in the circuit
		for(int x = 0; x < componentBag.size(); x++)
		{
			CircuitComponent compiz = (CircuitComponent) componentBag.get(x);
			if( !(compiz instanceof Input) && !(compiz instanceof Output)){
				circuit.addPart(compiz);
			}
		}
		
		return circuit;
		
	}
	
	private void addInput(CircuitComponent in){
		inputs.add(in);
	}
	
	private void addOutput(CircuitComponent output){
		outputs.add(output);
	}
	
	private void addPart(CircuitComponent part){
		parts.add(part);
	}
	
	public void printCircuit(){
		ArrayList entradas = this.getInputs();
		Iterator e = entradas.iterator();
		while(e.hasNext()){
			CircuitComponent ent = (CircuitComponent) e.next();
			System.out.println("Entrada" + ent.getID() );
			printHijos(ent.getNextComp(), "	");
	
		}
	} 
	private void printHijos(ArrayList<CircuitComponent> nextComp, String string) {
		
		if(nextComp != null){
			Iterator e = nextComp.iterator();
			while(e.hasNext()){
				CircuitComponent component = (CircuitComponent) e.next();
				if(component instanceof AND){
					System.out.println(string +"AND" + component.getID());
				}else if(component instanceof OR){
					System.out.println(string +"OR" + component.getID());
				}else if(component instanceof NOT){
					System.out.println(string +"NOT"+ component.getID());
				}
				else if(component instanceof Output){
					System.out.println(string +"Salida"+ component.getID());
				}
				printHijos(component.nextComp, string + "	");
			}
		}
			
			
	}

	/**
	 * Assume that the input is binary
	 * @param input
	 * @return
	 */
	public long operate(int input){
		//Put the input in the inputs of the circuit
		ArrayList ins = this.getInputs();
		//Collections.reverse(ins);
		Iterator e = ins.iterator();
		
		ArrayList<CircuitComponent> evaluationQueue = new ArrayList<CircuitComponent>();
		
		while(e.hasNext()){
			CircuitComponent entrance = (CircuitComponent) e.next();
			entrance.setInput(input % 10);
			input /= 10;
			evaluationQueue.add(entrance);
		}
		
		
	
		while(!evaluationQueue.isEmpty()){
			CircuitComponent element = (CircuitComponent) evaluationQueue.get(0);
			evaluationQueue.remove(0);
			if(element.isReady()){
				element.operate();
				if(!(element instanceof Output)){
					ArrayList nextComponents = element.getNextComp();
					Iterator ix = nextComponents.iterator();
					while(ix.hasNext()){
						CircuitComponent p = (CircuitComponent) ix.next();
						if(!evaluationQueue.contains(p))
							evaluationQueue.add(p);
					}
				}
			}else{
				evaluationQueue.add(element);
			}
			
		}
		
		//Generates the output
		long out = 0;
		ArrayList outputs = this.getOutputs();
		Collections.reverse(outputs);
		
		Iterator ep = outputs.iterator();
		while(ep.hasNext()){
			Output theOut = (Output) ep.next();
			int res = theOut.getOutputValue();
			out = out * 10 + res;
		}
		Collections.reverse(outputs);
		this.resetCircuit();
		
		return out;
		
		
		
	}

	private void resetCircuit() {
		ArrayList ins = this.getInputs();
		ArrayList outs = this.getOutputs();
		ArrayList parts = this.getParts();
		
		Iterator i = ins.iterator();
		Iterator o = outs.iterator();
		Iterator p = parts.iterator();
		
		while(i.hasNext()){
			CircuitComponent x = (CircuitComponent) i.next();
			x.resetComponent();
		}
		while(o.hasNext()){
			CircuitComponent y = (CircuitComponent) o.next();
			y.resetComponent();
		
		}
		while(p.hasNext()){
			CircuitComponent z = (CircuitComponent) p.next();
			z.resetComponent();
		}
		
	}

	private static ArrayList<CircuitComponent> getInputs() {
		return inputs;
	}

	private static void setInputs(ArrayList<CircuitComponent> inputs) {
		Circuit.inputs = inputs;
	}

	private static ArrayList<CircuitComponent> getOutputs() {
		return outputs;
	}

	private static void setOutputs(ArrayList<CircuitComponent> outputs) {
		Circuit.outputs = outputs;
	}

	private static ArrayList<CircuitComponent> getParts() {
		return parts;
	}

	private static void setParts(ArrayList<CircuitComponent> parts) {
		Circuit.parts = parts;
	}
	
	public synchronized Circuit clone(){
		ArrayList alreadyCloned = new ArrayList();
		ArrayList insCloned = new ArrayList();
		ArrayList partsCloned = new ArrayList();
		ArrayList outsCloned = new ArrayList();
		
		Iterator e = this.getInputs().iterator();
		while(e.hasNext()){
			CircuitComponent comp = (CircuitComponent) e.next();
			CircuitComponent cloned = comp.clone();
			alreadyCloned.add(cloned);	
		}
		e = this.getParts().iterator();
		while(e.hasNext()){
			CircuitComponent comp = (CircuitComponent) e.next();
			CircuitComponent cloned = comp.clone();
			alreadyCloned.add(cloned);	
		}
		e = this.getOutputs().iterator();
		while(e.hasNext()){
			CircuitComponent comp = (CircuitComponent) e.next();
			CircuitComponent cloned = comp.clone();
			alreadyCloned.add(cloned);	
		}
		
		ArrayList total = new ArrayList();
		total.addAll(this.getInputs());
		total.addAll(this.getParts());
		e = total.iterator();
		while(e.hasNext()){
			CircuitComponent component = (CircuitComponent) e.next();
			if(!(component instanceof Output)){
				ArrayList compoSons = component.getNextComp();
				Iterator x = compoSons.iterator();
				CircuitComponent clonedFather = getCloned(alreadyCloned, component);
				while(x.hasNext()){
					CircuitComponent son = (CircuitComponent) x.next();
					CircuitComponent clonedSon = getCloned(alreadyCloned, son);
					//if(!clonedFather.getNextComp().contains(clonedSon)){
						clonedFather.addNextComp(clonedSon);
				//	}
				}
			
			}
		}
		Iterator a = alreadyCloned.iterator();
		while(a.hasNext()){
			CircuitComponent c = (CircuitComponent) a.next();
			if(c instanceof Gate){
				partsCloned.add(c);
			}else if( c instanceof Output){
				outsCloned.add(c);
			}else{
				insCloned.add(c);
			}
		}
		Circuit circuit = new Circuit();
		circuit.setInputs(insCloned);
		circuit.setOutputs(outsCloned);
		circuit.setParts(partsCloned);
		return circuit;
		//TODO
		
	}
	

	private CircuitComponent getCloned(ArrayList alreadyCloned, CircuitComponent compN) {
		boolean found = false;
		CircuitComponent aux = null;
		Iterator e = alreadyCloned.iterator();
		while(e.hasNext() && !found){
			CircuitComponent c = (CircuitComponent) e.next();
			if(c.getID().equals(compN.getID()) ){
				if((c.getClass().equals(compN.getClass()))){
					aux = c;
					found = true;
				}
			}
		}
		return aux;
	}

	public void mutateCircuit(){
//		TODO
	}
	
	public static void crossOverCircuits(Circuit circuit1, Circuit circuit2){
//		TODO
	}
	
	
}
