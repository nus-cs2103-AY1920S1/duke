package duke.command;

import duke.exception.DukeException;
import duke.component.TaskList;
import duke.database.Storage;
import duke.function.CheckDuplicate;
import duke.task.Todo;

/**
 * This To-do Command class get the input of the task description
 * and execute the to-do method to add task.
 *
 * @author TeoShyanJie
 *
 */
public class TodoCommand extends Command {
    /**
     * TodoCommand Constructor.
     * @param input The input task enter by user.
     * @param type The type of task enter by user.
     */
    public TodoCommand(String input, String type) {
        super(input, type);
    }

    /**
     * Execute method to execute to-do method.
     * @param tasks The list task enter by user.
     * @param storage The Database of Duke Program.
     * @return String of output.
     * @throws DukeException If to-do method is not able to get executed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return todo(super.input, tasks);
    }

    /**
     * "to-do" command to enter the task description.
     * @param data to-do command and description of task.
     * @param tasks List of task save in arraylist.
     * @return String of output.
     * @throws DukeException If description of data is empty.
     */
    public String todo(String data, TaskList tasks) throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("OOPS !!! " + "The description of a todo cannot be empty.");
        }

        StringBuilder reply = new StringBuilder();

        Todo todo = new Todo(data);
        CheckDuplicate check = new CheckDuplicate(todo, tasks);

        if (check.addTodo()) {
            tasks.getTask().add(todo);
        } else {
            throw new DukeException("OOPS !!! " + "Duplicate Task Detected.");
        }

        reply.append("Got it. I've added this task: ");
        reply.append("\n");
        reply.append(tasks.getTask().get(tasks.getItemNo()));
        reply.append("\n");
        tasks.setItemNo(tasks.getItemNo() + 1);
        reply.append("Now you have "
                + tasks.getItemNo() + " tasks in the list.");

        return reply.toString();
    }
}