package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;

public class AddTodoCommand extends Command {
    protected String details;

    public AddTodoCommand(String details) {
        super();
        this.details = details;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (details.trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        Task todo = new Todo(details.trim());
        tasks.addTask(todo);
        int numberOfTasks = tasks.getListSize();
        ui.printAddedMessage(todo, numberOfTasks);
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }

    public boolean isExit() {
        return false;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof AddTodoCommand) {
            AddTodoCommand obj = (AddTodoCommand) o;
            return obj.getDetails().equals(details);
        } else {
            return false;
        }
    }


}
