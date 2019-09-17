package duke.command;

import duke.exception.DukeIllegalDescriptionException;
import duke.io.Storage;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

import java.io.FileNotFoundException;

public class AddTodo extends Command {

    /**
     * Adds a to-do task to list and returns a notification.
     * @param act command string
     * @param sto storage of current duke instance
     * @return notification string
     * @throws DukeIllegalDescriptionException DukeIllegalDescriptionException
     * @throws FileNotFoundException FileNotFoundException
     */
    public static String addTodo(String act, Storage sto) throws DukeIllegalDescriptionException,
            FileNotFoundException {
        String tdDescription = act.substring(5);
        if (tdDescription.isBlank()) {
            throw new DukeIllegalDescriptionException(act);
        }
        Task todo = new ToDo(tdDescription);
        TaskList.taskList.add(todo);
        String response = ("Got it. I've added this task: \n" + todo.toString()
                + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
        sto.saveData();
        return response;
    }
}
