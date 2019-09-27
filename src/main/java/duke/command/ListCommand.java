package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.FileNotFoundException;

public class ListCommand extends Command {
    private String argument;

    /**
     * Constructs a command to list all tasks.
     */
    public ListCommand() {
    }

    /**
     * Constructs a command to list tasks of a specific type.
     *
     * @param argument the argument supplied to the command.
     */
    public ListCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes a list command using the given task list, UI and file storage.
     *
     * @param tasks the task list supplied.
     * @param ui the UI supplied.
     * @param storage the file storage supplied.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (argument == null) {
            ui.printListTasks(tasks);
        } else {
            switch (argument) {
            case "all":
                ui.printListTasks(tasks);
                break;
            case "todo":
                ui.printFindTasks(tasks.filterByTaskType(Todo.class));
                break;
            case "deadline":
                ui.printFindTasks(tasks.filterByTaskType(Deadline.class));
                break;
            case "event":
                ui.printFindTasks(tasks.filterByTaskType(Event.class));
                break;
            case "archive":
                TaskList archivedTasks;
                try {
                    archivedTasks = new TaskList(storage.loadArchive());
                } catch (FileNotFoundException e) {
                    archivedTasks = new TaskList();
                }
                ui.printFindTasks(archivedTasks);
                break;
            default:
                throw new DukeException("\"" + argument + "\" is not a valid task type.");
            }
        }
    }
}
