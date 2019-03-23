/*
* TCSS 305 – Autumn 2018
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Point;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * This abstract class has all the methods a PaintTool needs. 
 * 
 * @author Brittany Guilbert
 * @version 23 November 2018
 *
 */
public abstract class AbstractPaintTool implements PaintTool {

    /** A point outside the drawing area. */
    public static final Point NO_POINT = new Point(-50, -50);

    /**
     * The initial anchor point for the shape drawn by this tool.
     */
    private Point myStartPoint;
    
    /**
     * The end point for the shape drawn by this tool.
     */
    private Point myEndPoint;

    /**
     * Constructs a paint tool.
     */
    public AbstractPaintTool() {
        myStartPoint = NO_POINT;
        myEndPoint = NO_POINT;
    }

    /**
     * Set the start point for this PaintTool.
     * 
     * @param thePoint the point where this tool should start painting
     */
    @Override
    public void setStartPoint(final Point thePoint) {      
        myStartPoint = thePoint;
        myEndPoint = thePoint;
    }

    /**
     * Returns the start point for this PaintTool.
     * 
     * @return the start point for this PaintTool.
     */
    protected Point getStartPoint() {
        return myStartPoint;
    }
    
    /** Set the end point for this PaintTool.
     * 
     * @param thePoint the point where this PaintTool will stop painting.
     */
    @Override
    public void setEndPoint(final Point thePoint) {      
        myEndPoint = thePoint;
    }

    /**
     * Returns the end point for this PaintTool.
     * 
     * @return the end point for this PaintTool.
     */
    protected Point getEndPoint() {
        return myEndPoint;
    }
    
    /**
     * Returns a one word description of the class.
     * 
     * @return a String representing the tool
     */
    public String getDescription() {
        String toolName = getClass().getSimpleName();
        toolName = toolName.replace("Tool", "");
        return toolName;
    }
    
    /**
     * Returns an icon description of the class.
     * 
     * @return an ImageIcon representing the tool
     */
    public  ImageIcon getIcon() {
        final StringBuilder builder = new StringBuilder(128);

        builder.append("/");
        builder.append(getDescription().toLowerCase());
        builder.append(".gif");
        
        ImageIcon icon = null;
        final URL imgURL = this.getClass().getResource(builder.toString());
        icon = new ImageIcon(imgURL);
     
        return icon;
    }
    
    
    /**
     * Informs the tool that the mouse has been released and the stroke is complete.
     * This empty implementation can be overridden
     */
    public void strokeComplete() {
        
    }

}
