import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values.
 */
public class TwoDPoint implements Point {
    private double[] point;
    public TwoDPoint(double x, double y) {
        // TODO
        this.point = new double[2];
        this.point[0] = x;
        this.point[1] = y;
    }

    /**
     * @return the coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        return this.point; // TODO
    }

    /**
     * Returns a list of <code>TwoDPoint</code>s based on the specified array of doubles. A valid argument must always
     * be an even number of doubles so that every pair can be used to form a single <code>TwoDPoint</code> to be added
     * to the returned list of points.
     *
     * @param coordinates the specified array of doubles.
     * @return a list of two-dimensional point objects.
     * @throws IllegalArgumentException if the input array has an odd number of doubles.
     */
    public static List<TwoDPoint> ofDoubles(double... coordinates) throws IllegalArgumentException {
        if(coordinates == null) throw new IllegalArgumentException("Argument passed to ofDoubles was null.");
        if (coordinates.length % 2 != 0) throw new IllegalArgumentException("Input array passed to ofDoubles has odd number of doubles.");

        List<TwoDPoint> points = new ArrayList<>();

        for (int i = 0; i < coordinates.length; i += 2) {
            double x = coordinates[i];
            double y = coordinates[i+1];
            points.add(new TwoDPoint(x,y));
        }

        return points; // TODO
    }

    //Extra
    /*
    public void setCoordinates(double[] newCoordinates) {
        this.point = newCoordinates;
    }
     */

    public double distance(TwoDPoint p1, TwoDPoint p2) {
        double dx = p2.coordinates()[0] - p1.coordinates()[0];
        double dy = p2.coordinates()[1] - p1.coordinates()[1];
        return Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0));
    }

    /*
    @Override
    public int compare(TwoDPoint x, TwoDPoint y){
        TwoDPoint origin = new TwoDPoint(0.0,0.0);
        double first = distance(origin, x);
        double second = distance(origin, y);
        return (int)(first-second);
    }

    @Override
    public int compareTo(TwoDPoint o) {
        TwoDPoint origin = new TwoDPoint(0.0,0.0);
        return (int) (distance(origin) - distance(origin, o));
    }

     */



    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TwoDPoint) {
            TwoDPoint candidate = (TwoDPoint) obj;

            return (this.point[0] == candidate.coordinates()[0]) && (this.point[1] == candidate.coordinates()[1]);
        }
        else return false;

        /*
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoDPoint twoDPoint = null;
        if(o instanceof TwoDPoint) twoDPoint = (TwoDPoint) o;
        return Arrays.equals(point, twoDPoint.point);

         */
    }

    @Override
    public String toString() {
        return "(" + String.format("%.2f", this.coordinates()[0]) + ", " + String.format("%.2f", this.coordinates()[1]) + ")";
    }
}