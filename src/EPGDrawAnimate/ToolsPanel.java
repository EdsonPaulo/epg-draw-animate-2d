package EPGDrawAnimate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author EdsonPaulo
 */
public class ToolsPanel implements ActionListener {

    private final DrawingBoard drawBoard;
    private  Pallete pallete;
    private JComboBox cbxLineHeight;

    private JToolBar toolBar;
    private JPanel optionsPanel, drawToolsPanel, lineHeightPanel;
    private JButton btnPencil, btnFill, btnLine, btnText, btnEraser;
    private JButton btnFillRectangle, btnFillRoundRectangle, btnFillCircle;
    private JButton btnRectangle, btnRoundRectangle, btnCircle;

    private JButton btnClear, btnUndo, btnRedo;

    String[] lineHeightItems = {"Tamanho da Linha", "2", "4", "6", "8", "10", "12", "14", "16"};

    public ToolsPanel(DrawingBoard drawBoard) {
        this.drawBoard = drawBoard;
        this.initComponents();

        btnPencil.addActionListener(this);
        btnFill.addActionListener(this);
        btnText.addActionListener(this);
        btnLine.addActionListener(this);

        btnCircle.addActionListener(this);
        btnFillCircle.addActionListener(this);

        btnRectangle.addActionListener(this);
        btnFillRectangle.addActionListener(this);

        btnFillRoundRectangle.addActionListener(this);
        btnRoundRectangle.addActionListener(this);

        btnEraser.addActionListener(this);
        btnClear.addActionListener(this);
        btnUndo.addActionListener(this);
        btnRedo.addActionListener(this);

        cbxLineHeight.addActionListener(this);
    }

    private void initComponents() {
        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        toolBar.setFloatable(false);
        toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        toolBar.setLayout(new GridLayout(1, 3));
        
        pallete = new Pallete(drawBoard);

        btnPencil = new JButton("", new ImageIcon(this.getClass().getResource("icons/pencil.png")));
        btnLine = new JButton("", new ImageIcon(this.getClass().getResource("icons/line.png")));
        btnFill = new JButton("", new ImageIcon(this.getClass().getResource("icons/fill.png")));
        btnText = new JButton("", new ImageIcon(this.getClass().getResource("icons/text.png")));
        btnEraser = new JButton("", new ImageIcon(this.getClass().getResource("icons/eraser.png")));

        btnRectangle = new JButton("", new ImageIcon(this.getClass().getResource("icons/rectangle.png")));
        btnFillRectangle = new JButton("", new ImageIcon(this.getClass().getResource("icons/rectangle-fill.png")));

        btnCircle = new JButton("", new ImageIcon(this.getClass().getResource("icons/circle.png")));
        btnFillCircle = new JButton("", new ImageIcon(this.getClass().getResource("icons/circle-fill.png")));

        btnRoundRectangle = new JButton("", new ImageIcon(this.getClass().getResource("icons/round-rectangle.png")));
        btnFillRoundRectangle = new JButton("", new ImageIcon(this.getClass().getResource("icons/round-rectangle.png")));

        btnClear = new JButton("", new ImageIcon(this.getClass().getResource("icons/clear.png")));
        btnUndo = new JButton("", new ImageIcon(this.getClass().getResource("icons/undo.png")));
        btnRedo = new JButton("", new ImageIcon(this.getClass().getResource("icons/redo.png")));

        cbxLineHeight = new JComboBox(lineHeightItems);

        optionsPanel = new JPanel(new GridLayout(1, 3));
        optionsPanel.add(btnUndo);
        optionsPanel.add(btnRedo);
        optionsPanel.add(btnClear);

        drawToolsPanel = new JPanel(new GridLayout(2, 6));
        drawToolsPanel.add(btnPencil);
        drawToolsPanel.add(btnLine);
        drawToolsPanel.add(btnRectangle);
        drawToolsPanel.add(btnCircle);
        drawToolsPanel.add(btnRoundRectangle);
        drawToolsPanel.add(btnText);

        drawToolsPanel.add(btnEraser);
        drawToolsPanel.add(btnFill);
        drawToolsPanel.add(btnFillRectangle);
        drawToolsPanel.add(btnFillCircle);
        drawToolsPanel.add(btnFillRoundRectangle);

        lineHeightPanel = new JPanel(new FlowLayout());
        lineHeightPanel.add(new JLabel("", new ImageIcon(this.getClass().getResource("icons/line-height.png")), 0));
        lineHeightPanel.add(cbxLineHeight);

        toolBar.add(optionsPanel);
        toolBar.addSeparator();
        toolBar.add(drawToolsPanel);
        toolBar.addSeparator();
        toolBar.add(pallete);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object btnPressed = e.getSource();
        if (btnPressed == btnPencil) {
            drawBoard.setCurrentTool(Constants.TOOL_PENCIL);
        } else if (btnPressed == btnRectangle) {
            drawBoard.setCurrentTool(Constants.TOOL_RECTANGLE);
        } else if (btnPressed == btnRoundRectangle) {
            drawBoard.setCurrentTool(Constants.TOOL_ROUND_RECTANGLE);
        } else if (btnPressed == btnCircle) {
            drawBoard.setCurrentTool(Constants.TOOL_CIRCLE);
        } else if (btnPressed == btnFillRectangle) {
            drawBoard.setCurrentTool(Constants.TOOL_FILL_RECTANGLE);
        } else if (btnPressed == btnFillRoundRectangle) {
            drawBoard.setCurrentTool(Constants.TOOL_FILL_ROUND_RECTANGLE);
        } else if (btnPressed == btnFillCircle) {
            drawBoard.setCurrentTool(Constants.TOOL_FILL_CIRCLE);
        } else if (btnPressed == btnLine) {
            drawBoard.setCurrentTool(Constants.TOOL_LINE);
        } else if (btnPressed == btnEraser) {
            drawBoard.setCurrentTool(Constants.TOOL_ERASER);
        } else if (btnPressed == btnFill) {
            drawBoard.setCurrentTool(Constants.TOOL_FILL);
        } else if (btnPressed == cbxLineHeight) {
            try {
                JComboBox combo = (JComboBox) e.getSource();
                int selectedValue = Integer.valueOf((String) combo.getSelectedItem());
                drawBoard.setStrokeSize(selectedValue);
            } catch (NumberFormatException ex) {

            }
        } else if (btnPressed == btnUndo) {
            drawBoard.undo();
        } else if (btnPressed == btnRedo) {
            drawBoard.redo();
        } else if (btnPressed == btnClear) {
            drawBoard.clear();
        }
    }

    public JToolBar getToolBar() {
        return this.toolBar;
    }
}
