package command;

import error.DukeException;
import task.Task;
import task.Deadline;
import task.Todo;
import task.Event;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

/**
 * Deals with operation to add new tasks.
 */
public class AddCommand extends Command {
    protected String command;
    protected Task task;
    protected String type;

    /**
     * Initializes AddCommand object.
     * Checks if command is valid before assignment object.
     *
     * @param type Type of task
     * @param command String user input of the task details
     * @throws DukeException if command is invalid
     */
    public AddCommand(String type, String command) throws DukeException {
        try {
            Parser.checkTask(type, command);
            if (type.equals("T")) {
                this.task = new Todo(command);
                this.type = "T";
            } else if (type.equals("D")) {
                String[] arr = command.split("/by");
                this.task = new Deadline(arr[0].trim(), Parser.datetimeformat(arr[1].trim()));
                this.type = "D";
            } else if (type.equals("E")) {
                String[] arr = command.split("/at");
                this.task = new Event(arr[0].trim(), Parser.datetimeformat(arr[1].trim()));
                this.type = "E";
            } else {
                throw new AssertionError("ERROR: Invalid Task Type!");
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Executes operation to add new task.
     *
     * @param tasks TaskList to perform changes from
     * @param ui Ui to generate message outputs
     * @param storage Object to save tasks
     * @return String generate message as output from successful operation
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.getList().add(task);
        storage.saveTasks(task, type);
        return ui.printAddTask(task, tasks.getList().size());
    }

    public boolean isExit() {
        return false;
    }

    public String toString() {
        return task.toString();
    }
}