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
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
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
	private XYPlot plot;

	private XYItemRenderer linesRenderer;
	private XYItemRenderer splinesRenderer;

	private boolean splinesOn;
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

		// Init splines renderer.
		this.splinesRenderer = new XYSplineRenderer(50);
		((XYLineAndShapeRenderer) this.splinesRenderer).setSeriesShapesVisible(
				0, false);
		this.splinesRenderer.setBaseStroke(new BasicStroke(2.0F));
		this.splinesRenderer.setBasePaint(Color.BLUE);
		// Init lines renderer.
		this.linesRenderer = new StandardXYItemRenderer();
		this.linesRenderer.setBaseStroke(new BasicStroke(2.0F));
		this.linesRenderer.setBasePaint(Color.BLUE);

		// Default renderer (lines renderer).
		XYItemRenderer renderer = this.linesRenderer;

		this.plot = new XYPlot(dataset, this.generationsAxis, aptitudeAxis,
				renderer);
		this.plot.setBackgroundPaint(Color.lightGray);
		this.plot.setDomainGridlinePaint(Color.white);
		this.plot.setRangeGridlinePaint(Color.white);
		this.plot.setOutlineVisible(false);

		JFreeChart chart = new JFreeChart(
				"Population aptitude over generations", this.plot);
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

	public boolean isSplinesOn() {
		return splinesOn;
	}

	public void setSplinesOn(boolean splinesOn) {

		this.splinesOn = splinesOn;

		if (splinesOn) {
			this.plot.setRenderer(this.splinesRenderer);
		} else {
			this.plot.setRenderer(this.linesRenderer);
		}
	}
}
