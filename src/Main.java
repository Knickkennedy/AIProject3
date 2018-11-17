import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException {

        ArrayList<Point> dayOne = new ArrayList<>();
        ArrayList<Point> dayTwo = new ArrayList<>();
        ArrayList<Point> dayThree = new ArrayList<>();

        File dayOneFile = new File("train_data_1.txt");
        File dayTwoFile = new File("train_data_2.txt");
        File dayThreeFile = new File("train_data_3.txt");
        BufferedReader reader = new BufferedReader(new FileReader(dayOneFile));

        String line = null;
        while((line = reader.readLine()) != null){
            String[] values = line.split(", ");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);

            dayOne.add(new Point(x, y));
        }

        reader = new BufferedReader(new FileReader(dayTwoFile));
        while((line = reader.readLine()) != null){
            String[] values = line.split(", ");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);

            dayTwo.add(new Point(x, y));
        }

        reader = new BufferedReader(new FileReader(dayThreeFile));
        while((line = reader.readLine()) != null){
            String[] values = line.split(", ");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);

            dayThree.add(new Point(x, y));
        }

        ScatterPlot scatterPlot = new ScatterPlot("Example Chart", dayOne, dayTwo, dayThree);
        scatterPlot.setSize(1600, 800);
        scatterPlot.setLocationRelativeTo(null);
        scatterPlot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        scatterPlot.setVisible(true);
    }
}
