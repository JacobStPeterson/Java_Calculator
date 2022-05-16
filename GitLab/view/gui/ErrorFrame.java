package view.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorFrame extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  static final ResourceBundle STRINGS = ResourceBundle.getBundle("view.gui.Language");

  public ErrorFrame(String error) {

    JPanel messageFrame = new JPanel();
    JLabel message = new JLabel(error);
    message.setFont(new java.awt.Font("Italic", Font.ITALIC, 25));

    messageFrame.add(message);
    add(messageFrame);

    centerForm();

    setSize(400, 200);
    setTitle(STRINGS.getString("CALC_ERROR"));
    setVisible(true); // display this
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

}
