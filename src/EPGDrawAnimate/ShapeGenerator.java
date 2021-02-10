package EPGDrawAnimate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class ShapeGenerator {

    private final int x1;
    private final int x2;
    private final  int y1;
    private final int y2;

    private final Color color;
    private Color fillColor;
    private final BasicStroke stroke;
    private String message;

    public boolean isFilled = false;

    private final String shapeType;
    private Font font;

    public int group = 0;

    public ShapeGenerator(int x1, int y1, int x2, int y2, Color color, BasicStroke stroke, String shape, Color fill, boolean isFilled) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.stroke = stroke;
        this.shapeType = shape;
        this.group = 0;
        this.fillColor = fill;
        this.isFilled = isFilled;
    }

    public ShapeGenerator(int x1, int y1, int x2, int y2, Color color, BasicStroke stroke, String shape) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.stroke = stroke;
        this.shapeType = shape;
        this.group = 0;
    }

    public ShapeGenerator(int x1, int y1, int fontSize, Font font, Color color, BasicStroke stroke, String shape, String message) {
        this.x1 = x1;
        this.y1 = y1;
        this.y2 = 0;
        this.font = font;
        this.x2 = fontSize;
        this.color = color;
        this.stroke = stroke;
        this.shapeType = shape;
        this.group = 0;
        this.message = message;
    }

    public ShapeGenerator(int x1, int y1, int x2, int y2, Color color, BasicStroke stroke, String shape, int group) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.stroke = stroke;
        this.shapeType = shape;
        this.group = group;
    }

    public String getShapeType() {
        return this.shapeType;
    }

    public String getMessage() {
        return this.message;
    }

    public Font getFont() {
        return this.font;
    }

    public int getX1() {
        return this.x1;
    }

    public int getX2() {
        return this.x2;
    }

    public int getY1() {
        return this.y1;
    }

    public int getY2() {
        return this.y2;
    }

    public Color getColor() {
        return this.color;
    }

    public Color getfillColor() {
        return this.fillColor;
    }

    public BasicStroke getStroke() {
        return this.stroke;
    }

    public boolean getTransparency() {
        return this.isFilled;
    }

    public int getGroup() {
        return this.group;
    }
}
