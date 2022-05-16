package view.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.Listener;

/**
 * Main frame that stores the calculator panel.
 * 
 * @author Jacob Peterson (peter2js)
 *
 */
public class MainFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static MainFrame frame;

    private CalculatorPanel calculatorPanel;

    private JMenu menu;
    private JMenu settings;
    private JMenu language;
    private JMenu help;
    private JMenuBar menuBar;
    private JMenuItem save;
    private JMenuItem print;
    private JMenuItem load;
    private JMenuItem english;
    private JMenuItem spanish;
    private JMenuItem french;
    private JMenuItem help2;
    private JMenuItem about;

    /**
     * generates the Main frame then makes it visible. (peter2js)
     */
    public MainFrame() {

        final ResourceBundle STRINGS = ResourceBundle.getBundle(
                "view.gui.Language");

        calculatorPanel = new CalculatorPanel();

        menuBar = new JMenuBar();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        menu = new JMenu(STRINGS.getString("MENU"));
        settings = new JMenu(STRINGS.getString("SETTINGS"));
        language = new JMenu(STRINGS.getString("LANGUAGE"));
        help = new JMenu(STRINGS.getString("HELP"));
        menu.setMnemonic(KeyEvent.VK_F);

        save = new JMenuItem(STRINGS.getString("SAVE"));
        print = new JMenuItem(STRINGS.getString("PRINT"));
        load = new JMenuItem("Load");
        english = new JMenuItem(STRINGS.getString("ENGLISH"));
        spanish = new JMenuItem(STRINGS.getString("SPANISH"));
        french = new JMenuItem(STRINGS.getString("FRENCH"));
        help2 = new JMenuItem(STRINGS.getString("MORE_HELP"));
        about = new JMenuItem(STRINGS.getString("ABOUT"));

        menu.add(save);
        menu.add(print);
        menu.add(load);
        language.add(english);
        language.add(spanish);
        language.add(french);
        settings.add(language);
        help.add(help2);
        help.add(about);

        menuBar.add(menu);
        menuBar.add(settings);
        menuBar.add(help);

        setJMenuBar(menuBar);

        getContentPane().add(calculatorPanel);
        calculatorPanel.setVisible(true);

        setListeners();

        setSize(700, 600);
        setTitle(STRINGS.getString("CALCULATOR"));
        setVisible(true); // display this
        centerForm();
    }

    /**
     * centerForm makes the frame center of the screen. (peter2js)
     */
    private void centerForm() {

        Dimension dimScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dimFrameSize = getSize();

        if (dimFrameSize.height > dimScreenSize.height) {
            dimFrameSize.height = dimScreenSize.height;
        }
        if (dimFrameSize.width > dimScreenSize.width) {
            dimFrameSize.width = dimScreenSize.width;
        }

        setLocation((dimScreenSize.width - dimFrameSize.width) / 2,
                (dimScreenSize.height - dimFrameSize.height) / 2);

    } // method centerForm

    private void setListeners() {

        Listener listener = Listener.getInstance();

        this.addWindowListener(listener);
        this.addKeyListener(listener);

        for (int i = 0; i < menu.getItemCount(); i++) {
            menu.getItem(i).addMouseListener(listener);
        }
        for (int i = 0; i < language.getItemCount(); i++) {
            language.getItem(i).addMouseListener(listener);
        }
        for (int i = 0; i < help.getItemCount(); i++) {
            help.getItem(i).addMouseListener(listener);
        }

    }

    public void changeFrameSize(int size) {

        setSize(this.getSize().width + size, this.getSize().height);
    }

    /**
     * generates a mainframe and only allows one to be made. (peter2js)
     * 
     * @return the frame
     */
    public static MainFrame getInstance() {

        if (frame == null) {
            frame = new MainFrame();
        }

        return frame;
    }

    public CalculatorPanel getPanel() {

        return calculatorPanel;
    }

    public void changeLanguage(String lang) {

        switch (lang) {
            case "English":
                Locale.setDefault(new Locale("en", "US"));
                frame.setVisible(false);
                frame = new MainFrame();
                break;
            case "French":
                Locale.setDefault(new Locale("fr", "FRA"));
                frame.setVisible(false);
                frame = new MainFrame();
                break;
            case "Spanish":
                Locale.setDefault(new Locale("es", "MEX"));
                frame.setVisible(false);
                frame = new MainFrame();
                break;
        }
    }

}
