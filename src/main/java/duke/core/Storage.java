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
    private String filePath;

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
        File storageFile = new File(filePath);
        storageFile.getParentFile().mkdir();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(storageFile);
            while (fileScanner.hasNext()) {
                String nextLine = fileScanner.nextLine();
                String[] words = nextLine.split(" \\| ");
                Task t = Parser.parseTaskFromFile(words);
                tasks.add(t);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            createNewFile(storageFile);
            throw new DukeException("Oops, there seems to be no existing storage file...\n"
                    + "A new folder will be created with an empty storage file inside it.");
        } catch (DukeException e) {
            throw new DukeException("Oops, an error occurred when parsing past tasks from the storage file :-(");
        }
    }

    private void createNewFile(File storageFile) throws DukeException {
        try {
            storageFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("An error occurred when creating a new storage file.");
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
            throw new DukeException("Oops, an error occurred when saving task to the local file :-(");
        }
    }
}





