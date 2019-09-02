package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(String description) {
        super(false);
        task = new Todo(description);
    }

    public AddCommand(String action, String description, String time) throws DukeException {
        super(false);
        if (action.equals("event")) {
            task = new Event(description, time);
        } else if (action.equals("deadline")) {
            task = new Deadline(description, time);
        } else {
            throw new DukeException("Unrecognised command :(");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task, ui);
    }
}
