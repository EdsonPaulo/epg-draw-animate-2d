package EPGDrawAnimate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author EdsonPaulo
 */
public class Workspace extends JFrame implements ActionListener {

    private DrawingBoard drawingBoard;
    private JPanel timeLine;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu help;
    private JMenu tools;

    private JMenu pencilMenu;
    private JMenu shapeMenu;
    private JMenu eraserMenu;

    private JMenuItem pencil;
    private JMenuItem circle;
    private JMenuItem rectangle;
    private JMenuItem exit;

    private JMenuItem eraser;

    public Workspace() throws Exception {
        super("EPG - Draw & Animate");
        //configTheme();
        initComponents();
        addComponents();

        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setResizable(false);
        setJMenuBar(menuBar);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() throws Exception {
        /**
         * Drawing Board
         */
        drawingBoard = new DrawingBoard();
        drawingBoard.setBounds(0, 0, Constants.DRAWING_BOARD_WIDTH, Constants.DRAWING_BOARD_HEIGHT);

        menuBar = new JMenuBar();
        // file menu
        menu = new JMenu("Ficheiro");
        exit = new JMenuItem("Sair");
        exit.addActionListener(this);

        pencilMenu = new JMenu("Desenho Livre");
        pencil = new JMenuItem("Pincel");
        pencil.addActionListener(this);

        // shape menu
        shapeMenu = new JMenu("Formas Geométricas");
        circle = new JMenuItem("Círculo");
        circle.addActionListener(this);
        rectangle = new JMenuItem("Rectângulo");
        rectangle.addActionListener(this);

        //eraser menu 
        eraserMenu = new JMenu("Borracha");
        eraser = new JMenuItem("Borracha");
        eraser.addActionListener(this);

        tools = new JMenu("Ferramentas");
        tools.addActionListener(this);

        help = new JMenu("Ajuda");
        help.addActionListener(this);

    }

    private void addComponents() {
        // file menu
        menu.add(exit);
        menuBar.add(menu);

        // pencil menu
        pencilMenu.add(pencil);
        menuBar.add(pencilMenu);

        // shape menu
        shapeMenu.add(circle);
        shapeMenu.add(rectangle);
        menuBar.add(shapeMenu);

        //eraser menu 
        eraserMenu.add(eraser);
        menuBar.add(eraserMenu);

        menuBar.add(tools);
        menuBar.add(help);

        this.add(menuBar);
        this.add(drawingBoard);
        this.addMouseListener(drawingBoard);
        this.addMouseMotionListener(drawingBoard);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pencil) {
            drawingBoard.currentTool = Constants.PENCIL;
        } else if (e.getSource() == circle) {
            drawingBoard.currentTool = Constants.SHAPE_CIRCLE;
        } else if (e.getSource() == rectangle) {
            drawingBoard.currentTool = Constants.SHAPE_RECTANGLE;
        } else if (e.getSource() == eraser) {
            drawingBoard.currentTool = Constants.ERASER;

        } else if (e.getSource() == tools) {
            System.out.print("CLICOU TOOLS");
        } else if (e.getSource() == help) {
            System.out.print("CLICOU HELP");
        }
    }

    private void configTheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
    }

    public static void main(String[] args) throws Exception {
        new Workspace();
    }

}
