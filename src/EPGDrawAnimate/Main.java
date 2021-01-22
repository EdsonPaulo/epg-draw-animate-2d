package EPGDrawAnimate;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author EdsonPaulo
 */
public class Main {

    public static void main(String[] args) throws Exception {
        JFrame frmMain = new JFrame("EPG - Draw & Animate");

        frmMain.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT + 30);
        frmMain.setResizable(false);
        frmMain.setLayout(null);
        frmMain.setLocationRelativeTo(null);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Workspace workspace = new Workspace();
        workspace.setBackground(Color.LIGHT_GRAY);
        workspace.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        frmMain.add(workspace);
        frmMain.addMouseListener(workspace);
        frmMain.addMouseMotionListener(workspace);
        frmMain.setVisible((true));
    }
}
