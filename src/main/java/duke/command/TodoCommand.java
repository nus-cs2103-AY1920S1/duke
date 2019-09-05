package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {
    public TodoCommand(String[] parts) {
        super(parts);
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        // to do
        // Remaining words
        try {
            String name = parts[1];
            // Add new task to list
            Todo newTodo = new Todo(name, false);
            tasks.add(newTodo);

            // Print output of ADD
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
