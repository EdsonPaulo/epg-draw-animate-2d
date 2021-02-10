package EPGDrawAnimate;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public final class Constants {

    //Window dimensions
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 820;

    //Elements default dimensions
    //Timeline
    public static final int TIMELINE_WIDTH = WINDOW_WIDTH;
    public static final int TIMELINE_HEIGHT = 100;

    //DrawingBoard
    public static final int DRAWING_BOARD_WIDTH = WINDOW_WIDTH;
    public static final int DRAWING_BOARD_HEIGHT = WINDOW_HEIGHT - TIMELINE_HEIGHT;

    //Tools Types
    public static final String TOOL_TEXT = "TOOL_TEXT";
    public static final String TOOL_FILL = "TOOL_FILL";
    public static final String TOOL_PENCIL = "TOOL_PENCIL";
    public static final String TOOL_ERASER = "TOOL_ERASER";
    public static final String TOOL_LINE = "TOOL_LINE";
    
    public static final String TOOL_CIRCLE = "TOOL_CIRCLE";
    public static final String TOOL_FILL_CIRCLE = "TOOL_FILL_CIRCLE";

    public static final String TOOL_RECTANGLE = "TOOL_RECTANGLE";
    public static final String TOOL_FILL_RECTANGLE = "TOOL_FILL_RECTANGLE";

    public static final String TOOL_ROUND_RECTANGLE = "TOOL_ROUND_RECTANGLE";
    public static final String TOOL_FILL_ROUND_RECTANGLE = "TOOL_FILL_ROUND_RECTANGLE";

    /*
     * Draw a String centered in the middle of a Rectangle.
     */
    public static void drawCenteredString(
            Graphics2D g, String text, Font font,
            int containerX, int containerY,
            int containerWidth, int containerHeight
    ) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = containerX + (containerWidth - metrics.stringWidth(text)) / 2;
        int y = containerY + ((containerHeight - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

}
