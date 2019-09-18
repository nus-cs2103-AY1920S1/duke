package duke.core;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a <code>Storage</code> class that deals with loading tasks from 
 * a file and saving tasks in the file.
 */
public class Storage {

    /** A string that represents the file path in local hard disk. */
    private String filePath;

    /**
     * Constructs a <code>Storage</code> object with a specific file path.
     *
     * @param filePath A string that represents the path of the file to be
     *          loaded and modified.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into a <code>ArrayList</code> of 
     * <code>Task</code>.
     *
     * @return A <code>ArrayList</code> of tasks as recorded in the file.
     * @throws DukeException If file is not found.
     */
    public ArrayList<Task> load() throws DukeException {
        File duke = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(duke);
            while (fileScanner.hasNext()) {
                String nextLine = fileScanner.nextLine();
                String[] words = nextLine.split(" \\| ");
                Task t = Parser.parseTaskFromFile(words);
                tasks.add(t);
            }
        } catch (FileNotFoundException | DukeException e) {
            throw new DukeException("Oops, there seems to be no existing file...\n"
                    + "A new file will be created once you add a task.");
        }
        return tasks;
    }

    /**
     * Saves tasks from a <code>TaskList</code> to the local file.
     *
     * @param tasks The <code>TaskList</code> storing tasks.
     * @throws DukeException If writing to the local file failed.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks.getList()) {
                fw.write(t.format() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save task to the local file :-(");
        }
    }
}





