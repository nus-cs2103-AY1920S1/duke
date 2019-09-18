package task;

import exceptions.DukeException;
import utilities.ExpenseList;
import utilities.Storage;
import utilities.TaskList;

/**
 * contains actions for the todo task.
 */
public class ToDo extends Task {
    /**
     * constructor for the child class.
     *
     * @param command is the user input
     */
    public ToDo(String command) {
        super(command);
        this.done = false;
    }

    /**
     * to print for "list" command.
     *
     * @return string in the format required
     */
    public String printer() {
        if (done) {
            return "[T][:)] " + command;
        } else {
            return "[T][:(] " + command;
        }
    }

    /**
     * to print for text file.
     *
     * @return string in the format required
     */
    public String printToOutput() {
        if (done) {
            return "T | 1 | " + command;
        } else {
            return "T | 0 | " + command;
        }
    }

    /**
     * read user input as convert it into a Task.
     *
     * @param command is the user input string
     *
     * @return a Task.Task (Task.ToDo) object
     */
    public static Task outputAsToDo(String command) {
        String[]segments = command.split("\\|");
        ToDo newTask = new ToDo(segments[2].trim());

        if (segments[1].equals(" 1 ")) {
            newTask.taskDone();
        }

        return newTask;
    }

    /**
     * Creates a new Task.ToDo task.
     *
     * @param command is the user string input to be processed
     *
     * @throws DukeException in case user inputs in an incorrect format
     */
    public static void createTodo(String command, TaskList tasks, Storage storage, ExpenseList expenses) throws DukeException {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        try {
            if (midCommand.length() != 0) {
                tasks.add(new ToDo(midCommand));
                storage.updateFile(tasks, expenses);
            } else {
                throw new DukeException("");
            }
        } catch (Exception e) {
            throw new DukeException("");
        }
    }

    /**
     * marks task as done.
     */
    public void taskDone() {
        done = true;
    }
}
