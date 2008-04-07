package edu.itba.ia.tp1.ui;

import edu.itba.ia.tp1.ui.listener.SwitchExecuteActionListener;


/**
 * This is the main application frame.
 * 
 * @author Martín A. Heras
 *
 */
public class MainFrame extends javax.swing.JFrame {
    
	private static final long serialVersionUID = -2984799051996992358L;
	
	/* UI components */
	private javax.swing.JButton buttonSwitchExecution;
    private javax.swing.JComboBox comboReplacementMethod;
    private javax.swing.JComboBox comboSelectionMethod;
    private javax.swing.JLabel labelExecutionTime;
    private javax.swing.JLabel labelExecutionTimeTitle;
    private javax.swing.JLabel labelMaximumParents;
    private javax.swing.JLabel labelMaximumParents1;
    private javax.swing.JLabel labelMutationProbability;
    private javax.swing.JLabel labelPopulationSize;
    private javax.swing.JLabel labelReplacementMethod;
    private javax.swing.JLabel labelSelectionMethod;
    private javax.swing.JPanel panelChart;
    private javax.swing.JPanel panelExecutionParameters;
    private javax.swing.JPanel panelStatusBar;
    private javax.swing.JSpinner spinnerMaximumGenerations;
    private javax.swing.JSpinner spinnerMaximumParents;
    private javax.swing.JSpinner spinnerMutationProbability;
    private javax.swing.JSpinner spinnerPopulationSize;
	
    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
    }
    
    /**
     * Initializes the UI components used in this frame.
     */
    private void initComponents() {

        panelStatusBar = new javax.swing.JPanel();
        labelExecutionTimeTitle = new javax.swing.JLabel();
        labelExecutionTime = new javax.swing.JLabel();
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
        panelChart = AptitudeChart.getInstance().getChartPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Binary2BCD Converter Genetic Algorithm"); 
        setName("Form"); 
        setResizable(false);

        panelStatusBar.setBackground(java.awt.SystemColor.inactiveCaption);
        panelStatusBar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelStatusBar.setName("panelStatusBar"); 

        labelExecutionTimeTitle.setText("Execution Time:"); 
        labelExecutionTimeTitle.setName("labelExecutionTimeTitle"); 

        labelExecutionTime.setText("00:00:00 hs."); 
        labelExecutionTime.setName("labelExecutionTime"); 

        javax.swing.GroupLayout panelStatusBarLayout = new javax.swing.GroupLayout(panelStatusBar);
        panelStatusBar.setLayout(panelStatusBarLayout);
        panelStatusBarLayout.setHorizontalGroup(
            panelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelExecutionTimeTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelExecutionTime, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(450, Short.MAX_VALUE))
        );
        panelStatusBarLayout.setVerticalGroup(
            panelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelExecutionTime)
                .addComponent(labelExecutionTimeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        labelExecutionTimeTitle.getAccessibleContext().setAccessibleName(""); 

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

        spinnerMutationProbability.setModel(new javax.swing.SpinnerNumberModel(0.3d, 0.0d, 1.0d, 0.05d));
        spinnerMutationProbability.setToolTipText(""); 
        spinnerMutationProbability.setName("spinnerMutationProbability"); 

        labelMutationProbability.setText("Mutation probability:"); 
        labelMutationProbability.setName("labelMutationProbability"); 

        labelPopulationSize.setText("Population size:"); 
        labelPopulationSize.setName("labelPopulationSize"); 

        spinnerPopulationSize.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(200L), Long.valueOf(0L), null, Long.valueOf(1L)));
        spinnerPopulationSize.setToolTipText(""); 
        spinnerPopulationSize.setName("spinnerPopulationSize"); 

        labelMaximumParents.setText("Maximum parents:"); 
        labelMaximumParents.setName("labelMaximumParents"); 

        spinnerMaximumParents.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(25L), Long.valueOf(0L), null, Long.valueOf(1L)));
        spinnerMaximumParents.setToolTipText(""); 
        spinnerMaximumParents.setName("spinnerMaximumParents"); 

        labelMaximumParents1.setText("Maximum generations:"); 
        labelMaximumParents1.setName("labelMaximumParents1"); 

        spinnerMaximumGenerations.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(300L), Long.valueOf(0L), null, Long.valueOf(1L)));
        spinnerMaximumGenerations.setToolTipText(""); 
        spinnerMaximumGenerations.setName("spinnerMaximumGenerations"); 

        buttonSwitchExecution.setText("Execute"); 
        buttonSwitchExecution.setName("buttonSwitchExecution");
        buttonSwitchExecution.addActionListener(new SwitchExecuteActionListener());

        javax.swing.GroupLayout panelExecutionParametersLayout = new javax.swing.GroupLayout(panelExecutionParameters);
        panelExecutionParameters.setLayout(panelExecutionParametersLayout);
        panelExecutionParametersLayout.setHorizontalGroup(
            panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelExecutionParametersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonSwitchExecution, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                                .addComponent(labelPopulationSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(spinnerPopulationSize, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                                .addComponent(labelMaximumParents)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(spinnerMaximumParents, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                                .addComponent(labelMaximumParents1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(spinnerMaximumGenerations, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(101, 101, 101)
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSelectionMethod)
                            .addComponent(labelReplacementMethod)
                            .addComponent(labelMutationProbability))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboReplacementMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSelectionMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerMutationProbability, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelExecutionParametersLayout.setVerticalGroup(
            panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExecutionParametersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerPopulationSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPopulationSize)
                    .addComponent(comboSelectionMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSelectionMethod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerMaximumParents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMaximumParents)
                    .addComponent(comboReplacementMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelReplacementMethod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelExecutionParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerMaximumGenerations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMaximumParents1)
                    .addComponent(spinnerMutationProbability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMutationProbability))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(buttonSwitchExecution)
                .addContainerGap())
        );

        panelChart.setBackground(new java.awt.Color(204, 204, 204));
        panelChart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelChart.setName("panelChart"); 

        javax.swing.GroupLayout panelChartLayout = new javax.swing.GroupLayout(panelChart);
        panelChart.setLayout(panelChartLayout);
        panelChartLayout.setHorizontalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        panelChartLayout.setVerticalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelExecutionParameters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelExecutionParameters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }
    
    /**
     * This is the application entry point.
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

	public javax.swing.JButton getButtonSwitchExecution() {
		return buttonSwitchExecution;
	}

	public void setButtonSwitchExecution(javax.swing.JButton buttonSwitchExecution) {
		this.buttonSwitchExecution = buttonSwitchExecution;
	}

	public javax.swing.JComboBox getComboReplacementMethod() {
		return comboReplacementMethod;
	}

	public void setComboReplacementMethod(
			javax.swing.JComboBox comboReplacementMethod) {
		this.comboReplacementMethod = comboReplacementMethod;
	}

	public javax.swing.JComboBox getComboSelectionMethod() {
		return comboSelectionMethod;
	}

	public void setComboSelectionMethod(javax.swing.JComboBox comboSelectionMethod) {
		this.comboSelectionMethod = comboSelectionMethod;
	}

	public javax.swing.JLabel getLabelExecutionTime() {
		return labelExecutionTime;
	}

	public void setLabelExecutionTime(javax.swing.JLabel labelExecutionTime) {
		this.labelExecutionTime = labelExecutionTime;
	}

	public javax.swing.JLabel getLabelExecutionTimeTitle() {
		return labelExecutionTimeTitle;
	}

	public void setLabelExecutionTimeTitle(
			javax.swing.JLabel labelExecutionTimeTitle) {
		this.labelExecutionTimeTitle = labelExecutionTimeTitle;
	}

	public javax.swing.JLabel getLabelMaximumParents() {
		return labelMaximumParents;
	}

	public void setLabelMaximumParents(javax.swing.JLabel labelMaximumParents) {
		this.labelMaximumParents = labelMaximumParents;
	}

	public javax.swing.JLabel getLabelMaximumParents1() {
		return labelMaximumParents1;
	}

	public void setLabelMaximumParents1(javax.swing.JLabel labelMaximumParents1) {
		this.labelMaximumParents1 = labelMaximumParents1;
	}

	public javax.swing.JLabel getLabelMutationProbability() {
		return labelMutationProbability;
	}

	public void setLabelMutationProbability(
			javax.swing.JLabel labelMutationProbability) {
		this.labelMutationProbability = labelMutationProbability;
	}

	public javax.swing.JLabel getLabelPopulationSize() {
		return labelPopulationSize;
	}

	public void setLabelPopulationSize(javax.swing.JLabel labelPopulationSize) {
		this.labelPopulationSize = labelPopulationSize;
	}

	public javax.swing.JLabel getLabelReplacementMethod() {
		return labelReplacementMethod;
	}

	public void setLabelReplacementMethod(javax.swing.JLabel labelReplacementMethod) {
		this.labelReplacementMethod = labelReplacementMethod;
	}

	public javax.swing.JLabel getLabelSelectionMethod() {
		return labelSelectionMethod;
	}

	public void setLabelSelectionMethod(javax.swing.JLabel labelSelectionMethod) {
		this.labelSelectionMethod = labelSelectionMethod;
	}

	public javax.swing.JPanel getPanelChart() {
		return panelChart;
	}

	public void setPanelChart(javax.swing.JPanel panelChart) {
		this.panelChart = panelChart;
	}

	public javax.swing.JPanel getPanelExecutionParameters() {
		return panelExecutionParameters;
	}

	public void setPanelExecutionParameters(
			javax.swing.JPanel panelExecutionParameters) {
		this.panelExecutionParameters = panelExecutionParameters;
	}

	public javax.swing.JPanel getPanelStatusBar() {
		return panelStatusBar;
	}

	public void setPanelStatusBar(javax.swing.JPanel panelStatusBar) {
		this.panelStatusBar = panelStatusBar;
	}

	public javax.swing.JSpinner getSpinnerMaximumGenerations() {
		return spinnerMaximumGenerations;
	}

	public void setSpinnerMaximumGenerations(
			javax.swing.JSpinner spinnerMaximumGenerations) {
		this.spinnerMaximumGenerations = spinnerMaximumGenerations;
	}

	public javax.swing.JSpinner getSpinnerMaximumParents() {
		return spinnerMaximumParents;
	}

	public void setSpinnerMaximumParents(javax.swing.JSpinner spinnerMaximumParents) {
		this.spinnerMaximumParents = spinnerMaximumParents;
	}

	public javax.swing.JSpinner getSpinnerMutationProbability() {
		return spinnerMutationProbability;
	}

	public void setSpinnerMutationProbability(
			javax.swing.JSpinner spinnerMutationProbability) {
		this.spinnerMutationProbability = spinnerMutationProbability;
	}

	public javax.swing.JSpinner getSpinnerPopulationSize() {
		return spinnerPopulationSize;
	}

	public void setSpinnerPopulationSize(javax.swing.JSpinner spinnerPopulationSize) {
		this.spinnerPopulationSize = spinnerPopulationSize;
	}
}
