package edu.itba.ia.tp1.ui.alternative;

/**
 * Abstract class of alternative aptitude charts.
 * 
 * @author Martín A. Heras
 *
 */
public abstract class AbstractAlternativeAptitudeChart {

	public abstract void reset();
	
	public abstract void setMaxGenerations(long maxGenerations);
	
	public abstract void addGenerationAptitudeAvg(double aptitude);
	
	public abstract void addGenerationBestAptitude(double aptitude);
	
	public abstract void addGenerationWorstAptitude(double aptitude);
	
	public abstract void incrementGeneration();
}
