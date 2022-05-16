package view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import controller.Listener;

public class SavePanel extends AbstractPanel {


  private JPanel northPanel;
  private JPanel midPanel;
  private JPanel southPanel;
  private JPanel dimenPanel;
  private JPanel dimenPanel2;
  private JPanel sPanel;

  private JTextField name;
  private JTextArea title;
  private JLabel saveLabel;
  private JLabel directory;

  private JButton findDirectory;

  private JButton save;
  private JButton cancel;

  private JTextField directName;

  private JComboBox options;

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public SavePanel() {

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

    setLayout(new BorderLayout());

    northPanel = new JPanel();
    midPanel = new JPanel();
    southPanel = new JPanel();

    midPanel.setLayout(new GridLayout(3, 1));

    dimenPanel = new JPanel();
    dimenPanel2 = new JPanel();
    sPanel = new JPanel();

    directory = new JLabel();
    directory.setText(STRINGS.getString("CHOOSE_DIRECTORY"));
    saveLabel = new JLabel(STRINGS.getString("FILE_NAME"));

    name = new JTextField();
    name.setPreferredSize(new Dimension(200, 40));
    directName = new JTextField();
    directName.setPreferredSize(new Dimension(300, 40));
    directName.setEditable(false);
    title = new JTextArea();
    title.setText(STRINGS.getString("SAVE_HISTORY"));
    title.setFont(new java.awt.Font("Italic", Font.ITALIC, 20));
    title.setEditable(false);

    save = new JButton(STRINGS.getString("SAVE"));
    cancel = new JButton(STRINGS.getString("CANCEL"));
    findDirectory = new JButton(STRINGS.getString("SELECT_DIRECTORY"));

    String[] opt = {".txt", ".jpg", ".png", ".pdf"};

    options = new JComboBox(opt);

  }

  @Override
  protected void setListeners() {
    // TODO Auto-generated method stub
    Listener listener = Listener.getInstance();

    save.addActionListener(listener);
    cancel.addActionListener(listener);
    findDirectory.addActionListener(listener);

  }

  @Override
  protected void setPanel() {
    // TODO Auto-generated method stub

  }

  public void setDirectory(String dir) {
    directName.setText(dir);
    refresh();
  }

  public String saveName() {

    String fullString = "";
    String fileName = name.getText();

    if (directName.getText() != null && directName.getText().length() > 0) {
      fullString += directName.getText();
    }
    if (fileName == null || fileName.length() == 0) {
      fullString += "\\Calculator";
    } else {
      fullString += "\\" + fileName;
    }

    fullString += options.getSelectedItem().toString();

    return fullString;
  }

  public void refresh() {
    northPanel.add(title);
    southPanel.add(save);
    southPanel.add(cancel);

    dimenPanel.add(directory);
    dimenPanel.add(findDirectory);
    dimenPanel2.add(directName);
    dimenPanel2.add(options);
    sPanel.add(saveLabel);
    sPanel.add(name);

    midPanel.add(dimenPanel);
    midPanel.add(dimenPanel2);
    midPanel.add(sPanel);

    add(northPanel, BorderLayout.NORTH);
    add(midPanel);
    add(southPanel, BorderLayout.SOUTH);
  }

}
