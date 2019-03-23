/*
* TCSS 305 – Autumn 2018
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * This class is a tool to draw rectangles. 
 * 
 * @author Brittany Guilbert
 * @version 23 November 2018
 *
 */
public class RectangleTool extends AbstractPaintTool {

    /**
     * The shape that needs to be drawn.
     * 
     * @return the precise shape of the ellipse that needs to be drawn
     */
    @Override
    public Shape getShape() {
        final Rectangle2D.Double rectangle = new Rectangle2D.Double();
        final Point start = getStartPoint();
        final Point end = getEndPoint();
        //rectangle.setFrameFromCenter(start, end);
        rectangle.setFrameFromDiagonal(start, end);
        return rectangle;
    }
}
