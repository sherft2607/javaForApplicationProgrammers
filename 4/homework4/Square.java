/*
 * @author: Shandon Herft
 */

public class Square extends Rectangle {
    /**
     * side.
     */
    private double side;

    public Square(double newSide) {
        super(newSide, newSide);
        side = newSide;
    }

    public double getSide() {
        return side;
    }

    @Override
    public String toString() {
        return String.format("Square %.3f %.3f", getArea(), getPerimeter());
    }
}
