package view.gui;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;

public class MenuFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    static final ResourceBundle STRINGS = ResourceBundle.getBundle(
            "view.gui.Language");

    private static MenuFrame menuFrame;

    private SavePanel savePanel;
    private PrintPanel printPanel;
    private LoadPanel loadPanel;

    private AboutPanel aboutPanel;

    private int panelType;

    public MenuFrame() {

        savePanel = new SavePanel();
        printPanel = new PrintPanel();
        loadPanel = new LoadPanel();
        aboutPanel = new AboutPanel();

        setSize(400, 350);
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

    public void setMenuType(int panel) throws URISyntaxException, IOException {

        panelType = panel;

        switch (panel) {

            case 0:
                setTitle(STRINGS.getString("SAVE"));
                this.getContentPane().add(savePanel);
                break;
            case 1:
                setTitle(STRINGS.getString("PRINT"));
                this.getContentPane().add(printPanel);
                setSize(400, 275);
                break;
            case 2:
                Desktop desktop = Desktop.getDesktop();
                URL file;
                if (Locale.getDefault().equals(new Locale("en", "US"))) {
                    file = getClass().getResource("/htmlFiles/Project.html");
                } else if (Locale.getDefault().equals(
                        new Locale("fr", "FRA"))) {
                    file = getClass().getResource("/htmlFiles/ProjectFRA.html");
                } else if (Locale.getDefault().equals(
                        new Locale("es", "MEX"))) {
                    file = getClass().getResource("/htmlFiles/ProjectSPA.html");
                } else {
                    file = getClass().getResource("/htmlFiles/Project.html");
                }
                URI uri = file.toURI();
                desktop.browse(uri);

            case 3:
                setTitle(STRINGS.getString("ABOUT"));
                this.getContentPane().add(aboutPanel);
                break;
            case 4:
                setTitle(STRINGS.getString("LOAD"));
                this.getContentPane().add(loadPanel);
                break;
            default:
                new ErrorFrame("Not a Menu Option,");
                break;
        }
    }

    public int getPanel() {
        return panelType;
    }

    public SavePanel getSavePanel() {
        return savePanel;
    }

    public PrintPanel getPrintPanel() {
        return printPanel;
    }

    public AboutPanel getAboutPanel() {
        return aboutPanel;
    }

    public LoadPanel getLoadPanel() {
        return loadPanel;
    }
}
