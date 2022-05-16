package view.gui;

import javax.swing.JPanel;

/**
 * Template for panel classes.
 * 
 * @author Jacob Peterson (peter2js)
 *
 */
public abstract class AbstractPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor. (peter2js)
     */
    public AbstractPanel() {

        createComponents();
        setPanel();
        addComponents();
        setListeners();

    } // constructor

    /*************************** abstract methods ************************/

    /**
     * addComponents - add components to panels. (peter2js)
     */
    protected abstract void addComponents();

    /**
     * createComponents - instantiate necessary components (including
     * containers). (peter2js)
     */
    protected abstract void createComponents();

    /**
     * setListeners - set listeners for components on the panel. (peter2js)
     */
    protected abstract void setListeners();

    /**
     * setPanel - set parameters for panels. (peter2js)
     */
    protected abstract void setPanel();

}
