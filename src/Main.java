import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException {

        ArrayList<Point> allPoints = new ArrayList<>();

        ArrayList<Point> dayOne = new ArrayList<>();
        ArrayList<Point> dayTwo = new ArrayList<>();
        ArrayList<Point> dayThree = new ArrayList<>();
        ArrayList<Point> dayFour = new ArrayList<>();

        File dayOneFile = new File("train_data_1.txt");
        File dayTwoFile = new File("train_data_2.txt");
        File dayThreeFile = new File("train_data_3.txt");
        File dayFourFile = new File("test_data_4.txt");
        BufferedReader reader = new BufferedReader(new FileReader(dayOneFile));

        double learningConstant = 0.3;

        //Normalized Cubic Weights
        double weightBias = -.92164822;
        double weightOne = 0.386255366;
        double weightTwo = -.03701613;
        double weightThree = .001072931;

        //Quadratic Weights
        /*double weightBias = 0.991584321;
        double weightOne = -.12938558;
        double weightTwo = .004461571;*/

        //Linear Weights
        /*double weightBias = 0.999362854;
        double weightOne = 0.035691530;*/

        double maxY = 9.718;
        double minY = 2.837;

        String line;
        while((line = reader.readLine()) != null){
            String[] values = line.split(", ");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);
            x = 1/(20.0 - 5.0) * (x - 20.0) + 1;
            y = 1/(maxY - minY) * (y - maxY) + 1;

            dayOne.add(new Point(x, y));
        }

        reader = new BufferedReader(new FileReader(dayTwoFile));
        while((line = reader.readLine()) != null){
            String[] values = line.split(", ");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);
            x = 1/(20.0 - 5.0) * (x - 20.0) + 1;
            y = 1/(maxY - minY) * (y - maxY) + 1;

            dayTwo.add(new Point(x, y));
        }

        reader = new BufferedReader(new FileReader(dayThreeFile));
        while((line = reader.readLine()) != null){
            String[] values = line.split(", ");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);
            x = 1/(20.0 - 5.0) * (x - 20.0) + 1;
            y = 1/(maxY - minY) * (y - maxY) + 1;

            dayThree.add(new Point(x, y));
        }

        reader = new BufferedReader(new FileReader(dayFourFile));
        while((line = reader.readLine()) != null){
            String[] values = line.split(", ");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);
            x = 1/(20.0 - 5.0) * (x - 20.0) + 1;
            y = 1/(maxY - minY) * (y - maxY) + 1;

            dayFour.add(new Point(x, y));
        }

        allPoints.addAll(dayOne);
        allPoints.addAll(dayTwo);
        allPoints.addAll(dayThree);

        for(Point p : dayOne){
            System.out.print(p);
        }

        System.out.println();

        for(Point p : dayTwo){
            System.out.print(p);
        }

        System.out.println();

        for(Point p : dayThree){
            System.out.print(p);
        }

        System.out.println();

        for(Point p : dayFour){
            System.out.print(p);
        }

        System.out.println();

        for(int i = 0; i < 10000; i++) {

            // Cubic Function
            for (Point p : allPoints) {
                double weightShift = learningConstant*(p.y - (weightThree * Math.pow(p.x, 3.0) + weightTwo * Math.pow(p.x, 2.0) + weightOne * p.x + weightBias));
                weightBias += weightShift;
                weightOne += weightShift*p.x;
                weightTwo += weightShift*p.x*p.x;
                weightThree += weightShift*p.x*p.x*p.x;
            }

            //Quadratic Function
            /*for(Point p : allPoints){
                double weightShift = learningConstant*(p.y - (weightTwo*Math.pow(p.x, 2.0) + weightOne*p.x + weightBias));
                weightBias += weightShift;
                weightOne += weightShift*p.x;
                weightTwo += weightShift*p.x*p.x;
            }*/

            //Linear Function
            /*for(Point p : allPoints){
                double weightShift = learningConstant*(p.y - (weightOne*p.x + weightBias));
                weightBias += weightShift;
                weightOne += weightShift*p.x;
            }*/
        }

        //Day One
        double yMean = 0.0;
        for(Point p : dayOne){
            yMean += p.y;
        }

        yMean = yMean / dayOne.size();

        double totalError = 0.0;
        for(Point p : dayOne){
            totalError += (p.y - (weightThree * Math.pow(p.x, 3.0) + weightTwo * Math.pow(p.x, 2.0) + weightOne * p.x + weightBias))
                    * (p.y - (weightThree * Math.pow(p.x, 3.0) + weightTwo * Math.pow(p.x, 2.0) + weightOne * p.x + weightBias));
        }

        double errorMean = 0.0;
        for(Point p : dayOne){
            errorMean += ((p.y - yMean) * (p.y - yMean));
        }

        double rScore = 1 - totalError/errorMean;

        System.out.println("R Score : " + rScore);

        yMean = 0.0;
        //Day Two
        for(Point p : dayTwo){
            yMean += p.y;
        }

        yMean = yMean / dayTwo.size();

        totalError = 0.0;
        for(Point p : dayTwo){
            totalError += (p.y - (weightThree * Math.pow(p.x, 3.0) + weightTwo * Math.pow(p.x, 2.0) + weightOne * p.x + weightBias))
                    * (p.y - (weightThree * Math.pow(p.x, 3.0) + weightTwo * Math.pow(p.x, 2.0) + weightOne * p.x + weightBias));
        }

        errorMean = 0.0;
        for(Point p : dayTwo){
            errorMean += ((p.y - yMean) * (p.y - yMean));
        }

        rScore = 1 - totalError/errorMean;

        System.out.println("R Score : " + rScore);


        //Day Three
        yMean = 0.0;
        for(Point p : dayThree){
            yMean += p.y;
        }

        yMean = yMean / dayThree.size();

        totalError = 0.0;
        for(Point p : dayThree){
            totalError += (p.y - (weightThree * Math.pow(p.x, 3.0) + weightTwo * Math.pow(p.x, 2.0) + weightOne * p.x + weightBias))
                    * (p.y - (weightThree * Math.pow(p.x, 3.0) + weightTwo * Math.pow(p.x, 2.0) + weightOne * p.x + weightBias));
        }

        errorMean = 0.0;
        for(Point p : dayThree){
            errorMean += ((p.y - yMean) * (p.y - yMean));
        }

        rScore = 1 - totalError/errorMean;

        System.out.println("R Score : " + rScore);

        //Day Four
        yMean = 0.0;
        for(Point p : dayFour){
            yMean += p.y;
        }

        yMean = yMean / dayFour.size();

        totalError = 0.0;
        for(Point p : dayFour){
            totalError += (p.y - (weightThree * Math.pow(p.x, 3.0) + weightTwo * Math.pow(p.x, 2.0) + weightOne * p.x + weightBias))
                    * (p.y - (weightThree * Math.pow(p.x, 3.0) + weightTwo * Math.pow(p.x, 2.0) + weightOne * p.x + weightBias));
        }

        errorMean = 0.0;
        for(Point p : dayFour){
            errorMean += ((p.y - yMean) * (p.y - yMean));
        }

        rScore = 1 - totalError/errorMean;

        System.out.println("R Score : " + rScore);

        //Cubic Print Out
        System.out.printf("Weight Three: %f\nWeight Two: %f\nWeight One: %f\nWeight Zero: %f\n", weightThree, weightTwo, weightOne, weightBias);

        //Quadratic Print Out
        //System.out.printf("Weight Two: %f\nWeight One: %f\nWeight Zero: %f\n", weightTwo, weightOne, weightBias);

        //Linear Print out
        //System.out.printf("Weight One: %f\nWeight Zero: %f\n", weightOne, weightBias);

        ScatterPlot scatterPlot = new ScatterPlot("Example Chart", dayOne, dayTwo, dayThree);
        scatterPlot.setSize(1200, 600);
        scatterPlot.setLocationRelativeTo(null);
        scatterPlot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        scatterPlot.setVisible(true);
    }
}
