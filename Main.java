import java.util.ArrayList;
import java.util.List;

class Visual {
    public static void drawLine(double x1, double y1, double x2, double y2) {
        // Implementação para desenhar uma linha
        System.out.println("Desenhando uma linha de (" + x1 + "," + y1 + ") para (" + x2 + "," + y2 + ")");
    }

    public static void drawRectangle(double x, double y, double altura, double largura) {
        // Implementação para desenhar um retângulo
        System.out.println("Desenhando um retângulo em (" + x + "," + y + ") com altura " + altura + " e largura " + largura);
    }

    public static void drawCircle(double x, double y, double raio) {
        // Implementação para desenhar um círculo
        System.out.println("Desenhando um círculo em (" + x + "," + y + ") com raio " + raio);
    }
}

class GeometricObject implements Drawable{
    private String type;
    private double x1, y1, x2, y2, radius;

    public GeometricObject(String type, double x1, double y1, double x2, double y2, double radius) {
        this.type = type;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.radius = radius;
    }

    @Override
    public void draw() {
        if ("Line".equals(type)) {
            Visual.drawLine(x1, y1, x2, y2);
        } else if ("Rectangle".equals(type)) {
            Visual.drawRectangle(x1, y1, x2 - x1, y2 - y1);
        } else if ("Circle".equals(type)) {
            Visual.drawCircle(x1 + (x2 - x1) / 2, y1 + (y2 - y1) / 2, radius);
        }
    }
}

interface Drawable {
    void draw();
}

class CompositeObject implements Drawable {
    private List<Drawable> components = new ArrayList<>();

    public void addComponent(Drawable component) {
        components.add(component);
    }

    @Override
    public void draw() {
        for (Drawable component : components) {
            component.draw();
        }
    }
}

interface GeometricFactory {
    GeometricObject createLine(double x1, double y1, double x2, double y2);

    GeometricObject createRectangle(double x1, double y1, double x2, double y2);

    GeometricObject createCircle(double x1, double y1, double radius);

    CompositeObject createComposite();
}

class SimpleGeometricFactory implements GeometricFactory {
    @Override
    public GeometricObject createLine(double x1, double y1, double x2, double y2) {
        return new GeometricObject("Line", x1, y1, x2, y2, 0);
    }

    @Override
    public GeometricObject createRectangle(double x1, double y1, double x2, double y2) {
        return new GeometricObject("Rectangle", x1, y1, x2, y2, 0);
    }

    @Override
    public GeometricObject createCircle(double x1, double y1, double radius) {
        return new GeometricObject("Circle", x1, y1, 0, 0, radius);
    }

    @Override
    public CompositeObject createComposite() {
        return new CompositeObject();
    }
}

public class Main {
    public static void main(String[] args) {
        GeometricFactory factory = new SimpleGeometricFactory();

        GeometricObject line = factory.createLine(10, 10, 50, 50);
        GeometricObject rectangle = factory.createRectangle(20, 20, 60, 60);
        GeometricObject circle = factory.createCircle(40, 40, 30);

        CompositeObject composite = factory.createComposite();
        composite.addComponent(line);
        composite.addComponent(rectangle);
        composite.addComponent(circle);

        composite.draw();
    }
}
