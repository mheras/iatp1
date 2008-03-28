package src.edu.itba.ia.tp1.circuit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;



public abstract class Gate extends CircuitComponent{

	public static final int AND = 0;
	public static final int OR = 1;
	public static final int NOT = 2;
	public static final int GATES = 3;
		
	public abstract void operate();
	
	
	public static Gate randomGate(){
		Random rand = new Random();
		int chosenGate = rand.nextInt(GATES);
		if( chosenGate == AND){
			return  new AND();
		}
		else if (chosenGate == OR){
			return new OR();
		}
		else{
			return new NOT();
		}
		
		
	}
}
