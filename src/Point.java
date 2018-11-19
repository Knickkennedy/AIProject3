public class Point {
    public final double x;
    public final double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double net(double firstWeight, double secondWeight, double bias){
        return firstWeight*x + secondWeight*y + bias;
    }

    @Override
    public String toString(){
        return String.format("(%f, %f),", this.x, this.y);
    }
}
