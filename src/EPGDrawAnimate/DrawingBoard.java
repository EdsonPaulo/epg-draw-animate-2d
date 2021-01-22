package EPGDrawAnimate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EdsonPaulo
 */
public class DrawingBoard extends PanelAsMouseListener implements Runnable {

    private Graphics2D g2D;
    private boolean isDrawing = false;
    private final Pencil pencil;
    private final ArrayList<ArrayList<Point>> points;
    private ArrayList<Point> currentPath;

    public DrawingBoard() throws Exception {
        this.setBackground(Color.WHITE);
        this.getGraphics();

        Thread mainThread = new Thread(this);
        mainThread.start();

        pencil = new Pencil();
        points = new ArrayList<>();
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

        points.forEach((path) -> {
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

    @Override

    public void mouseDragged(MouseEvent e) {
        currentPath.add(e.getPoint());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentPath = new ArrayList<>();
        currentPath.add(e.getPoint());
        points.add(currentPath);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentPath = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
