/*
* TCSS 305 – Autumn 2018
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * This class is a tool to draw lines. 
 * 
 * @author Brittany Guilbert
 * @version 23 November 2018
 *
 */
public class LineTool extends AbstractPaintTool {

    @Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }
}
