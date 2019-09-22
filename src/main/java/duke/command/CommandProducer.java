package duke.command;

import error.command.CommandCreationException;

/**
 * An abstract class to encapsulate the process of parsing user input as arguments and producing a corresponding
 * command. Each CommandProducer is responsible for parsing the arguments in a way that is required by the Command
 * class that it is trying to produce. Each CommandProducer MUST be registered as a dependency in CommandFactory
 * for it to be utilized by the program. The CommandFactory will compare the first word of each user input to
 * the list of CommandProducers to find the corresponding one to generate the corresponding command. Each
 * CommandProducer registered in the CommandFactory MUST have a unique keyword.
 */
public abstract class CommandProducer {
    private String keyword;

    protected CommandProducer(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the keyword used to map user inputs to this CommandProducer. The first word of each user input will
     * be treated as the keyword.
     * @return the keyword used to map the user input to this CommandProducer.
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Parses the arguments following the keyword according to the requirements of the Command it is trying to produce
     * and returns an instance of that command.
     * @param arguments the remainder of the user input after the first word.
     * @return a command instance to be executed by the program.
     */
    public abstract Command generateCommand(String arguments) throws CommandCreationException;
}
