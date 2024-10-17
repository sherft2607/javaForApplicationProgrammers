/*
 * @author: Shandon Herft
 */

public class Hexagon extends Shape {
    /**
     * side.
     */
    private double side;
    public Hexagon(double newSide) {
        super(newSide);
        side = newSide;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double getArea() {
        return (3 * Math.sqrt(3) * side * side) / 2;
    }

    @Override
    public double getPerimeter() {
        return 6 * side;
    }

    @Override
    public String toString() {
        return String.format("Hexagon %.3f %.3f", getArea(), getPerimeter());
    }
}
