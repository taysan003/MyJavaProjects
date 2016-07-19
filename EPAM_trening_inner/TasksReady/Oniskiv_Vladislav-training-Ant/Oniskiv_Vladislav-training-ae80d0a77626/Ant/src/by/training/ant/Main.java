package by.training.ant;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Main.
 */
final class Main {

    private Main() {

    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        List<Shape> shapes = new ArrayList<Shape>();
        shapes.add(new Circle());
        shapes.add(new Triangle());
        shapes.add(new Square());
        for (Shape s : shapes) {
            s.myName();
        }
    }
}
