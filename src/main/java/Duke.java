import duke.command.Command;
import duke.command.CommandCentre;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

import java.util.Scanner;

/**
 * Duke is a chat based task manager that keeps track of the Tasks keyed in by the user.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private CommandCentre commandCentre;
    private boolean isExit;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        ui = new Ui(new Scanner(System.in));
        storage = new Storage();
        isExit = false;
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        } finally {
            commandCentre = new CommandCentre(tasks, ui);
            try {
                storage.loadAlias();
            } catch (DukeException e) {
                ui.showError(e.toString());
            }
            assert ui == null : "Ui should not be null";
            assert storage == null : "Storage should not be null";
            assert tasks == null : "TaskList should not be null";
        }
    }

    /**
     * Starts Duke.
     */
    public void run() {
        System.out.println(ui.showWelcome());

        while (!isExit) {
            String input = ui.readMessage();
            try {
                Command command = CommandCentre.getCommand(Parser.parseCommand(input));
                ui.printMessage(command.execute(Parser.parseDescription(input)));
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printMessage(ui.showError(e.getMessage()));
            }
        }
        ui.closeScanner();
        storage.writeData(tasks);
        storage.writeAlias();
    }

    /**
     * Get's Duke's response after executing the User's command.
     *
     * @param input the User's input.
     * @return a String representation of Duke's response.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            storage.writeData(tasks);
            storage.writeAlias();
            Platform.exit();
            return null;
        } else {
            try {
                Command command = CommandCentre.getCommand(Parser.parseCommand(input));
                return command.execute(Parser.parseDescription(input));
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
