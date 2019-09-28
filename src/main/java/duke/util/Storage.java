package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /** File where tasks are loaded and saved from. */
    private File file;

    /**
     * Constructor for Storage.
     *
     * @param filePath Path where the .txt file is at.
     */
    public Storage(String filePath) {
        file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Retrieve and return the tasks stored in the file.
     *
     * @return List of tasks stored in the file.
     * @throws FileNotFoundException If file is not found.
     * @throws DukeException If string representing Task in the file is not formatted correctly.
     */
    public TaskList getTasks() throws FileNotFoundException, DukeException {
        TaskList tasks = new TaskList();
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String saveString = sc.nextLine();
            String[] stringComponents =  saveString.split(Pattern.quote(" | "));
            String taskType = stringComponents[0];

            switch (taskType) {
            case "T":
                Todo todo = new Todo(stringComponents[2]);
                if (stringComponents[1].equals("1")) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(stringComponents[2], stringComponents[3]);
                if (stringComponents[1].equals("1")) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "E":
                Event event = new Event(stringComponents[2], stringComponents[3]);
                if (stringComponents[1].equals("1")) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                break;
            }
        }

        return tasks;
    }

    /**
     * Save the tasks currently tracked by Duke into the file.
     *
     * @param tasks List of tasks currently tracked by Duke.
     * @throws DukeException If the tasks cannot be written and saved into file properly.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            StringBuilder fileStringBuilder = new StringBuilder();

            for (Task task : tasks) {
                fileStringBuilder.append(task.getSaveString());
                fileStringBuilder.append(System.lineSeparator());
            }

            fw.write(fileStringBuilder.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
