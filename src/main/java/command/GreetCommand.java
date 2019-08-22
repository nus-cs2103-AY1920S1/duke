package command;

import java.util.Optional;

import util.DukeOutput;

public class GreetCommand implements Command {
    private static final String DUKE_LOGO =
            " ____        _        \n" +
                    "|  _ \\ _   _| | _____ \n" +
                    "| | | | | | | |/ / _ \\\n" +
                    "| |_| | |_| |   <  __/\n" +
                    "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING =
            DUKE_LOGO + "\n" + "\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n";

    @Override
    public Optional<Command> execute() {
        DukeOutput.printMessage(GREETING);
        return Optional.empty();
    }
}
