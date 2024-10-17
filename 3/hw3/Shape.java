/*
 * @author: Shandon Herft
 */

// class
public class Shape {
    /**
     * area instance variable.
    */
    private double area;
    /**
     * perimeter instance variable.
    */
    private double perimeter;
    // constructor
    public Shape(double newArea, double newPerimeter) {
        area = newArea;
        perimeter = newPerimeter;
    }

    //method to get area
    public double getArea() {
        return area;
    }

    //method to get Perimeter
    public double getPerimeter() {
        return perimeter;
    }

    //overriding string method to print area and perimeter
    @Override
    public String toString() {
        return String.format("Shape %.3f %.3f", getArea(), getPerimeter());
    }
}
