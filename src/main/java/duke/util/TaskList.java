package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * Create a TaskList to store Task objects.
 */
public class TaskList {
    public ArrayList<Task> list = new ArrayList<Task>();

    /**
     * TaskList to store.
     *
     * @param content Input an ArrayList of String for TaskList to read inputs line by line
     * @throws DukeException If input is not formatted properly.
     */
    public TaskList(ArrayList<String> content) throws DukeException {
        for (String s : content) {
            loadTask(s);
        }
    }

    public TaskList() {
    }

    private void loadTask(String s) throws DukeException {
        String[] strArr = s.split(" \\| ");
        String action = strArr[0];
        Task t;
        switch (action) {
        case "T":
            t = new ToDo(strArr[2]);
            break;
        case "D":
            t = new Deadline(strArr[2], strArr[3]);
            break;
        case "E":
            t = new Event(strArr[2], strArr[3]);
            break;
        default:
            throw new DukeException("Previous Task storage is corrupted. Resetting your task . .");
        }
        if (strArr[1].equals("1")) {
            t.markAsDone();
        }
        list.add(t);
    }
}
