package duke.core;

import duke.task.Task;
import duke.task.NormalTask;
import duke.task.Deadline;
import duke.task.Event;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
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
                if (words[0].equals("T")) {
                    Task t = new NormalTask(words[2]);
                    if (words[1].equals("✓")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                }
                if (words[0].equals("D")) {
                    Task t = new Deadline(words[2], words[3]);
                    if (words[1].equals("✓")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                } 
                if (words[0].equals("E")) {
                    Task t = new Event(words[2], words[3]);
                    if (words[1].equals("✓")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Load failed");
        }
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
            throw new DukeException("Failed to save tasks :-(");
        }
    }
}






