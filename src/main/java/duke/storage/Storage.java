package duke.storage;

import duke.exception.DukeException;
import duke.exception.FailedToLoadIOException;
import duke.exception.FailedToSaveIOException;
import duke.task.TaskManager;

import java.io.*;
import java.util.stream.Stream;

/**
 * This is the storage of the Duke Program. The storage is the <code>filePath</code> specified in the constructor of the
 * {@link duke.main.Duke} object. The <code>Storage</code> class loads and save the file into a stream of lines for
 * easy parsing into the list of tasks. See {@link TaskManager} for more information.
 */
public class Storage {
    /**
     * This is a string representation of the file path for storage.
     */
    private String filePath;

    /**
     * Constructs a new storage object using the specified file path.
     * @param filePath the path to the file for storage
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the file and return a stream of lines inside the file.
     * @return a stream of lines inside the file
     * @throws IOException if an I/O error occurs
     */
    public Stream<String> load() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            return reader.lines();
        } catch(FileNotFoundException fnfe) {
            createTextFile();
            return Stream.empty();
        }
    }

    /**
     * Saves the stream of lines inside the file.
     * @param stream the stream of lines to be saved into the file
     * @throws IOException if an I/O error occurs
     */
    public void save(Stream<String> stream) throws FailedToSaveIOException, IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            stream.forEach(line -> {
                writeLine(writer, line);
            });
            writer.close();
        } catch (FileNotFoundException fnfe) {
            createTextFile();
            save(stream);
        } catch (UncheckedIOException uioe) {
            throw new FailedToSaveIOException(uioe.getMessage());
        }
    }

    private static void writeLine(BufferedWriter writer, String line) throws UncheckedIOException {
        try {
            writer.write(line);
        } catch (IOException ioe) {
            throw new UncheckedIOException(line, ioe);
        }
    }

    private static void createTextFile() throws IOException {
        File file = new File("data/duke.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();
    }
}
