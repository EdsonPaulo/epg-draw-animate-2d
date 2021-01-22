package EPGDrawAnimate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author EdsonPaulo
 */
public class Pencil {

    private int size = 10;
    private Color color = Color.BLACK;
    private Boolean isDrawing;

    public Pencil() {

    }

    public Pencil(Color color, int size) {
        this.color = color;
        this.size = size;
        this.isDrawing = false;
    }

    public void draw(Graphics2D g2D, int x, int y) {
        g2D.setColor(color);
        g2D.fillOval(x - size, y - size, size, size);
    }

    public void drawLine(Graphics2D g2D, int x1, int y1, int x2, int y2) {
        g2D.setColor(color);
        g2D.setStroke(new BasicStroke(size));
        g2D.drawLine(x1, y1, x2, y2);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean getIsDrawing() {
        return isDrawing;
    }

    public void setIsDrawing(Boolean isDrawing) {
        this.isDrawing = isDrawing;
    }

}
