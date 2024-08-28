/**
 * An unmodifiable point in the three-dimensional space. The coordinates are specified by exactly three doubles (its
 * <code>x</code>, <code>y</code>, and <code>z</code> values).
 */
public class ThreeDPoint implements Point {
    private double[] point;
    public ThreeDPoint(double x, double y, double z) {
        // TODO
        point = new double[3];
        point[0] = x;
        point[1] = y;
        point[2] = z;
    }

    /**
     * @return the (x,y,z) coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        return point; // TODO
    }
}