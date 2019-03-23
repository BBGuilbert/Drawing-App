/*
* TCSS 305 – Autumn 2018
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * This class is a tool to draw ellipses. 
 * 
 * @author Brittany Guilbert
 * @version 23 November 2018
 *
 */
public class EllipseTool extends AbstractPaintTool {
    
    /**
     * The shape that needs to be drawn.
     * 
     * @return the precise shape of the ellipse that needs to be drawn
     */
    @Override
    public Shape getShape() {
        final Ellipse2D.Double circle = new Ellipse2D.Double();
        final Point start = getStartPoint();
        final Point end = getEndPoint();
        //circle.setFrameFromCenter(start, end);
        circle.setFrameFromDiagonal(start, end);
        return circle;
    }   
}
