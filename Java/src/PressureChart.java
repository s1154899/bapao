import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import raspberry.RaspberryPi;

import java.awt.*;

public class PressureChart extends ApplicationFrame {

    public PressureChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Time", "Air Pressure",
                createDataset(),
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 350));
        setContentPane(chartPanel);
    }




    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(15, "Air pressure", "10:45:05");
        dataset.addValue(30, "Air pressure", "10:46.05");
        dataset.addValue(60, "Air pressure", "10:47.05");
        dataset.addValue(120, "Air pressure", "10:48.05");
        dataset.addValue(240, "Air pressure", "10:49.05");
        dataset.addValue(300, "Air pressure", "10:50.05");
        return dataset;
    }

}
