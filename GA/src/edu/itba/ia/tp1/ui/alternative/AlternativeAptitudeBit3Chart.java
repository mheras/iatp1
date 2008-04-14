package edu.itba.ia.tp1.ui.alternative;

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
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author Martín A. Heras
 * 
 */
public class AlternativeAptitudeBit3Chart extends
		AbstractAlternativeAptitudeChart {

	/* Singleton instance. */
	private static AlternativeAptitudeBit3Chart instance;

	private JPanel chartPanel;
	private XYSeries aptitudeAvgData;
	private XYSeries bestAptitudeData;
	private XYSeries worstAptitudeData;
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
	public static AlternativeAptitudeBit3Chart getInstance() {
		if (instance == null) {
			instance = new AlternativeAptitudeBit3Chart();
		}
		return instance;
	}

	/**
	 * Private constructor. Used by getInstance method.
	 */
	private AlternativeAptitudeBit3Chart() {

		this.aptitudeAvgData = new XYSeries("Average Aptitude");
		this.bestAptitudeData = new XYSeries("Best Aptitude");
		this.worstAptitudeData = new XYSeries("Worst Aptitude");

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(this.aptitudeAvgData);
		dataset.addSeries(this.bestAptitudeData);
		dataset.addSeries(this.worstAptitudeData);

		NumberAxis aptitudeAxis = new NumberAxis("Aptitude");
		aptitudeAxis.setLowerBound(0.0);
		aptitudeAxis.setUpperBound(1.0);
		aptitudeAxis.setAutoRange(false);

		TickUnitSource units = NumberAxis.createIntegerTickUnits();

		this.generationsAxis = new NumberAxis("Generation");
		this.generationsAxis.setStandardTickUnits(units);
		this.generationsAxis.setLowerBound(0.0);
		this.generationsAxis.setAutoRange(true);
		// This must be changed with public setter every time a new execution is
		// performed.
		this.generationsAxis.setUpperBound(200.0);

		aptitudeAxis.setAutoRangeIncludesZero(false);

		// Init splines renderer.
		this.splinesRenderer = new XYSplineRenderer(50);
		for (int i = 0; i < dataset.getSeriesCount(); i++) {
			((XYLineAndShapeRenderer) this.splinesRenderer)
					.setSeriesShapesVisible(i, false);
		}
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

		JFreeChart chart = new JFreeChart("Bit 3", this.plot);
		chart.setBackgroundPaint(Color.white);

		this.reset();
		this.chartPanel = new ChartPanel(chart, true);
	}

	public void reset() {
		this.generation = 0.0;
		this.aptitudeAvgData.clear();
		this.bestAptitudeData.clear();
		this.worstAptitudeData.clear();
	}

	public void setMaxGenerations(long maxGenerations) {
		this.generationsAxis.setUpperBound(maxGenerations);
		this.generationsAxis.setAutoRange(true);
	}

	public JPanel getChartPanel() {
		return chartPanel;
	}

	public void addGenerationAptitudeAvg(double aptitude) {
		this.aptitudeAvgData.add(this.generation, aptitude);
	}

	public void addGenerationBestAptitude(double aptitude) {
		this.bestAptitudeData.add(this.generation, aptitude);
	}

	public void addGenerationWorstAptitude(double aptitude) {
		this.worstAptitudeData.add(this.generation, aptitude);
	}

	public void incrementGeneration() {
		this.generation++;
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
