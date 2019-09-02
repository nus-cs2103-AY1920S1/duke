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
     * Runs the chat bot.
     */
    public void run() {
        ui.printLogo();
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException exception) {
                ui.printExceptionMessage(exception);
            } finally {
                ui.printLine();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\Yi Wai\\Documents\\Year 2 Semester 1\\CS2103\\duke\\data\\duke.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        PrintStream old = System.out;
        System.setOut(ps);
        try {
            ui.printLine();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException exception) {
            ui.printExceptionMessage(exception);
        } finally {
            ui.printLine();
        }
        System.out.flush();
        System.setOut(old);
        return outputStream.toString();
    }
}
