/**
 * @author: Shandon Herft
 */

public class ShapeSortTest {
    /**
     * Build an array of StockHolding object and print using for loop.
     * @param args arguments
     */
    public static void main(String[] args) {
        // create an array of shapes
        Shape[] shapes = new Shape[args.length];
        for (int i = 0; i < args.length; i++) {
            String word = args[i];
            String type = word.substring(0, 1);
            String size = word.substring(1);
                double newSize = Double.parseDouble(size);

            switch (type) {
            case "C" :
                shapes[i] = new Circle(newSize);
                break;
            case "S" :
                shapes[i] = new Square(newSize);
                break;
            case "H":
                shapes[i] = new Hexagon(newSize);
                break;
            case "O":
                shapes[i] = new Octagon(newSize);
                break;
            default:
                System.out.println("???");
            }
        }

        for (int i = 0; i < shapes.length; i++) {
            for (int j = i + 1; j < shapes.length; j++) {
                if (shapes[j].getArea() < shapes[i].getArea()) {
                    Shape temp = shapes[i];
                    shapes[i] = shapes[j];
                    shapes[j] = temp;
                }
            }
        }

        for (Shape shape : shapes) {
            System.out.println(shape);
        }

        System.out.println("");

        for (int i = 0; i < shapes.length; i++) {
            for (int j = i + 1; j < shapes.length; j++) {
                if (shapes[j].getPerimeter() > shapes[i].getPerimeter()) {
                    Shape temp = shapes[j];
                    shapes[j] = shapes[i];
                    shapes[i] = temp;
                }
            }
        }

        for (Shape shape : shapes) {
            System.out.println(shape);
        }

    }
}
