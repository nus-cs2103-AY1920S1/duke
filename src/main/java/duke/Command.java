package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

class Command {
    private CommandType type;
    private String arguments;
    private boolean isExit;

    Command(final CommandType type, final String arguments) {
        this.type = type;
        this.arguments = arguments;
        this.isExit = false;
    }

    boolean isExit() {
        return this.isExit;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
            return LocalDateTime.parse(dateTime, Task.dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Failed to parse date time: " + e.getMessage());
        }
    }

    private void handleAddTask(final Task task, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.addTask(task)) {
            storage.writeTasks(tasks);
            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("  " + task.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list");
        } else {
            throw new DukeException("Error: Failed to add task");
        }
    }

    private void handleTodo(final String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isBlank()) {
            throw new DukeException("The description of a task cannot be empty");
        }
        handleAddTask(new Todo(arguments), tasks, ui, storage);
    }

    private void handleDeadline(final String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] tokens = arguments.split("\\s*/by\\s*", 2);
        if (tokens[0].isBlank()) {
            throw new DukeException("The description of a task cannot be empty");
        } else if (tokens.length == 1) {
            throw new DukeException("Deadline cannot be empty");
        }
        Deadline deadline = new Deadline(tokens[0], parseDateTime(tokens[1]));
        handleAddTask(deadline, tasks, ui, storage);
    }

    private void handleEvent(final String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] tokens = arguments.split("\\s*/at\\s*", 2);
        if (tokens[0].isBlank()) {
            throw new DukeException("The description of a task cannot be empty");
        } else if (tokens.length == 1) {
            throw new DukeException("Event time cannot be empty");
        }
        Event event = new Event(tokens[0], parseDateTime(tokens[1]));
        handleAddTask(event, tasks, ui, storage);
    }

    private void handleDone(final String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(arguments) - 1;
            Task task = tasks.getTask(taskNumber);
            task.markAsDone();
            storage.writeTasks(tasks);
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage("  " + task);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number format");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist");
        }
    }

    private void handleDelete(final String arguments, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(arguments) - 1;
            Task task = tasks.deleteTask(taskNumber);
            storage.writeTasks(tasks);
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage("  " + task);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list");
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number format");
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
