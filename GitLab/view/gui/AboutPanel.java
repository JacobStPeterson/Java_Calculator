package view.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AboutPanel extends AbstractPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JTextArea title;
  private JTextArea about;

  private JPanel northPanel;
  private JPanel midPanel;
  private JPanel southPanel;

  private JButton toHelp;

  // private jTextArea title
  public AboutPanel() {
    super();
  }

  @Override
  protected void addComponents() {
    // TODO Auto-generated method stub
    refresh();
  }

  @Override
  protected void createComponents() {
    // TODO Auto-generated method stub


    final ResourceBundle STRINGS = ResourceBundle.getBundle("view.gui.Language");


    northPanel = new JPanel();
    midPanel = new JPanel();
    southPanel = new JPanel();


    title = new JTextArea();
    title.setText(STRINGS.getString("RIMPLEX_ABOUT"));
    title.setFont(new java.awt.Font("Bold", Font.BOLD, 15));
    title.setPreferredSize(new Dimension(300, 40));
    title.setEditable(false);

    about = new JTextArea();
    about.setText(STRINGS.getString("FULL_ABOUT1") + STRINGS.getString("FULL_ABOUT2")
        + STRINGS.getString("FULL_ABOUT3") + STRINGS.getString("FULL_ABOUT4")
        + STRINGS.getString("FULL_ABOUT5"));
    about.setPreferredSize(new Dimension(300, 200));
    about.setLineWrap(true);
    about.setWrapStyleWord(true);
    about.setEditable(false);


    toHelp = new JButton(STRINGS.getString("MORE_HELP"));

    setLayout(new BorderLayout());



    toHelp = new JButton(STRINGS.getString("MORE_HELP"));
    toHelp.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        Desktop desktop = Desktop.getDesktop();
        URL file;
        if (Locale.getDefault().equals(new Locale("en", "US"))) {
          file = getClass().getResource("/htmlFiles/Project.html");
        } else if (Locale.getDefault().equals(new Locale("fr", "FRA"))) {
          file = getClass().getResource("/htmlFiles/ProjectFRA.html");
        } else if (Locale.getDefault().equals(new Locale("es", "MEX"))) {
          file = getClass().getResource("/htmlFiles/ProjectSPA.html");
        } else {
          file = getClass().getResource("/htmlFiles/Project.html");
        }
        URI uri;
        try {
          uri = file.toURI();

          try {
            desktop.browse(uri);
          } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        } catch (URISyntaxException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();

        }

      }
    });

    setLayout(new BorderLayout());

  }

  @Override
  protected void setListeners() {
    // TODO Auto-generated method stub

  }

  @Override
  protected void setPanel() {
    // TODO Auto-generated method stub

  }

  public void refresh() {

    northPanel.add(title);
    midPanel.add(about);
    southPanel.add(toHelp);

    add(northPanel, BorderLayout.NORTH);
    add(midPanel);
    add(southPanel, BorderLayout.SOUTH);
  }

}
