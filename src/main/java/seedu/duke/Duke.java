package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.DukeException;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Main class of Duke application.
 */
public class Duke {
    private static final String DATA_PATH = "./duke_data.txt";

    private Storage storage;
    private Parser parser;

    /**
     * Constructor.
     */
    public Duke() {
        storage = new Storage(DATA_PATH);
        parser = new Parser(storage);
    }

    /**
     * Returns the response from the parser.
     * @param input The input from the user.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            if (command == null) {
                return null;
            }
            if (command.isExitCommand()) {
                exit();
            }
            return command.execute();
        } catch (DukeException e) {
            return e.toString();
        }
    }

    private void exit() {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                },
                250
        );
    }
}
