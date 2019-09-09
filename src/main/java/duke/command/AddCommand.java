package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to add tasks from user input.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs a new Command to add a todo.
     * @param description description of the todo task
     */
    public AddCommand(String description) {
        super(false);
        task = new Todo(description);
    }

    /**
     * Constructs a new Command to add either a deadline or event.
     *
     * @param action type of task - either deadline or event
     * @param description description of the task
     * @param time time of task
     * @throws DukeException if the action is neither "event" nor "deadline"
     */
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

    /**
     * Adds its assigned task to the task list.
     *
     * @param taskList the list which the task will be added to
     * @param ui the ui object to print messages
     * @param storage storage object to write files to
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.add(task, ui);
    }
}
