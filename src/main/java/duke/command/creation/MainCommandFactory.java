package duke.command.creation;

import duke.command.Command;
import duke.command.CommandProducer;
import error.command.CommandCreationException;
import error.command.CommandProducerRegisterException;
import util.strings.CommandSplitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Factory to generate commands to be executed by the program from user inputs. To enable a command to be generated,
 * its corresponding CommandProducer MUST be registered in the factory using the
 * registerCommandProducer(CommandProducer producer) method. The factory maps the first word of the user's input
 * to the keyword of the CommandProducer and invokes it to generate the corresponding Command instance.
 */
public class MainCommandFactory implements CommandFactory {
    private static final String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    private List<CommandFactory> commandFactories;
    private HashMap<String, CommandProducer> commandProducerHashMap;

    public MainCommandFactory() {
        this.commandFactories = new ArrayList<>();
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
            throw new CommandProducerRegisterException("Cannot register CommandProducer as its "
                    + "keyword already exists.");
        }

        this.commandProducerHashMap.put(keyword, producer);
    }

    /**
     * Registers other CommandFactory instances that would be used to create commands before checking against the
     * internal CommandProducer hashmap. This allows other commands with more complicated instantiation logic to be
     * produced in another factory but still be created by the program.
     * @param factory the CommandFactory instance to be added.
     */
    public void registerCommandFactory(CommandFactory factory) {
        this.commandFactories.add(factory);
    }

    /**
     * Parses a user's input and produces a corresponding Command instance to be executed. A user input's first word
     * would have to correspond to they keyword of a command.
     * @param input the user input to be parsed.
     * @return the command instance to be executed by the program.
     * @throws CommandCreationException if user input is an invalid command.
     */
    public Optional<Command> getCommandFromUserInput(String input) throws CommandCreationException {
        Optional<Command> command = this.getCommandFromFactories(input);

        if (command.isPresent()) {
            return command;
        }

        return this.getCommandFromCommandProducers(input);
    }

    private Optional<Command> getCommandFromFactories(String input) throws CommandCreationException {
        for (CommandFactory factory : this.commandFactories) {
            Optional<Command> command = factory.getCommandFromUserInput(input);

            if (command.isPresent()) {
                return command;
            }
        }

        return Optional.empty();
    }

    private Optional<Command> getCommandFromCommandProducers(String input) throws CommandCreationException {
        String commandKeyword = CommandSplitter.getCommand(input);
        String arguments = CommandSplitter.getArguments(input);


        CommandProducer matchingProducer = this.commandProducerHashMap.get(commandKeyword);

        if (matchingProducer == null) {
            return Optional.empty();
        }

        return Optional.of(matchingProducer.generateCommand(arguments));
    }
}
