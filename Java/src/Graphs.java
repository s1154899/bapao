import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import raspberry.RaspberryPi;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Graphs {


    public JPanel lineGraph(int[] values, String[] categoryData, String name, String categoryLabel, String valuesLabel){

    return new LineGraph(values,categoryData, name, categoryLabel, valuesLabel);

    }
}

class LineGraph extends JPanel{
    public LineGraph(int[] values, String[] categoryData, String name, String categoryLabel, String valuesLabel) {
        JFreeChart lineChart = ChartFactory.createLineChart(
                name,
                categoryLabel, valuesLabel,
                createDataset(values, categoryData),
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(560, 350));
        add(chartPanel);
    }




    private DefaultCategoryDataset createDataset(int[] value, String[] columnKey) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i =0; i< value.length;i++) {
            dataset.addValue(value[i], "Temperature", columnKey[i]);
        }
        return dataset;
    }

}





//class LineGrap extends JPanel{
//
//    private int steps = 5;
//    private int[] Values = {30,20,50,200,50,20,40,30,10,20,5,20,10,0};
//    private int Dates;
//    private int HighestValue;
//
//    private int height;
//    private int width;
//
//    private int heightPer;
//    private int widthPer;
//
//    private int xs;
//    private int ys;
//
//    private int xt;
//    private int yt;
//
//    private int xe;
//    private int ye;
//
//    private int sideLine;
//    private int bottomLine;
//
//    private int spaceBetweenDates;
//
//    private int spaceBetweenSteps;
//    private int valueBetweenSteps;
//    private float pixToVallRatio;
//
//    private int points[];
//
//    public LineGrap(int[] amounts){
//
//        Values = amounts;
//
//        points = new int[Values.length];
//
//        for (int i = 0; i < Values.length; i++){
//            HighestValue = Math.max(HighestValue,Values[i]);
//            //System.out.println(HighestValue);
//        }
//
//    }
//
//    @Override
//    protected void paintComponent(Graphics g){
//
//
//        Shape vis = g.getClip();
//
//        width = vis.getBounds().width;
//        height = vis.getBounds().height;
//
//        widthPer = width / 100;
//        heightPer = height / 100;
//
//        xs = 5 * widthPer;
//        ys = height - 5 * heightPer;
//
//        xe = 95 * widthPer;
//        ye = height - 5 * heightPer;
//
//        xt = 5 * widthPer;
//        yt = height - 95 * heightPer;
//
//        sideLine = 90 * heightPer;
//        bottomLine = 90 * widthPer;
//
//        spaceBetweenSteps = sideLine / steps;
//        valueBetweenSteps = HighestValue / steps;
//        pixToVallRatio = sideLine / HighestValue;
//
//        spaceBetweenDates = bottomLine / points.length;
//
//        System.out.println(pixToVallRatio);
//        System.out.println(sideLine);
//        System.out.println(HighestValue);
//        System.out.println(pixToVallRatio * sideLine);
//        System.out.println(pixToVallRatio * HighestValue);
//
//        for (int i = 0; i < points.length; i++){
//            points[i] = (int) ((Values[i] * pixToVallRatio) + (heightPer * 5));
//
//        }
//
//
//        g.drawString(String.valueOf(HighestValue),20,20);
//
//        g.drawLine(xs,ys,xe,ye);
//        g.drawLine(xs,ys,xt,yt);
//
//        for (int i = 0; i <= steps; i++){
//            g.drawString(String.valueOf((i * valueBetweenSteps)),widthPer, ys - (spaceBetweenSteps * i));
//            g.setColor(Color.gray);
//            g.drawLine(xs, ys - (spaceBetweenSteps * i),xe,ys - (spaceBetweenSteps * i));
//            g.setColor(Color.black);
//        }
//        g.setColor(Color.MAGENTA);
//        for (int i = 1; i < points.length; i++){
//            g.drawLine(xs + spaceBetweenDates * (i - 1),height - points[i - 1] ,xs + spaceBetweenDates * i, height - (points[i]));
////            System.out.println(points[i]);
//        }
//        g.setColor(Color.black);
//
//
//
//    }
//
//
//
//}