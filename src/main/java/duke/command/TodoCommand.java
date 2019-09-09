package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {
    public TodoCommand(String[] parts) {
        super(parts);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            String taskName = parts[1];
            // Add new task to list
            Todo newTodoTask = new Todo(taskName, false);
            tasks.add(newTodoTask);

            String response = "Got it. I've added this task:\n"
                    + tasks.get(tasks.size() - 1) + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.\n";
            return response;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
