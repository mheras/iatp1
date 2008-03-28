package src.edu.itba.ia.tp1.circuit;

import java.util.ArrayList;
import java.util.Iterator;


public class NOT extends Gate {

		
	public void operate() {
		ArrayList nexts = this.getNextComp();
		Iterator e = nexts.iterator();
		int response = 0;
			if(input[0] == 0)
				response = 1;
			else
				response = 0;
		
		while(e.hasNext()){
			CircuitComponent circ = (CircuitComponent) e.next();
			circ.setInput(response);
		}
		
	}
	
}
