package edu.itba.ia.tp1.ui.divideandconquer.thread;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import edu.itba.ia.tp1.engine.A_Problem;
import edu.itba.ia.tp1.engine.Engine;
import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.Utils;
import edu.itba.ia.tp1.engine.population.reproduction.ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.I_SelectionAlgorithm;
import edu.itba.ia.tp1.problem.binary2bcd.AbstractCircuit;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.DivideAndConquerCircuitStringProblem;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.algorithm.CircuitStringCrossGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.algorithm.CircuitStringMutationGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.aptitude.DivideAndConquerCircuitStringAptitudeImpl;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.DivideAndConquerCircuitTreeProblem;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.algorithm.CircuitTreeCrossGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.algorithm.CircuitTreeMutationGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.aptitude.DivideAndConquerCircuitTreeAptitudeImpl;
import edu.itba.ia.tp1.ui.AptitudeChart;
import edu.itba.ia.tp1.ui.thread.IEngineInfo;
import edu.itba.ia.tp1.ui.thread.IExecutionThreadDone;

/**
 * This is the background Swing worker thread implementation to execute and run
 * the engine. Multithreading is used here to avoid UI lags when long-running
 * tasks like this are executed.
 * 
 * @author Martín A. Heras
 * 
 */
public class DivideAndConquerExecutionThread extends SwingWorker<Void, Void> {

	private final String CIRCUIT_TREE = "Circuit Tree";
	// private final String CIRCUIT_STRING = "Circuit String";
	
	/* Algorithm best circuit. */
	private AbstractCircuit bestCircuit;
	/* Done callback. */
	private IExecutionThreadDone doneCallback;
	/* Engine info callback. */
	private IEngineInfo engineInfoCallback;
	/* Population size. */
	private Long populationSize;
	/* Parents pool. */
	private Long parentsPool;
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
	/* Current bit. */
	private int currentBit;
	/* Current generation. */
	private long currentGeneration;

	/**
	 * Creates a new execution thread.
	 * 
	 * @param doneCallback
	 *            Callback used to notify when this thread is done.
	 */
	public DivideAndConquerExecutionThread(IExecutionThreadDone doneCallback,
			IEngineInfo engineInfoCallback) {
		this();
		this.doneCallback = doneCallback;
		this.engineInfoCallback = engineInfoCallback;
	}

	/**
	 * Creates a new execution thread.
	 */
	public DivideAndConquerExecutionThread() {
		// Do nothing.
	}

	private Engine createAppropiateEngine(int currentBit) {

		I_Aptitude aptitudeAlg;
		ReproductionAlgorithm reproductionAlg;
		A_Problem circuitProblem = null;

		if (this.problemDesc.equalsIgnoreCase(CIRCUIT_TREE)) {

			/* IMPLEMENTACION CIRCUITTREE */

			/* Aptitude function. */
			aptitudeAlg = new DivideAndConquerCircuitTreeAptitudeImpl(currentBit);

			/* Reproduction: Crossover & mutation. */
			reproductionAlg = new ReproductionAlgorithm(
					new CircuitTreeCrossGeneticOperation(),
					new CircuitTreeMutationGeneticOperation(
							this.mutationProbability), aptitudeAlg);

			circuitProblem = new DivideAndConquerCircuitTreeProblem(this.selectionAlgorithm,
					this.replacementAlgorithm, reproductionAlg, aptitudeAlg,
					this.populationSize);
		} else {

			/* IMPLEMENTACION CIRCUITSTRING */

			/* Aptitude function. */
			aptitudeAlg = new DivideAndConquerCircuitStringAptitudeImpl(currentBit);

			/* Reproduction: Crossover & mutation. */
			reproductionAlg = new ReproductionAlgorithm(
					new CircuitStringCrossGeneticOperation(),
					new CircuitStringMutationGeneticOperation(
							this.mutationProbability), aptitudeAlg);

			circuitProblem = new DivideAndConquerCircuitStringProblem(this.selectionAlgorithm,
					this.replacementAlgorithm, reproductionAlg, aptitudeAlg,
					this.populationSize);
		}

		return new Engine(circuitProblem, this.parentsPool,
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
		Engine engine = createAppropiateEngine(this.currentBit);

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

			this.refreshBestCircuit(Utils.getBestCircuit(currentPopulation));

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
	
	/**
	 * Refreshes the best circuit.
	 * @param currentBest The current step best circuit.
	 */
	private void refreshBestCircuit(AbstractCircuit currentBest) {
		if (this.bestCircuit == null) {
			this.bestCircuit = currentBest;
		} else {
			if (currentBest.getAptitude().compareTo(this.bestCircuit.getAptitude()) > 0) {
				// If it has better aptitude, it is the best.
				this.bestCircuit = currentBest;
			} else if (currentBest.getAptitude().compareTo(this.bestCircuit.getAptitude()) == 0) {
				// If it has the best one aptitude, checks if it has less gates. 
				if (currentBest.getGatesLength() < this.bestCircuit.getGatesLength()) {
					this.bestCircuit = currentBest;
				}
			}
		}
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
		} catch (InterruptedException ie) {
		} catch (CancellationException ce) {
		} catch (ExecutionException ee) {
			ee.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.doneCallback != null) {
				this.doneCallback.onExecutionThreadDone(this.bestCircuit);
			}
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
		System.out.println("Parents pool: " + parentsPool);
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
	 * Gets parents pool.
	 * 
	 * @return Parents pool.
	 */
	public Long getParentsPool() {
		return parentsPool;
	}

	/**
	 * Sets parents pool.
	 * 
	 * @param parentsPool
	 *            Parents pool.
	 */
	public void setParentsPool(Long parentsPool) {
		this.parentsPool = parentsPool;
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

	public int getCurrentBit() {
		return currentBit;
	}

	public void setCurrentBit(int currentBit) {
		this.currentBit = currentBit;
	}
}
