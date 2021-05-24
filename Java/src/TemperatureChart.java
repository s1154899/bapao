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
                createDataset(new int[]{1, 2, 3, 4, 5, 6}, new String[]{"t","t","t","t","t","t","t",}),
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 350));
        setContentPane(chartPanel);
    }




    private DefaultCategoryDataset createDataset(int[] value,String[] columnKey) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i =0; i< value.length;i++) {
            dataset.addValue(value[i], "Temperature", columnKey[i]);
        }
        return dataset;
    }

}
