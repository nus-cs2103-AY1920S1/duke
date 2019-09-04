package duke.command;

import duke.error.DukeException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * subclass of command.
 * execute adding tasks operations: todo, event, deadline
 * */
public class AddCommand extends Command {
    protected String command;
    protected Task task;
    protected String type;
    protected Parser parser = new Parser();

    /**
     * constructor - check if command is valid before assignment object.
     * */
    public AddCommand(String type, String command) throws DukeException {
        try {
            parser.checkTask(type, command);
            if (type.equals("T")) {
                this.task = new Todo(command);
                this.type = "T";
            } else if (type.equals("D")) {
                String[] arr = command.split("/by");
                this.task = new Deadline(arr[0].trim(), parser.datetimeformat(arr[1].trim()));
                this.type = "D";
            } else if (type.equals("E")) {
                String[] arr = command.split("/at");
                this.task = new Event(arr[0].trim(), arr[1].trim());
                this.type = "E";
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * main method to perform operation.
     * add task in TaskList and text file (in storage)
     * if successful, print out task added
     * */
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