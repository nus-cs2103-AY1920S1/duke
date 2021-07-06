package duke.ui;


import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.DukeUnknownCommandException;
import duke.command.LoadCommand;
import duke.command.Parser;
import duke.command.RelaxedSearchCommand;
import duke.command.SaveCommand;
import duke.command.SearchCommand;
import duke.io.Storage;

import duke.error.DukeException;

import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.tasklist.ToDo;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;

import java.util.ArrayList;

/**
 * The driver class that uses the various components of Duke to represent a Task managing assistant.
 */
public class Duke {

    public static String DEFAULT_SAVE_FILE_NAME = "DukeSave01";
    public ObservableStringValue observableStorageName;
    private Storage storage;
    private TaskList taskList;
    private boolean isActive;
    private SimpleStringProperty storageName;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        isActive = false;
        storageName = new SimpleStringProperty("");
        observableStorageName = new ObservableStringValue() {
            @Override
            public String get() {
                return storageName.get();
            }

            @Override
            public String getValue() {
                return storageName.getValue();
            }

            @Override
            public void addListener(ChangeListener<? super String> changeListener) {
                storageName.addListener(changeListener);
            }

            @Override
            public void addListener(InvalidationListener invalidationListener) {
                storageName.addListener(invalidationListener);
            }

            @Override
            public void removeListener(ChangeListener<? super String> changeListener) {
                storageName.removeListener(changeListener);
            }

            @Override
            public void removeListener(InvalidationListener invalidationListener) {
                storageName.removeListener(invalidationListener);
            }
        };
    }

    /**
     * Returns the Response from activating Duke.
     *
     * @return The Response from activating Duke
     */
    public Response greet() {
        isActive = true;
        return Response.fromString("Hi, I'm Duke! What can I do for you?", isActive);
    }


    /**
     * Returns the Response from Duke as a result of the given user input.
     *
     * @param input The user input given to Duke
     * @return the Response from Duke as a result of the given user input.
     */
    public Response getResponse(String input) {
        assert input != null;
        if (!isActive) {
            return Response.fromError(new DukeException("not accepting commands"), isActive);
        }
        try {
            return Response.fromString(executeCommand(Parser.parseAsCommand(input)), isActive);
        } catch (DukeException dukeException) {
            return Response.fromError(dukeException, isActive);
        }
    }

    /**
     * Returns true if duke is active, and false otherwise.
     *
     * @return true if duke is active, and false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Determines which Command handler to use, then executes the provided Command with that handler.
     *
     * @param command The Command to be executed
     * @throws DukeException when the Command cannot be properly executed for some reason
     */
    private String executeCommand(Command command) throws DukeException {
        assert command != null;
        // all commands passed to this method have all required parameter non-empty
        switch (command.getType()) {
        case COMMAND_ADD_TODO:
            //Fallthrough
        case COMMAND_ADD_EVENT:
            //Fallthrough
        case COMMAND_ADD_DEADLINE:
            return executeAddTaskCommand((AddTaskCommand) command);
        case COMMAND_COMPLETE_TASK:
            return executeCompleteTaskCommand((CompleteTaskCommand) command);
        case COMMAND_DELETE_TASK:
            return executeDeleteTaskCommand((DeleteTaskCommand) command);
        case COMMAND_LOAD_FILE:
            return executeLoadCommand((LoadCommand) command);
        case COMMAND_SAVE_FILE:
            return executeSaveCommand((SaveCommand) command);
        case COMMAND_SEARCH:
            return executeSearchCommand((SearchCommand) command);
        case COMMAND_RELAX_SEARCH:
            return executeSearchCommand((RelaxedSearchCommand) command);
        case COMMAND_SHOW_LIST:
            return executeShowListCommand();
        case COMMAND_EXIT:
            return executeExitCommand();
        default:
            throw new DukeUnknownCommandException();
        }
    }

    private String executeAddTaskCommand(AddTaskCommand command) throws DukeException {
        assert command != null;
        String[] parameters = command.getArgumentsUsed();
        Task task;

        switch (command.getType()) {
        case COMMAND_ADD_TODO:
            task = new ToDo(parameters[0]);
            break;
        case COMMAND_ADD_DEADLINE:
            try {
                task = new Deadline(parameters[0], Parser.parseDateTime(parameters[1]));
            } catch (DukeException dukeException) {
                task = new Deadline(parameters[0], parameters[1]);
            }
            break;
        case COMMAND_ADD_EVENT:
            try {
                task = new Event(parameters[0], Parser.parseDateTime(parameters[1]));
            } catch (DukeException dukeException) {
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
        assert command != null;

        String[] parameters = command.getArgumentsUsed();

        Task task = taskList.complete(parameters[0]);

        storage.save(taskList);
        return String.format("Got it! I've marked this task as done:\n%s", task.toString());
    }

    private String executeDeleteTaskCommand(DeleteTaskCommand command) throws DukeException {
        assert command != null;

        String[] parameters = command.getArgumentsUsed();

        Task task = taskList.delete(parameters[0]);

        storage.save(taskList);
        return String.format(
                "Got it! I've removed this task from the list:\n%s\nNow you have %d task(s) in your list.",
                task.toString(),
                taskList.size());
    }

    private String executeSearchCommand(SearchCommand command) {
        assert command != null;

        String[] parameters = command.getArgumentsUsed();
        ArrayList<Task> results = taskList.search(parameters[0]);
        int resultsCount = results.size();

        if (resultsCount > 0) {
            StringBuilder output = new StringBuilder();
            int width = Integer.toString(resultsCount).length();
            int count = 0;

            output.append("Here are the matching task(s) in your list:");

            for (Task task : results) {
                count++;
                output.append(String.format("\n%0" + width + "d. %s", count, task.toString()));
            }

            return output.toString();
        } else {
            return new StringBuilder("There are no matching tasks in your list!\n")
                    .append("Maybe you can check your query, or use relaxfind instead!")
                    .toString();
        }
    }

    private String executeSearchCommand(RelaxedSearchCommand command) {
        assert command != null;

        String[] parameters = command.getArgumentsUsed();
        ArrayList<Task> results = taskList.relaxedSearch(parameters[0]);
        int resultsCount = results.size();

        if (resultsCount > 0) {
            StringBuilder output = new StringBuilder();
            int width = Integer.toString(resultsCount).length();
            int count = 0;

            output.append("Here are the matching task(s) in your list:");

            for (Task task : results) {
                count++;
                output.append(String.format("\n%0" + width + "d. %s", count, task.toString()));
            }

            return output.toString();
        } else {
            return "There are no matching tasks in your list!";
        }
    }

    private String executeShowListCommand() {
        assert taskList != null;
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

    private String executeLoadCommand(LoadCommand loadCommand) throws DukeException {
        assert loadCommand != null;
        assert loadCommand.getArgumentsUsed()[0] != null;
        String saveFileName = loadCommand.getArgumentsUsed()[0];

        storage = new Storage(saveFileName);
        taskList = storage.loadTaskList();
        storageName.set(storage.getFileName());
        // task list successfully loaded
        return "Your TaskList was successfully loaded from: " + storage.getFileName();
    }

    private String executeSaveCommand(SaveCommand saveCommand) throws DukeException {
        assert saveCommand != null;
        assert saveCommand.getArgumentsUsed()[0] != null;
        String saveFileName = saveCommand.getArgumentsUsed()[0];

        Storage storage = new Storage(saveFileName);
        storage.save(taskList);

        // task list successfully loaded
        return "Your TaskList was successfully saved to: " + storage.getFileName();
    }
}

