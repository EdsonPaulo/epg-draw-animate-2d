package EPGDrawAnimate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author EdsonPaulo
 */
public class Pallete extends JPanel implements ActionListener {

    private final Color BLACK = Color.BLACK;
    private final Color WHITE = Color.WHITE;
    private final Color GRAY = Color.GRAY;
    private final Color RED = Color.RED;
    private final Color GREEN = new Color(22, 160, 133);
    private final Color YELLOW = new Color(241, 196, 15);
    private final Color ORANGE = new Color(230, 126, 34);
    private final Color BLUE = new Color(41, 128, 185);
    private final Color BLUE_DARK = new Color(12, 36, 97);
    private final Color MAGENTA = new Color(142, 68, 173);

    private final DrawingBoard drawBoard;

    private JButton btnBlack, btnWhite, btnGray, btnGreen, btnYellow, btnRed, btnOrange, btnBlue, btnBlueDark, btnMagenta;
    private JButton btnPrimaryColor, btnSecondaryColor;

    private boolean isPrimaryActive;

    public Pallete(DrawingBoard drawBoard) {
        this.drawBoard = drawBoard;
        this.setLayout(new GridLayout(2, 7));

        this.initComponents();

        isPrimaryActive = true;

        // 1st row
        this.add(btnPrimaryColor);
        this.add(new JLabel(""));
        this.add(btnRed);
        this.add(btnMagenta);
        this.add(btnOrange);
        this.add(btnGray);
        this.add(btnBlack);

        // 2nd row
        this.add(btnSecondaryColor);
        this.add(new JLabel(""));
        this.add(btnBlueDark);
        this.add(btnBlue);
        this.add(btnGreen);
        this.add(btnYellow);
        this.add(btnWhite);
    }

    private void initComponents() {
        btnPrimaryColor = generateColorButton(drawBoard.getPrimaryColor());
        btnSecondaryColor = generateColorButton(drawBoard.getSecondaryColor());

        btnBlack = generateColorButton(BLACK);
        btnWhite = generateColorButton(WHITE);
        btnOrange = generateColorButton(ORANGE);
        btnGray = generateColorButton(GRAY);
        btnGreen = generateColorButton(GREEN);
        btnYellow = generateColorButton(YELLOW);
        btnRed = generateColorButton(RED);
        btnBlue = generateColorButton(BLUE);
        btnBlueDark = generateColorButton(BLUE_DARK);
        btnMagenta = generateColorButton(MAGENTA);

        btnPrimaryColor.addActionListener(this);
        btnSecondaryColor.addActionListener(this);
        btnBlack.addActionListener(this);
        btnWhite.addActionListener(this);
        btnOrange.addActionListener(this);
        btnMagenta.addActionListener(this);
        btnGray.addActionListener(this);
        btnGreen.addActionListener(this);
        btnYellow.addActionListener(this);
        btnRed.addActionListener(this);
        btnBlue.addActionListener(this);
        btnBlueDark.addActionListener(this);
    }

    private JButton generateColorButton(Color color) {
        JButton btnColor = new JButton("");
        btnColor.setSize(new Dimension(35, 35));
        btnColor.setBackground(color);
        return btnColor;
    }

    private void toggleColor(Color color) {
        if (isPrimaryActive) {
            drawBoard.setPrimaryColor(color);
            btnPrimaryColor.setBackground(color);
        } else {
            drawBoard.setSecondaryColor(color);
            btnSecondaryColor.setBackground(color);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object btnPressed = e.getSource();
        if (btnPressed == btnPrimaryColor) {
            isPrimaryActive = true;
        } else if (btnPressed == btnSecondaryColor) {
            isPrimaryActive = false;
        } else if (btnPressed == btnBlack) {
            toggleColor(BLACK);
        } else if (btnPressed == btnGray) {
            toggleColor(GRAY);
        } else if (btnPressed == btnGreen) {
            toggleColor(GREEN);
        } else if (btnPressed == btnRed) {
            toggleColor(RED);
        } else if (btnPressed == btnYellow) {
            toggleColor(YELLOW);
        } else if (btnPressed == btnOrange) {
            toggleColor(ORANGE);
        } else if (btnPressed == btnBlue) {
            toggleColor(BLUE);
        } else if (btnPressed == btnBlueDark) {
            toggleColor(BLUE_DARK);
        } else if (btnPressed == btnWhite) {
            toggleColor(WHITE);
        } else if (btnPressed == btnRed) {
            toggleColor(RED);
        } else if (btnPressed == btnMagenta) {
            toggleColor(MAGENTA);
        } else {
            toggleColor(BLACK);
        }
    }

}
