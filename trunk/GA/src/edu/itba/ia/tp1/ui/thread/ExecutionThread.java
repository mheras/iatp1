package edu.itba.ia.tp1.ui.thread;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import edu.itba.ia.tp1.engine.A_Problem;
import edu.itba.ia.tp1.engine.Engine;
import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.Utils;
import edu.itba.ia.tp1.engine.population.reproduction.ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.I_SelectionAlgorithm;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.CircuitStringAptitudeImpl;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.CircuitStringProblem;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.algorithm.CircuitStringCrossGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.algorithm.CircuitStringMutationGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.CircuitTreeAptitudeImpl;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.CircuitTreeProblem;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.algorithm.CircuitTreeCrossGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.algorithm.CircuitTreeMutationGeneticOperation;
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

	private final String CIRCUIT_TREE = "Circuit Tree";
	// private final String CIRCUIT_STRING = "Circuit String";

	/* Done callback. */
	private IExecutionThreadDone doneCallback;
	/* Engine info callback. */
	private IEngineInfo engineInfoCallback;
	/* Population size. */
	private Long populationSize;
	/* Maximum parents. */
	private Long maximumParents;
	/* Maximum generations. */
	private Long maximumGenerations;
	/* Mutation probability. */
	private Double mutationProbability;
	/* Selection algorithm. */
	private I_SelectionAlgorithm selectionAlgorithm;
	/* Replacement algorithm. */
	private I_SelectionAlgorithm replacementAlgorithm;
	/* Problem description string. */
	private String problemDesc;
	/* Current generation. */
	private long currentGeneration;

	/**
	 * Creates a new execution thread.
	 * 
	 * @param doneCallback
	 *            Callback used to notify when this thread is done.
	 */
	public ExecutionThread(IExecutionThreadDone doneCallback,
			IEngineInfo engineInfoCallback) {
		this();
		this.doneCallback = doneCallback;
		this.engineInfoCallback = engineInfoCallback;
	}

	/**
	 * Creates a new execution thread.
	 */
	public ExecutionThread() {
		// Do nothing.
	}

	private Engine createAppropiateEngine() {

		I_Aptitude aptitudeAlg;
		ReproductionAlgorithm reproductionAlg;
		A_Problem circuitProblem = null;

		if (this.problemDesc.equalsIgnoreCase(CIRCUIT_TREE)) {

			/* IMPLEMENTACION CIRCUITTREE */

			/* Aptitude function. */
			aptitudeAlg = new CircuitTreeAptitudeImpl();

			/* Reproduction: Crossover & mutation. */
			reproductionAlg = new ReproductionAlgorithm(
					new CircuitTreeCrossGeneticOperation(),
					new CircuitTreeMutationGeneticOperation(
							this.mutationProbability), aptitudeAlg);

			circuitProblem = new CircuitTreeProblem(this.selectionAlgorithm,
					this.replacementAlgorithm, reproductionAlg, aptitudeAlg,
					this.populationSize);
		} else {

			/* IMPLEMENTACION CIRCUITSTRING */

			/* Aptitude function. */
			aptitudeAlg = new CircuitStringAptitudeImpl();

			/* Reproduction: Crossover & mutation. */
			reproductionAlg = new ReproductionAlgorithm(
					new CircuitStringCrossGeneticOperation(),
					new CircuitStringMutationGeneticOperation(
							this.mutationProbability), aptitudeAlg);

			circuitProblem = new CircuitStringProblem(this.selectionAlgorithm,
					this.replacementAlgorithm, reproductionAlg, aptitudeAlg,
					this.populationSize);
		}

		return new Engine(circuitProblem, this.maximumParents,
				this.maximumGenerations);
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
		// chart.setSplinesOn(true);
		chart.reset();
		chart.setMaxGenerations(this.maximumGenerations);

		// Creates the engine based on UI parameters.
		Engine engine = createAppropiateEngine();

		if (this.engineInfoCallback != null) {
			this.engineInfoCallback.onInitPopulationDone();
		}

		Population currentPopulation = null;
		this.currentGeneration = 0L;

		while (!this.isCancelled()
				&& this.currentGeneration <= this.maximumGenerations) {

			engine.step();
			currentPopulation = engine.getPopulation();

			// Gathers population statistics.
			Double avgAptitude = Utils.getAptitudeAvg(currentPopulation);
			Double bestAptitude = Utils.getBestAptitude(currentPopulation);
			Double worstAptitude = Utils.getWorstAptitude(currentPopulation);

			if (this.engineInfoCallback != null) {
				this.engineInfoCallback.onEngineStep(avgAptitude, bestAptitude,
						worstAptitude);
			}

			// We update the aptitude chart.
			chart.addGenerationAptitudeAvg(avgAptitude);
			chart.addGenerationBestAptitude(bestAptitude);
			chart.addGenerationWorstAptitude(worstAptitude);
			// Increments counters.
			chart.incrementGeneration();
			this.currentGeneration++;
			// Lets other processes use CPU time.
			Thread.yield();
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
		try {
			super.done();
			this.get();
			if (this.doneCallback != null) {
				this.doneCallback.onExecutionThreadDone(this);
			}
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
			e.printStackTrace();
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

	/**
	 * Gets the selection algorithm.
	 * 
	 * @return The selection algorithm.
	 */
	public I_SelectionAlgorithm getSelectionAlgorithm() {
		return selectionAlgorithm;
	}

	/**
	 * Sets the selection algorithm.
	 * 
	 * @param selectionAlgorithm
	 *            The selection algorithm.
	 */
	public void setSelectionAlgorithm(I_SelectionAlgorithm selectionAlgorithm) {
		this.selectionAlgorithm = selectionAlgorithm;
	}

	/**
	 * Gets the replacement algorithm.
	 * 
	 * @return The replacement algorithm.
	 */
	public I_SelectionAlgorithm getReplacementAlgorithm() {
		return replacementAlgorithm;
	}

	/**
	 * Sets the replacement algorithm.
	 * 
	 * @param replacementAlgorithm
	 *            The replacement algorithm.
	 */
	public void setReplacementAlgorithm(
			I_SelectionAlgorithm replacementAlgorithm) {
		this.replacementAlgorithm = replacementAlgorithm;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}
}
