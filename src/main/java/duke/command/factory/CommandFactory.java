package duke.command.factory;

import duke.command.*;
import duke.command.command.*;
import duke.task.Task;
import duke.task.TasksController;
import duke.task.factory.TaskFactory;
import error.command.CommandCreationException;
import error.task.TaskCreationException;
import error.ui.UiException;
import ui.UiController;
import util.CommandUtils;

import java.util.Arrays;
import java.util.Optional;

/***
 * <p>
 * Command to listen and parse user input.
 * </p>
 */
public class CommandFactory {
    private TasksController tasksController;
    private TaskFactory taskFactory;
    private UiController ui;

    private static final String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /***
     * <p>
     * ListenCommand constructor.
     * </p>
     * @param tasksController controller for duke.task list on which commands are executed.
     */
    public CommandFactory(TasksController tasksController, UiController ui) {
        this.tasksController = tasksController;
        this.taskFactory = new TaskFactory();
        this.ui = ui;
    }

    /***
     * <p>
     * Reads and parse user input.
     * </p>
     * @return corresponding commands.
     */
    public Optional<Command> parse(String input) throws UiException {
        try {

            // try to parse as a regular command
            Optional<Command> command = parseAsCommand(input);
            if (command.isPresent()) {
                return command;
            }

            // try to parse command as a task
            Optional<Task> task = parseAsTask(input);
            if (task.isPresent()) {
                Command result = new AddCommand(task.get());
                result.setUi(ui);
                result.setTasksController(tasksController);

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

    private Optional<Command> parseAsCommand(String input) throws CommandCreationException {
        String command = CommandUtils.getCommand(input);
        String arguments = CommandUtils.getArguments(input);

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
