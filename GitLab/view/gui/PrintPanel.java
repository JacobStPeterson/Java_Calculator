package view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Listener;

public class PrintPanel extends AbstractPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel title;
    private JPanel directoryPanel;
    private JPanel fileDisplayPanel;
    private JPanel bottomPanel;
    private JPanel mainPanel;

    private JButton print;
    private JButton close;
    private JButton fileButton;

    private JTextArea titleString;
    private JTextArea fileString;

    private JLabel fileLabel;

    public PrintPanel() {

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
        final ResourceBundle STRINGS = ResourceBundle.getBundle(
                "view.gui.Language");

        title = new JPanel();
        directoryPanel = new JPanel();
        fileDisplayPanel = new JPanel();
        bottomPanel = new JPanel();
        mainPanel = new JPanel();

        print = new JButton(STRINGS.getString("PRINT"));
        close = new JButton(STRINGS.getString("CANCEL"));
        fileButton = new JButton(STRINGS.getString("SELECT_FILE"));

        titleString = new JTextArea();
        titleString.setText(STRINGS.getString("FILE_PRINT"));
        titleString.setFont(new java.awt.Font("Italic", Font.ITALIC, 20));
        titleString.setPreferredSize(new Dimension(300, 40));
        titleString.setEditable(false);
        fileString = new JTextArea();
        fileString.setPreferredSize(new Dimension(300, 40));
        fileString.setEditable(false);

        fileLabel = new JLabel(STRINGS.getString("PSELECT_FILE"));

        setLayout(new BorderLayout());
        mainPanel.setLayout(new GridLayout(3, 1));
    }

    @Override
    protected void setListeners() {
        // TODO Auto-generated method stub
        Listener listener = Listener.getInstance();
        print.addActionListener(listener);
        close.addActionListener(listener);
        fileButton.addActionListener(listener);
    }

    @Override
    protected void setPanel() {
        // TODO Auto-generated method stub

    }

    public void refresh() {

        title.add(titleString);
        directoryPanel.add(fileLabel);
        directoryPanel.add(fileButton);
        fileDisplayPanel.add(fileString);
        bottomPanel.add(print);
        bottomPanel.add(close);

        mainPanel.add(title);
        mainPanel.add(directoryPanel);
        mainPanel.add(fileDisplayPanel);

        add(mainPanel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setFileString(String file) {
        fileString.setText(file);
    }

}
