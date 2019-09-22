package duke.command.factory;

import duke.command.Command;
import duke.command.CommandProducer;
import duke.command.command.AddCommand;
import duke.task.Task;
import duke.task.TasksController;
import duke.task.factory.TaskFactory;
import error.command.CommandCreationException;
import error.command.CommandProducerRegisterException;
import error.task.TaskCreationException;
import error.ui.UiException;
import ui.UiController;
import util.command.CommandUtils;

import java.util.HashMap;
import java.util.Optional;
import java.util.Stack;

/**
 * Factory to generate commands to be executed by the program from user inputs. To enable a command to be generated,
 * its corresponding CommandProducer MUST be registered in the factory using the
 * registerCommandProducer(CommandProducer producer) method. The factory maps the first word of the user's input
 * to the keyword of the CommandProducer and invokes it to generate the corresponding Command instance.
 */
public class CommandFactory {
    private static final String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    private HashMap<String, CommandProducer> commandProducerHashMap;


    public CommandFactory() {
        this.commandProducerHashMap = new HashMap<>();
    }

    /**
     * Registers a CommandProducer instance with the factory. If user input's first word matches the CommandProducer,
     * the CommandProducer will be invoked to create the next Command instance to be executed by the program. Each
     * registerd CommandProducer MUST have a unique keyword.
     * @param producer the CommandProducer instance to be registered.
     */
    public void registerCommandProducer(CommandProducer producer) throws CommandProducerRegisterException {
        String keyword = producer.getKeyword();

        if (this.commandProducerHashMap.containsKey(keyword)) {
            throw new CommandProducerRegisterException("Cannot register CommandProducer as its keyword already exists.");
        }

        this.commandProducerHashMap.put(keyword, producer);
    }

    /**
     * Parses a user's input and produces a corresponding Command instance to be executed. A user input's first word
     * would have to correspond to they keyword of a command.
     * @param input the user input to be parsed.
     * @return the command instance to be executed by the program.
     * @throws CommandCreationException if user input is an invalid command.
     */
    public Command getCommandFromUserInput(String input) throws CommandCreationException {
        String[] inputArray = input.split(" ", 2);
        String commandKeyword = inputArray[0];
        String arguments = inputArray[1];

        CommandProducer matchingProducer = this.commandProducerHashMap.get(commandKeyword);

        if (matchingProducer == null) {
            throw new CommandCreationException(UNKNOWN_COMMAND_MESSAGE);
        }

        return matchingProducer.generateCommand(arguments);
    }
}
