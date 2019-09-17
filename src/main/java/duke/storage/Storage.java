package duke.storage;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a Storage file to access and write to files.
 */
public class Storage {
    /**
     * The filepath to store to/access from.
     */
    private String filePath;

    /**
     * The File created from accessing the file.
     */
    private File tasksFile;

    /**
     * Constructor for Storage.
     * Throws an IOException if the file cannot be accessed/written to.
     * It creates the directories provided in the filePath if they don't already exist.
     * It also creates a new file if the file doesn't already exist.
     *
     * @param filePath the file to be accessed/written to
     * @throws IOException if an error occurs during access/writing
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.tasksFile = new File(this.filePath);
        if (!this.tasksFile.exists()) {
            //the file does not exist yet
            this.tasksFile.getParentFile().mkdirs();
            this.tasksFile.createNewFile();
            this.tasksFile = new File(this.filePath);
        }
    }

    /**
     * Loads the file into a taskList.
     * It uses static methods from the Parser class to parse the lines of the .txt file
     * into commands, and then creates a new TaskList from all the tasks.
     * Throws a DukeException if there is any error parsing the .txt file.
     *
     * @return TaskList of all the tasks contained inside the .txt file.
     * @throws DukeException If there is an error parsing the command
     * @throws IOException   If there is an error accessing the file
     */
    public TaskList load() throws DukeException, IOException {
        Scanner fileScanner = new Scanner(this.tasksFile);
        TaskList tasks = new TaskList();
        while (fileScanner.hasNext()) {
            tasks.addTaskToList(Parser.parseStoredMessage(fileScanner.nextLine()));
        }
        return tasks;
    }

    /**
     * Saves a TaskList to a file.
     * It takes in a TaskList and then calls listAllTasksAsString to write to a .txt file.
     *
     * @param tasks the TaskList to be saved
     * @throws IOException if there is amn error writing to the file
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        fileWriter.write(tasks.listAllTasksAsString());
        System.out.println("WRITING SUCCESSFUL");
        fileWriter.close();
    }
}
