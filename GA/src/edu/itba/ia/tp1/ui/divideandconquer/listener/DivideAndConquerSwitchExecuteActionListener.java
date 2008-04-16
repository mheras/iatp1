package edu.itba.ia.tp1.ui.divideandconquer.listener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.itba.ia.tp1.engine.population.selection.ISelectionAlgorithm;
import edu.itba.ia.tp1.problem.binary2bcd.AbstractCircuit;
import edu.itba.ia.tp1.ui.divideandconquer.DivideAndConquerFrame;
import edu.itba.ia.tp1.ui.divideandconquer.thread.DivideAndConquerExecutionThread;
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
public class DivideAndConquerSwitchExecuteActionListener implements
		ActionListener, IExecutionThreadDone, IEngineInfo {

	private final String EXECUTE = "Execute";
	private final String CANCEL = "Cancel";
	/* Main frame. */
	private DivideAndConquerFrame dacFrame;

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
			DivideAndConquerExecutionThread thread = ThreadsBag.getInstance()
					.getDivideAndConquerExecutionThread();
			if (thread != null) {
				thread.cancel(true);
				this.dacFrame.getLabelInfo().setText("Execution cancelled");
			}
			return;
		}

		this.dacFrame = (DivideAndConquerFrame) this.getParentFrame(source);

		this.dacFrame.getLabelInfo().setText("Initializing population...");

		/* Retrieves parameters from UI components. */
		Long populationSize = (Long) this.dacFrame.getSpinnerPopulationSize()
				.getValue();
		Long parentsPool = (Long) this.dacFrame.getSpinnerParentsPool()
				.getValue();
		Long maximumGenerations = (Long) this.dacFrame
				.getSpinnerMaximumGenerations().getValue();
		Double mutationProbability = (Double) this.dacFrame
				.getSpinnerMutationProbability().getValue();
		ISelectionAlgorithm selection = (ISelectionAlgorithm) this.dacFrame
				.getComboSelectionMethod().getSelectedItem();
		ISelectionAlgorithm replacement = (ISelectionAlgorithm) this.dacFrame
				.getComboReplacementMethod().getSelectedItem();
		String problemDesc = (String) this.dacFrame.getComboProblemImpl()
				.getSelectedItem();
		String strBit = (String) this.dacFrame.getComboCurrentBit()
				.getSelectedItem();

		int currentBit;
		if (strBit.equalsIgnoreCase("Bit 4")) {
			currentBit = 4;
		} else if (strBit.equalsIgnoreCase("Bit 3")) {
			currentBit = 3;
		} else if (strBit.equalsIgnoreCase("Bit 2")) {
			currentBit = 2;
		} else if (strBit.equalsIgnoreCase("Bit 1")) {
			currentBit = 1;
		} else {
			currentBit = 0;
		}

		/* Creates a worker thread, configures it and executes it. */
		DivideAndConquerExecutionThread thread = new DivideAndConquerExecutionThread(
				this, this);
		thread.setMaximumGenerations(maximumGenerations);
		thread.setParentsPool(parentsPool);
		thread.setMutationProbability(mutationProbability);
		thread.setPopulationSize(populationSize);
		thread.setSelectionAlgorithm(selection);
		thread.setReplacementAlgorithm(replacement);
		thread.setProblemDesc(problemDesc);
		thread.setCurrentBit(currentBit);

		ThreadsBag.getInstance().setDivideAndConquerExecutionThread(thread);
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
	public void onExecutionThreadDone(AbstractCircuit bestCircuit) {
		if (this.dacFrame != null) {
			this.dacFrame.getButtonSwitchExecution().setText(EXECUTE);
			this.dacFrame.getLabelInfo().setText("Execution finalized");
		}

		StringBuffer resultInfo = new StringBuffer("");
		resultInfo.append("Best circuit:\n\n");
		resultInfo.append("Number of gates = ");
		resultInfo.append(bestCircuit.getGatesLength() + "\n");
		resultInfo.append("Aptitude = ");
		resultInfo.append(bestCircuit.getAptitude() + "\n");
		resultInfo.append("Result =\n");
		resultInfo.append(bestCircuit.toString());

		JOptionPane.showMessageDialog(this.dacFrame, resultInfo.toString(),
				"Result", JOptionPane.INFORMATION_MESSAGE);

		 System.out.println("Best Circuit [gates = "
		 + bestCircuit.getGatesLength() + "; aptitude: "
		 + bestCircuit.getAptitude() + "]:");
		 System.out.println(bestCircuit.toString());
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

		this.dacFrame.getLabelAvgAptitude().setText(
				String.valueOf(fmt.format(avgAptitude)));
		this.dacFrame.getLabelBestAptitude().setText(
				String.valueOf(fmt.format(bestAptitude)));
		this.dacFrame.getLabelWorstAptitude().setText(
				String.valueOf(fmt.format(worstAptitude)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.itba.ia.tp1.ui.thread.IEngineInfo#onInitPopulationDone()
	 */
	public void onInitPopulationDone() {
		this.dacFrame.getLabelInfo().setText("Executing...");
	}
}
