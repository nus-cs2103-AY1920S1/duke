package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadlines;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an Add Command.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for the AddCommand.
     * @param typeOfTask "todo", "deadline" or "event".
     * @param name The name of the specfied task.
     * @param datetime The datetime, if the task is a deadline or an event.
     */
    public AddCommand(String typeOfTask, String name, String datetime) {
        switch (typeOfTask) {
        case "todo":
            task = new ToDo(name);
            break;
        case "deadline":
            task = new Deadlines(name, datetime);
            break;
        case "event":
            task = new Event(name, datetime);
            break;
        default:
            break;
        }
    }

    /**
     * Overloaded constructor for AddCommand, if datetime is not given as a parameter.
     * @param typeOfEvent "todo".
     * @param name The name of the specified event.
     */
    public AddCommand(String typeOfEvent, String name) {
        this(typeOfEvent, name, null);
    }

    /**
     * Method that returns true only if this is an instance of an ExitCommand.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Command: adds the current task to current TaskList and writes it in the storage file.
     *
     * @param tasks   current TaskList instance
     * @param ui      current UI instance
     * @param storage current Storage instance
     * @throws DukeException DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        storage.addTask(task);
        ui.printLine("Got it. I've added this task: ");
        ui.printLine("  " + task);
        ui.printLine("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Method that checks whether this instance is logically equivalent to another Object.
     *
     * @param obj The other object in question.
     * @return true if logically equivalent, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof AddCommand) {
            AddCommand other = (AddCommand) obj;
            return other.task.equals(this.task);
        }
        return false;
    }
}
