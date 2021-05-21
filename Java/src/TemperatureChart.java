import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import raspberry.RaspberryPi;

public class TemperatureChart extends ApplicationFrame {

    public TemperatureChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Time", "Temperature",
                createDataset(),
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 350));
        setContentPane(chartPanel);
    }




    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(50, "Temperature", "10:45:05");
        dataset.addValue(90, "Temperature", "10:46.05");
        dataset.addValue(20, "Temperature", "10:47.05");
        dataset.addValue(120, "Temperature", "10:48.05");
        dataset.addValue(290, "Temperature", "10:49.05");
        dataset.addValue(250, "Temperature", "10:50.05");
        return dataset;
    }

}
