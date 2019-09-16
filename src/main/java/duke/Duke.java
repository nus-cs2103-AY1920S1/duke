package duke;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Represents a personal assistant chatbot that keeps track of tasks.
 * An <code>Duke</code> object corresponds to a new chatbot with its own <code>Storage</code> and <code>TaskList</code>.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for <code>Duke</code>.
     * @param filePath File path of file to store the information in the task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.drawLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printException(e);
            } finally {
                ui.drawLine();
            }
        }
    }

    /**
     * Generates the response by Duke in response to the user's input.
     * @param input Input by user.
     * @return Duke's response.
     */
    public String getResponse(String input) throws UnsupportedEncodingException {
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(message, true, "UTF-8");
        final PrintStream old = System.out;
        System.setOut(ps);
        generateResponse(input);
        System.out.flush();
        System.setOut(old);
        return message.toString();
    }

    private void generateResponse(String input) {
        try {
            ui.drawLine();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.printException(e);
        } finally {
            ui.drawLine();
        }
    }

    /**
     * Generates Duke's starting greeting.
     * @return Duke's greeting.
     */
    public String generateWelcomeMessage() {
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(message);
        final PrintStream old = System.out;
        System.setOut(ps);
        ui.greetUser();
        System.out.flush();
        System.setOut(old);
        return message.toString();
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\Yi Yin\\Documents\\Year 2\\Semester 1\\CS2103\\duke\\data\\duke.txt").run();
    }
}