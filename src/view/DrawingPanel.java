/*
* TCSS 305 – Autumn 2018
* Assignment 5 – PowerPaint
*/
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import tools.PaintTool;
import tools.StrokeAttributes;

/**
 * This class sets up the drawing panel to draw shapes on. 
 * 
 * @author Brittany Guilbert
 * @version 23 November 2018
 *
 */
public class DrawingPanel extends JPanel {

    /** Property name for weather the drawing panel is clear". */
    public static final String CLEAR = "CLEAR_STATE";
    
    /** Property name for when a new tool is selected". */
    public static final String TOOL = "CURRENT_TOOL";
    
    /** Property name for when a new tool is selected". */
    public static final String COLOR = "COLOR_SELECTED";
    
    /** A generated serial number for serialization. */
    private static final long serialVersionUID = 1867923922522217828L;

    /** The preferred size of this panel. */
    private static final Dimension SIZE = new Dimension(600, 300);
    
    /** True when the panel has just been cleared. */
    private boolean myJustCleared;
    
    /** The PaintTool currently in use. */
    private PaintTool myCurrentTool;
    
    /** A collection of previously drawn shapes. */
    private final List<Shape> myPreviousShapes;
    
    /** A collection of previously drawn strokes. */
    private final List<StrokeAttributes> myPreviousStrokes;

    /** The current color for drawing. */
    private Color myCurrentColor;
    
    /** The new color for drawing. */
    private Color myNewColor;
    
    /** The current thickness for drawing. */
    private int myCurrentThickness;
    
    /** The new thickness for drawing. */
    private int myNewThickness;
    
    /** The current shape for drawing. */
    private Shape myCurrentShape;
    
    /** True when there is nothing drawn on the drawing panel. */
    private boolean myDrawingPanelIsClear;
    
    /** The previous value of myDrawingPanelIsClear. */
    private boolean myDrawingPanelWasClear;
    

    /**
     * Initializes the panel.
     */
    public DrawingPanel() {
        super();
        myPreviousShapes = new ArrayList<>();
        myPreviousStrokes  = new ArrayList<>();
        initalizePanel();
        myCurrentThickness = 2;
        myJustCleared = false;
        myDrawingPanelIsClear = true;
        myDrawingPanelWasClear = myDrawingPanelIsClear;
    }
    
    /**
     * Sets up the panel.
     */
    private void initalizePanel() {
        setPreferredSize(SIZE);
        setBackground(Color.WHITE);
        
        // setup the mouse listener
        final MouseInputAdapter mouseHandler = new MyMouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        
    }
    
    /**
     * Sets the current paint tool. 
     * @param theTool the PaintTool to use.
     */
    public void setCurrentTool(final PaintTool theTool) {
        firePropertyChange(TOOL, myCurrentTool, theTool);
        myCurrentTool = theTool;
    }
    
    /**
     * Sets the current paint color. 
     * @param theColor the color to use.
     */
    public void setCurrentColor(final Color theColor) {
        myCurrentColor = theColor;
    }
    
    /**
     * Sets the current thickness. 
     * @param theThickness the line thickness to use.
     */
    public void setCurrentThickness(final int theThickness) {
        myCurrentThickness = theThickness;
    }
    
    /**
     * Sets the new paint color. 
     * @param theColor the color to use.
     */
    public void setNewColor(final Color theColor) {
        myNewColor = theColor;
        firePropertyChange(COLOR, myCurrentColor, myNewColor);
    }
    
    /**
     * Sets the new thickness. 
     * @param theThickness the line thickness to use.
     */
    public void setNewThickness(final int theThickness) {
        myNewThickness = theThickness;
    }
    
    /**
     * Clears the drawing panel.
     * 
     */
    public void clear() {
        myPreviousShapes.clear();
        myPreviousStrokes.clear();
        myJustCleared = true;
        myDrawingPanelIsClear = true;
        firePropertyChange(CLEAR, myDrawingPanelWasClear, myDrawingPanelIsClear);
        myDrawingPanelWasClear = myDrawingPanelIsClear;
    }

    /**
     * Paint all the previous shapes and the current one onto the drawing panel.
     * 
     * @param theGraphics the graphics objects used to draw
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2D = (Graphics2D) theGraphics;
        final BasicStroke basicStroke = new BasicStroke(myCurrentThickness);
        
        for (final StrokeAttributes s : myPreviousStrokes) {
            g2D.setColor(s.getMyStrokeColor());           
            g2D.setStroke(new BasicStroke(s.getMyStrokeThickness()));
            g2D.draw(s.getMyStrokeShape());
        }
        
        //draw current shape as its being formed
        if (!myJustCleared && myCurrentShape != null) {
            if (myCurrentThickness > 0) {
                g2D.setColor(myCurrentColor);
                g2D.setStroke(basicStroke); 
                g2D.draw(myCurrentShape);   
            }
        }        
    }
 

    /**
     * Listens for mouse events to draw on our panel.
     */
    private class MyMouseHandler extends MouseInputAdapter {
        
        /**
         * When the mouse enters the drawing panel change the cursor to a cross shape.
         * 
         * @param theEvent the mouse event object 
         */
        @Override
        public void mouseEntered(final MouseEvent theEvent) {
            DrawingPanel.this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }
        
        /**
         * When the mouse is pressed in the drawing panel set up to draw a new shape.
         * 
         *@param theEvent the mouse event object 
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myCurrentTool.setStartPoint(theEvent.getPoint());
            myJustCleared = false;
            myCurrentShape = myCurrentTool.getShape();
            myCurrentColor = myNewColor;
            myCurrentThickness = myNewThickness;
            repaint();
        }

        /**
         * When the mouse is dragged in the drawing panel draw the selected shape.
         * 
         * @param theEvent the mouse event object
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myCurrentTool.setEndPoint(theEvent.getPoint());
            myCurrentShape = myCurrentTool.getShape();
            repaint(); 
        }

        /**
         * When the mouse is released in the drawing panel add the shape info to the 
         * myPreviousStrokes list to be drawn later. Also notify the tools and the PaintGui.
         * Also update myDrawingPanelIsClear. 
         * 
         * @param theEvent the mouse event object
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            myCurrentShape = myCurrentTool.getShape();
            if (myCurrentThickness > 0) {
                myPreviousStrokes.add(new StrokeAttributes(myCurrentShape, 
                                                          myCurrentThickness, myCurrentColor));
                myCurrentTool.strokeComplete();
                myDrawingPanelIsClear = false;
                firePropertyChange(CLEAR, myDrawingPanelWasClear, 
                                   myDrawingPanelIsClear);
                myDrawingPanelWasClear = myDrawingPanelIsClear;
            }
        }
    }
    
}
