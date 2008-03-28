package src.edu.itba.ia.tp1.circuit;

import java.util.ArrayList;
import java.util.Iterator;


public class Input extends CircuitComponent{

	@Override
	public void operate() {
		ArrayList nexts = this.getNextComp();
		Iterator e = nexts.iterator();
		while(e.hasNext()){
			CircuitComponent circ = (CircuitComponent) e.next();
			circ.setInput(this.input[0]);
		}
	
		
	}

	
}
