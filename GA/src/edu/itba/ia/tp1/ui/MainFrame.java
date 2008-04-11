package edu.itba.ia.tp1.ui;

import edu.itba.ia.tp1.ui.listener.SwitchExecuteActionListener;

/**
 * Main application frame.
 *
 * @author  Martín A. Heras
 */
public class MainFrame extends javax.swing.JFrame {
    
	private static final long serialVersionUID = -4318743507589837307L;
	
    private javax.swing.JButton buttonSwitchExecution;
    private javax.swing.JComboBox comboReplacementMethod;
    private javax.swing.JComboBox comboProblemImpl;
    private javax.swing.JComboBox comboSelectionMethod;
    private javax.swing.JLabel labelAvgAptitude;
    private javax.swing.JLabel labelAvgAptitudeTitle;
    private javax.swing.JLabel labelBestAptitude;
    private javax.swing.JLabel labelBestAptitudeTitle;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JLabel labelMaximumParents;
    private javax.swing.JLabel labelMaximumParents1;
    private javax.swing.JLabel labelMutationProbability;
    private javax.swing.JLabel labelMutationProbability1;
    private javax.swing.JLabel labelPopulationSize;
    private javax.swing.JLabel labelReplacementMethod;
    private javax.swing.JLabel labelSelectionMethod;
    private javax.swing.JLabel labelWorstAptitude;
    private javax.swing.JLabel labelWorstAptitudeTitle;
    private javax.swing.JPanel panelChart;
    private javax.swing.JPanel panelExecutionParameters;
    private javax.swing.JPanel panelStatusBar;
    private javax.swing.JSpinner spinnerMaximumGenerations;
    private javax.swing.JSpinner spinnerMaximumParents;
    private javax.swing.JSpinner spinnerMutationProbability;
    private javax.swing.JSpinner spinnerPopulationSize;
    
	public javax.swing.JButton getButtonSwitchExecution() {
		return buttonSwitchExecution;
	}

	public javax.swing.JComboBox getComboReplacementMethod() {
		return comboReplacementMethod;
	}

	public javax.swing.JComboBox getComboSelectionMethod() {
		return comboSelectionMethod;
	}

	public javax.swing.JLabel getLabelAvgAptitude() {
		return labelAvgAptitude;
	}

	public javax.swing.JLabel getLabelAvgAptitudeTitle() {
		return labelAvgAptitudeTitle;
	}

	public javax.swing.JLabel getLabelBestAptitude() {
		return labelBestAptitude;
	}

	public javax.swing.JLabel getLabelBestAptitudeTitle() {
		return labelBestAptitudeTitle;
	}

	public javax.swing.JLabel getLabelInfo() {
		return labelInfo;
	}

	public javax.swing.JLabel getLabelMaximumParents() {
		return labelMaximumParents;
	}

	public javax.swing.JLabel getLabelMaximumParents1() {
		return labelMaximumParents1;
	}

	public javax.swing.JLabel getLabelMutationProbability() {
		return labelMutationProbability;
	}

	public javax.swing.JLabel getLabelMutationProbability1() {
		return labelMutationProbability1;
	}

	public javax.swing.JLabel getLabelPopulationSize() {
		return labelPopulationSize;
	}

	public javax.swing.JLabel getLabelReplacementMethod() {
		return labelReplacementMethod;
	}

	public javax.swing.JLabel getLabelSelectionMethod() {
		return labelSelectionMethod;
	}

	public javax.swing.JLabel getLabelWorstAptitude() {
		return labelWorstAptitude;
	}

	public javax.swing.JLabel getLabelWorstAptitudeTitle() {
		return labelWorstAptitudeTitle;
	}

	public javax.swing.JPanel getPanelChart() {
		return panelChart;
	}

	public javax.swing.JPanel getPanelExecutionParameters() {
		return panelExecutionParameters;
	}

	public javax.swing.JPanel getPanelStatusBar() {
		return panelStatusBar;
	}

	public javax.swing.JSpinner getSpinnerMaximumGenerations() {
		return spinnerMaximumGenerations;
	}

	public javax.swing.JSpinner getSpinnerMaximumParents() {
		return spinnerMaximumParents;
	}

	public javax.swing.JSpinner getSpinnerMutationProbability() {
		return spinnerMutationProbability;
	}

	public javax.swing.JSpinner getSpinnerPopulationSize() {
		return spinnerPopulationSize;
	}

	/** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
    }
    
    
    /**
     * initializes components. 
     */
    private void initComponents() {

        panelStatusBar = new javax.swing.JPanel();
        labelAvgAptitudeTitle = new javax.swing.JLabel();
        labelAvgAptitude = new javax.swing.JLabel();
        labelBestAptitude = new javax.swing.JLabel();
        labelBestAptitudeTitle = new javax.swing.JLabel();
        labelWorstAptitude = new javax.swing.JLabel();
        labelWorstAptitudeTitle = new javax.swing.JLabel();
        labelInfo = new javax.swing.JLabel();
        panelExecutionParameters = new javax.swing.JPanel();
        labelSelectionMethod = new javax.swing.JLabel();
        comboSelectionMethod = new javax.swing.JComboBox();
        comboReplacementMethod = new javax.swing.JComboBox();
        labelReplacementMethod = new javax.swing.JLabel();
        spinnerMutationProbability = new javax.swing.JSpinner();
        labelMutationProbability = new javax.swing.JLabel();
        labelPopulationSize = new javax.swing.JLabel();
        spinnerPopulationSize = new javax.swing.JSpinner();
        labelMaximumParents = new javax.swing.JLabel();
        spinnerMaximumParents = new javax.swing.JSpinner();
        labelMaximumParents1 = new javax.swing.JLabel();
        spinnerMaximumGenerations = new javax.swing.JSpinner();
        buttonSwitchExecution = new javax.swing.JButton();
        labelMutationProbability1 = new javax.swing.JLabel();
        comboProblemImpl = new javax.swing.JComboBox();
        panelChart = AptitudeChart.getInstance().getChartPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Binary2BCD Converter Genetic Algorithm"); 
        setName("Form"); 
        setResizable(false);

        panelStatusBar.setBackground(java.awt.SystemColor.inactiveCaption);
        panelStatusBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelStatusBar.setName("panelStatusBar"); 

        labelAvgAptitudeTitle.setText("Avg. Aptitude:"); 
        labelAvgAptitudeTitle.setName("labelAvgAptitudeTitle"); 

        labelAvgAptitude.setText("0.0000"); 
        labelAvgAptitude.setName("labelAvgAptitude"); 

        labelBestAptitude.setText("0.0000"); 
        labelBestAptitude.setName("labelBestAptitude"); 

        labelBestAptitudeTitle.setText("Best Aptitude:"); 
        labelBestAptitudeTitle.setName("labelBestAptitudeTitle"); 

        labelWorstAptitude.setText("0.0000"); 
        labelWorstAptitude.setName("labelWorstAptitude"); 

        labelWorstAptitudeTitle.setText("Worst Aptitude:"); 
        labelWorstAptitudeTitle.setName("labelWorstAptitudeTitle"); 

        labelInfo.setName("labelInfo"); 

        javax.swing.GroupLayout panelStatusBarLayout = new javax.swing.GroupLayout(panelStatusBar);
        panelStatusBar.setLayout(panelStatusBarLayout);
        panelStatusBarLayout.setHorizontalGroup(
            panelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAvgAptitudeTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAvgAptitude, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelBestAptitudeTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelBestAptitude, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelWorstAptitudeTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelWorstAptitude, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelStatusBarLayout.setVerticalGroup(
            panelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelAvgAptitudeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelAvgAptitude)
                .addComponent(labelBestAptitudeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelBestAptitude)
                .addComponent(labelWorstAptitudeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelWorstAptitude)
                .addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        labelAvgAptitudeTitle.getAccessibleContext().setAccessibleName(""); 

        panelExecutionParameters.setBorder(javax.swing.BorderFactory.createTitledBorder("Execution Parameters"));
        panelExecutionParameters.setName("panelExecutionParameters"); 

        labelSelectionMethod.setText("Selection method:"); 
        labelSelectionMethod.setName("labelSelectionMethod"); 

        comboSelectionMethod.setModel(new MethodComboBoxModel());
        comboSelectionMethod.setName("comboSelectionMethod"); 

        comboReplacementMethod.setModel(new MethodComboBoxModel());
        comboReplacementMethod.setName("comboReplacementMethod"); 

        labelReplacementMethod.setText("Replacement method:"); 
        labelReplacementMethod.setName("labelReplacementMethod"); 

        spinnerMutationProbability.setModel(new javax.swing.SpinnerNumberModel(0.15d, 0.0d, 1.0d, 0.05d));
        spinnerMutationProbability.setToolTipText(""); 
        spinnerMutationProbability.setName("spinnerMutationProbability"); 

        labelMutationProbability.setText("Mutation probability:"); 
        labelMutationProbability.setName("labelMutationProbability"); 

        labelPopulationSize.setText("Population size:"); 
        labelPopulationSize.setName("labelPopulationSize"); 

        spinnerPopulationSize.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(800L), Long.valueOf(0L), null, Long.valueOf(1L)));
        spinnerPopulationSize.setToolTipText(""); 
        spinnerPopulationSize.setName("spinnerPopulationSize"); 

        labelMaximumParents.setText("Maximum parents:"); 
        labelMaximumParents.setName("labelMaximumParents"); 

        spinnerMaximumParents.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(180L), Long.valueOf(0L), null, Long.valueOf(1L)));
        spinnerMaximumParents.setToolTipText(""); 
        spinnerMaximumParents.setName("spinnerMaximumParents"); 

        labelMaximumParents1.setText("Maximum generations:"); 
        labelMaximumParents1.setName("labelMaximumParents1"); 

        spinnerMaximumGenerations.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(10000L), Long.valueOf(0L), null, Long.valueOf(1L)));
        spinnerMaximumGenerations.setToolTipText(""); 
        spinnerMaximumGenerations.setName("spinnerMaximumGenerations"); 

        buttonSwitchExecution.setText("Execute"); 
        buttonSwitchExecution.setName("buttonSwitchExecution");
        buttonSwitchExecution.addActionListener(new SwitchExecuteActionListener());

        labelMutationProbability1.setText("Problem representation:"); 
        labelMutationProbability1.setName("labelMutationProbability1"); 

        comboProblemImpl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Circuit Tree", "Circuit String" }));
        comboProblemImpl.setName("comboProblemImpl"); 

        javax.swing.GroupLayout panelExecutionParametersLayout = new javax.swing.GroupLayout(panelExecutionParameters);
        panelExecutionParameters.setLayout(panelExecutionParametersLayout);
        panelExecutionParametersLayout.setHorizontalGroup(
            panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelExecutionParametersLayout.createSequentialGroup()
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                                .addComponent(labelPopulationSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(spinnerPopulationSize, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                                .addComponent(labelMaximumParents)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(spinnerMaximumParents, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                                .addComponent(labelMaximumParents1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(spinnerMaximumGenerations, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(91, 91, 91)
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                                .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSelectionMethod)
                                    .addComponent(labelReplacementMethod)
                                    .addComponent(labelMutationProbability))
                                .addGap(15, 15, 15)
                                .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboReplacementMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboSelectionMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinnerMutationProbability, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                                .addComponent(labelMutationProbability1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboProblemImpl, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(buttonSwitchExecution, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelExecutionParametersLayout.setVerticalGroup(
            panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerPopulationSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPopulationSize))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerMaximumParents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMaximumParents))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerMaximumGenerations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMaximumParents1)))
                    .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboSelectionMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSelectionMethod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboReplacementMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelReplacementMethod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerMutationProbability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMutationProbability))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMutationProbability1)
                            .addComponent(comboProblemImpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(buttonSwitchExecution)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelChart.setBackground(new java.awt.Color(204, 204, 204));
        panelChart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelChart.setName("panelChart"); 

        javax.swing.GroupLayout panelChartLayout = new javax.swing.GroupLayout(panelChart);
        panelChart.setLayout(panelChartLayout);
        panelChartLayout.setHorizontalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );
        panelChartLayout.setVerticalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelExecutionParameters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelExecutionParameters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(panelStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

	public javax.swing.JComboBox getComboProblemImpl() {
		return comboProblemImpl;
	} 
}
