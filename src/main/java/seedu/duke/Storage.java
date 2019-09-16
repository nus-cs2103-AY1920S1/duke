package seedu.duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

import seedu.duke.task.TaskList;

public class Storage {
    // save
    // read
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a stream of String that is loaded from the file as specified by the
     * Storage object.
     * 
     * @return a stream of strings, each string is a line in the file
     */
    public Stream<String> load() throws DukeException {
        // load tasks from SAVE_LOCATION into the arraylist of tasks, cache.
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            Stream<String> lines = br.lines();
            br.close();
            return lines;
        } catch (FileNotFoundException e) {
            throw new DukeException("The data file cannot be found!");
        } catch (IOException e) {
            throw new DukeException("The data file cannot be read!");
        }
    }

    /**
     * Saves the current tasklist into a text file.
     * 
     * @param tasks TaskList to be saved
     * @throws IOException if the file is not found
     */
    public void store(TaskList tasks) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        bw.write(tasks.getSaveString());
        bw.close();
    }
}
