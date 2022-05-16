package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import file.CalculatorFile;
import file.Printer;
import operations.ComplexNumber;
import operations.DivideByZeroException;
import operations.Fraction;
import operations.Operation;
import operations.PolarForm;
import view.gui.CalculatorPanel;
import view.gui.ErrorFrame;
import view.gui.MainFrame;
import view.gui.MenuFrame;

public class Listener
        implements ActionListener, WindowListener, KeyListener, MouseListener {

    protected String operation;
    protected String lastResult;
    protected String history;
    protected String lastDirectory;
    private static Listener listener;
    private MenuFrame menu;
    private DecimalFormat df = new DecimalFormat("#.###");

    public void actionPerformed(ActionEvent e) {

        JButton comp = (JButton) e.getSource();
        MainFrame frame = MainFrame.getInstance();
        CalculatorPanel calc = (CalculatorPanel) frame.getPanel();

        String text = comp.getText();

        // runs basicButtons and if the button pressed isnt basic, it'll run
        // operationButtons
        if (!operationButtons(text, calc))
            basicButtons(text, calc);

        calc.refresh();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        MainFrame frame = MainFrame.getInstance();
        CalculatorPanel calc = frame.getPanel();

        String text;
        if (e.getKeyChar() == (KeyEvent.VK_ENTER)) {
            text = "=";
        } else if (e.getKeyChar() == (KeyEvent.VK_BACK_SPACE)) {
            text = "\u00AB";
        } else if (e.getKeyChar() == '*') {
            text = "X";
        } else {
            text = Character.toString(e.getKeyChar());
        }

        // runs basicButtons and if the button pressed isnt basic, it'll run
        // operationButtons
        if (!operationButtons(text, calc))
            basicButtons(text, calc);

        calc.refresh();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        CalculatorPanel calc = MainFrame.getInstance().getPanel();
        String text;
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            text = "up";
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            text = "down";
        } else {
            text = "what";
        }

        operationButtons(text, calc);
    }

    /**
     * for all text based buttons that add a value to the end of the textfield.
     */
    public void basicButtons(String text, CalculatorPanel calc) {

        final ResourceBundle STRINGS = ResourceBundle.getBundle(
                "view.gui.Language");

        String input = null;
        if (MainFrame.getInstance().getPanel().buttonExists(text)) {
            if (lastResult != null && calc.getInput().equals("")) {
                if (text.equals("+") || text.equals("-") || text.equals("/")
                        || text.equals("X")) {
                    input = lastResult;
                } else {
                    input = calc.getInput();
                }
            } else if (lastResult == null && calc.getInput().equals("")) {
                if (text.equals("+") || text.equals("-") || text.equals("/")
                        || text.equals("X") || text.equals("Inv")
                        || text.equals("Real") || text.equals("Imag")
                        || text.equals("Dec/Frac") || text.equals("^")
                        || text.equals("\u00B1") || text.equals("ϴ")
                        || text.equals("=")) {
                    new ErrorFrame(STRINGS.getString("NO_OPERAND"));
                    calc.setInput("");
                    calc.refresh();
                } else {
                    input = calc.getInput();
                }
            } else {
                input = calc.getInput();
            }
            calc.setInput(input + text);
            operation = text;
            calc.refresh();
        }
    }

    /**
     * for all buttons that require opperations other than adding something to
     * end of the textfield (mathmatical operations or other).
     * 
     * @throws IOException
     */
    public boolean operationButtons(String text, CalculatorPanel calc) {

        final ResourceBundle STRINGS = ResourceBundle.getBundle(
                "view.gui.Language");

        String input = null;
        Boolean success = true;

        switch (text) {
            case "=":
                try {
                    // Real operands calculations
                    if (calc.getInput().indexOf('i') == -1) {
                        input = calculateReal(calc.getInput());
                        lastResult = input;
                        calc.addToHistory(calc.getInput() + " = " + input);
                        // Imaginary operands calculations
                    } else {
                        if (calc.getInput().indexOf('(') == -1) {
                            input = calculateImagineOp(calc.getInput());
                            lastResult = input;
                            calc.addToHistory(calc.getInput() + " = " + input);
                        } else {
                            input = calculateImagine(calc.getInput());
                            lastResult = input;
                            calc.addToHistory(calc.getInput() + " = " + input);
                        }
                    }
                } catch (DivideByZeroException e1) {
                    e1.printStackTrace();
                    new ErrorFrame(STRINGS.getString("CANNOT_DIVIDE_BY_ZERO"));
                }
                calc.setInput(input);
                break;
            case "R":
                calc.clearInput();
                calc.clearHistory();
                lastResult = null;
                break;
            case "C":
                calc.clearInput();
                break;
            case "\u00B1": // plus minus
                if (calc.getInput().length() > 0) {
                    String tmp = calc.getInput();
                    if (!tmp.substring(0, 1).equals("-")) {
                        calc.setInput("-" + tmp);
                    } else {
                        calc.setInput(tmp.substring(1, tmp.length()));
                    }
                }
                break;
            case "Inv":
                if (calc.getInput().length() > 0) {
                    String in = calc.getInput().toString();
                    if (calc.getInput().contains("/")) {
                        String[] fraction = in.split("\\/");
                        calc.setInput(fraction[1] + "/" + fraction[0]);
                    } else {
                        calc.setInput("1/" + calc.getInput());
                    }
                }
                break;
            // i think <= is supposed to be a delete button
            case "\u00AB":
                if (calc.getInput().length() > 0) {
                    calc.setInput(calc.getInput().substring(0,
                            calc.getInput().length() - 1));
                }
                break;
            case ">":
                calc.expandHistory();
                break;
            case "<":
                calc.shrinkHistory();
                break;
            case "Dec/Frac":
                String answer = calc.getInput().toString();
                if (answer.contains("/")) {
                    double total;
                    String[] parts = answer.split("\\/");
                    double numer = Double.parseDouble(parts[0]);
                    double denom = Double.parseDouble(parts[1]);
                    total = numer / denom;
                    calc.setInput(df.format(total));

                } else if (answer.contains(".")) {
                    int[] fraction = Fraction.fractionConverter(answer);
                    calc.setInput(String.valueOf(fraction[0]) + "/"
                            + String.valueOf(fraction[1]));
                } else {
                    calc.setInput(answer + "/" + String.valueOf(1));
                }
                break;
            case "\u03F4": // theta button
                String conver = calc.getInput().toString();
                PolarForm pol;
                // real operand conversion
                if (conver.indexOf('i') == -1) {
                    pol = new PolarForm(Double.parseDouble(conver), 0);
                    calc.setInput(pol.toString());
                    // Imaginary and complex operands conversions
                } else {
                    if (conver.indexOf('(') == -1) { // imaginary
                        conver = conver.replaceAll("i", "");
                        pol = new PolarForm(0, Double.parseDouble(conver));
                        calc.setInput(pol.toString());
                    } else { // complex
                        conver = conver.substring(conver.indexOf('(') + 1,
                                conver.indexOf(')'));
                        conver.replaceAll("\\s", "");
                        String realPartIn;
                        String imagPartIn;
                        if (conver.indexOf("-",
                                conver.indexOf("(") + 2) == -1) {
                            realPartIn = conver.substring(
                                    conver.indexOf("(") + 1,
                                    conver.indexOf("+"));
                            imagPartIn = conver.substring(
                                    conver.indexOf("+") + 1,
                                    conver.indexOf("i"));
                        } else {
                            realPartIn = conver.substring(
                                    conver.indexOf("(") + 1, conver.indexOf("-",
                                            conver.indexOf("(") + 2));
                            imagPartIn = conver.substring(
                                    conver.indexOf("-",
                                            conver.indexOf("(") + 2),
                                    conver.indexOf("i"));
                        }
                        pol = new PolarForm(Double.parseDouble(realPartIn),
                                Double.parseDouble(imagPartIn));
                        calc.setInput(pol.toString());
                    }
                }
                break;

            case "Real":
                String calcInputReal = calc.getInput().toString();
                String[] partsReal = null;
                if (calcInputReal.contains("i")) {
                    partsReal = splitComplexNumber(calcInputReal);
                } else {
                    calc.setInput("0");
                }
                if (partsReal[0].contains("(")) {
                    partsReal = partsReal[0].split("\\(");
                    calc.setInput(partsReal[1]);
                } else {
                    calc.setInput(partsReal[0]);
                }

                break;
            case "Imag":
                String calcInputImag = calc.getInput().toString();
                String[] partsImag = new String[2];
                if (calcInputImag.contains("i")) {
                    partsImag = splitComplexNumber(calcInputImag);
                    if (partsImag[1].contains(")")) {
                        calc.setInput(partsImag[1].substring(0,
                                partsImag[1].length() - 1));
                    } else {
                        calc.setInput(partsImag[1]);
                    }
                } else {
                    calc.setInput("0");
                }
                break;
            case "Select directory":
            case "S\u00E9lectionnez le r\u00E9pertoire":
            case "Selecccionar directorio":
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                f.showSaveDialog(null);
                menu.getSavePanel().setDirectory(
                        f.getSelectedFile().toString());
                break;
            case "Select File":
            case "Choisir le dossier":
            case "Seleccione Archivo":
                JFileChooser g = new JFileChooser();
                g.setFileSelectionMode(JFileChooser.FILES_ONLY);
                g.showSaveDialog(null);
                if (menu.getPanel() == 4) {
                    menu.getLoadPanel().setDirectory(
                            g.getSelectedFile().toString());
                } else {
                    menu.getPrintPanel().setFileString(
                            g.getSelectedFile().toString());
                }
                break;
            case "Save":
            case "Sauvegarder":
            case "Ahorrar":
                String name = menu.getSavePanel().saveName();
                lastDirectory = name;
                CalculatorFile file = new CalculatorFile(name);
                CalculatorPanel panel = MainFrame.getInstance().getPanel();
                file.save(panel.getHistory());
                menu.dispose();
                menu = null;
                break;
            case "Load":
                String directory = menu.getLoadPanel().getDirectory();
                try {
                    MainFrame.getInstance().getPanel().addToHistory(
                            CalculatorFile.load(directory));
                } catch (IOException e) {

                }
                menu.dispose();
                menu = null;
                break;
            case "Cancel":
            case "Annuler":
            case "Cancelar":
                menu.dispose();
                menu = null;
                break;
            case "Print":
                new Printer();
                menu.dispose();
                menu = null;
                break;
            case "up":
                calc.traverseHistory(-1);
                break;
            case "down":
                calc.traverseHistory(1);
                break;
            default:
                success = false;
                break;

        }

        return success;
    }

    private String calculateImagineOp(String equation) {
        String oldInput = equation;
        oldInput.replaceAll("\\s", "");

        int imagine = 0;
        for (int i = 0; i < oldInput.length(); i++) {
            if (oldInput.charAt(i) == 'i') {
                imagine++;
            }
        }
        String input = oldInput.replaceAll("i", "");
        Double first;
        Double second;
        if (input.indexOf('+') != -1) {
            first = Double.parseDouble(input.substring(0, input.indexOf('+')));
            second = Double.parseDouble(
                    input.substring(input.indexOf('+') + 1));
            Double result = first + second;
            input = result.toString() + "i";
        }
        if (input.indexOf('-', 1) != -1) {
            first = Double.parseDouble(
                    input.substring(0, input.indexOf('-', 1)));
            second = Double.parseDouble(
                    input.substring(input.indexOf('-', 1) + 1));
            Double result = first - second;
            input = result.toString() + "i";
        }
        if (input.indexOf('X') != -1) {
            first = Double.parseDouble(input.substring(0, input.indexOf('X')));
            second = Double.parseDouble(
                    input.substring(input.indexOf('X') + 1));
            Double result = first * second;
            input = result.toString();
            if (imagine == 1) {
                input = input + "i";
            } else {
                input = "-" + input;
            }
        }
        if (input.indexOf('/') != -1) {
            first = Double.parseDouble(input.substring(0, input.indexOf('/')));
            second = Double.parseDouble(
                    input.substring(input.indexOf('/') + 1));
            Double result = first / second;
            input = result.toString();
            if (imagine == 1) {
                input = input + "i";
            }
        }
        if (input.indexOf('^') != -1) {
            first = Double.parseDouble(input.substring(0, input.indexOf('^')));
            second = Double.parseDouble(
                    input.substring(input.indexOf('^') + 1));
            Double result = Math.pow(first, second);
            int powerofI = (int) (second % 4);
            if (powerofI == 0)
                input = result.toString();
            if (powerofI == 1)
                input = result.toString() + "i";
            if (powerofI == 2) {
                result *= -1;
                input = result.toString();
            }
            if (powerofI == 3) {
                result *= -1;
                input = result.toString() + "i";
            }
        }
        if (input.indexOf('\u221A') != -1) {
            first = Double.parseDouble(
                    input.substring(input.indexOf('\u221A') + 1));
            ComplexNumber numOne = new ComplexNumber(0, first);
            numOne = Operation.sqrt(numOne);
            input = numOne.toString();
        }
        return input;
    }

    private String calculateReal(String equation) throws DivideByZeroException {
        String input = equation;
        input.replaceAll("\\s", "");
        if (input.indexOf('(') != -1) {
            input = input.substring(0, input.indexOf('('))
                    + calculateReal(input.substring(input.indexOf('(') + 1,
                            input.lastIndexOf(')')))
                    + input.substring(input.lastIndexOf(')') + 1);
        }
        Double first;
        Double second;
        if (input.indexOf('+') != -1) {
            first = Double.parseDouble(input.substring(0, input.indexOf('+')));
            second = Double.parseDouble(
                    input.substring(input.indexOf('+') + 1));
            Double result = first + second;
            input = result.toString();
        }
        if (input.indexOf('-', 1) != -1) {
            first = Double.parseDouble(
                    input.substring(0, input.indexOf('-', 1)));
            second = Double.parseDouble(
                    input.substring(input.indexOf('-', 1) + 1));
            Double result = first - second;
            input = result.toString();
        }
        if (input.indexOf('X') != -1) {
            first = Double.parseDouble(input.substring(0, input.indexOf('X')));
            second = Double.parseDouble(
                    input.substring(input.indexOf('X') + 1));
            Double result = first * second;
            input = result.toString();
        }
        if (input.indexOf('/') != -1) {
            first = Double.parseDouble(input.substring(0, input.indexOf('/')));
            second = Double.parseDouble(
                    input.substring(input.indexOf('/') + 1));
            Double result = first / second;
            input = result.toString();
        }
        if (input.indexOf('^') != -1) {
            first = Double.parseDouble(input.substring(0, input.indexOf('^')));
            second = Double.parseDouble(
                    input.substring(input.indexOf('^') + 1));
            Double result = Math.pow(first, second);
            input = result.toString();
        }
        if (input.indexOf('\u221A') != -1) {
            first = Double.parseDouble(
                    input.substring(input.indexOf('\u221A') + 1));
            Double result = Math.sqrt(first);
            input = result.toString();

        }
        return input;
    }

    /*
     * Parse and calculate. Should prob break this up into two cause code
     * duplication. Currently only add works everything else is out of bounds
     * 
     * Convert this to Imaginary operand calculate
     */
    public String calculateImagine(String input) throws DivideByZeroException {
        String answer = "";
        String input1 = input.substring(input.indexOf('(') + 1,
                input.indexOf(')'));
        String input2 = input.substring(input.lastIndexOf('(') + 1,
                input.lastIndexOf(')'));
        String operation;
        if (input.indexOf('\u221A') != -1) {
            operation = "\u221A";
        } else if (input.indexOf('^') != -1) {
            operation = "^";
        } else {
            operation = input.substring(input.indexOf(')') + 1,
                    input.lastIndexOf('('));
        }

        // Parsing
        input1.replaceAll("\\s", "");
        input2.replaceAll("\\s", "");
        // Splits the first input into parseable doubles
        String realPartIn1;
        String imagPartIn1;
        if (input1.indexOf("-", input1.indexOf("(") + 2) == -1) {
            realPartIn1 = input1.substring(input1.indexOf("(") + 1,
                    input1.indexOf("+"));
            imagPartIn1 = input1.substring(input1.indexOf("+") + 1,
                    input1.indexOf("i"));
        } else {
            realPartIn1 = input1.substring(input1.indexOf("(") + 1,
                    input1.indexOf("-", input1.indexOf("(") + 2));
            imagPartIn1 = input1.substring(
                    input1.indexOf("-", input1.indexOf("(") + 2),
                    input1.indexOf("i"));
        }

        // The first input split into two parts Real and Imaginary
        double input1Real = Double.parseDouble(realPartIn1);
        double input1Imag = Double.parseDouble(imagPartIn1);

        // Splits the second input into parseable doubles
        String realPartIn2 = "";
        String imagPartIn2 = "";
        Double exp = 0.0;

        if (input.indexOf('^') != -1) {
            exp = exp.parseDouble(input.substring(input.indexOf('^') + 1));
        } else if (input2.indexOf("-", input2.indexOf("(") + 2) == -1) {
            realPartIn2 = input2.substring(input2.indexOf("(") + 1,
                    input2.indexOf("+"));
            imagPartIn2 = input2.substring(input2.indexOf("+") + 1,
                    input2.indexOf("i"));
        } else {
            realPartIn2 = input2.substring(input2.indexOf("(") + 1,
                    input2.indexOf("-", input2.indexOf("(") + 2));
            imagPartIn2 = input2.substring(
                    input2.indexOf("-", input2.indexOf("(") + 2),
                    input2.indexOf("i"));
        }

        // The second input split into two parts Real and Imaginary if there is
        // not exponent sign
        double input2Real = 0.0;
        double input2Imag = 0.0;
        if (input.indexOf('^') == -1 && input.indexOf('\u221A') == -1) {
            input2Real = Double.parseDouble(realPartIn2);
            input2Imag = Double.parseDouble(imagPartIn2);
        }
        // Create the complex numbers
        ComplexNumber numOne = new ComplexNumber(input1Real, input1Imag);
        ComplexNumber numTwo = new ComplexNumber(input2Real, input2Imag);
        // calculate the answer
        if (operation.equals("+")) {
            answer = Operation.addition(numOne, numTwo).toString();
        }
        if (operation.equals("/")) {
            answer = Operation.division(numOne, numTwo).toString();
        }
        if (operation.equals("X")) {
            answer = Operation.multiplication(numOne, numTwo).toString();
        }
        if (operation.equals("-")) {
            answer = Operation.subtraction(numOne, numTwo).toString();
        }
        if (operation.equals("^")) {
            answer = Operation.power(numOne, exp).toString();
        }
        if (operation.equals("\u221A")) {
            answer = Operation.sqrt(numOne).toString();
        }
        return answer;
    }

    /*
     * will handle all events that occur for the JMenuItems.
     */
    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        String menuItemName = ((JMenuItem) arg0.getComponent()).getText();

        menu = new MenuFrame();

        switch (menuItemName) {

            case "Save":
            case "Sauvegarder":
            case "Ahorrar":
                try {
                    menu.setMenuType(0);
                } catch (URISyntaxException | IOException e3) {
                    // TODO Auto-generated catch block
                    e3.printStackTrace();
                }
                break;
            case "Print":
            case "Imprimer":
            case "Impresi\u00F3n":
                try {
                    menu.setMenuType(1);
                } catch (URISyntaxException | IOException e3) {
                    // TODO Auto-generated catch block
                    e3.printStackTrace();
                }
                if (lastDirectory != null && lastDirectory.length() > 0) {
                    menu.getPrintPanel().setFileString(lastDirectory);
                }
                break;
            case "Load":
                try {
                    menu.setMenuType(4);
                } catch (URISyntaxException | IOException e3) {
                    // TODO Auto-generated catch block
                    e3.printStackTrace();
                }
                break;
            case "English":
            case "Anglais":
            case "Ingles":
                menu.hide();
                Locale.setDefault(new Locale("en", "US"));
                MainFrame.getInstance().changeLanguage("English");
                break;
            case "Spanish":
            case "Espanol":
            case "Espa\u00F1ol":
                menu.hide();
                Locale.setDefault(new Locale("es", "MEX"));
                MainFrame.getInstance().changeLanguage("Spanish");
                break;
            case "French":
            case "Fran\u00E7ais":
            case "Franc\u00E9s":
                menu.hide();
                Locale.setDefault(new Locale("fr", "FRA"));
                MainFrame.getInstance().changeLanguage("French");
                break;
            case "More Help":
            case "Plus d'aide":
            case "M\u00E1s ayuda":
                try {
                    menu.setMenuType(2);
                    menu.dispose();
                } catch (URISyntaxException | IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                break;
            case "About":
            case "\u00C0 propos":
            case "Acerca de":
                try {
                    menu.setMenuType(3);
                } catch (URISyntaxException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    public String[] splitComplexNumber(String parts) {
        String[] partsSplit = new String[2];
        if (parts.contains("+")) {
            partsSplit = parts.split("\\+");
        } else if (parts.contains("-")) {
            partsSplit = parts.split("\\-");
        } else if (parts.contains("X")) {
            partsSplit = parts.split("\\X");
        } else if (parts.contains("/")) {
            partsSplit = parts.split("\\/");
        }
        return partsSplit;
    }

    /**
     * close the window properly
     */
    @Override
    public void windowClosing(WindowEvent arg0) {
        // TODO Auto-generated method stub
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
        // TODO Auto-generated method stub
        System.exit(0);
    }

    /**
     * returns only only one instance of the listener class. (so only one
     * listener is created.
     * 
     * @return Listener class (this one)
     */
    public static Listener getInstance() {

        if (listener == null) {
            listener = new Listener();
        }

        return listener;
    }

    // ----------------------------------------------------------------------------------------------//

    /**
     * Guys don't delete these. most are useless but we need the window close in
     * order to shut the program down properly. (Jacob).
     */

    @Override
    public void windowActivated(WindowEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }
}
