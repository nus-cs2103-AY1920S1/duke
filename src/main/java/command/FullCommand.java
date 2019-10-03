package command;

import exception.DukeException;

/**
 * Enum detailing all the valid commands Duke understands.
 * Used by Parser to generate Command Object.
 * Easy way to check if user types invalid command.
 */
public enum FullCommand {
    LIST("list"), DONE("done"), DELETE("delete"), TODO("todo"), DEADLINE("deadline"),
        EVENT("event"), BYE("bye"), FIND("find"), RECUR("recur"), CLEAR("clear"),
        REVERT("revert");

    private String name;

    private FullCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Generates FullCommand based on String user input.
     * @param keyword Specifies the type of FullCommand).
     * @return return FullCommand to be parsed by parser.
     * @throws DukeException Thrown when user enters invalid command.
     */
    public static FullCommand getByName(String keyword) throws DukeException {
        for (FullCommand command : values()) {
            if (command.getName().equals(keyword)) {
                return command;
            }
        }

        throw new DukeException("no such command");
    }
}

