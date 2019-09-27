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

import java.nio.file.Files;

import java.util.stream.Stream;

/**
 * Implements the Storage class that reads from and writes to the hard disk.
 * @author Lim Yong Shen, Kevin
 */
public class Storage {

    private File dataFile;
    private String filePath;
    private static final int FULL_COMMAND_INDEX = 0;
    private static final int TASK_STATUS_INDEX = 1;
    private static final String FILE_NOT_FOUND_MESSAGE = "The hard disk file could not be loaded."
            + " Creating an empty task list.";
    private static final String FILE_INPUT_ERROR_MESSAGE = "The hard disk file could not be read."
            + " Creating an empty task list.";
    private static final String SAVE_FAIL_MESSAGE = "Data could not be saved."
            + " Please check the hard disk file.\n";

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
            TaskList tasks = new TaskList();
            Stream<String> dataLinesStream = Files.lines(dataFile.toPath());
            dataLinesStream.forEach(dataLine -> {
                String input = getInput(dataLine);
                Command command = Parser.parse(input);
                command.setTaskListToExecuteOn(tasks);
                command.execute();
                boolean isTaskDone = getTaskStatus(dataLine).equals("1");
                if (isTaskDone) {
                    tasks.get(tasks.size() - 1).setAsDone();
                }
            });
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException(FILE_NOT_FOUND_MESSAGE);
        } catch  (IOException e) {
            throw new DukeException(FILE_INPUT_ERROR_MESSAGE);
        }
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
