/*
 * @author: Shandon Herft
 */

public class Hexagon extends Shape {
    /**
     * side.
     */
    private double side;
    public Hexagon(double newSide) {
        super((3 * Math.sqrt(3) * newSide * newSide) / 2, 6 * newSide);
        side = newSide;
    }

    public double getSide() {
        return side;
    }

    @Override
    public String toString() {
        return String.format("Hexagon %.3f %.3f", getArea(), getPerimeter());
    }
}
