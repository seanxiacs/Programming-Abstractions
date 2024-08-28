import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Triangle implements TwoDShape, Positionable, Comparable<TwoDShape>, Comparator<TwoDShape> {

    List<TwoDPoint> vertices;

    public Triangle(List<TwoDPoint> vertices) {
        // TODO
        if (vertices.size() < 3 || !isMember(vertices)) throw new IllegalArgumentException("Argument passed to Triangle constructor is invalid to construct a triangle.");
        setPosition(vertices);
        //sortVerticies(this.vertices);
    }

    /**
     * Sets the position of this triangle according to the first three elements in the specified list of points. The
     * triangle is formed on the basis of these three points taken in a clockwise manner on the two-dimensional
     * x-y plane, starting with the point with the least x-value. If the input list has more than three elements, the
     * subsequent elements are ignored.
     *
     * @param points the specified list of points.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        // TODO
        if (points.size() < 3) throw new IllegalArgumentException("Argument passed to setPosition does not have enough points to create a triangle.");
        if(!isMember(points)) {
            throw new IllegalArgumentException("Argument passed to setPosition is invalid to construct a triangle.");
        }
        this.vertices = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (points.get(i) instanceof TwoDPoint) this.vertices.add((TwoDPoint) points.get(i));
            else throw new IllegalArgumentException("Invalid Point");
        }
        sort();
    }


    /**
     * Retrieve the position of an object as a list of points. The points are be retrieved and added to the returned
     * list in a clockwise manner on the two-dimensional x-y plane, starting with the point with the least x-value. If
     * two points have the same least x-value, then the clockwise direction starts with the point with the lower y-value.
     *
     * @return the retrieved list of points.
     */
    @Override
    public List<? extends Point> getPosition() {
        return vertices; // TODO
    }

    /**
     * @return the number of sides of this triangle, which is always set to three
     */
    @Override
    public int numSides() {
        return 3;
    }

    /**
     * Checks whether or not a list of vertices forms a valid triangle. The <i>trivial</i> triangle, where all three
     * corner vertices are the same point, is considered to be an invalid triangle.
     *
     * @param vertices the list of vertices to check against, where each vertex is a <code>Point</code> type.
     * @return <code>true</code> if <code>vertices</code> is a valid collection of points for a triangle, and
     * <code>false</code> otherwise. For example, three vertices are in a straight line is invalid.
     */
    @Override
    public boolean isMember(List<? extends Point> vertices) {
        if(vertices.size() < 3) {
            throw new IllegalArgumentException("Argument passed to isMember is list of size less than 3.");
        }

        TwoDPoint v1 = (TwoDPoint) vertices.get(0);
        TwoDPoint v2 = (TwoDPoint) vertices.get(1);
        TwoDPoint v3 = (TwoDPoint) vertices.get(2);

        if(v1.equals(v2) || v2.equals(v3) || v3.equals(v1)) return false;

        double d12 = distance(v1, v2);
        double d23 = distance(v2, v3);
        double d31 = distance(v3, v1);

        //This checks for if they are all in the same line
        if((d12+d23 <= d31) || (d23+d31 <= d12) || (d31+d12 <= d23)) return false;

        return true; // TODO
    }

    /**
     * This method snaps each vertex of this triangle to its nearest integer-valued x-y coordinate. For example, if
     * a corner is at (0.8, -0.1), it will be snapped to (1,0). The resultant triangle will thus have all three
     * vertices in positions with integer x and y values. If the snapping procedure described above results in this
     * triangle becoming invalid (e.g., all corners collapse to a single point), then it is left unchanged. Snapping is
     * an in-place procedure, and the current instance is modified.
     */
    public void snap() {
        // TODO
        TwoDPoint v1 = new TwoDPoint(this.vertices.get(0).coordinates()[0], this.vertices.get(0).coordinates()[1]);
        TwoDPoint v2 = new TwoDPoint(this.vertices.get(1).coordinates()[0], this.vertices.get(0).coordinates()[1]);
        TwoDPoint v3 = new TwoDPoint(this.vertices.get(2).coordinates()[0], this.vertices.get(0).coordinates()[1]);

        for(int i = 0; i < numSides(); i++) {
            TwoDPoint temp = vertices.get(i);
            temp.coordinates()[0] = Math.round(temp.coordinates()[0]);
            temp.coordinates()[1] = Math.round(temp.coordinates()[1]);
            //this.vertices.get(i) = new TwoDPoint(Math.round(temp[0]), Math.round(temp[1 ]));
        }

        if(!isMember(this.vertices)) {
            TwoDPoint temp = vertices.get(0);
            temp.coordinates()[0] = v1.coordinates()[0];
            temp.coordinates()[1] = v1.coordinates()[1];

            temp = vertices.get(1);
            temp.coordinates()[0] = v2.coordinates()[0];
            temp.coordinates()[1] = v2.coordinates()[1];

            temp = vertices.get(2);
            temp.coordinates()[0] = v3.coordinates()[0];
            temp.coordinates()[1] = v3.coordinates()[1];
        }
        /*
        List<TwoDPoint> snapped = new ArrayList<>();

        for(int i = 0; i < numSides(); i++) {
            TwoDPoint temp = vertices.get(i);
            int xSnapped = (int) Math.round(temp.coordinates()[0]);
            int ySnapped = (int) Math.round(temp.coordinates()[1]);
            TwoDPoint newPoint = new TwoDPoint(xSnapped, ySnapped);
            snapped.add(newPoint);
        }

        if(isMember(snapped)) setPosition(snapped);

         */
    }


    /**
     * @return the area of this triangle
     */
    public double area() {
        double x1 = this.vertices.get(0).coordinates()[0];
        double y1 = this.vertices.get(0).coordinates()[1];
        double x2 = this.vertices.get(1).coordinates()[0];
        double y2 = this.vertices.get(1).coordinates()[1];
        double x3 = this.vertices.get(2).coordinates()[0];
        double y3 = this.vertices.get(2).coordinates()[1];
        return Math.abs(x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2))/2.0; // TODO
    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this triangle
     */
    public double perimeter() {
        return distance(this.vertices.get(0), this.vertices.get(1)) + distance(this.vertices.get(1), this.vertices.get(2)) + distance(this.vertices.get(2), this.vertices.get(0)); // TODO
    }

    //Extra
    public double distance(TwoDPoint p1, TwoDPoint p2) {
        double dx = p2.coordinates()[0] - p1.coordinates()[0];
        double dy = p2.coordinates()[1] - p1.coordinates()[1];
        return Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0));
    }

    /*
    public void sort() {

    }

     */

    public Point findReference(List<TwoDPoint> points) {
        double x = 0;
        double y = 0;
        for(Point p : points) {
            x += p.coordinates()[0];
            y += p.coordinates()[1];
        }
        Point center = new TwoDPoint(0, 0);
        center.coordinates()[0] = x / points.size();
        center.coordinates()[1] = y / points.size();
        return center;
    }

    public void sort() {
        // get centroid
        TwoDPoint center = (TwoDPoint) findReference(this.vertices);
        this.vertices.sort((a, b) -> {
            double a1 = (Math.toDegrees(Math.atan2(a.coordinates()[0] - center.coordinates()[0], a.coordinates()[1] - center.coordinates()[1])) + 360) % 360;
            double a2 = (Math.toDegrees(Math.atan2(b.coordinates()[0] - center.coordinates()[0], b.coordinates()[1] - center.coordinates()[1])) + 360) % 360;
            return (int) (a1 - a2);
        });
        int minIndex = 0;
        TwoDPoint min = this.vertices.get(0);
        for(int i = 1; i < this.vertices.size(); i++) {
            double minX = min.coordinates()[0];
            double minY = min.coordinates()[1];
            double curX = this.vertices.get(i).coordinates()[0];
            double curY = this.vertices.get(i).coordinates()[1];
            if(minX >= curX) {
                if(curX == minX) {
                    if(curY < minY) {
                        minIndex = i;
                        min = this.vertices.get(i);
                    }
                } else {
                    minIndex = i;
                    min = this.vertices.get(i);
                }
            }
        }
        int shift = numSides() - minIndex;
        List<TwoDPoint> newList = Arrays.asList(null, null, null);
        for(int i = 0; i < this.vertices.size(); i++) {
            int newPosition = (i + shift) % this.vertices.size();
            newList.set(newPosition, this.vertices.get(i));
        }
        this.vertices = newList;
    }


    @Override
    public int compare(TwoDShape o1, TwoDShape o2) {
        return (int) (o1.area() - o2.area());
    }

    @Override
    public int compareTo(TwoDShape o) {
        return (int)(area() - o.area());
    }



    @Override
    public String toString(){
        return "Triangle[" + this.vertices.get(0) + ", " + this.vertices.get(1) + ", " + this.vertices.get(2) + "]";
    }


}