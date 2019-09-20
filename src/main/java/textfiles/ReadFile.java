package textfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads duke.txt file in the user local drive and loads it
 * whenever the user start up Duke.
 */
public class ReadFile extends Storage {
    /**
     * This field stores the file path of duke.txt in the user local drive.
     */
    private String path;

    /**
     * Constructor for the ReadFile class. Gives thee file path to duke.txt
     * file and load the file whenever Duke starts up.
     *
     * @param path Indicates the file path of duke.txt file in local drive.
     */
    public ReadFile(String path) {
        this.path = path;
    }

    /**
     * Opens duke.txt file in the user local drive whenever Duke starts.
     *
     * @return ArrayList of tasks that the user currently have.
     * @throws IOException Gives exception if there is no file to read from.
     */
    public ArrayList<String> openFile() throws IOException {
        FileReader fr = new FileReader(path);

        BufferedReader textReader = new BufferedReader(fr);
        String line = textReader.readLine();

        ArrayList<String> textData = new ArrayList<>();

        while (line != null) {
            textData.add(line);
            line = textReader.readLine();
        }

        textReader.close();
        return textData;
    }
}
