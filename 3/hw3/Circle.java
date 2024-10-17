/*
 * @author: Shandon Herft
 */

public class Circle extends Shape {
    /**
     * radius.
     */
    private double radius;
    public Circle(double newRadius) {
        super(Math.PI * newRadius * newRadius, (2 * Math.PI * newRadius));
        radius = newRadius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return String.format("Circle %.3f %.3f", getArea(), getPerimeter());
    }
}
