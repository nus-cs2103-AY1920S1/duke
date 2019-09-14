package duke.io;

import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

import duke.exception.DukeException;

/**
 * The Storage class provides an abstraction of File handling methods specific to the
 * duke.ui.Duke program. The class is able to read and write to a specified file path, and return
 * the contents of the file to the caller.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object with the given file path.
     *
     * @param filePath filePath to the text storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a new String ArrayList containing data in given filePath.
     *
     * @return a new String ArrayList containing data in given filePath
     * @throws DukeException if the data from file cannot be loaded
     */
    public ArrayList<String> load() throws DukeException {
        try {
            return readFile();
        } catch (IOException e) {
            throw new DukeException("Loading from file failed.");
        }
    }

    /**
     * Writes the contents of the given List into a file.
     * 
     * @param list the list to be written
     * @throws IOException if an IOException occurs
     */
    public void writeToFile(ArrayList<String> list) throws IOException {
        FileWriter fw;
        String path = filePath.substring(0, 7);
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        fw = new FileWriter(filePath);
        for (String s : list) {
            fw.write(s + "\n");
        }
        fw.close();
    }

    /**
     * Helper method to get lines from the File specified in the file path.
     *
     * @return a new String ArrayList containing the info found in filePath.
     * @throws IOException if an IOException occurs
     */
    private ArrayList<String> readFile() throws IOException {
        Stream<String> stream = Files.lines(Paths.get(filePath));
        ArrayList<String> list = new ArrayList<>();
        stream.forEach(list::add);
        stream.close();
        return list;
    }
}
