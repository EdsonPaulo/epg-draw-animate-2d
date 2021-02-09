package EPGDrawAnimate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author EdsonPaulo
 */
public class Timeline extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();

        g2D.setColor(Color.BLACK);
        g2D.fillRoundRect(0, 0, Constants.WINDOW_WIDTH, Constants.TIMELINE_HEIGHT, 10, 10);
    }

}
