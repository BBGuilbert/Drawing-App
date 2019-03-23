/*
* TCSS 305 – Autumn 2018
* Assignment 5 – PowerPaint
*/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import tools.EllipseTool;
import tools.LineTool;
import tools.PaintTool;
import tools.PencilTool;
import tools.RectangleTool;


/**
 * This class sets up the GUI for the PowerPaint program.
 * 
 * @author Brittany Guilbert
 * @version 23 November 2018
 *
 */
public class PaintGUI extends JFrame implements PropertyChangeListener {

    /** A generated serial number for serialization. */
    private static final long serialVersionUID = -6357541731263963100L;

    /** The color of UW Purple. */
    private static final Color UW_PURPLE = new Color(51, 0, 111);
    
    /** The maximum thickness of a shape drawn on the drawing panel. */
    private static final int MAX_LINE_THICKNESS = 15;
    
    /** The initial thickness of a shape drawn on the drawing panel. */
    private static final int INITIAL_LINE_THICKNESS = 2;
    
    /** The title of this program. */
    private static final String NAME_OF_PROGRAM = "TCSS 305 – Paint Program";
    
    /** A panel for drawing shapes. */
    private final DrawingPanel myPanel;
    
    /** A map of the PaintTools to be used and their names. */
    private final Map<String, PaintTool> myToolsAndNamesMap;
    
    /** A map of the tool menu items to be used and their names. */
    private final Map<String, JMenuItem> myMenuItemsAndNamesMap;
    
    /** A map of the tool toggle buttons to be used and their names. */
    private final Map<String, JToggleButton> myToggleButtonsAndNamesMap;

    /** The current color for drawing on the panel. */
    private Color myCurrentColor;
    
    /** The button on the menu that clears the drawing panel. */
    private final JMenuItem myClear;
    
    /** The button on the menu that brings up the description of the program. */
    private final JMenuItem myAboutMenuItem;
    
    /** The count of the tool that has been added to the menu. */
    private int myToolCount;
    
    /** The action that sets the current color.*/
    private final ColorListener myColorAction;
    
    /** The toggle button to bring up the color color chooser dialog. */
    private final JToggleButton myColorToggle;
    
    /** The menu item that brings up the color chooser dialog.*/
    private final JMenuItem myColorMenuItem;
    
    /**
     * Initialize the GUI.
     */
    public PaintGUI() {
        myPanel = new DrawingPanel();
        myToolsAndNamesMap = new LinkedHashMap<String, PaintTool>();
        myMenuItemsAndNamesMap = new LinkedHashMap<String, JMenuItem>();
        myToggleButtonsAndNamesMap = new LinkedHashMap<String, JToggleButton>();
        myClear = new JMenuItem("Clear");
        myAboutMenuItem = new JMenuItem("About...");
        myToolCount = 0;
        myColorAction = new ColorListener();
        myColorToggle = new JToggleButton(myColorAction);
        myColorMenuItem = new JMenuItem(myColorAction);
       
    }
    
    /** Sets up the elements of the GUI. */ 
    public void start() {
             
        myPanel.setNewColor(UW_PURPLE);
        myPanel.setNewThickness(INITIAL_LINE_THICKNESS);
        
        myClear.setEnabled(false);
        
        myCurrentColor = UW_PURPLE;
        
        add(myPanel, BorderLayout.CENTER);
        
        fillMapWithToolAndName(new LineTool(), new PencilTool(), new RectangleTool(), 
                               new EllipseTool());
        myPanel.setCurrentTool(myToolsAndNamesMap.get("Line"));

        createToolBarAndMenu();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setIconImage(prepareIcon());
        setBoundsHelper();
        setTitle(NAME_OF_PROGRAM);
        
        myPanel.addPropertyChangeListener(this);
        
        setVisible(true);         
    }
    
    
    /**
     * Gets info about the screen and sets the window to open in the center of the screen at 
     * one third the size of the screen.
     */
    private void setBoundsHelper() {
        final Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = kit.getScreenSize();
        final int screenWidth = screenSize.width;
        final int screenHeight = screenSize.height;
        final int preferedWindowWidth = screenWidth / 3;
        final int preferedWindowHeight = screenHeight / 3;
        setBounds(screenWidth / 2 - preferedWindowWidth / 2, 
                  screenHeight / 2 - preferedWindowHeight / 2,
                  preferedWindowWidth, preferedWindowHeight);
    }
  
    /**
     * Prepares the icon for the program.
     * 
     * @return an image for the icon for this program
     */
    private Image prepareIcon() {
        final URL imgURL = this.getClass().getResource("/paintbrush9.png");
        final Image image = new ImageIcon(imgURL).getImage();
        return image;
    }
    
    /**
     * This method accepts all the Tools to be used in this program and adds them to a Map.
     * 
     * Any modification to the Tools used in this program should be done 
     * by changing the parameters passed to this method.
     * 
     * This method adds the tools along with their names to myToolsAndNamesMap.
     * 
     * @param thePaintToolsToAdd   All of the Tools to be used in this program
     */
    private void fillMapWithToolAndName(final PaintTool...thePaintToolsToAdd) {
        
        for (PaintTool paintToolToAdd: thePaintToolsToAdd) {
            myToolsAndNamesMap.put(paintToolToAdd.getDescription(), paintToolToAdd);
        }
    }
  
    /**
     * Creates a ToolBar and Menu with appropriate buttons.
     * 
     */
    private void createToolBarAndMenu() {
        myColorAction.putValue(Action.NAME, "Color...");
        
        final JToolBar toolbar = new JToolBar();
        final ButtonGroup toggleButtonGroup = new ButtonGroup();
        toggleButtonGroup.add(myColorToggle);
        toolbar.add(myColorToggle);
        
        final JMenuBar menubar = new JMenuBar();
        
        //Options submenu
        final JMenu options = new JMenu("Options");
        final JMenu thickness = new JMenu("Thickness");
        menubar.add(options);
        options.add(thickness);
        thickness.add(createSlider());
        options.addSeparator();
        options.add(myColorMenuItem);
        options.addSeparator();
        options.add(myClear);
        myClear.addActionListener(new ClearListener());
        
        
        //Tools submenu
        final JMenu toolsSubMenu = new JMenu("Tools");
        menubar.add(toolsSubMenu);
         
        final ButtonGroup radioButtonGroup = new ButtonGroup();
        
        myToolsAndNamesMap.forEach((k, v) -> {
            
            final ToolListener toolAction = new ToolListener();
            toolAction.putValue(Action.NAME, k);
            toolAction.putValue(Action.SMALL_ICON, v.getIcon());
            
            final JRadioButtonMenuItem radioButtonForMenu;
            radioButtonForMenu = new JRadioButtonMenuItem(toolAction);
            radioButtonGroup.add(radioButtonForMenu);
            toolsSubMenu.add(radioButtonForMenu);
            myMenuItemsAndNamesMap.put(k, radioButtonForMenu);
            
            final JToggleButton toggleForToolbar;
            toggleForToolbar = new JToggleButton(toolAction);
            toggleButtonGroup.add(toggleForToolbar);
            toolbar.add(toggleForToolbar);
            myToggleButtonsAndNamesMap.put(k, toggleForToolbar);
            
            if (myToolCount == 0) {
                radioButtonForMenu.setSelected(true);
                toggleForToolbar.setSelected(true);
            }
            myToolCount++;
        });
        myToolCount = 0;
        
        //Help submenu
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.add(myAboutMenuItem);
        myAboutMenuItem.addActionListener(new AboutListener());
        menubar.add(helpMenu);
        
        add(toolbar, BorderLayout.SOUTH);
        setJMenuBar(menubar);
        menubar.setVisible(true);       
    }
        
    /**
     * A helper method to make a Slider.
     * 
     * @return the Slider
     */
    private JSlider createSlider() {
        final int majorTickMarks = 5;
        final int minorTickMarks = 1;
        final JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, MAX_LINE_THICKNESS,
                                           INITIAL_LINE_THICKNESS);
        slider.setMajorTickSpacing(majorTickMarks);
        slider.setMinorTickSpacing(minorTickMarks);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
        
            /** Sets the current thickness when the number is chosen on the slider. 
             * 
             * @param theEvent the ActionEvent object containing the Action details.
             */
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = slider.getValue();
                myPanel.setNewThickness(value);
            }
            });
        
        return slider;
    }
    
    /**
     * Disables clear button when Drawing Panel is empty, enables it otherwise.
     * 
     * @param theEvent an event object containing the changed value
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (DrawingPanel.CLEAR.equals(theEvent.getPropertyName())) {
            myClear.setEnabled(!((boolean) theEvent.getNewValue()));
        } else if (DrawingPanel.TOOL.equals(theEvent.getPropertyName())) {
            myMenuItemsAndNamesMap.get(((PaintTool) theEvent.
                            getNewValue()).getDescription()).setSelected(true);
            myToggleButtonsAndNamesMap.get(((PaintTool) theEvent.
                            getNewValue()).getDescription()).setSelected(true);
        } else if (DrawingPanel.COLOR.equals(theEvent.getPropertyName())) {
            myCurrentColor = (Color) theEvent.getNewValue(); //coordinates colors 
        }
        
    }
    
    /**
     * This class sets a new current tool when a tool button is pressed.
     * 
     * @author Brittany Guilbert
     * @version 23 November 2018
     *
     */
    private class ToolListener extends AbstractAction {
 
        private static final long serialVersionUID = 3596747554065855489L;

        /** Constructor to set up default values for ToolListener. */
        ToolListener() {
            //putValue(SELECTED_KEY, true);
        }
        
        /** Sets the current tool to the type clicked. 
         * 
         * @param theEvent the ActionEvent object containing the Action details.
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myPanel.setCurrentTool(myToolsAndNamesMap.get(theEvent.getActionCommand()));
        }
    }
    
    /**
     * This class pops up a color dialog and sets a new current color 
     * when the button it is attached to is clicked.
     * 
     * @author Brittany Guilbert
     * @version 23 November 2018
     *
     */
    public class ColorListener extends AbstractAction {
        
        private static final long serialVersionUID = 5276040631042936817L;
        
        /** Constructor to set up default values for ColorListener. */
        ColorListener() {
            putValue(SELECTED_KEY, true);
        }
        
        /** Opens a dialog box and lets the user choose a color
         * then sets the color to the current color. 
         * 
         * @param theEvent the ActionEvent object containing the Action details.
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            //myCurrentColor = JColorChooser.showDialog(null, "Choose A Color",myCurrentColor);
            //myPanel.setNewColor(myCurrentColor);
            myPanel.setNewColor(JColorChooser.
                                showDialog(null, "Choose A Color", myCurrentColor));
        }
    }
    
    /**
     * This class pops up a dialog with a description of the program 
     * when the button it is attached to is clicked.
     * 
     * @author Brittany Guilbert
     * @version 23 November 2018
     *
     */
    public class AboutListener implements ActionListener {
        
        /** Opens a dialog box and displays a description of the program
         * when the attached button is pressed. 
         * 
         * @param theEvent the ActionEvent object containing the Action details.
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            JOptionPane.showMessageDialog(myAboutMenuItem, 
                                          "Autumn 2018 \nBrittany Guilbert", 
                                          NAME_OF_PROGRAM, 
                                JOptionPane.PLAIN_MESSAGE, new ImageIcon(prepareIcon()));
        }    
    }
    
    /**
     * This class pops up a dialog with a description of the program 
     * when the about button is clicked.
     * 
     * @author Brittany Guilbert
     * @version 23 November 2018
     *
     */
    public class ClearListener implements ActionListener {
        
        /** Clears the drawing panel and clears all of the PaintTools
         * when the attached button is pressed. 
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myPanel.clear();
            repaint();
        }
    }

  
    
}
