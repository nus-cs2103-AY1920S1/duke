package duke.directprocessor;

import duke.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is in charge of reloading previous task list from the hard disk.
 *  It reads the task list from the file from a given file name,
 *  In this file, each line is a task.
 *  Its only method is static, so there is no necessity to initialize one.
 */
public class Storage {

    private static BufferedReader reader;

    /**
     * Reload the task list as an array list of tasks from the target file.
     * @param fileName The path of the file.
     * @return an array list of tasks saved in the file.
     * @throws IOException If loading process is wrong.
     * @throws DukeException If the initialization of a task type fails.
     */
    public static ArrayList<Task> reload(String fileName) throws IOException, DukeException {
        reader = new BufferedReader(new FileReader(fileName));
        ArrayList<Task> toReturn = new ArrayList<>();
        if (reader == null) {
            return toReturn;
        }
        String line = reader.readLine();
        while (line != null) {
            String[] lineComponents = line.split("\\|");
            Task toAdd = translateTask(lineComponents);
            toReturn.add(toAdd);
            line = reader.readLine();
        }
        reader.close();
        return toReturn;
    }

    /**
     * The method that translate a single line into a task.
     *
     * @param lineComponents The line in the file split by "|".
     * @return the translated Task.
     * @throws DukeException If the initialization of the task type fails.
     */
    private static Task translateTask(String[] lineComponents) throws DukeException{
        boolean isFinished;
        if (lineComponents[1].equals("0")) {
            isFinished = false;
        } else {
            isFinished = true;
        }
        String taskName = lineComponents[2];
        if (lineComponents[0].equals("T")) {
            assert lineComponents.length != 3: "The file is wrong.";
            return  translateTodo(taskName, isFinished);
        } else if (lineComponents[0].equals("D")) {
            assert lineComponents.length != 4: "The file is wrong";
            return translateDeadline(taskName, isFinished, lineComponents[3]);
        } else {
            assert lineComponents.length != 4: "The file is wrong";
            return translateEvent(taskName, isFinished, lineComponents[3]);
        }
    }

    private static Todo translateTodo(String taskName, boolean isFinished) throws DukeException {
        Todo toAdd = new Todo(taskName);
        if (isFinished) {
            toAdd.setAsFinish();
        }
        return toAdd;
    }

    private static Event translateEvent(String taskName, boolean isFinished, String taskTime) throws DukeException {
        Event toAdd = new Event(taskName, taskTime);
        if (isFinished) {
            toAdd.setAsFinish();
        }
        return toAdd;
    }

    private static Deadline translateDeadline(String taskName, boolean isFinished, String taskTime) throws DukeException {
        Deadline toAdd = new Deadline(taskName, taskTime);
        if (isFinished) {
            toAdd.setAsFinish();
        }
        return toAdd;
    }
}
