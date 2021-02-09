package EPGDrawAnimate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * @author EdsonPaulo
 */
public class DrawingBoard extends JPanel implements Runnable, MouseListener, MouseMotionListener {

    private final Pencil pencil;
    private final MakeShapes makeShapes;

    private final ArrayList<Shape> shapes;
    private final ArrayList<ArrayList<Point>> pencilPoints;
    private ArrayList<Point> pencilCurrentPath;

    private Graphics2D g2D;
    private Point initialPoint;
    private Point finalPoint;
    public String currentTool;

    private boolean isDrawing = true;
    private int strokeSize = 5;
    private Color strokeColor = Color.GRAY;

    public DrawingBoard() throws Exception {
        this.setBackground(Color.WHITE);

        currentTool = Constants.PENCIL;
        pencil = new Pencil();
        makeShapes = new MakeShapes();
        pencilPoints = new ArrayList<>();
        shapes = new ArrayList<>();

        Thread mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                update();
                Thread.sleep(41);
            } catch (InterruptedException ex) {
                Logger.getLogger(DrawingBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void update() throws InterruptedException {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2D = (Graphics2D) g.create();

        //ACTIVATE ANTIALISING
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2D.setStroke(new BasicStroke(strokeSize));
        g2D.setColor(strokeColor);

        shapes.forEach((shape) -> {
            g2D.draw(shape);
        });

        pencilPoints.forEach((path) -> {
            Point from = null;
            for (Point p : path) {
                //pencil.draw(g2D, p.x, p.y);
                if (from != null) {
                    pencil.drawLine(g2D, from.x, from.y, p.x, p.y);
                }
                from = p;
            }
        });
    }

    private void drawShape() {
        Shape shape = null;
        if (currentTool.equals(Constants.SHAPE_RECTANGLE)) {
            shape = makeShapes.makeRectangle(initialPoint.x, initialPoint.y, finalPoint.x, finalPoint.y);
        } else if (currentTool.equals(Constants.SHAPE_CIRCLE)) {
            shape = makeShapes.makeCircle(initialPoint.x, initialPoint.y, finalPoint.x, finalPoint.y);
        }
        if (shape != null) {
            shapes.add(shape);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.isDrawing = true;

        if (currentTool.equals(Constants.PENCIL)) {
            pencilCurrentPath.add(e.getPoint());
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.isDrawing = true;
        initialPoint = e.getPoint();

        if (currentTool.equals(Constants.PENCIL)) {
            pencilCurrentPath = new ArrayList<>();
            pencilCurrentPath.add(e.getPoint());
            pencilPoints.add(pencilCurrentPath);
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pencilCurrentPath = null;
        finalPoint = e.getPoint();

        if (isDrawing) {
            drawShape();
            isDrawing = false;
        }
        initialPoint = null;
        finalPoint = null;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
