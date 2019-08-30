package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static duke.task.Task.DATE_TIME_FORMATTER;

public class Command {
    private CommandType type;
    private String arguments;
    private boolean isExit;

    /**
     * Constructs a command.
     *
     * @param type      Type of the command
     * @param arguments arguments of the command
     */
    public Command(final CommandType type, final String arguments) {
        this.type = type;
        this.arguments = arguments;
        this.isExit = false;
    }

    /**
     * Returns true if the command signals exit.
     *
     * @return true if the command signals exit
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes this command.
     *
     * @param tasks   the existing tasks
     * @param ui      the current Ui
     * @param storage the data file
     * @throws DukeException the command cannot be executed
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (this.type) {
            case TODO:
                handleTodo(arguments, tasks, ui, storage);
                break;
            case EVENT:
                handleEvent(arguments, tasks, ui, storage);
                break;
            case DEADLINE:
                handleDeadline(arguments, tasks, ui, storage);
                break;
            case DONE:
                handleDone(arguments, tasks, ui, storage);
                break;
            case FIND:
                handleFind(arguments, tasks, ui);
                break;
            case LIST:
                handleList(tasks, ui);
                break;
            case DELETE:
                handleDelete(arguments, tasks, ui, storage);
                break;
            case BYE:
                this.isExit = true;
                break;
            case UNKNOWN:
            default:
                handleUnknown(ui);
                break;
        }
    }

    private LocalDateTime parseDateTime(final String dateTime) throws DukeException {
        try {
            return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Failed to parse date time: " + e.getMessage());
        }
    }

    private void handleAddTask(final Task task, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.addTask(task)) {
            storage.writeTasks(tasks);
            ui.showMessage("Got it. I've added this duke.task:");
            ui.showMessage("  " + task.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list");
        } else {
            throw new DukeException("Error: Failed to add duke.task");
        }
    }

    private void handleTodo(final String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isBlank()) {
            throw new DukeException("The description of a duke.task cannot be empty");
        }
        handleAddTask(new Todo(arguments), tasks, ui, storage);
    }

    private void handleDeadline(final String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] tokens = arguments.split("\\s*/by\\s*", 2);
        if (tokens[0].isBlank()) {
            throw new DukeException("The description of a duke.task cannot be empty");
        } else if (tokens.length == 1) {
            throw new DukeException("Deadline cannot be empty");
        }
        Deadline deadline = new Deadline(tokens[0], parseDateTime(tokens[1]));
        handleAddTask(deadline, tasks, ui, storage);
    }

    private void handleEvent(final String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] tokens = arguments.split("\\s*/at\\s*", 2);
        if (tokens[0].isBlank()) {
            throw new DukeException("The description of a duke.task cannot be empty");
        } else if (tokens.length == 1) {
            throw new DukeException("Event time cannot be empty");
        }
        Event event = new Event(tokens[0], parseDateTime(tokens[1]));
        handleAddTask(event, tasks, ui, storage);
    }

    private void handleFind(final String arguments, TaskList tasks, Ui ui) {
        TaskList matches = new TaskList();
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(arguments)) {
                matches.addTask(task);
            }
        }
        if (matches.size() == 0) {
            ui.showMessage("There are no matching tasks in your list");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            ui.showMessage(matches.toString());
        }
    }

    private void handleDone(final String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(arguments) - 1;
            Task task = tasks.getTask(taskNumber);
            task.markAsDone();
            storage.writeTasks(tasks);
            ui.showMessage("Nice! I've marked this duke.task as done:");
            ui.showMessage("  " + task);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid duke.task number format");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist");
        }
    }

    private void handleDelete(final String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(arguments) - 1;
            Task task = tasks.deleteTask(taskNumber);
            storage.writeTasks(tasks);
            ui.showMessage("Noted. I've removed this duke.task:");
            ui.showMessage("  " + task);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list");
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid duke.task number format");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist");
        }
    }

    private void handleList(TaskList tasks, Ui ui) {
        ui.showMessage("Here are the tasks in your list:");
        ui.showMessage(tasks.toString());
    }

    private void handleUnknown(Ui ui) {
        ui.showError("Unknown command");
    }
}
