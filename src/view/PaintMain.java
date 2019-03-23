/*
* TCSS 305 – Autumn 2018
* Assignment 5 – PowerPaint
*/
package view;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Provides the main method for the PowerPaint program.
 * Also sets the look and feel for the program.
 * 
 * @author Brittany Guilbert
 * @version 23 November 2018
 *
 */
public final class PaintMain {

    /**
     * Private constructor to inhibit instantiation.
     */
    private PaintMain() {
        throw new IllegalStateException();
    }
    
    
    /**
     * Set the look and feel for the GUI program.
     */
    private static void setLookAndFeel() {
        
        try {          
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); 
        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }        
    }


    /**
     * The start point for the program.
     * 
     * @param theArgs command line arguments
     */
    public static void main(final String[] theArgs) {
        setLookAndFeel();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaintGUI().start();
            }
        });
    }
}
