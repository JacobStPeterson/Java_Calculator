package view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Listener;

/**
 * panel that holds all tools for the calculator.
 * 
 * @author Jacob Peterson ( peter2js )
 *
 */
public class CalculatorPanel extends AbstractPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static CalculatorPanel calculator;

    private JTextField input1;
    private JTextArea historyText;

    private JButton equals;
    private JButton add;
    private JButton subtract;
    private JButton multiply;
    private JButton divide;
    private JButton reset;
    private JButton clear;
    private JButton negPos;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button0;
    private JButton buttoni;
    private JButton leftPar;
    private JButton rightPar;
    private JButton period;
    private JButton inverse;
    private JButton history;
    private JButton leftArrow;
    private JButton rightArrow;
    private JButton decFrac;
    private JButton sqrt;
    private JButton exp;
    private JButton polarForm;
    private JButton copyPas;
    private JButton real;
    private JButton imaginary;

    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel extraCenter;
    private JPanel bottomPanel;
    private JPanel rightPanel;

    private JLabel logo;

    private Listener listener;

    private int placeHolder;

    /**
     * runs operations specified in abstract panel. (peter2js)
     */
    public CalculatorPanel() {

        super();
    }

    /**
     * adds all components to the panel. (peter2js)
     */
    @SuppressWarnings("deprecation")
    @Override
    protected void addComponents() {

        topPanel.add(logo);
        centerPanel.add(topPanel, BorderLayout.NORTH);
        extraCenter.add(input1, BorderLayout.NORTH);

        bottomPanel.add(negPos);
        bottomPanel.add(clear);
        bottomPanel.add(leftArrow);
        bottomPanel.add(reset);
        bottomPanel.add(inverse);
        bottomPanel.add(button1);
        bottomPanel.add(button2);
        bottomPanel.add(button3);
        bottomPanel.add(imaginary);

        bottomPanel.add(real);
        bottomPanel.add(real);
        bottomPanel.add(button4);
        bottomPanel.add(button5);
        bottomPanel.add(button6);
        bottomPanel.add(leftPar);
        bottomPanel.add(rightPar);
        bottomPanel.add(button7);
        bottomPanel.add(button8);
        bottomPanel.add(button9);
        bottomPanel.add(decFrac);
        bottomPanel.add(polarForm);
        bottomPanel.add(decFrac);
        bottomPanel.add(period);
        bottomPanel.add(button0);
        bottomPanel.add(buttoni);

        bottomPanel.add(add);
        bottomPanel.add(subtract);

        bottomPanel.add(multiply);
        bottomPanel.add(divide);

        bottomPanel.add(sqrt);
        bottomPanel.add(exp);

        bottomPanel.add(equals);

        // bottomPanel.add(copyPas);
        rightPanel.add(history);

        extraCenter.add(bottomPanel);
        centerPanel.add(extraCenter);

        for (int i = 0; i < bottomPanel.countComponents(); i++) {
            bottomPanel.getComponent(i).setFont(
                    new java.awt.Font("Italic", Font.ITALIC, 25));
        }
        decFrac.setFont(new java.awt.Font("Italic", Font.ITALIC, 12));

        setPanels();
    }

    /**
     * creates all components. (peter2js)
     */
    @Override
    protected void createComponents() {

        URL url = this.getClass().getResource("/images" + LookAndFeel.logo);
        logo = new JLabel(new ImageIcon(url));

        input1 = new JTextField();
        input1.setSelectionColor(LookAndFeel.colors);
        input1.setPreferredSize(new Dimension(500, 80));
        input1.setEditable(false);
        historyText = new JTextArea();
        historyText.setEditable(false);
        historyText.setPreferredSize(new Dimension(200, 500));
        input1.setFont(new java.awt.Font("Italic", Font.ITALIC, 25));

        equals = new JButton("=");

        add = new JButton("+");
        subtract = new JButton("-");
        multiply = new JButton("X");
        divide = new JButton("/");
        reset = new JButton("R");
        clear = new JButton("C");
        negPos = new JButton("\u00B1"); // plus minus sign
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");
        button0 = new JButton("0");
        buttoni = new JButton("i");
        leftPar = new JButton("(");
        rightPar = new JButton(")");
        period = new JButton(".");
        inverse = new JButton("Inv");
        history = new JButton(">");
        leftArrow = new JButton("\u00AB"); // backspace arrows
        rightArrow = new JButton("<");
        decFrac = new JButton("Dec/Frac");
        sqrt = new JButton("\u221A"); // square root symbol
        exp = new JButton("^");
        polarForm = new JButton("\u03F4"); // theta symbol
        copyPas = new JButton("ans");
        real = new JButton("Real");
        imaginary = new JButton("Imag");

        rightPanel = new JPanel();
        centerPanel = new JPanel();
        extraCenter = new JPanel();
        bottomPanel = new JPanel();
        topPanel = new JPanel();

        centerPanel.setLayout(new BorderLayout());
        extraCenter.setLayout(new BorderLayout());

        GridLayout layout = new GridLayout(6, 6);

        layout.setVgap(10);
        layout.setHgap(20);

        bottomPanel.setLayout(layout);

        listener = Listener.getInstance();

        bottomPanel.setBackground(Color.lightGray);
        topPanel.setBackground(Color.lightGray);
        rightPanel.setBackground(Color.lightGray);

        input1.setBackground(Color.cyan);
        historyText.setBackground(Color.cyan);

        placeHolder = -1;
    }

    /**
     * sets a listener type to all items users will interact with. (peter2js)
     */
    @Override
    protected void setListeners() {

        Component[] panelItems = bottomPanel.getComponents();

        for (int i = 0; i < panelItems.length; i++) {
            ((JButton) panelItems[i]).addActionListener(listener);
        }

        history.addActionListener(listener);
        rightArrow.addActionListener(listener);

        input1.addKeyListener(listener);
    }

    /**
     * sets the layout of the calculator panel. (peter2js)
     */
    @Override
    protected void setPanel() {
        setLayout(new BorderLayout());
    }

    private void setPanels() {

        add(centerPanel);
        add(rightPanel, BorderLayout.EAST);
    }

    public void clearHistory() {
        historyText.setText("");
    }

    public void addToHistory(String str) {
        historyText.setText(historyText.getText() + "\n" + str);
        placeHolder++;
    }

    public void clearInput() {

        input1.setText("");
    }

    public boolean buttonExists(String str) {

        Component[] panelItems = bottomPanel.getComponents();
        boolean exists = false;

        for (int i = 0; i < panelItems.length; i++) {
            if (str.equals(((JButton) panelItems[i]).getText()))
                exists = true;
        }

        return exists;
    }

    /**
     * retrieves text in answer text field (peter2js).
     * 
     * @return answer text
     */
    public JTextArea getHistory() {

        return historyText;
    }

    /**
     * gets text inside input text field (peter2js).
     * 
     * @return input text
     */
    public String getInput() {

        return input1.getText();
    }

    public void setInput(String str) {

        input1.setText(str);
    }

    /**
     * refreshes the page (peter2js);
     */
    public void refresh() {

        this.revalidate();
        this.repaint();
    }

    /**
     * opens the history panel to be viewed.
     * 
     * @throws InterruptedException
     */
    public void expandHistory() {

        rightPanel.removeAll();
        rightPanel.add(historyText);
        rightPanel.add(rightArrow);
        rightPanel.setPreferredSize(new Dimension(300, 300));
        MainFrame.getInstance().changeFrameSize(260);

        setPanels();
        refresh();

    }

    /**
     * closes history view to only show calculator panel.
     */
    public void shrinkHistory() {

        rightPanel.removeAll();
        rightPanel.add(history);
        rightPanel.setPreferredSize(new Dimension(40, 200));
        MainFrame.getInstance().changeFrameSize(-260);

        setPanels();
    }

    /**
     * generates a mainframe and only allows one to be made. (peter2js)
     * 
     * @return the frame
     */
    public static CalculatorPanel getInstance() {

        if (calculator == null) {
            calculator = new CalculatorPanel();

        }

        return calculator;
    }

    public void traverseHistory(int a) {
        if (historyText.getText() != null
                && historyText.getText().length() > 0) {
            if (placeHolder + a < historyText.getLineCount()
                    && placeHolder + a >= 0) {
                placeHolder += a;
            }
            String[] equations = historyText.getText().split("\\n");
            setInput(equations[placeHolder]);
        }
    }
}
