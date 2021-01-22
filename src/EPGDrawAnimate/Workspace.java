package EPGDrawAnimate;

/**
 *
 * @author EdsonPaulo
 */
public class Workspace extends PanelAsMouseListener {

    public Workspace() throws Exception {

        DrawingBoard drawingBoard = new DrawingBoard();
        drawingBoard.setBounds(Constants.TOOLBAR_WIDTH, 0, Constants.DRAWING_BOARD_WIDTH, Constants.DRAWING_BOARD_HEIGHT);

        Toolbar toolBar = new Toolbar();
        toolBar.setSize(Constants.TOOLBAR_WIDTH, Constants.TOOLBAR_HEIGHT);

        Timeline timeLine = new Timeline();
        timeLine.setBounds(0, Constants.WINDOW_HEIGHT - Constants.TIMELINE_HEIGHT, Constants.TIMELINE_WIDTH, Constants.TIMELINE_HEIGHT);

        this.setLayout(null);
        this.add(drawingBoard);
        this.add(toolBar);
        this.add(timeLine);

        this.addMouseListener(drawingBoard);
        this.addMouseMotionListener(drawingBoard);
    }
}
