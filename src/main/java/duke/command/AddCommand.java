package duke.command;

import duke.date.InvalidDateDukeException;
import duke.storage.Storage;

import duke.task.InvalidTaskDukeException;
import duke.task.InvalidDeadlineDukeException;
import duke.task.InvalidEventDukeException;
import duke.task.InvalidTodoDukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import duke.tasklist.TaskList;

/**
 * Represents a command instructing Duke to add a task.
 */
public class AddCommand extends Command {

    private String input;

    /** Add-Command start phrases. */
    private static final String TODO_COMMAND_START = "todo";
    private static final String DEADLINE_COMMAND_START = "deadline";
    private static final String EVENT_COMMAND_START = "event";

    /**
     * Constructor for the add command.
     * @param input Command description.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the add command.
     * @param tasks List of tasks
     * @param storage Storage class
     * @return Duke's response.
     * @throws InvalidTaskDukeException If the task is invalid.
     * @throws InvalidDateDukeException If the date is invalid.
     */
    public String execute(TaskList tasks, Storage storage)
            throws InvalidTaskDukeException, InvalidDateDukeException {
        String cleanedInput = cleanInput(input);
        Task t = makeTask(cleanedInput);
        String output = tasks.addTask(t);
        return output;
    }

    /**
     * Makes a task corresponding to the input.
     * @param cleanedInput Cleaned user input.
     * @return Task representing user input.
     * @throws InvalidTaskDukeException If task is invalid.
     * @throws InvalidDateDukeException If the date format is invalid.
     */
    public Task makeTask(String cleanedInput) throws InvalidTaskDukeException, InvalidDateDukeException {
        if (cleanedInput.startsWith(TODO_COMMAND_START)) {
            return makeTodo(cleanedInput);
        } else if (cleanedInput.startsWith(DEADLINE_COMMAND_START)) {
            return makeDeadline(cleanedInput);
        } else if (cleanedInput.startsWith(EVENT_COMMAND_START)) {
            return makeEvent(cleanedInput);
        } else {
            throw new InvalidTaskDukeException("Invalid add command!");
        }
    }

    private Todo makeTodo(String task) throws InvalidTodoDukeException {
        try {
            assert task.startsWith(TODO_COMMAND_START) : "This is not a to-do command";
            String[] tokens = task.split("\\s+");
            StringBuilder description = new StringBuilder();
            for (int i = 1; i < tokens.length; i++) {
                description.append(tokens[i] + " ");
            }
            return new Todo(description.toString().strip());
        } catch (IndexOutOfBoundsException | InvalidTaskDukeException e) {
            throw new InvalidTodoDukeException("Oops! Invalid \"todo\" command. Please stick to this format:\n"
                    + "  \"todo [description]\"");
        }
    }

    private Event makeEvent(String task) throws InvalidEventDukeException, InvalidDateDukeException {
        try {
            assert task.startsWith(EVENT_COMMAND_START) : "This is not an event command";
            int indexOfEvent = task.indexOf("event");
            int indexOfAt = task.indexOf("/at");
            String description = task.substring(indexOfEvent + 5, indexOfAt).strip();
            String at = task.substring(indexOfAt + 3).strip();
            return new Event(description, at);
        } catch (IndexOutOfBoundsException | InvalidTaskDukeException e) {
            throw new InvalidEventDukeException("Oops! Invalid \"event\" command. Please stick to this format:\n"
                    + "  \"event [description] /at [time]\"");
        }
    }

    private Deadline makeDeadline(String task) throws InvalidDeadlineDukeException, InvalidDateDukeException {
        try {
            assert task.startsWith(DEADLINE_COMMAND_START) : "This is not a deadline command";
            int indexOfDeadline = task.indexOf("deadline");
            int indexOfBy = task.indexOf("/by");
            String description = task.substring(indexOfDeadline + 8, indexOfBy).strip();
            String by = task.substring(indexOfBy + 3).strip();
            return new Deadline(description, by);
        } catch (IndexOutOfBoundsException | InvalidTaskDukeException e) {
            throw new InvalidDeadlineDukeException("Oops! Invalid \"deadline\" command. Please stick to this format:\n"
                    + "  \"deadline [description] /by [time]\"");
        }
    }

    /**
     * Checks if the command is an exit command.
     * @return False
     */
    public boolean checkExit() {
        return false;
    }

}
