/*
* TCSS 305 – Autumn 2018
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Color;
import java.awt.Shape;

/**
 * This class stores the attributes of shapes to be drawn. 
 * 
 * @author Brittany Guilbert
 * @version 23 November 2018
 */
public class StrokeAttributes {
    
    /** The thickness of the shape to be drawn on the drawing panel. */
    private int myStrokeThickness;
    
    /** The color of the shape to be drawn on the drawing panel. */
    private Color myStrokeColor;
    
    /** The the shape to be drawn on the drawing panel. */
    private Shape myStrokeShape;
    
    /**
     * The constructor for StrokeAttributes, initializes fields.
     * 
     * @param theStrokeShape        the shape to be drawn on the drawing panel
     * @param theStrokeThickness    the thickness for drawing on the panel
     * @param theStrokeColor        the color for drawing on the panel
     */
    public StrokeAttributes(final Shape theStrokeShape, final int theStrokeThickness, 
                            final Color theStrokeColor) {
        myStrokeShape = theStrokeShape;
        myStrokeThickness = theStrokeThickness;
        myStrokeColor = theStrokeColor;
    }

    /**
     * Returns the shape to be drawn on the drawing panel.
     * 
     * @return the shape to be drawn on the drawing panel
     */
    public Shape getMyStrokeShape() {
        return myStrokeShape;
    }

    /**
     * Sets the parameter to myStrokeShape.
     * 
     * @param theStrokeShape the shape to be be drawn to the drawing panel
     */
    public void setMyStrokeShape(final Shape theStrokeShape) {
        myStrokeShape = theStrokeShape;
    }

    /**
     * Returns the color to be drawn on the drawing panel.
     * 
     * @return the color to be drawn on the drawing panel
     */
    public Color getMyStrokeColor() {
        return myStrokeColor;
    }

    /**
     * Sets the parameter to myStrokeColor.
     * 
     * @param theStrokeColor the color to be used when drawing the associated shape
     */
    public void setMyStrokeColor(final Color theStrokeColor) {
        myStrokeColor = theStrokeColor;
    }

    /**
     * The thickness to be drawn onto the drawing panel.
     * 
     * @return the thickness needed to draw on the drawing panel
     */
    public int getMyStrokeThickness() {
        return myStrokeThickness;
    }

    /**
     * Sets the parameter to myStrokeThickness. 
     * 
     * @param theStrokeThickness the thickness to be used when drawing to the drawing panel
     */
    public void setMyStrokeThickness(final int theStrokeThickness) {
        myStrokeThickness = theStrokeThickness;
    }
}
