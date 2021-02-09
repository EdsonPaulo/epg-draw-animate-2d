package EPGDrawAnimate;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author EdsonPaulo
 */
public class MakeShapes {

    public MakeShapes() {
    }

    public Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    public Ellipse2D.Float makeCircle(int x1, int y1, int x2, int y2) {
        return new Ellipse2D.Float(x1 + ((x2 - x1) / 2), y1 + ((y2 - y1) / 2), x2 - x1, y2 - y1);
    }
}
