package EPGDrawAnimate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author EdsonPaulo
 */
public class Workspace extends JFrame implements ActionListener {
    
    private DrawingBoard drawingBoard;
    private Timeline timeLine;
    
    private JToolBar toolsBar;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu tools;
    private JMenuItem help;
    private JMenuItem info;
    private JMenuItem exit;
    
    public Workspace() throws Exception {
        super("EPG - Draw & Animate");
        configTheme();
        initComponents();
        addComponents();
        
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setResizable(false);
        setJMenuBar(menuBar);
        // setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void initComponents() throws Exception {
        drawingBoard = new DrawingBoard();
        drawingBoard.setSize(Constants.DRAWING_BOARD_WIDTH - 300, Constants.DRAWING_BOARD_HEIGHT);
        
        timeLine = new Timeline();
        timeLine.setLayout(new GridLayout(4, 60));
        timeLine.setBackground(Color.gray);
        timeLine.setBounds(0, Constants.WINDOW_HEIGHT - 30,Constants.TIMELINE_WIDTH, Constants.TIMELINE_HEIGHT);
        
        toolsBar = (new ToolsPanel(drawingBoard)).getToolBar();
        
        menuBar = new JMenuBar();
        menu = new JMenu("Ficheiro");
        exit = new JMenuItem("Sair", new ImageIcon(this.getClass().getResource("icons/exit.png")));
        exit.addActionListener(this);
        help = new JMenuItem("Ajuda", new ImageIcon(this.getClass().getResource("icons/help.png")));
        help.addActionListener(this);
        info = new JMenuItem("Sobre", new ImageIcon(this.getClass().getResource("icons/info.png")));
        info.addActionListener(this);
        tools = new JMenu("Ferramentas");
        tools.addActionListener(this);
    }
    
    private void addComponents() {
        menu.add(help);
        menu.add(info);
        menu.add(exit);
        menuBar.add(menu);
        menuBar.add(tools);
        
        
        this.add(menuBar);
        this.add(toolsBar, BorderLayout.NORTH);
        this.add(drawingBoard, BorderLayout.CENTER);
        this.add(timeLine, BorderLayout.SOUTH);
        
        this.addMouseListener(drawingBoard);
        this.addMouseMotionListener(drawingBoard);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tools) {
            System.out.print("CLICOU TOOLS");
        } else if (e.getSource() == help) {
            System.out.print("CLICOU HELP");
        }
    }
    
    private void configTheme() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("ERRO: Falha ao carregar tema do sistema!");
        }
    }
    
    public static void main(String[] args) throws Exception {
        new Workspace();
    }
}
