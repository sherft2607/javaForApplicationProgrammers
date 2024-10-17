/*
 * @author: Shandon Herft
 */

public class Octagon extends Shape {
    /**
     * side.
     */
    private double side;
    public Octagon(double newSide) {
        super(2 * (1 + Math.sqrt(2)) * newSide * newSide, 8 * newSide);
        side = newSide;
    }

    public double getSide() {
        return side;
    }

    @Override
    public String toString() {
        return String.format("Octagon %.3f %.3f", getArea(), getPerimeter());
    }
}
