package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class TodoCommand extends Command {
    /**
     * Constructs a todo command.
     *
     * @param params String containing description.
     */
    public TodoCommand(String params) {
        super(params);
    }

    /**
     * Executes the command.
     *
     * @param tasks   Task list containing all tasks.
     * @param storage Storage instance.
     * @return String representation of todo that was added.
     */
    @Override
    public String executeCommand(TaskList tasks, Storage storage) {
        Todo newTodo = new Todo(this.params);
        tasks.addTask(newTodo);

        return Ui.showAddedTask(newTodo);
    }
}
