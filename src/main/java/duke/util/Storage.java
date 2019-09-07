package duke.util;

import duke.exception.CannotSaveTasksException;
import duke.exception.CorruptedTasksException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private final File file;

    public Storage(String fileUrl) {
        this.file = new File(fileUrl);
    }

    /**
     * Loads all tasks from storage.
     *
     * @return the list of tasks saved in the storage
     */
    public TaskList loadTasks() throws CorruptedTasksException {
        try {
            return loadTasksFromFile(this.file);
        } catch (FileNotFoundException e) {
            return new TaskList();
        } catch (InvalidDateException | InvalidCommandException e) {
            throw new CorruptedTasksException();
        }
    }

    /**
     * Saves all tasks into storage.
     *
     * @param tasks the list of tasks to be saved into storage
     */
    public void saveTasks(TaskList tasks) throws CannotSaveTasksException {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.write(Parser.convertTasksToEncodedString(tasks));
            fileWriter.close();
        } catch (IOException e) {
            throw new CannotSaveTasksException();
        }

    }

    private TaskList loadTasksFromFile(File file) throws FileNotFoundException, InvalidDateException, InvalidCommandException {
        Scanner scanner = new Scanner(file);
        TaskList tasks = new TaskList();

        while (scanner.hasNext()) {
            String savedText = scanner.nextLine();
            Task task = Parser.convertEncodedStringToTask(savedText);
            tasks.add(task);
        }

        return tasks;
    }

}
