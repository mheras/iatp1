package edu.itba.ia.tp1.ui.threads;

import javax.swing.SwingWorker;

import edu.itba.ia.tp1.ui.AptitudeChart;

/**
 * This is the background Swing worker thread implementation to execute and run
 * the engine. Multithreading is used here to avoid UI lags when long-running
 * tasks like this are executed.
 * 
 * @author Mart�n A. Heras
 * 
 */
public class ExecutionThread extends SwingWorker<Void, Void> {

	/* Done callback. */
	private IExecutionThreadDone doneCallback;
	/* Population size. */
	private Long populationSize;
	/* Maximum parents. */
	private Long maximumParents;
	/* Maximum generations. */
	private Long maximumGenerations;
	/* Mutation probability. */
	private Double mutationProbability;
	/* Current generation. */
	private long currentGeneration;

	/**
	 * Creates a new execution thread.
	 * @param doneCallback Callback used to notify when this thread is done.
	 */
	public ExecutionThread(IExecutionThreadDone doneCallback) {
		this();
		this.doneCallback = doneCallback;
	}

	/**
	 * Creates a new execution thread.
	 */
	public ExecutionThread() {
		// Do nothing.
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {

		this.printParameters();
		AptitudeChart chart = AptitudeChart.getInstance();
		chart.reset();
		chart.setMaxGenerations(this.maximumGenerations);

		this.currentGeneration = 0L;
		while (!this.isCancelled()
				&& this.currentGeneration <= this.maximumGenerations) {
			// TODO: Here we have to call engine's step method.

			// We update the aptitude chart.
			chart.addGenerationAptitude(Math.random());
			this.currentGeneration++;
			// It might be usefull to configure this sleep from the UI, with a
			// slider.
			Thread.sleep(50);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		super.done();
		if (this.doneCallback != null) {
			this.doneCallback.onExecutionThreadDone(this);
		}
	}

	/**
	 * Prints parameters to the standard output.
	 */
	private void printParameters() {

		System.out.println("*************");
		System.out.println("* EXECUTION *");
		System.out.println("*************");
		System.out.println("");

		System.out.println("Population size: " + populationSize);
		System.out.println("Maximum parents: " + maximumParents);
		System.out.println("Maximum generations: " + maximumGenerations);
		System.out.println("Mutation probability: " + mutationProbability);
		
		System.out.println("");
	}

	/* Getters and setters. */

	/**
	 * Gets population size.
	 * 
	 * @return Population size.
	 */
	public Long getPopulationSize() {
		return populationSize;
	}

	/**
	 * Sets population size.
	 * 
	 * @param populationSize
	 *            Population size.
	 */
	public void setPopulationSize(Long populationSize) {
		this.populationSize = populationSize;
	}

	/**
	 * Gets maximum parents.
	 * 
	 * @return Maximum parents.
	 */
	public Long getMaximumParents() {
		return maximumParents;
	}

	/**
	 * Sets maximum parents.
	 * 
	 * @param maximumParents
	 *            Maximum parents.
	 */
	public void setMaximumParents(Long maximumParents) {
		this.maximumParents = maximumParents;
	}

	/**
	 * Gets maximum generations.
	 * 
	 * @return Maximum generations.
	 */
	public Long getMaximumGenerations() {
		return maximumGenerations;
	}

	/**
	 * Sets maximum generations.
	 * 
	 * @param maximumGenerations
	 *            Maximum generations.
	 */
	public void setMaximumGenerations(Long maximumGenerations) {
		this.maximumGenerations = maximumGenerations;
	}

	/**
	 * Gets mutation probability.
	 * 
	 * @return Mutation probability.
	 */
	public Double getMutationProbability() {
		return mutationProbability;
	}

	/**
	 * Sets mutation probability.
	 * 
	 * @param mutationProbability
	 *            Mutation probability.
	 */
	public void setMutationProbability(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
}
