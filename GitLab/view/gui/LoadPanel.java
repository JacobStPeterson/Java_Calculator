package view.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Listener;

public class LoadPanel extends AbstractPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel title;
    private JPanel directoryPanel;
    private JPanel fileDisplayPanel;
    private JPanel bottomPanel;
    private JPanel mainPanel;

    private JButton load;
    private JButton close;
    private JButton fileButton;

    private JTextArea titleString;
    private JTextArea fileString;

    private JLabel fileLabel;

    public LoadPanel() {
        super();
    }

    @Override
    protected void addComponents() {
        // TODO Auto-generated method stub

        title.add(titleString);
        directoryPanel.add(fileLabel);
        directoryPanel.add(fileButton);
        fileDisplayPanel.add(fileString);
        bottomPanel.add(load);
        bottomPanel.add(close);

        mainPanel.add(title);
        mainPanel.add(directoryPanel);
        mainPanel.add(fileDisplayPanel);

        add(mainPanel);
        add(bottomPanel, BorderLayout.SOUTH);

    }

    @Override
    protected void createComponents() {
        // TODO Auto-generated method stub
        title = new JPanel();
        directoryPanel = new JPanel();
        fileDisplayPanel = new JPanel();
        bottomPanel = new JPanel();
        mainPanel = new JPanel();

        load = new JButton("Load");
        close = new JButton("Cancel");
        fileButton = new JButton("Select File");

        titleString = new JTextArea();
        titleString.setText("What File should be loaded into History?");
        titleString.setFont(new java.awt.Font("Italic", Font.ITALIC, 20));
        titleString.setPreferredSize(new Dimension(375, 40));
        titleString.setEditable(false);
        fileString = new JTextArea();
        fileString.setPreferredSize(new Dimension(300, 40));
        fileString.setEditable(false);

        fileLabel = new JLabel("Please Select File:");

        setLayout(new BorderLayout());
        mainPanel.setLayout(new GridLayout(3, 1));
    }

    @Override
    protected void setListeners() {
        // TODO Auto-generated method stub
        Listener listener = Listener.getInstance();

        fileButton.addActionListener(listener);

        Component[] panelItems = bottomPanel.getComponents();
        for (int i = 0; i < panelItems.length; i++) {
            ((JButton) panelItems[i]).addActionListener(listener);
        }
    }

    @Override
    protected void setPanel() {
        // TODO Auto-generated method stub

    }

    public void setDirectory(String t) {
        fileString.setText(t);
    }

    public String getDirectory() {
        return fileString.getText();
    }

}
