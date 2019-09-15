package duke.command.factory;

import duke.command.Command;
import duke.command.command.AddCommand;
import duke.task.Task;
import duke.task.TasksController;
import duke.task.factory.TaskFactory;
import error.command.CommandCreationException;
import error.task.TaskCreationException;
import error.ui.UiException;
import ui.UiController;
import util.command.CommandUtils;

import java.util.Optional;
import java.util.Stack;

/**
 * Factory for command instances.
 */
public class CommandFactory {
    private TasksController tasksController;
    private UiController ui;
    private UndoCommandFactory undoCommandFactory;
    private TaskFactory taskFactory;
    private Stack<Command> history;

    private static final String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * ListenCommand constructor.
     * @param tasksController controller to execute task operations
     * @param ui ui interface for I/O
     */
    public CommandFactory(TasksController tasksController, UiController ui) {
        this.tasksController = tasksController;
        this.ui = ui;
        this.undoCommandFactory = new UndoCommandFactory(ui, tasksController);
        this.taskFactory = new TaskFactory();
        this.history = new Stack<>();
    }

    /**
     * Reads and parse user input.
     * @return corresponding commands.
     */
    public Optional<Command> parse(String input) throws UiException {
        try {
            // try to parse as undo command
            if (isUndoCommand(input)) {
                return undoCommandFactory.getUndoCommand(input, history);
            }

            // try to parse as other commands
            Optional<Command> command = CommandUtils.getCommand(input, ui, tasksController);
            if (command.isPresent()) {
                history.add(command.get());
                return command;
            }

            // try to parse command as a task
            Optional<Task> task = parseAsTask(input);
            if (task.isPresent()) {
                Command result = new AddCommand(task.get(), ui, tasksController);

                history.add(result);
                return Optional.of(result);
            }

            // not identified as a command or task
            ui.displayOutput(UNKNOWN_COMMAND_MESSAGE);
            return Optional.empty();

        } catch (CommandCreationException | TaskCreationException e) {

            ui.displayOutput(e.getMessage());
            return Optional.empty();
        }
    }

    private boolean isUndoCommand(String input) {
        return input.split(" ")[0].equals("undo");
    }


    private Optional<Task> parseAsTask(String input) throws TaskCreationException {
        return taskFactory.getTask(input);
    }
}
