package edu.itba.ia.tp1.ui;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author Martín A. Heras
 *
 */
public class AptitudeChart {

	/* Singleton instance. */
	private static AptitudeChart instance;

	private JPanel chartPanel;
	private XYSeries data;
	private NumberAxis generationsAxis;

	private double generation;

	/**
	 * Gets the singleton instance of the aptitude chart.
	 * 
	 * @return Singleton instance of the aptitude chart.
	 */
	public static AptitudeChart getInstance() {
		if (instance == null) {
			instance = new AptitudeChart();
		}
		return instance;
	}

	/**
	 * Private constructor. Used by getInstance method.
	 */
	private AptitudeChart() {

		this.data = new XYSeries("Aptitude");
		XYDataset dataset = new XYSeriesCollection(this.data);

		NumberAxis aptitudeAxis = new NumberAxis("Aptitude");
		aptitudeAxis.setLowerBound(0.0);
		aptitudeAxis.setUpperBound(1.0);

		TickUnitSource units = NumberAxis.createIntegerTickUnits();
		
		this.generationsAxis = new NumberAxis("Generation");
		this.generationsAxis.setStandardTickUnits(units);
		this.generationsAxis.setLowerBound(0.0);		
		// This must be changed with public setter every time a new execution is
		// performed.
		this.generationsAxis.setUpperBound(200.0);

		aptitudeAxis.setAutoRangeIncludesZero(false);
		
		XYItemRenderer renderer = new StandardXYItemRenderer();
		renderer.setBaseStroke(new BasicStroke(2.0F));
		
		XYPlot plot = new XYPlot(dataset, this.generationsAxis, aptitudeAxis,
				renderer);
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setOutlineVisible(false);

		JFreeChart chart = new JFreeChart(
				"Population aptitude over generations", plot);
		chart.setBackgroundPaint(Color.white);

		this.reset();
		this.chartPanel = new ChartPanel(chart, true);
	}

	public void reset() {
		this.generation = 0.0;
		this.data.clear();
	}

	public void setMaxGenerations(long maxGenerations) {
		this.generationsAxis.setUpperBound(maxGenerations);
	}

	public JPanel getChartPanel() {
		return chartPanel;
	}

	public void addGenerationAptitude(double aptitude) {
		this.data.add(this.generation++, aptitude);
	}
}
