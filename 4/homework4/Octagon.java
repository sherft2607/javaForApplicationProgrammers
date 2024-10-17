/*
 * @author: Shandon Herft
 */

public class Octagon extends Shape {
    /**
     * side.
     */
    private double side;
    public Octagon(double newSide) {
        super(newSide);
        side = newSide;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double getArea() {
        return 2 * (1 + Math.sqrt(2)) * side * side;
    }

    @Override
    public double getPerimeter() {
        return 8 * side;
    }

    @Override
    public String toString() {
        return String.format("Octagon %.3f %.3f", getArea(), getPerimeter());
    }
}
