package duke.commands;

import duke.errands.Task;
import duke.errands.Todo;
import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class TodoCommand extends Command {

    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        if (this.input.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(this.input.trim());
        tasks.input(newTask);
        store.write(tasks);
        return ui.addTask(newTask, tasks);
    }

}
