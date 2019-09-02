package duke.ui;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.SearchCommand;
import duke.command.DukeUnknownCommandException;
import duke.command.Parser;

import duke.io.Storage;

import duke.error.DukeException;

import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.tasklist.ToDo;
import duke.tasklist.Deadline;
import duke.tasklist.Event;

import java.util.ArrayList;

/**
 * The driver class that uses the various components of Duke to represent a task managing assistant (Duke).
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private boolean isActive;

    /**
     * Constructs a new Duke instance with the working TaskList whose data is saved at the path given
     *
     * @param taskListPath path of TaskList save file
     */
    public Duke(String taskListPath) {
        storage = new Storage(taskListPath);
        try {
            taskList = storage.loadTaskList();
        } catch (DukeException e0) {
            taskList = new TaskList();
            try {
                storage.save(taskList);
            } catch (DukeException e1) {
                e1.printStackTrace();
            }
        }
        isActive = true;
    }

    /**
     * Returns the Response from Duke as a result of the given user input.
     *
     * @param input The user input given to Duke
     * @return the Response from Duke as a result of the given user input.
     */
    public Response getResponse(String input) {
        if (!isActive) {
            return Response.fromError(new DukeException("not accepting commands"), isActive);
        }

        try {
            return Response.fromString(executeCommand(Parser.parseAsCommand(input)), isActive);
        } catch (DukeException e) {
            return Response.fromError(e, isActive);
        }
    }

    /**
     * Returns true if duke is active, and false otherwise
     *
     * @return true if duke is active, and false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Determine which command handler to use, executes command with that handler, returns response/throws exception
     */
    private String executeCommand(Command command) throws DukeException {
        // all commands passed to this method have all required parameter non-empty
        switch (command.getClass().getSimpleName()) {
        case "AddTaskCommand":
            return executeAddTaskCommand((AddTaskCommand) command);
        case "CompleteTaskCommand":
            return executeCompleteTaskCommand((CompleteTaskCommand) command);
        case "DeleteTaskCommand":
            return executeDeleteTaskCommand((DeleteTaskCommand) command);
        case "SearchCommand":
            return executeSearchCommand((SearchCommand) command);
        case "ShowListCommand":
            return executeShowListCommand();
        case "ExitCommand":
            return executeExitCommand();
        default:
            throw new DukeUnknownCommandException();
        }
    }

    private String executeAddTaskCommand(AddTaskCommand command) throws DukeException {
        String[] parameters = Command.getArgumentsUsed(command);
        Task task;

        switch (Command.getTypeOf(command)) {
        case COMMAND_ADD_TODO:
            task = new ToDo(parameters[0]);
            break;
        case COMMAND_ADD_DEADLINE:
            try {
                task = new Deadline(parameters[0], Parser.parseDateTime(parameters[1]));
            } catch (DukeException ex) {
                task = new Deadline(parameters[0], parameters[1]);
            }
            break;
        case COMMAND_ADD_EVENT:
            try {
                task = new Event(parameters[0], Parser.parseDateTime(parameters[1]));
            } catch (DukeException e) {
                task = new Event(parameters[0], parameters[1]);
            }
            break;
        default:
            throw new DukeException("This task type is not supported yet");
        }

        taskList.add(task);

        storage.save(taskList);

        return String.format(
                "Got it! I've added this task to the list:\n%s\nNow you have %d task(s) in your list.",
                task.toString(),
                taskList.size());
    }


    private String executeCompleteTaskCommand(CompleteTaskCommand command) throws DukeException {
        String[] parameters = Command.getArgumentsUsed(command);

        Task task = taskList.complete(parameters[0]);

        storage.save(taskList);
        return String.format("Got it! I've marked this task as done:\n%s", task.toString());
    }

    private String executeDeleteTaskCommand(DeleteTaskCommand command) throws DukeException {
        String[] parameters = Command.getArgumentsUsed(command);

        Task task = taskList.delete(parameters[0]);

        storage.save(taskList);
        return String.format(
                "Got it! I've removed this task from the list:\n%s\nNow you have %d task(s) in your list.",
                task.toString(),
                taskList.size());
    }

    private String executeSearchCommand(SearchCommand command) {
        String[] parameters = Command.getArgumentsUsed(command);
        ArrayList<Task> results = taskList.search(parameters[0]);
        int resultsCount = results.size();

        if (resultsCount > 0) {
            StringBuilder output = new StringBuilder();
            int width = Integer.toString(resultsCount).length();
            int count = 0;

            output.append("Here are the matching task(s) in your list:");

            for (Task task : taskList.search(parameters[0])) {
                count++;
                output.append(String.format("\n%0" + width + "d. %s", count, task.toString()));
            }

            return output.toString();
        } else {
            return "There are no matching tasks in your list!";
        }
    }

    private String executeShowListCommand() {
        int taskCount = taskList.size();

        if (taskCount < 1) {
            return "Your list is empty!";
        } else {
            StringBuilder output = new StringBuilder();
            int count = 0;
            int width = Integer.toString(taskCount).length();

            output.append("Here are the task(s) in your list:");

            for (Task task : taskList.list()) {
                count++;
                output.append(String.format("\n%0" + width + "d. %s", count, task.toString()));
            }

            return output.toString();
        }
    }

    private String executeExitCommand() {
        isActive = false;
        return "GoodBye! Hope to see you again!";
    }
}

