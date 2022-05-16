package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JTextArea;

import view.gui.ErrorFrame;

public class CalculatorFile {

    static final ResourceBundle STRINGS = ResourceBundle.getBundle(
            "view.gui.Language");

    private File file;
    private BufferedWriter writer;

    public CalculatorFile(String fileName) {

        file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            new ErrorFrame(STRINGS.getString("COULDNT_SAVE"));
        }
    }

    public File getFile() {

        return file;
    }

    private void open() {

        try {
            FileWriter fileWriter = new FileWriter(file.getPath());
            writer = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            new ErrorFrame(STRINGS.getString("COULDNT_SAVE"));
        }

    }

    private void write(String line) {

        if (line != null && line.length() >= 0) {
            try {

                writer.write(line);
                writer.flush();
                writer.newLine();
            } catch (IOException e) {

            } catch (NullPointerException e) {

            }
        } else {
            new ErrorFrame(STRINGS.getString("NO_TEXT"));
        }
    }

    public void save(JTextArea text) {

        open();
        write(text.getText());
        try {
            writer.close();
        } catch (IOException e) {
            new ErrorFrame(STRINGS.getString("FAILED_CLOSE"));
        }

    }

    public static String load(String fString) throws IOException {
        try {
            File f = new File(fString);
            @SuppressWarnings("resource")
            BufferedReader read = new BufferedReader(
                    new FileReader(f.getPath()));

            String fullString = "";
            String line = read.readLine();
            while (line != null) {
                fullString += line + "\n";
                line = read.readLine();
            }
            return fullString;
        } catch (FileNotFoundException e) {
            new ErrorFrame(STRINGS.getString("FILE_NOT_FOUND"));
            return "";
        }

    }

}
