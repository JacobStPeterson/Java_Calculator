package view.gui;

import java.awt.Color;
import java.awt.Container;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LookAndFeel extends JFrame {


  private static final long serialVersionUID = 1L;

  public static String logo = "/iconRimplex.png";
  public static Color colors = Color.BLUE;

  public LookAndFeel() {
    logo = "iconRimplex.png";
  }

  public static void setLogo(String logoPath) {
    logo = logoPath;
  }

  public String getLogo() {
    return logo;
  }

  public static void setColor(Color color) {
    colors = color;
  }

  public Color getColor() {
    return colors;
  }


  public static void main(String[] args) throws FileNotFoundException, IOException {

    JFrame f2;
    Container contentPane, contentPane2;
    Color color;
    String response;



    response = JOptionPane.showInputDialog("Enter image name:");

    setLogo("/" + response);

    f2 = new JFrame();
    f2.setSize(400, 200);

    contentPane2 = f2.getContentPane();

    f2.setVisible(true);


    color = JColorChooser.showDialog(f2, "Choose a color", Color.blue);

    if (color != null) {
      setColor(color);
    }
  }

}
