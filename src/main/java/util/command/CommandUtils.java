package util.command;

import duke.command.Command;
import duke.command.CommandProducer;
import duke.command.CommandType;
import duke.task.TasksController;
import error.command.CommandCreationException;
import ui.UiController;

import java.util.Arrays;
import java.util.Optional;

/**
 * Simple utility class to split user input into commands and arguments.
 */
public class CommandUtils {
    /**
     * Gets command from user input.
     * @param userInput user input
     * @param ui ui needed to instantiate command
     * @param tasksController task needed to instantiate command
     * @return command
     */
    public static Optional<Command> getCommand(String userInput, UiController ui, TasksController tasksController) throws CommandCreationException {
        String keyword =  userInput.split(" ", 2)[0];
        String arguments = getArgumentsAsString(userInput);

        Optional<CommandProducer> producer = Arrays.stream(CommandType.values())
                .filter(type -> type.keyword.equals(keyword))
                .findFirst()
                .map(commandType -> commandType.producer);

        if (producer.isPresent()) {
            Command command = producer.get().getCommand(arguments, ui, tasksController);
            return Optional.of(command);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Gets arguments from user input for tasks in the form of an easily operatable class.
     * @param userInput user input
     * @return arguments
     */
    public static TaskArguments getTaskArguments(String userInput) {
        String[] splitInput = userInput.split(" ", 2);
        if (splitInput.length > 1) {
            return new TaskArguments(splitInput[1]);
        } else {
            return new TaskArguments("");
        }
    }

    /**
     * Gets arguments from user input for tasks in String form
     * @param userInput user input
     * @return arguments
     */
    public static String getArgumentsAsString(String userInput) {
        String[] splitInput = userInput.split(" ", 2);
        if (splitInput.length > 1) {
            return splitInput[1];
        } else {
            return "";
        }
    }
}
