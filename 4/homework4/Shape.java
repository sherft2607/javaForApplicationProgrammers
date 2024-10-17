/*
 * @author: Shandon Herft
 */

// class
public abstract class Shape {
    /**
     * area instance variable.
    */
    private double area;
    /**
     * perimeter instance variable.
    */
    private double perimeter;
    /**
     * size instance variable.
    */
    private double size;

    // constructor
    public Shape(double newArea, double newPerimeter) {
        area = newArea;
        perimeter = newPerimeter;
    }
    public Shape(double newSide) {
        size = newSide;
    }

    //method to get area
    public abstract double getArea();

    //method to get Perimeter
    public abstract double getPerimeter();

    //overriding string method to print area and perimeter
    @Override
    public String toString() {
        return String.format("Shape %.3f %.3f", getArea(), getPerimeter());
    }
}
