/*
* TCSS 305 – Autumn 2018
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * This class is a tool to draw free form. 
 * 
 * @author Brittany Guilbert
 * @version 23 November 2018
 *
 */
public class PencilTool extends AbstractPaintTool {
    
    /** The shape that this class will give to the drawing panel to draw. */
    private Path2D.Double myPath;
    
    /** The start point for the previous shape drawn with the pencil. */
    private Point myPreviousLineStart;
    
    /** True if the panel was just cleared. */
    private boolean myIsClear;
    
    /** True if the current stroke was just completed. */
    private boolean myNewStroke;
    
    /** Constructor for the PencilTool class, initializes fields. */
    public PencilTool() {
        super();
        myPath = new Path2D.Double();
        myIsClear = false;
    }
    
    /**
     * Returns the free form shape to draw on the drawing panel.
     * 
     * @return the shape to draw on the drawing panel
     */
    @Override
    public Shape getShape() {
        final Point start = getStartPoint();
        final Point end = getEndPoint();
        
        //if it is the beginning of this stroke
        if (myPreviousLineStart == null || myIsClear || myNewStroke) {
            myPath.moveTo(start.getX(), start.getY());
            myPreviousLineStart = start;
            myIsClear = false;
            myNewStroke = false;
        }
        
        myPath.lineTo(end.getX(), end.getY());
        
        return myPath;
    }
    
    /** Resets the pencil shape. */
    private void resetStrokeHelper() {
        myPath = new Path2D.Double();
        myNewStroke = true;
    }
    
    /** Resets the pencil shape when the stroke is completed. */
    @Override
    public void strokeComplete() {
        resetStrokeHelper();
    }   
}
