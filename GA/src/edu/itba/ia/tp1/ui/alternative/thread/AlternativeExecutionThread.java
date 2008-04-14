package edu.itba.ia.tp1.ui.alternative.thread;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import edu.itba.ia.tp1.engine.A_Problem;
import edu.itba.ia.tp1.engine.Engine;
import edu.itba.ia.tp1.engine.I_Aptitude;
import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.engine.population.Population;
import edu.itba.ia.tp1.engine.population.Utils;
import edu.itba.ia.tp1.engine.population.reproduction.ReproductionAlgorithm;
import edu.itba.ia.tp1.engine.population.selection.I_SelectionAlgorithm;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.CircuitStringProblem;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.algorithm.CircuitStringCrossGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.algorithm.CircuitStringMutationGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuitstring.aptitude.CircuitStringAptitudeImpl;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.AlternativeCircuitTreeProblem;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.algorithm.CircuitTreeCrossGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.algorithm.CircuitTreeMutationGeneticOperation;
import edu.itba.ia.tp1.problem.binary2bcd.circuittree.aptitude.AlternativeCircuitTreeAptitudeImpl;
import edu.itba.ia.tp1.ui.alternative.AbstractAlternativeAptitudeChart;
import edu.itba.ia.tp1.ui.alternative.AlternativeAptitudeBit0Chart;
import edu.itba.ia.tp1.ui.alternative.AlternativeAptitudeBit1Chart;
import edu.itba.ia.tp1.ui.alternative.AlternativeAptitudeBit2Chart;
import edu.itba.ia.tp1.ui.alternative.AlternativeAptitudeBit3Chart;
import edu.itba.ia.tp1.ui.alternative.AlternativeAptitudeBit4Chart;

/**
 * This is the background Swing worker thread implementation to execute and run
 * the engine. Multithreading is used here to avoid UI lags when long-running
 * tasks like this are executed.
 * 
 * @author Martín A. Heras
 * 
 */
public class AlternativeExecutionThread extends SwingWorker<Void, Void> {

	private final String CIRCUIT_TREE = "Circuit Tree";
	// private final String CIRCUIT_STRING = "Circuit String";

	/* Done callback. */
	private IAlternativeExecutionThreadDone doneCallback;
	/* Engine info callback. */
	private IAlternativeEngineInfo engineInfoCallback;
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
	/* Best individual for each output bit. */
	private A_Individual bestBit4;
	private A_Individual bestBit3;
	private A_Individual bestBit2;
	private A_Individual bestBit1;
	private A_Individual bestBit0;

	/**
	 * Creates a new execution thread.
	 * 
	 * @param doneCallback
	 *            Callback used to notify when this thread is done.
	 */
	public AlternativeExecutionThread(
			IAlternativeExecutionThreadDone doneCallback,
			IAlternativeEngineInfo engineInfoCallback) {
		this();
		this.doneCallback = doneCallback;
		this.engineInfoCallback = engineInfoCallback;
	}

	/**
	 * Creates a new execution thread.
	 */
	public AlternativeExecutionThread() {
		// Do nothing.
	}

	private Engine createAppropiateEngine(int bitNumber) {

		I_Aptitude aptitudeAlg;
		ReproductionAlgorithm reproductionAlg;
		A_Problem circuitProblem = null;

		if (this.problemDesc.equalsIgnoreCase(CIRCUIT_TREE)) {

			/* IMPLEMENTACION CIRCUITTREE */

			/* Aptitude function. */
			aptitudeAlg = new AlternativeCircuitTreeAptitudeImpl(bitNumber);

			/* Reproduction: Crossover & mutation. */
			reproductionAlg = new ReproductionAlgorithm(
					new CircuitTreeCrossGeneticOperation(),
					new CircuitTreeMutationGeneticOperation(
							this.mutationProbability), aptitudeAlg);

			circuitProblem = new AlternativeCircuitTreeProblem(
					this.selectionAlgorithm, this.replacementAlgorithm,
					reproductionAlg, aptitudeAlg, this.populationSize);
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

	/**
	 * Returns alternative chart to be painted.
	 * 
	 * @param bitNumber
	 *            Number of the chart.
	 * @return Alternative chart.
	 */
	private AbstractAlternativeAptitudeChart getCurrentChart(int bitNumber) {
		switch (bitNumber) {
		case 0:
			return AlternativeAptitudeBit0Chart.getInstance();
		case 1:
			return AlternativeAptitudeBit1Chart.getInstance();
		case 2:
			return AlternativeAptitudeBit2Chart.getInstance();
		case 3:
			return AlternativeAptitudeBit3Chart.getInstance();
		case 4:
			return AlternativeAptitudeBit4Chart.getInstance();
		default:
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	protected Void doInBackground() throws Exception {

		this.printParameters();

		this.bestBit0 = null;
		this.bestBit1 = null;
		this.bestBit2 = null;
		this.bestBit3 = null;
		this.bestBit4 = null;

		for (int bit = 4; bit >= 0; bit--) {

			if (this.isCancelled()) {
				break;
			}

			// Gets the chart.
			AbstractAlternativeAptitudeChart chart = getCurrentChart(bit);
			chart.reset();
			chart.setMaxGenerations(this.maximumGenerations);

			// Creates the engine based on UI parameters.
			Engine engine = createAppropiateEngine(bit);
			if (this.engineInfoCallback != null) {
				this.engineInfoCallback.onInitPopulationDone(engine);
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
				Double worstAptitude = Utils
						.getWorstAptitude(currentPopulation);

				this.assignBestIndividual(bit, currentPopulation);

				if (this.engineInfoCallback != null) {
					this.engineInfoCallback.onEngineStep(engine, avgAptitude,
							bestAptitude, worstAptitude);
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
		}

		return null;
	}

	/**
	 * Assigns the best individual per output bit, which is notified when
	 * execution is finalized.
	 * 
	 * @param bit
	 *            Output bit.
	 * @param currentPopulation
	 *            The population.
	 */
	private void assignBestIndividual(int bit, Population currentPopulation) {
		A_Individual currentBest = Utils.getBestIndividual(currentPopulation);
		if (bit == 0) {
			if (this.bestBit0 == null) {
				this.bestBit0 = currentBest;
			} else {
				if (this.bestBit0.getAptitude().compareTo(
						currentBest.getAptitude()) <= 0) {
					this.bestBit0 = currentBest;
				}
			}
		} else if (bit == 1) {
			if (this.bestBit1 == null) {
				this.bestBit1 = currentBest;
			} else {
				if (this.bestBit1.getAptitude().compareTo(
						currentBest.getAptitude()) <= 0) {
					this.bestBit1 = currentBest;
				}
			}
		} else if (bit == 2) {
			if (this.bestBit2 == null) {
				this.bestBit2 = currentBest;
			} else {
				if (this.bestBit2.getAptitude().compareTo(
						currentBest.getAptitude()) <= 0) {
					this.bestBit2 = currentBest;
				}
			}
		} else if (bit == 3) {
			if (this.bestBit3 == null) {
				this.bestBit3 = currentBest;
			} else {
				if (this.bestBit3.getAptitude().compareTo(
						currentBest.getAptitude()) <= 0) {
					this.bestBit3 = currentBest;
				}
			}
		} else {
			if (this.bestBit4 == null) {
				this.bestBit4 = currentBest;
			} else {
				if (this.bestBit4.getAptitude().compareTo(
						currentBest.getAptitude()) <= 0) {
					this.bestBit4 = currentBest;
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
		} catch (InterruptedException e) {
		} catch (CancellationException ce) {
		} catch (ExecutionException ee) {
			ee.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.doneCallback != null) {
				this.doneCallback.onExecutionThreadDone(this);
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

	public String getCIRCUIT_TREE() {
		return CIRCUIT_TREE;
	}

	public A_Individual getBestBit4() {
		return bestBit4;
	}

	public A_Individual getBestBit3() {
		return bestBit3;
	}

	public A_Individual getBestBit2() {
		return bestBit2;
	}

	public A_Individual getBestBit1() {
		return bestBit1;
	}

	public A_Individual getBestBit0() {
		return bestBit0;
	}
}
