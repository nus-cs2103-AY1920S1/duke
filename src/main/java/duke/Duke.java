package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Represents a personal assistant chat bot. A <code>Duke</code> object corresponds to a specific <code>Storage</code>,
 * <code>TaskList</code> and <code>Ui</code>.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
    }

    /**
     * Constructor for <code>Duke</code>.
     * @param filePath Path to file that should be written to and loaded from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException exception) {
            ui.printLoadingErrorMessage(exception);
            tasks = new TaskList();
        }
    }

    /**
     * Returns the welcome message by Duke.
     * @return Duke's welcome message.
     */
    public String getWelcome() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream old = System.out;
        changeSystemOut(outputStream);
        ui.printWelcome();
        resetSystemOut(old);
        return outputStream.toString();
    }

    /**
     * Returns the response corresponding to the input by the user.
     * @param input Input command provided by the user.
     * @return The response corresponding to the given command.
     */
    public String getResponse(String input) {
        PrintStream old = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        changeSystemOut(outputStream);
        generateResponse(input);
        resetSystemOut(old);
        return outputStream.toString();
    }

    private void generateResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException exception) {
            ui.printExceptionMessage(exception);
        }
    }

    private void changeSystemOut(ByteArrayOutputStream outputStream) {
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
    }

    private void resetSystemOut(PrintStream old) {
        System.out.flush();
        System.setOut(old);
    }
}
