package duke.command;

import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class CreateToDo {

    /** Task added successfully message. */
    private static String task_added_message = "Got it. I've added this task:\n";

    static String createToDo(ArrayList<Task> taskList, String[] params, Storage storage) throws DukeException {
        String task = Parser.joinStrings(params);
        if (task.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task current = new ToDo(task);
        taskList.add(current);
        String s = task_added_message + current + TotalNoOfTasks.totalNoOfTasks(taskList);
        boolean isSaved = Save.save(storage, taskList);
        assert isSaved == true : "Error: Not saved to disk.";
        return s;
    }
}
