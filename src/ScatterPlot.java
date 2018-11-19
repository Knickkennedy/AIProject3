import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.ArrayList;

public class ScatterPlot extends JFrame {

    public XYDataset dataset;
    public JFreeChart chart;

    public ScatterPlot(String title, ArrayList<Point> dayOne, ArrayList<Point> dayTwo, ArrayList<Point> dayThree){
        super(title);

        this.dataset = createDataSet(dayOne, dayTwo, dayThree);
        this.chart = ChartFactory.createScatterPlot("Energy Consumption Prediction Across Days", "Time", "Energy Consumption", dataset);
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataSet(ArrayList<Point> dayOne, ArrayList<Point> dayTwo, ArrayList<Point> dayThree){
        XYSeriesCollection collection = new XYSeriesCollection();
        XYSeries dayOneSeries = new XYSeries("Day One");
        XYSeries dayTwoSeries = new XYSeries("Day Two");
        XYSeries dayThreeSeries = new XYSeries("Day Three");


        for(Point point : dayOne){
            dayOneSeries.add(point.x, point.y);
        }

        dayOneSeries.add(-0.5, -0.5);
        dayOneSeries.add(1.2, 1.2);

        collection.addSeries(dayOneSeries);

        for(Point point : dayTwo){
            dayTwoSeries.add(point.x, point.y);
        }

        collection.addSeries(dayTwoSeries);

        for(Point point : dayThree){
            dayThreeSeries.add(point.x, point.y);
        }

        collection.addSeries(dayThreeSeries);

        return collection;
    }
}
