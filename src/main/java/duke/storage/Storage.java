package duke.storage;

import duke.exception.LineInFileWriteException;
import duke.ui.Ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * This is the storage of the Duke Program. The storage is the <code>filePath</code> specified in the constructor of the
 * {@link duke.main.Duke} object. The <code>Storage</code> class loads and save the file into a stream of lines for
 * easy parsing into the list of tasks. See {@link duke.task.TaskList} for more information.
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
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        return reader.lines();
    }

    /**
     * Saves the stream of lines inside the file.
     * @param stream the stream of lines to be saved into the file
     * @throws IOException if an I/O error occurs
     */
    public void save(Stream<String> stream) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        stream.forEach(line -> {
            try {
                writer.write(line);
            } catch (IOException io) {
                new Ui().showLineError(new LineInFileWriteException(line).getLineCount(), line);
            }
        });
        writer.close();
    }

}
