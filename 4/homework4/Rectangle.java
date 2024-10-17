/*
 * @author: Shandon Herft
 */

public class Rectangle extends Shape {
    /**
     * width.
     */
    private double width;
    /**
     * height.
     */
    private double height;
    public Rectangle(double newWidth, double newHeight) {
        super(newWidth, newHeight);
        width = newWidth;
        height = newHeight;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return String.format("Rectangle %.3f %.3f", getArea(), getPerimeter());
    }
}
