package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class List {

    /** Header line for list operation. */
    private static String tasklist_header = "Here are the tasks in your list:\n";

    static String list(ArrayList<Task> taskList) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("There are no tasks to display.");
        }
        StringBuilder s = new StringBuilder(tasklist_header);
        for (int i = 0; i < taskList.size(); i++) {
            s.append(i + 1).append(".").append(taskList.get(i));
        }

        return s.toString();
    }
}
