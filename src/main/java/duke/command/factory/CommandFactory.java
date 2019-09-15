package duke.command.factory;

import duke.command.Command;
import duke.command.CommandProducer;
import duke.command.CommandType;
import duke.command.UndoAction;
import duke.command.command.AddCommand;
import duke.task.Task;
import duke.task.TasksController;
import duke.task.factory.TaskFactory;
import error.command.CommandCreationException;
import error.task.TaskCreationException;
import error.ui.UiException;
import ui.UiController;
import util.command.CommandUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;

/**
 * Factory for command instances.
 */
public class CommandFactory {
    private TasksController tasksController;
    private TaskFactory taskFactory;
    private UiController ui;
    private Stack<Command> history;

    private static final String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * ListenCommand constructor.
     * @param tasksController controller to execute task operations
     * @param ui ui interface for I/O
     */
    public CommandFactory(TasksController tasksController, UiController ui) {
        this.tasksController = tasksController;
        this.taskFactory = new TaskFactory();
        this.ui = ui;
        this.history = new Stack<>();
    }

    /**
     * Reads and parse user input.
     * @return corresponding commands.
     */
    public Optional<Command> parse(String input) throws UiException {
        try {
            // check to see if its an undo command
            if (CommandUtils.getCommand(input).equals("undo")) {
                return getUndoCommand(input);
            }

            // try to parse as a regular command
            Optional<Command> command = parseAsCommand(input);
            if (command.isPresent()) {
                history.add(command.get());
                return command;
            }

            // try to parse command as a task
            Optional<Task> task = parseAsTask(input);
            if (task.isPresent()) {
                Command result = new AddCommand(task.get());
                result.setUi(ui);
                result.setTasksController(tasksController);

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

    private Optional<Command> getUndoCommand(String input) throws UiException, CommandCreationException {
        if (!CommandUtils.getArguments(input).equals("")) {
            ui.displayOutput("☹ OOPS!!! I'm sorry, undo doesn't accept arguments :-(");
            return Optional.empty();
        }

        UndoAction nextUndo = null;

        while (!history.isEmpty()) {
            Command prevCommand = history.pop();
            if (prevCommand.getUndoAction().isPresent()) {
                nextUndo = prevCommand.getUndoAction().get();
                break;
            }
        }

        if (nextUndo == null) {
            ui.displayOutput("☹ OOPS!!! There is no previous action to undo! :-(");
            return Optional.empty();
        }

        final UndoAction finalUndo = nextUndo;

        Command undoCommand = new Command(CommandType.UNDO) {
            private UndoAction undo = finalUndo;
            @Override
            public void execute() throws UiException {
                undo.undo();
            }

            @Override
            public Optional<UndoAction> getUndoAction() {
                return Optional.empty();
            }
        };

        undoCommand.setTasksController(tasksController);
        undoCommand.setUi(ui);
        return Optional.of(undoCommand);
    }

    private Optional<Command> parseAsCommand(String input) throws CommandCreationException {
        String command = CommandUtils.getCommand(input);
        String arguments = CommandUtils.getArguments(input).getArguments();

        Optional<CommandProducer> producerOptional = Arrays.stream(CommandType.values())
                .filter(type -> type.keyword.equals(command))
                .findFirst()
                .map(type -> type.producer);

        if (producerOptional.isEmpty()) {
            return Optional.empty();
        }

        Command result = producerOptional.get().getCommand(arguments);
        result.setUi(ui);
        result.setTasksController(tasksController);
        return Optional.of(result);
    }

    private Optional<Task> parseAsTask(String input) throws TaskCreationException {
        return taskFactory.getTask(input);
    }
}
