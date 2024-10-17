/*
 * @author: Shandon Herft
 */

public class Circle extends Shape {
    /**
     * radius.
     */
    private double radius;
    public Circle(double newRadius) {
        super(newRadius);
        radius = newRadius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Circle %.3f %.3f", getArea(), getPerimeter());
    }
}
