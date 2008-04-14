package edu.itba.ia.tp1.ui.alternative.listener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import edu.itba.ia.tp1.engine.Engine;
import edu.itba.ia.tp1.engine.population.A_Individual;
import edu.itba.ia.tp1.engine.population.selection.I_SelectionAlgorithm;
import edu.itba.ia.tp1.ui.alternative.AlternativeFrame;
import edu.itba.ia.tp1.ui.alternative.thread.AlternativeExecutionThread;
import edu.itba.ia.tp1.ui.alternative.thread.IAlternativeEngineInfo;
import edu.itba.ia.tp1.ui.alternative.thread.IAlternativeExecutionThreadDone;
import edu.itba.ia.tp1.ui.thread.ThreadsBag;

/**
 * SwitchExecute button listener. This class handles SwitchExecute button
 * behavior when it is clicked.
 * 
 * @author Martín A. Heras
 * 
 */
public class AlternativeSwitchExecuteActionListener implements ActionListener,
		IAlternativeExecutionThreadDone, IAlternativeEngineInfo {

	private final String EXECUTE = "Execute";
	private final String CANCEL = "Cancel";
	/* Main frame. */
	private AlternativeFrame alternativeFrame;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {

		JButton source = (JButton) event.getSource();

		/*
		 * If button says cancel, just cancel the active worker thread.
		 */
		if (source.getText().equalsIgnoreCase(CANCEL)) {
			AlternativeExecutionThread thread = ThreadsBag.getInstance()
					.getAlternativeExecutionThread();
			if (thread != null) {
				thread.cancel(true);
				this.alternativeFrame.getLabelInfo().setText("Execution cancelled");
			}
			return;
		}

		this.alternativeFrame = (AlternativeFrame) this.getParentFrame(source);

		this.alternativeFrame.getLabelInfo().setText("Initializing population...");

		/* Retrieves parameters from UI components. */
		Long populationSize = (Long) this.alternativeFrame.getSpinnerPopulationSize()
				.getValue();
		Long maximumParents = (Long) this.alternativeFrame.getSpinnerMaximumParents()
				.getValue();
		Long maximumGenerations = (Long) this.alternativeFrame
				.getSpinnerMaximumGenerations().getValue();
		Double mutationProbability = (Double) this.alternativeFrame
				.getSpinnerMutationProbability().getValue();
		I_SelectionAlgorithm selection = (I_SelectionAlgorithm) this.alternativeFrame
				.getComboSelectionMethod().getSelectedItem();
		I_SelectionAlgorithm replacement = (I_SelectionAlgorithm) this.alternativeFrame
				.getComboReplacementMethod().getSelectedItem();
		String problemDesc = (String) this.alternativeFrame.getComboProblemImpl()
				.getSelectedItem();	

		/* Creates a worker thread, configures it and executes it. */
		AlternativeExecutionThread thread = new AlternativeExecutionThread(this, this);
		thread.setMaximumGenerations(maximumGenerations);
		thread.setMaximumParents(maximumParents);
		thread.setMutationProbability(mutationProbability);
		thread.setPopulationSize(populationSize);
		thread.setSelectionAlgorithm(selection);
		thread.setReplacementAlgorithm(replacement);
		thread.setProblemDesc(problemDesc);

		ThreadsBag.getInstance().setAlternativeExecutionThread(thread);
		thread.execute();

		source.setText(CANCEL);
	}

	/**
	 * Gets the component's parent frame.
	 * 
	 * @param component
	 *            The <code>JComponent</code>, like a button, a spinner or
	 *            whatever.
	 * @return The frame owner.
	 */
	private JFrame getParentFrame(JComponent component) {

		Container container = component.getParent();

		while (!(container instanceof JFrame)) {
			try {
				container = container.getParent();
			} catch (NullPointerException e) {
				return null;
			}
		}

		return (JFrame) container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.ui.threads.IExecutionThreadDone#onExecutionThreadDone(edu.itba.ia.tp1.ui.threads.ExecutionThread)
	 */
	public void onExecutionThreadDone(AlternativeExecutionThread executionThread) {
		if (this.alternativeFrame != null) {
			this.alternativeFrame.getButtonSwitchExecution().setText(EXECUTE);
			this.alternativeFrame.getLabelInfo().setText("Execution finalized");
		}
		
		
		
		System.out.println("BIT 4:");
		System.out.println("Aptitude: "+ executionThread.getBestBit4().getAptitude());
		printInputOutput(executionThread.getBestBit4());
		System.out.println(executionThread.getBestBit4().toString());
		
		
		System.out.println("BIT 3:");
		System.out.println("Aptitude: "+ executionThread.getBestBit3().getAptitude());
		printInputOutput(executionThread.getBestBit3());
		System.out.println(executionThread.getBestBit3().toString());

		System.out.println("BIT 2:");
		System.out.println("Aptitude: "+ executionThread.getBestBit2().getAptitude());
		printInputOutput(executionThread.getBestBit2());
		System.out.println(executionThread.getBestBit2().toString());

		System.out.println("BIT 1:");
		System.out.println("Aptitude: "+ executionThread.getBestBit1().getAptitude());
		printInputOutput(executionThread.getBestBit1());
		System.out.println(executionThread.getBestBit1().toString());

		System.out.println("BIT 0:");
		System.out.println("Aptitude: "+ executionThread.getBestBit0().getAptitude());
		printInputOutput(executionThread.getBestBit0());
		System.out.println(executionThread.getBestBit0().toString());

	}
	
	private void printInputOutput(A_Individual ind) {
		System.out.println("0: " + ind.operate(0));
		System.out.println("1: " + ind.operate(1));
		System.out.println("2: " + ind.operate(2));
		System.out.println("3: " + ind.operate(3));
		System.out.println("4: " + ind.operate(4));
		System.out.println("5: " + ind.operate(5));
		System.out.println("6: " + ind.operate(6));
		System.out.println("7: " + ind.operate(7));
		System.out.println("8: " + ind.operate(8));
		System.out.println("9: " + ind.operate(9));
		System.out.println("10: " + ind.operate(10));
		System.out.println("11: " + ind.operate(11));
		System.out.println("12: " + ind.operate(12));
		System.out.println("13: " + ind.operate(13));
		System.out.println("14: " + ind.operate(14));
		System.out.println("15: " + ind.operate(15));
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.ui.thread.IEngineInfo#onEngineStep(double, double,
	 *      double)
	 */
	public void onEngineStep(Engine source, double avgAptitude, double bestAptitude,
			double worstAptitude) {
		
		// Do nothing because multiple engines are being used...
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.ui.thread.IEngineInfo#onInitPopulationDone()
	 */
	public void onInitPopulationDone(Engine source) {
		this.alternativeFrame.getLabelInfo().setText("Executing...");
	}
}
