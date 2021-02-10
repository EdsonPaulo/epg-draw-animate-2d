package EPGDrawAnimate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author EdsonPaulo
 */
public final class ShapeHelpers {

    public static void redrawShapes(Graphics2D g2D, ShapeGenerator shape) {
        switch (shape.getShapeType()) {
            case Constants.TOOL_ERASER:
            case Constants.TOOL_PENCIL:
            case Constants.TOOL_LINE:
                g2D.drawLine(shape.getX1(), shape.getY1(), shape.getX2(), shape.getY2());
                break;

            case Constants.TOOL_RECTANGLE:
            case Constants.TOOL_FILL_RECTANGLE:
                g2D.drawRect(shape.getX1(), shape.getY1(), shape.getX2(), shape.getY2());
                if (shape.isFilled) {
                    g2D.setColor(shape.getfillColor());
                    g2D.fillRect(shape.getX1(), shape.getY1(), shape.getX2(), shape.getY2());
                }
                break;

            case Constants.TOOL_ROUND_RECTANGLE:
            case Constants.TOOL_FILL_ROUND_RECTANGLE:
                g2D.drawRoundRect(shape.getX1(), shape.getY1(), shape.getX2(), shape.getY2(), 25, 25);
                if (shape.isFilled) {
                    g2D.setColor(shape.getfillColor());
                    g2D.fillRoundRect(shape.getX1(), shape.getY1(), shape.getX2(), shape.getY2(), 25, 25);
                }
                break;

            case Constants.TOOL_CIRCLE:
            case Constants.TOOL_FILL_CIRCLE:
                g2D.drawOval(shape.getX1(), shape.getY1(), shape.getX2(), shape.getY2());
                if (shape.isFilled) {
                    g2D.setColor(shape.getfillColor());
                    g2D.fillOval(shape.getX1(), shape.getY1(), shape.getX2(), shape.getY2());
                }
                break;

            case Constants.TOOL_TEXT:
                g2D.setFont(shape.getFont());
                g2D.drawString(shape.getMessage(), shape.getX1(), shape.getY1());
                break;
        }
    }


    public static ShapeGenerator generateShape(String shapeType, int x1, int y1, int x2, int y2, Color primaryColor, BasicStroke stroke) {
        if (x1 < x2 && y1 < y2) {
            return new ShapeGenerator(x1, y1, x2 - x1, y2 - y1, primaryColor, stroke, shapeType);
        } else if (x2 < x1 && y1 < y2) {
            return new ShapeGenerator(x2, y1, x1 - x2, y2 - y1, primaryColor, stroke, shapeType);
        } else if (x1 < x2 && y2 < y1) {
            return new ShapeGenerator(x1, y2, x2 - x1, y1 - y2, primaryColor, stroke, shapeType);
        } else {
            return new ShapeGenerator(x2, y2, x1 - x2, y1 - y2, primaryColor, stroke, shapeType);
        }
    }

    public static ShapeGenerator generateFilledShape(String shapeType, int x1, int y1, int x2, int y2, Color primaryColor, Color fillColor, BasicStroke stroke) {
        if (x1 < x2 && y1 < y2) {
            return new ShapeGenerator(x1, y1, x2 - x1, y2 - y1, primaryColor, stroke, shapeType, fillColor, true);
        } else if (x2 < x1 && y1 < y2) {
            return new ShapeGenerator(x2, y1, x1 - x2, y2 - y1, primaryColor, stroke, shapeType, fillColor, true);
        } else if (x1 < x2 && y2 < y1) {
            return new ShapeGenerator(x1, y2, x2 - x1, y1 - y2, primaryColor, stroke, shapeType, fillColor, true);
        } else {
            return new ShapeGenerator(x2, y2, x1 - x2, y1 - y2, primaryColor, stroke, shapeType, fillColor, true);
        }
    }

}
