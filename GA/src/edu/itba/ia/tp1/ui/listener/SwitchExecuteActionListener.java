package edu.itba.ia.tp1.ui.listener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import edu.itba.ia.tp1.engine.population.selection.I_SelectionAlgorithm;
import edu.itba.ia.tp1.ui.MainFrame;
import edu.itba.ia.tp1.ui.thread.ExecutionThread;
import edu.itba.ia.tp1.ui.thread.IEngineInfo;
import edu.itba.ia.tp1.ui.thread.IExecutionThreadDone;
import edu.itba.ia.tp1.ui.thread.ThreadsBag;

/**
 * SwitchExecute button listener. This class handles SwitchExecute button
 * behavior when it is clicked.
 * 
 * @author Martín A. Heras
 * 
 */
public class SwitchExecuteActionListener implements ActionListener,
		IExecutionThreadDone, IEngineInfo {

	private final String EXECUTE = "Execute";
	private final String CANCEL = "Cancel";
	/* Main frame. */
	private MainFrame mainFrame;

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
			ExecutionThread thread = ThreadsBag.getInstance()
					.getExecutionThread();
			if (thread != null) {
				thread.cancel(true);
				this.mainFrame.getLabelInfo().setText("Execution cancelled");
			}
			return;
		}

		this.mainFrame = (MainFrame) this.getParentFrame(source);

		this.mainFrame.getLabelInfo().setText("Initializing population...");

		/* Retrieves parameters from UI components. */
		Long populationSize = (Long) this.mainFrame.getSpinnerPopulationSize()
				.getValue();
		Long maximumParents = (Long) this.mainFrame.getSpinnerMaximumParents()
				.getValue();
		Long maximumGenerations = (Long) this.mainFrame
				.getSpinnerMaximumGenerations().getValue();
		Double mutationProbability = (Double) this.mainFrame
				.getSpinnerMutationProbability().getValue();
		I_SelectionAlgorithm selection = (I_SelectionAlgorithm) this.mainFrame
				.getComboSelectionMethod().getSelectedItem();
		I_SelectionAlgorithm replacement = (I_SelectionAlgorithm) this.mainFrame
				.getComboReplacementMethod().getSelectedItem();
		String problemDesc = (String) this.mainFrame.getComboProblemImpl()
				.getSelectedItem();	

		/* Creates a worker thread, configures it and executes it. */
		ExecutionThread thread = new ExecutionThread(this, this);
		thread.setMaximumGenerations(maximumGenerations);
		thread.setMaximumParents(maximumParents);
		thread.setMutationProbability(mutationProbability);
		thread.setPopulationSize(populationSize);
		thread.setSelectionAlgorithm(selection);
		thread.setReplacementAlgorithm(replacement);
		thread.setProblemDesc(problemDesc);

		ThreadsBag.getInstance().setExecutionThread(thread);
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
	public void onExecutionThreadDone(ExecutionThread executionThread) {
		if (this.mainFrame != null) {
			this.mainFrame.getButtonSwitchExecution().setText(EXECUTE);
			this.mainFrame.getLabelInfo().setText("Execution finalized");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.ui.thread.IEngineInfo#onEngineStep(double, double,
	 *      double)
	 */
	public void onEngineStep(double avgAptitude, double bestAptitude,
			double worstAptitude) {

		DecimalFormat fmt = (DecimalFormat) DecimalFormat.getInstance();

		this.mainFrame.getLabelAvgAptitude().setText(
				String.valueOf(fmt.format(avgAptitude)));
		this.mainFrame.getLabelBestAptitude().setText(
				String.valueOf(fmt.format(bestAptitude)));
		this.mainFrame.getLabelWorstAptitude().setText(
				String.valueOf(fmt.format(worstAptitude)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.ui.thread.IEngineInfo#onInitPopulationDone()
	 */
	public void onInitPopulationDone() {
		this.mainFrame.getLabelInfo().setText("Executing...");
	}
}
