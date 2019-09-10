package duke.storage;

import duke.commands.Command;

import duke.data.TaskList;
import duke.data.tasks.Task;

import duke.exceptions.DukeException;

import duke.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * Implements the Storage class that reads from and writes to the hard disk.
 * @author Lim Yong Shen, Kevin
 */
public class Storage {

    private File dataFile;
    private String filePath;
    private static final int FULL_COMMAND_INDEX = 0;
    private static final int TASK_STATUS_INDEX = 1;
    private static final String SAVE_FAIL_MESSAGE = "Data could not be saved."
            + "Please check the hard disk file.\n";

    /**
     * Constructs a Storage class that is connected to the specified file path.
     * @param filePath The specified file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of tasks which is the result of reading data from the hard disk.
     * @return An ArrayList of tasks which is the result of reading data from the hard disk.
     * @throws DukeException If the hard disk file cannot be found.
     */
    public TaskList load() throws DukeException {
        try {
            dataFile = new File(filePath);
            Scanner fileScanner = new Scanner(dataFile);
            TaskList tasks = new TaskList();
            while (fileScanner.hasNextLine()) {
                loadTasksFromHardDisk(fileScanner, tasks);
            }
            fileScanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
    }

    /**
     * Loads tasks from the hard disk into the specified task list using the specified scanner.
     * @param fileScanner The specified scanner.
     * @param tasks The specified task list.
     */
    private void loadTasksFromHardDisk(Scanner fileScanner, TaskList tasks) {
        String dataLine = fileScanner.nextLine();
        String input = getInput(dataLine);
        Command command = getCommand(input);
        command.setTaskListToExecuteOn(tasks);
        command.execute();
        if (getTaskStatus(dataLine).equals("1")) {
            tasks.get(tasks.size() - 1).setAsDone();
        }
    }

    /**
     * Parses the specified input and returns the appropriate command.
     * @param input The specified input.
     * @return The appropriate command.
     */
    private Command getCommand(String input) {
        return Parser.parse(input);
    }

    /**
     * Returns the full command in the specified line of data.
     * @param dataLine The specified line of data.
     * @return The full command in the specified line of data.
     */
    private String getInput(String dataLine) {
        return dataLine.split(" \\| ")[FULL_COMMAND_INDEX];
    }

    /**
     * Returns the task status in the specified line of data.
     * @param dataLine The specified line of data.
     * @return The task status in the specified line of data.
     */
    private String getTaskStatus(String dataLine) {
        return dataLine.split(" \\| ")[TASK_STATUS_INDEX];
    }

    /**
     * Saves the tasks in the specified task list to the hard disk.
     * @param tasks The specified task list.
     * @throws DukeException If the hard disk file cannot be found.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(dataFile);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String dataLine = String.format("%s | %s\n", task.getCommandString(),
                        task.isDone() ? "1" : "0");
                fileWriter.write(dataLine);
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(SAVE_FAIL_MESSAGE);
        }
    }

}
