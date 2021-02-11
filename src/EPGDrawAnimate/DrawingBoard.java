package EPGDrawAnimate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author EdsonPaulo
 */
public class DrawingBoard extends JPanel implements Runnable, MouseListener, MouseMotionListener {

    private Stack<ShapeGenerator> shapesOnBoard;
    private final Stack<ShapeGenerator> previewShapes;
    private final Stack<ShapeGenerator> removedShapes;
    private final ArrayList<Graphics2D> layerStates;

    private Graphics2D g2D;
    private BufferedImage canvas;

    private String currentTool;

    private BasicStroke stroke;

    private int grouped = 0;

    private boolean isDragged = false;

    private Color primaryColor = Color.BLACK;
    private Color secondaryColor = Color.WHITE;

    private Color strokeColor = primaryColor;

    private final int drawBoardWidth;
    private final int drawBoardHeight;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public DrawingBoard() throws Exception {
        this.setBackground(Color.WHITE);

        currentTool = Constants.TOOL_PENCIL;
        layerStates = new ArrayList<>();
        shapesOnBoard = new Stack<>();

        setPreferredSize(new Dimension(Constants.DRAWING_BOARD_WIDTH, Constants.DRAWING_BOARD_HEIGHT));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        drawBoardWidth = dimension.width - 150;
        drawBoardHeight = dimension.height - 160;

        setSize(drawBoardWidth - 3, drawBoardHeight - 3);
        setPreferredSize(new Dimension(drawBoardWidth - 3, drawBoardHeight - 3));
        setLayout(null);
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();
        setLocation(100, 100);
        setBackground(Color.WHITE);

        addMouseListener(this);
        addMouseMotionListener(this);

        primaryColor = Color.BLACK;
        secondaryColor = Color.white;

        grouped = 1;
        shapesOnBoard = new Stack<>();
        removedShapes = new Stack<>();
        previewShapes = new Stack<>();

        stroke = new BasicStroke((float) 2);

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

    public void clear() {
        g2D.setPaint(Color.white);
        g2D.fillRect(0, 0, getSize().width, getSize().height);
        shapesOnBoard.removeAllElements();
        removedShapes.removeAllElements();
        repaint();
        canvas.setRGB(255, 255, 255);
        g2D.setColor(primaryColor);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (canvas == null) {
            canvas = new BufferedImage(Constants.DRAWING_BOARD_WIDTH, Constants.DRAWING_BOARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            g2D = canvas.createGraphics();
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(canvas, 0, 0, null);

        g2D = (Graphics2D) g.create();

        if (g2D != null) {
            layerStates.add(g2D);
            // System.out.println("Salvou layer");
        }

        //ACTIVATE ANTIALISING
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2D.setStroke(stroke);
        g2D.setColor(strokeColor);

        shapesOnBoard.forEach((ShapeGenerator shape) -> {
            g2D.setColor(shape.getColor());
            g2D.setStroke(shape.getStroke());
            ShapeHelpers.redrawShapes(g2D, shape);
        });

        if (previewShapes.size() > 0) {
            ShapeGenerator shape = previewShapes.pop();
            g2D.setColor(shape.getColor());
            g2D.setStroke(shape.getStroke());
            ShapeHelpers.redrawShapes(g2D, shape);
        }
    }

    public void undo() {
        if (shapesOnBoard.size() > 0) {
            if (shapesOnBoard.peek().group == 0) {
                removedShapes.push(shapesOnBoard.pop());
                repaint();
            } else {
                ShapeGenerator lastRemoved = shapesOnBoard.pop();
                removedShapes.push(lastRemoved);
                while (!shapesOnBoard.isEmpty() && shapesOnBoard.peek().group == lastRemoved.group) {
                    removedShapes.push(shapesOnBoard.pop());
                    repaint();
                }
            }
        }
    }

    public void redo() {
        if (removedShapes.size() > 0) {
            if (removedShapes.peek().group == 0) {
                shapesOnBoard.push(removedShapes.pop());
                repaint();
            } else {
                ShapeGenerator lastRemoved = removedShapes.pop();
                shapesOnBoard.push(lastRemoved);
                while (!removedShapes.isEmpty() && removedShapes.peek().group == lastRemoved.group) {
                    shapesOnBoard.push(removedShapes.pop());
                    repaint();
                }
            }
        }
    }

    public void fillContainer(Point2D.Double point, Color fillColor) {
        Color targetColor = new Color(canvas.getRGB((int) point.getX(), (int) point.getY()));
        Queue<Point2D.Double> queue = new LinkedList<>();
        queue.add(point);
        if (!targetColor.equals(fillColor));
        while (!queue.isEmpty()) {
            Point2D.Double p = queue.remove();

            if ((int) p.getX() >= 0 && (int) p.getX() < canvas.getWidth()
                    && (int) p.getY() >= 0 && (int) p.getY() < canvas.getHeight()) {
                if (canvas.getRGB((int) p.getX(), (int) p.getY()) == targetColor.getRGB()) {
                    canvas.setRGB((int) p.getX(), (int) p.getY(), fillColor.getRGB());
                    queue.add(new Point2D.Double(p.getX() - 1, p.getY()));
                    queue.add(new Point2D.Double(p.getX() + 1, p.getY()));
                    queue.add(new Point2D.Double(p.getX(), p.getY() - 1));
                    queue.add(new Point2D.Double(p.getX(), p.getY() + 1));
                    //System.out.println("0");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Color primary = primaryColor;
        Color secondary = secondaryColor;
        if (SwingUtilities.isRightMouseButton(e)) {
            primary = secondary;
            secondary = primaryColor;
        }
        x2 = e.getX();
        y2 = e.getY();
        isDragged = true;

        switch (currentTool) {
            case Constants.TOOL_ERASER:
                shapesOnBoard.push(new ShapeGenerator(x1, y1, x2, y2, Color.white, stroke, Constants.TOOL_ERASER, grouped));
                x1 = x2;
                y1 = y2;
                break;
            case Constants.TOOL_PENCIL:
                shapesOnBoard.push(new ShapeGenerator(x1, y1, x2, y2, primary, stroke, Constants.TOOL_PENCIL, grouped));
                x1 = x2;
                y1 = y2;
                break;
            case Constants.TOOL_LINE:
                previewShapes.push(new ShapeGenerator(x1, y1, x2, y2, primary, stroke, Constants.TOOL_LINE, secondary, false));
                break;
            case Constants.TOOL_RECTANGLE:
                previewShapes.push(ShapeHelpers.generateShape(Constants.TOOL_RECTANGLE, x1, y1, x2, y2, primary, stroke));
                break;
            case Constants.TOOL_FILL_RECTANGLE:
                previewShapes.push(ShapeHelpers.generateFilledShape(Constants.TOOL_FILL_RECTANGLE, x1, y1, x2, y2, primary, secondary, stroke));
                break;
            case Constants.TOOL_ROUND_RECTANGLE:
                previewShapes.push(ShapeHelpers.generateShape(Constants.TOOL_ROUND_RECTANGLE, x1, y1, x2, y2, primary, stroke));
                break;
            case Constants.TOOL_FILL_ROUND_RECTANGLE:
                previewShapes.push(ShapeHelpers.generateFilledShape(Constants.TOOL_FILL_ROUND_RECTANGLE, x1, y1, x2, y2, primary, secondary, stroke));
                break;
            case Constants.TOOL_CIRCLE:
                previewShapes.push(ShapeHelpers.generateShape(Constants.TOOL_CIRCLE, x1, y1, x2, y2, primary, stroke));
                break;
            case Constants.TOOL_FILL_CIRCLE:
                previewShapes.push(ShapeHelpers.generateFilledShape(Constants.TOOL_FILL_CIRCLE, x1, y1, x2, y2, primary, secondary, stroke));
                break;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        grouped++;
        Color primary = primaryColor;
        Color secondary = secondaryColor;
        if (SwingUtilities.isRightMouseButton(e)) {
            primary = secondary;
            secondary = primaryColor;
        }
        if (isDragged) {
            switch (currentTool) {
                case Constants.TOOL_LINE:
                    shapesOnBoard.push(new ShapeGenerator(x1, y1, x2, y2, primary, stroke, Constants.TOOL_LINE, secondaryColor, false));
                    repaint();
                    break;
                case Constants.TOOL_RECTANGLE:
                    shapesOnBoard.push(ShapeHelpers.generateShape(Constants.TOOL_RECTANGLE, x1, y1, x2, y2, primary, stroke));
                    break;
                case Constants.TOOL_FILL_RECTANGLE:
                    shapesOnBoard.push(ShapeHelpers.generateFilledShape(Constants.TOOL_FILL_RECTANGLE, x1, y1, x2, y2, primary, secondary, stroke));
                    break;
                case Constants.TOOL_ROUND_RECTANGLE:
                    shapesOnBoard.push(ShapeHelpers.generateShape(Constants.TOOL_ROUND_RECTANGLE, x1, y1, x2, y2, primary, stroke));
                    break;
                case Constants.TOOL_FILL_ROUND_RECTANGLE:
                    shapesOnBoard.push(ShapeHelpers.generateFilledShape(Constants.TOOL_FILL_ROUND_RECTANGLE, x1, y1, x2, y2, primary, secondary, stroke));
                    break;
                case Constants.TOOL_CIRCLE:
                    shapesOnBoard.push(ShapeHelpers.generateShape(Constants.TOOL_CIRCLE, x1, y1, x2, y2, primary, stroke));
                    break;
                case Constants.TOOL_FILL_CIRCLE:
                    shapesOnBoard.push(ShapeHelpers.generateFilledShape(Constants.TOOL_FILL_CIRCLE, x1, y1, x2, y2, primary, secondary, stroke));
                    break;
                case Constants.TOOL_TEXT:
                    // int i = td.showCustomDialog(frame);
                    // if (i == TextDialog.APPLY_OPTION) {
                    //     shapes.push(new ShapeGenerator(x1, y1, td.getInputSize(), td.getFont(), primary, stroke, Constants.TOOL_TEXT, td.getText())); //int x1, int y1, int fontSize, Color color, Font font
                    // }
                    break;
                case Constants.TOOL_FILL:
                    fillContainer(new Point2D.Double(x1, y1), primaryColor);
                    break;
            }
            isDragged = false;
            removedShapes.removeAllElements();
            repaint();
        }

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

    public String getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(String tool) {
        this.currentTool = tool;
    }

    public Graphics2D getGraphics2D() {
        return g2D;
    }

    public void setGraphics2D(Graphics2D g2D) {
        this.g2D = g2D;
    }

    public boolean isIsDragged() {
        return isDragged;
    }

    public void setIsDragged(boolean isDragged) {
        this.isDragged = isDragged;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStrokeSize(int strokeSize) {
        stroke = new BasicStroke((float) strokeSize);
        g2D.setStroke(stroke);
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }
}
