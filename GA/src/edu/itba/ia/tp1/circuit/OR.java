package src.edu.itba.ia.tp1.circuit;

import java.util.ArrayList;
import java.util.Iterator;


public class OR extends Gate {

	public void operate() {
		ArrayList nexts = this.getNextComp();
		Iterator e = nexts.iterator();
		int response;
		
			response = input[0] | input[1];
//			Propagate result
		while(e.hasNext()){
			CircuitComponent circ = (CircuitComponent) e.next();
			circ.setInput(response);
		}
	
	}

}
