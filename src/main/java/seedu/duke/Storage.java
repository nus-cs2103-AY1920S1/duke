package seedu.duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import seedu.duke.task.TaskList;

public class Storage {
    // save
    // read
    private String filePath;

    /**
     * Instantiate a Storage object.
     *
     * @param filePath the path to the file where data is stored and read.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        if (!(new File(filePath)).exists()) {
            createFile();
        }
    }

    /**
     * Returns an array of String that is loaded from the file as specified by the
     * Storage object, each containing one line.
     * 
     * @return a stream of strings, each string is a line in the file
     */
    public String[] load() throws DukeException {
        // load tasks from SAVE_LOCATION into the arraylist of tasks, cache.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines().toArray(String[]::new);
        } catch (FileNotFoundException e) {
            throw new DukeException("The data file cannot be found!");
        } catch (IOException e) {
            throw new DukeException("The data file cannot be read!");
        }
    }

    private void createFile() {
        File file = new File(filePath);
        if (file.getParent() != null) {
            File parent = new File(file.getParent());
            if (!parent.exists()) {
                parent.mkdirs();
            }
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
