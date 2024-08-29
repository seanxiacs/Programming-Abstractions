import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Circle implements TwoDShape, Positionable, Comparable<TwoDShape>, Comparator<TwoDShape> {

    private TwoDPoint center;
    private double    radius;

    public Circle(double x, double y, double r) {
        this.center = new TwoDPoint(x, y);
        this.radius = r;
    }

    /**
     * Sets the position of this circle to be centered at the first element in the specified list of points.
     *
     * @param points the specified list of points.
     * @throws IllegalArgumentException if the input does not consist of {@link TwoDPoint} instances
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        // TODO
        if(points.size() == 0) throw new IllegalArgumentException("A list of size 0 was passed to setPosition.");
        else if(points.get(0) instanceof TwoDPoint) this.center = (TwoDPoint) points.get(0);
        else throw new IllegalArgumentException("Input passed to setPosition does not consist of TwoDPoint instances.");
    }

    /**
     * @return the center of this circle as an immutable singleton list
     */
    @Override
    public List<? extends Point> getPosition() {
        return Collections.singletonList(this.center); // TODO
    }

    /**
     * @return the number of sides of this circle, which is always set to positive infinity
     */
    @Override
    public int numSides() {
        return (int) Double.POSITIVE_INFINITY;
    }

    /**
     * Checks whether or not a list of vertices is a valid collection of vertices for the type of two-dimensional shape.
     *
     * @param centers the list of vertices to check against, where each vertex is a <code>Point</code> type. For
     *                the Circle object, this list is expected to contain only its center.
     * @return <code>true</code> if and only if <code>centers</code> is a single point, and the radius of this circle is
     * a positive value.
     */
    @Override
    public boolean isMember(List<? extends Point> centers) {
        return centers.size() == 1 && radius > 0;
    }

    /**
     * @return the area of this circle
     */
    public double area() {
        return Math.PI * Math.pow(this.radius, 2.0); // TODO
    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this quadrilateral
     */
    public double perimeter() {
        return Math.PI * 2.0 * this.radius; // TODO
    }

    //Extra
    public double getRadius() {
        return this.radius;
    }

    @Override
    public int compare(TwoDShape o1, TwoDShape o2) {
        return (int) (o1.area() - o2.area());
    }

    @Override
    public int compareTo(TwoDShape o) {
        return (int) (area() - o.area());
    }

    @Override
    public String toString() {
        return "Circle[center: " + String.format("%.2f", this.center.coordinates()[0]) + ", " + String.format("%.2f", this.center.coordinates()[1]) + "; radius: " + String.format("%.2f", this.radius) + "]";
    }

}