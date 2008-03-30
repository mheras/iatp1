package edu.itba.ia.tp1.ui.listeners;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import edu.itba.ia.tp1.ui.MainFrame;
import edu.itba.ia.tp1.ui.threads.ExecutionThread;
import edu.itba.ia.tp1.ui.threads.IExecutionThreadDone;
import edu.itba.ia.tp1.ui.threads.ThreadsBag;

/**
 * SwitchExecute button listener. This class handles SwitchExecute button
 * behavior when it is clicked.
 * 
 * @author Martín A. Heras
 * 
 */
public class SwitchExecuteActionListener implements ActionListener, IExecutionThreadDone {

	private final String EXECUTE = "Execute";
	private final String CANCEL = "Cancel";
	/* Main frame. */
	private MainFrame mainFrame;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
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
			}
			return;
		}

		this.mainFrame = (MainFrame) this.getParentFrame(source);

		/* Retrieves parameters from UI components. */
		Long populationSize = (Long) this.mainFrame.getSpinnerPopulationSize()
				.getValue();
		Long maximumParents = (Long) this.mainFrame.getSpinnerMaximumParents()
				.getValue();
		Long maximumGenerations = (Long) this.mainFrame
				.getSpinnerMaximumGenerations().getValue();
		Double mutationProbability = (Double) this.mainFrame
				.getSpinnerMutationProbability().getValue();

		/* Creates a worker thread, configures it and executes it. */
		ExecutionThread thread = new ExecutionThread(this);
		thread.setMaximumGenerations(maximumGenerations);
		thread.setMaximumParents(maximumParents);
		thread.setMutationProbability(mutationProbability);
		thread.setPopulationSize(populationSize);
		thread.execute();
		ThreadsBag.getInstance().setExecutionThread(thread);

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

	/* (non-Javadoc)
	 * @see edu.itba.ia.tp1.ui.threads.IExecutionThreadDone#onExecutionThreadDone(edu.itba.ia.tp1.ui.threads.ExecutionThread)
	 */
	@Override
	public void onExecutionThreadDone(ExecutionThread executionThread) {
		if (this.mainFrame != null) {
			this.mainFrame.getButtonSwitchExecution().setText(EXECUTE);
		}
	}
}
