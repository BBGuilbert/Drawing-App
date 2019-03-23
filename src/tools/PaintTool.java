/*
* TCSS 305 – Autumn 2018
* Assignment 5 – PowerPaint
*/

package tools;

import java.awt.Point;
import java.awt.Shape;
import javax.swing.ImageIcon;

/**
 * This interface has all the method signatures a PaintTool needs. 
 * 
 * @author Brittany Guilbert
 * @version 23 November 2018
 *
 */
public interface PaintTool {

    /**
     * Returns the Shape that this tools draws.
     * 
     * @return the Shape to draw
     */
    Shape getShape();

    /**
     * Sets the initial point for the Shape drawn by this tool.
     * 
     * @param thePoint the start point to set
     */
    void setStartPoint(Point thePoint);
    
    /**
     * Sets the end point for the Shape drawn by this tool.
     * 
     * @param thePoint the end point to set
     */
    void setEndPoint(Point thePoint);
    
    /**
     * Returns a one word description of the class.
     * 
     * @return a String representing the tool
     */
    String getDescription();
    
    /**
     * Returns an icon description of the class.
     * 
     * @return an ImageIcon representing the tool
     */
    ImageIcon getIcon();
    
    /**
     * Informs the tool that the mouse has been released and the stroke is complete.
     */
    void strokeComplete();

}
